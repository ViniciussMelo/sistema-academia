package database.service;

import database.connection.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class Service<T> {

    private Class<T> entity;

    public Service(Class<T> entity) {
        this.entity = entity;
    }

    public T save(T model) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            model = (T) session.merge(model);
            transaction.commit();
            return model;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public Boolean delete(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            T load = find(id);
            session.delete(load);
            transaction.commit();
            return Boolean.TRUE;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return Boolean.FALSE;
        } finally {
            session.close();
        }
    }

    public T find(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            T model = session.get(this.entity, id);

            if (model == null) {
                throw new Exception("Nenhum registro encontrado com o id: " + id);
            }

            return model;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }


    public List<T> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(this.entity);
        criteria.from(this.entity);
        List<T> data = session.createQuery(criteria).getResultList();
        session.close();
        return data;
    }
}
