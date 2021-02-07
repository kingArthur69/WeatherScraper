package md.buiucani.entities;

import java.time.LocalDateTime;

public class WeatherObject implements ScrapedObject {

    private LocalDateTime localDateTime;

    private String temperature;

    @Override
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    @Override
    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String getData() {
        return temperature;
    }

    @Override
    public void setData(String data) {
        this.temperature = data;
    }
}
