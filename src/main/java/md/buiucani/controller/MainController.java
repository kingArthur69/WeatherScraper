package md.buiucani.controller;

import md.buiucani.entities.SearchEntity;
import md.buiucani.entities.WeatherEntry;
import md.buiucani.entities.WeatherProvider;
import md.buiucani.repositories.WeatherEntryRepository;
import md.buiucani.repositories.WeatherProviderRepository;
import md.buiucani.util.Enum;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    private WeatherProviderRepository weatherProviderRepository;

    @Autowired
    private WeatherEntryRepository weatherEntryRepository;

    @GetMapping(path = {"/", "/index"})
    public String indexPage(Model model) throws Exception {

//        Document document = Jsoup
//                .connect("https://www.google.com/search?q=vremea+chisinau")
//                .userAgent("Mozilla/5.0 (Macintosh; U; Intel Mac OS X; de-de) AppleWebKit/523.10.3 (KHTML, like Gecko) Version/3.0.4 Safari/523.10")
//                .referrer("http://www.google.com")
//                .timeout(5000)
//                .get();
//
//        System.out.println(document.body());

        Document document = Jsoup.connect("https://www.google.com/search?q=vremea+chisinau+azi").get();
        Elements links = document.select("a[href]");

        List<SearchEntity> rawLinks = new ArrayList<>();

        for (Element link : links) {
            if(link.attr("href").matches(Enum.Regex.URL_1)){
                SearchEntity newSearchEntuty = new SearchEntity(link.attr("href"), link.text());
                rawLinks.add(newSearchEntuty);

//                System.out.println("\nlink : " + link.attr("href"));
//                System.out.println("text : " + link.text());
            }
        }

        rawLinks.removeIf(x -> !x.getLink().contains(Enum.WeatherSites.WEATHER)
                && !x.getLink().contains(Enum.WeatherSites.ACCUWEATHER)
                && !x.getLink().contains(Enum.WeatherSites.GISMETEO));
//        System.out.println("SIZE: " + rawLinks.size());

        getGismeteoInfo(resolveGismeteoLink(rawLinks));

//        for (SearchEntity entity : rawLinks){
//            System.out.println("\nlink-------------- : " + entity.getLink());
//            System.out.println("text-------------- : " + entity.getText());
//        }
        return "index";
    }

    private SearchEntity resolveGismeteoLink(List<SearchEntity> defaultList){
        List<SearchEntity> gismeteoSearchEntities = defaultList.stream().filter(x -> x.getLink()
                .contains(Enum.WeatherSites.GISMETEO))
                .collect(Collectors.toList());

        if (!gismeteoSearchEntities.isEmpty()){
            return gismeteoSearchEntities.stream()
                    .min(Comparator
                            .comparing(SearchEntity::getLinkLength)).get();
        }else {
            return null;
        }
    }

    private void getGismeteoInfo(SearchEntity searchEntity) throws IOException {
        Document gisMeteoDoc = Jsoup.connect(searchEntity.getLink()).get();
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
