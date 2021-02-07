package md.buiucani.entities;

import java.time.LocalDateTime;


public interface ScrapedObject {

    LocalDateTime getLocalDateTime();

    void setLocalDateTime(LocalDateTime localDateTime);

    String getData();

    void setData(String data);


}
