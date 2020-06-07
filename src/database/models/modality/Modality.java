package database.models.modality;

import database.models.Model;
import database.models.period.Period;
import database.models.user.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "modalities")
public class Modality extends Model<Modality> {

    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "modality_userId"))
    @ManyToOne
    private User teacher;

    @Column(name = "description")
    private String description;

    @Column(name = "value")
    private BigDecimal value;

    @JoinTable(name = "modalities_periods",
            joinColumns = {@JoinColumn(name = "modality_id")},
            inverseJoinColumns = {@JoinColumn(name = "period_id")})
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Period> period = new ArrayList<>();

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

    @Override
    public List<Modality> filter(String value) {
        return null;
    }
}
