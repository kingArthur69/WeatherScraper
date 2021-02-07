package md.buiucani.driver;

import md.buiucani.AppPropertiesConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class WebScraper implements IScraper {

    private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
    private AppPropertiesConfig appPropertiesConfig;
    private WebDriver driver;

    public WebScraper(AppPropertiesConfig appPropertiesConfig) {
        this.appPropertiesConfig = appPropertiesConfig;
    }

    @Override
    public void init() {
        driver = createWebDriver();
    }

    @Override
    public void close() {
        driver.close();
        driver.quit();
    }

    @Override
    public void get(String url) {
        driver.get(url);
    }

    @Override
    public String getPageSource() {
        return null;
    }

    @Override
    public void submitText(String selector, String text) {

    }

    @Override
    public void wait(String selector, Long ms) {

    }

    @Override
    public String getTextFromPath() {
        return null;
    }

    @Override
    public List<String> getAllTextFromAttributesPath(String selector, String attribute) {
        return null;
    }

    @Override
    public List<String> getAllTextFromPath(String selector) {
        return null;
    }

    private WebDriver createWebDriver() {
        String driver = appPropertiesConfig.getWebDriver();
        if ("chrome".equals(driver)) {
            System.setProperty(WEBDRIVER_CHROME_DRIVER, appPropertiesConfig.getPathToChromeDriver());
            return new ChromeDriver();
        }
        return null;
    }
}
