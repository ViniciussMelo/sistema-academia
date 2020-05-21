package database.models.modality;

import database.models.Model;
import database.models.address.Address;

import javax.persistence.*;
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

    @Column(name = "telephone")
    private String telephone;

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

    @Override
    public String[] getResult() {
        return new String[0];
    }

    @Override
    public List<Student> filter(String value) {
        return null;
    }
}
