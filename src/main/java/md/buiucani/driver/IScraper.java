package md.buiucani.driver;

import java.util.List;

public interface IScraper {

    void init();

    void close();

    void get(String url);

    String getPageSource();

    void submitText(String selector, String text);

    void wait(String selector, Long ms);

    String getTextFromPath();

    List<String> getAllTextFromAttributesPath(String selector, String attribute);

    List<String> getAllTextFromPath(String selector);
}
