package md.buiucani;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@PropertySource("classpath:weatherScraper.properties")
public class AppPropertiesConfig {

    @Value("${webDriver:chrome}")
    private String webDriver;

    @Value("${winPathToChromeDriver}")
    private String winPathToChromeDriver;

    @Value("${unixPathToChromeDriver}")
    private String unixPathToChromeDriver;

    public String getPathToChromeDriver() {
        return winPathToChromeDriver;
    }

    public String getWebDriver() {
        return webDriver;
    }
}
