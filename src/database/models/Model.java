package database.models;

import database.service.Service;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Model<T extends Model<T>> {

    @Id
    @GeneratedValue
    private Integer id;

    public abstract String[] getResult();

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
