package database.models.period;

import database.models.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity(name = "periods")
public class Period extends Model<Period> {

    @Column
    private String description;

    @Column
    private LocalTime initTime;

    @Column
    private LocalTime endTime;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getInitTime() {
        return initTime;
    }

    public void setInitTime(LocalTime initTime) {
        this.initTime = initTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String[] getResult() {
        return new String[]{getDescription(), getInitTime().format(DateTimeFormatter.ISO_TIME), getEndTime().format(DateTimeFormatter.ISO_TIME)};
    }

    @Override
    public List<Period> filter(String value) {
        return null;
    }
}
