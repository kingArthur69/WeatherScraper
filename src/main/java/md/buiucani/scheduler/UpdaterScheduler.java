package md.buiucani.scheduler;

import md.buiucani.entities.SearchEntity;
import md.buiucani.jsoup.JsoupWorker;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class UpdaterScheduler {
    private JsoupWorker worker;

    @Scheduled(fixedRate = 1000 * 10 * 1)
    public void updateValues() throws IOException {
        System.out.println("Scheduler up");
        List<SearchEntity> searchLinks = worker.getGoogleSearchResults();
        worker.updateGisMeteoInfo(searchLinks);
    }
}
