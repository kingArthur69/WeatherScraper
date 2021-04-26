package md.buiucani.repositories;

import md.buiucani.entities.WeatherProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherProviderRepository extends JpaRepository<WeatherProvider, Integer> {

    WeatherProvider findByProviderName(String providerName);
}
