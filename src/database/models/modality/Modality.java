package database.models.modality;

import database.models.Model;
import database.models.period.Period;
import database.models.user.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity(name = "modality")
public class Modality extends Model<Modality> {

    @JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "modality_userId"))
    @ManyToOne
    private User teacher;

    @Column(name = "description")
    private String description;

    @Column(name = "value")
    private BigDecimal value;

    @JoinTable(name = "modality_periods")
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Period> period;

    public User getTeacher() {
        return teacher;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public List<Period> getPeriod() {
        return period;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void setPeriod(List<Period> period) {
        this.period = period;
    }

    @Override
    public String[] getResult() {
        return new String[0];
    }
}
