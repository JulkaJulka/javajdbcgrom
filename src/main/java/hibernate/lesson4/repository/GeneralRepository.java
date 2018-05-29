package hibernate.lesson4.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by user on 15.12.2017.
 */

public abstract class GeneralRepository<T> {

    private SessionFactory sessionFactory;

    public T save(T t) throws Exception {

        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            session.save(t);

            tr.commit();
            return t;

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            if (tr != null)
                tr.rollback();
            throw new HibernateException("Save is failed");
        }
    }

    public T update(T t) throws HibernateException {

        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            session.update(t);

            tr.commit();
            return t;

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            if (tr != null)
                tr.rollback();
            throw new HibernateException("Update is failed");
        }
    }

    public void delete(String hqlDelEntity, long id) {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            Query queryDelHt = session.createQuery(hqlDelEntity);
            queryDelHt.setParameter("ID", id);
            queryDelHt.executeUpdate();

            tr.commit();

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            if (tr != null)
                tr.rollback();
            throw new HibernateException("Delete is failed");
        }
    }

    public T findById(String sql, Long id) {
        try (Session session = createSessionFactory().openSession()) {

            Query query = session.createQuery(sql);
            query.setParameter("ID", id);

            if (query.uniqueResult() == null)
                return null;
            T entity = (T) query.getSingleResult();

            return entity;

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            throw new HibernateException("Something went wrong");
        }
    }

    public List<T> findHotelByStrDescrip(String searchParametr, String name) {

        try (Session session = createSessionFactory().openSession()) {
            String hql = "FROM Hotel WHERE " + searchParametr + " = :NAME";

            Query query = session.createQuery(hql);
            query.setParameter("NAME", name);

            List<T> findHtls = query.list();

            return findHtls;

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            throw new HibernateException("Something went wrong");
        }
    }

    public SessionFactory createSessionFactory() {

        //singleton pattern
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }

}
