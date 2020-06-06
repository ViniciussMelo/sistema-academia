package database.models.modality;

import database.models.Model;
import database.models.address.Address;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "students")
public class Student extends Model<Student> {

    @Column(name = "name")
    private String name;

    @JoinTable(name = "student_modalities")
    @ManyToMany
    private List<Modality> modalities;

    @Embedded
    private Address address;

    @Column
    private String telephone;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Modality> getModalities() {
        return modalities;
    }

    public void setModalities(List<Modality> modalities) {
        this.modalities = modalities;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getActiveText() {
        return getActive() ? "Sim" : "Não";
    }

    @Override
    public String[] getResult() {
        return new String[]{getName(), getTelephone(), getActiveText()};
    }

    @Override
    public List<Student> filter(String value) {
        return null;
    }
}
