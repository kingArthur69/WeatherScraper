package md.buiucani.crawler;

import md.buiucani.driver.IScraper;
import md.buiucani.entities.Page;
import md.buiucani.parser.IParser;

public class WebCrawler implements ICrawler {

    private IScraper scraper;

    private IParser parser;

    @Override
    public Page crawlPage() {
        return null;
    }
}
