package models;

import service.Service;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.lang.invoke.MethodHandles;
import java.util.List;

@MappedSuperclass
public class Model<T extends Model<T>> {

    @Id
    @GeneratedValue
    private Integer id;

    public T save() {
        Service<T> service = new Service<T>((Class<T>) this.getClass());
        return service.save((T) this);
    }

    public Boolean delete() {
        Service<T> service = new Service<T>((Class<T>) this.getClass());
        Boolean isDeleted = service.delete(getId());

        if (isDeleted) {
            setId(null);
        }

        return isDeleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
