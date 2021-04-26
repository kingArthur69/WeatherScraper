package md.buiucani.jsoup;

import md.buiucani.entities.SearchEntity;
import md.buiucani.entities.WeatherEntry;
import md.buiucani.entities.WeatherProvider;
import md.buiucani.repositories.WeatherEntryRepository;
import md.buiucani.repositories.WeatherProviderRepository;
import md.buiucani.util.Enum;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JsoupWorker {

    @Autowired
    private WeatherProviderRepository weatherProviderRepository;

    @Autowired
    private WeatherEntryRepository weatherEntryRepository;

    private String googleSearchHref = Enum.SearchHref.GOOGLE_SEARCH;

    public JsoupWorker() {
    }

    public List<SearchEntity> getGoogleSearchResults() throws IOException {
        Document document = Jsoup.connect(Enum.SearchHref.GOOGLE_SEARCH).get();
        Elements links = document.select("a[href]");

        List<SearchEntity> rawLinks = new ArrayList<>();

        for (Element link : links) {
            if (link.attr("href").matches(Enum.Regex.URL_1)) {
                SearchEntity newSearchEntuty = new SearchEntity(link.attr("href"), link.text());
                rawLinks.add(newSearchEntuty);
            }
        }

        rawLinks.removeIf(x -> !x.getLink().contains(Enum.WeatherSites.WEATHER)
                && !x.getLink().contains(Enum.WeatherSites.ACCUWEATHER)
                && !x.getLink().contains(Enum.WeatherSites.GISMETEO));

            return rawLinks;
    }

    public void updateGisMeteoInfo(List<SearchEntity> defaultList) throws IOException {
        List<SearchEntity> gismeteoSearchEntities = defaultList.stream().filter(x -> x.getLink()
                .contains(Enum.WeatherSites.GISMETEO))
                .collect(Collectors.toList());

        SearchEntity gisMeteoEntity = gismeteoSearchEntities.stream()
                .min(Comparator
                        .comparing(SearchEntity::getLinkLength)).get();

        Document gisMeteoDoc = Jsoup.connect(gisMeteoEntity.getLink()).get();
        String temperature = gisMeteoDoc.body().select(Enum.GisMeteoSelector.DAY_TEMPERATURE).html();

        WeatherEntry newWeatherEntry = new WeatherEntry();
        newWeatherEntry.setTime(LocalDateTime.now());
        newWeatherEntry.setDayTemperature(temperature);

        WeatherProvider gismeteoProvider = weatherProviderRepository.findByProviderName(Enum.WeatherSites.GISMETEO_NAME);
        newWeatherEntry.setWeatherProvider(gismeteoProvider);
        WeatherEntry savedWeatherEntry = weatherEntryRepository.save(newWeatherEntry);
        gismeteoProvider.getWeatherEntries().add(savedWeatherEntry);

        weatherProviderRepository.save(gismeteoProvider);
    }
}
