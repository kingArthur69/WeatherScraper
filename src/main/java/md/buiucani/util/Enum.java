package md.buiucani.util;

public class Enum {
    public class UserAgent{
        public static final String MAC_OS = "Mozilla/5.0 (Macintosh; U; Intel Mac OS X; de-de) AppleWebKit/523.10.3 (KHTML, like Gecko) Version/3.0.4 Safari/523.10";
        public static final String WINDOWS_10 = "ADMIN";
        public static final String UBUNTU = "ADMIN";
    }

    public class WeatherSites{
        public static final String GISMETEO = "www.gismeteo.md";
        public static final String GISMETEO_NAME = "GisMeteo";
        public static final String ACCUWEATHER = "www.accuweather.com";
        public static final String WEATHER = "weather.com";

    }

    public class SearchHref{
        public static final String GOOGLE_SEARCH = "https://www.google.com/search?q=vremea+chisinau+azi";
    }

    public class GisMeteoSelector{
        public static final String DAY_TEMPERATURE = "body div.content_wrap div.flexbox div.main div.column-wrap div.__frame_sm div.forecast_frame div.tabs._center div.tab.tooltip div.tab_wrap div.tab-content div.tabtempline div.tabtempline_inner div.chart div.values div.value[style*=\"top: 0px\"] span.unit.unit_temperature_c";
    }

    public class Regex{
        public static final String URL_1 = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        public static final String URL_2 = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    }
}
