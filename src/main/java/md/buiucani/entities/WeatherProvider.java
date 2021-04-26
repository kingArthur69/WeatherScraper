package md.buiucani.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="weather_provider")
public class WeatherProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    protected Integer id;

    @Column(name="provider_name")
    protected String providerName;

    @OneToMany(mappedBy = "weatherProvider", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    protected List<WeatherEntry> weatherEntries;

    public WeatherProvider() {
    }

    public WeatherProvider(Integer id, String providerName) {
        this.id = id;
        this.providerName = providerName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public List<WeatherEntry> getWeatherEntries() {
        return weatherEntries;
    }

    public void setWeatherEntries(List<WeatherEntry> weatherEntries) {
        this.weatherEntries = weatherEntries;
    }
}
