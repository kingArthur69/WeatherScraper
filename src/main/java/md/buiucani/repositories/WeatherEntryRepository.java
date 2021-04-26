package md.buiucani.repositories;

import md.buiucani.entities.WeatherEntry;
import md.buiucani.entities.WeatherProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherEntryRepository extends JpaRepository<WeatherEntry, Integer> {
}
