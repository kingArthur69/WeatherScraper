package md.buiucani.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="weather_entry")
public class WeatherEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    protected Integer id;

    @Column(name="time")
    protected LocalDateTime time;

    @Column(name="day_temperature")
    protected String dayTemperature;

    @ManyToOne
    @JoinColumn(name = "weather_provider_id", nullable = false)
    protected WeatherProvider weatherProvider;

    public WeatherEntry() {
    }

    public WeatherEntry(Integer id, LocalDateTime time, String dayTemperature) {
        this.id = id;
        this.time = time;
        this.dayTemperature = dayTemperature;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getDayTemperature() {
        return dayTemperature;
    }

    public void setDayTemperature(String dayTemperature) {
        this.dayTemperature = dayTemperature;
    }

    public WeatherProvider getWeatherProvider() {
        return weatherProvider;
    }

    public void setWeatherProvider(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }
}
