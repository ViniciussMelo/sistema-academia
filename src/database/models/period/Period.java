package database.models.period;

import database.models.Model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "periods")
public class Period extends Model<Period> {

    @Column(name = "description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String[] getResult() {
        return new String[0];
    }
}
