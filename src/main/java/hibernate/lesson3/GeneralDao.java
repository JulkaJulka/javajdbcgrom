package hibernate.lesson3;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * Created by user on 08.05.2018.
 */
public abstract class GeneralDao<T> {

    private SessionFactory sessionFactory;
    public static String findByIdHql = "";
    public static final String FIND_BY_ID_HOTEL = "FROM Hotel WHERE ID = :ID ";
    public static final String FIND_BY_ID_ROOM = "FROM Room WHERE ID = :ID ";
    public static String FIND_EQUALS_HOTEL = "FROM Hotel WHERE NAME = :NAME AND COUNTRY = :COUNTRY AND CITY = :CITY AND STREET = :STREET";


    public static void setFindByIdHql(String findByIdHql) {
        GeneralDao.findByIdHql = findByIdHql;
    }

    public abstract T save(T t) throws Exception;

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

    public T findById(Long id) throws HibernateException {
        try (Session session = createSessionFactory().openSession()) {

            Query query = session.createQuery(findByIdHql);
            query.setParameter("ID", id);

            if(query.uniqueResult() == null)
                return null;
            T entity = (T) query.getSingleResult();

            return entity;

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            throw new HibernateException("Something went wrong");
        }
    }

    public T delete(long id) throws HibernateException {

        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            T deleteEntity = findById(id);
            session.delete(deleteEntity);

            tr.commit();

            return deleteEntity;
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            if (tr != null)
                tr.rollback();
            throw new HibernateException("Delete is failed");
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
