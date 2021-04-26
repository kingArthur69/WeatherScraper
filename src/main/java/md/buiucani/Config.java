package md.buiucani;

import md.buiucani.entities.SearchEntity;
import md.buiucani.jsoup.JsoupWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.util.List;

@Configuration
@EnableScheduling
public class Config implements WebMvcConfigurer {

	@Autowired
	private JsoupWorker jsoupWorker;

	@Scheduled(fixedRate = 5000, initialDelay = 5000)
	private void mailSchedule() throws IOException {
		System.out.println("scheduler");
		List<SearchEntity> searchEntities = jsoupWorker.getGoogleSearchResults();
		jsoupWorker.updateGisMeteoInfo(searchEntities);
	}
}
