package hibernate.lesson2.hw_productDAO_HQL;

import hibernate.lesson1.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by user on 18.04.2018.
 */
public class ProductDAO {

    private static final String FIND_BY_ID = "from Product WHERE ID = :ID ";
    private static final String FIND_BY_NAME = "from Product WHERE NAME = :NAME ";
    private static final String FIND_BY_CONTAIN_NAME = "from Product where NAME  like ?  ";
    private static final String FIND_BY_PRICE_DELTA = "from Product where PRICE >= ? AND PRICE <= ? ";
    private static final String FIND_BY_NAME_ASC = "from Product WHERE NAME = :NAME order by NAME ASC ";
    private static final String FIND_BY_NAME_DESC = "from Product WHERE NAME = :NAME order by NAME DESC ";
    private static final String FIND_BY_PRICE_RANGE_DESC = "from Product WHERE PRICE >= ? AND PRICE <= ? ORDER BY PRICE DESC ";
    private SessionFactory sessionFactory;

    public Product findById(Long id) throws HibernateException {

        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            Query query = session.createQuery(FIND_BY_ID);
            query.setParameter("ID", id);
            Product product = (Product) query.getSingleResult();

            tr.commit();
            return product;

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            if (tr != null)
                tr.rollback();
            throw new HibernateException("Something went wrong");
        }
    }

    public List<Product> findByName(String name) throws HibernateException {

        return findByNameGeneral(name, FIND_BY_NAME);
    }

    public List<Product> findByContainedName(String name) throws HibernateException {

        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            Query query = session.createQuery(FIND_BY_CONTAIN_NAME);
            query.setParameter(0, "%" + name + "%");

            List<Product> list = query.list();

            tr.commit();
            return list;

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            if (tr != null)
                tr.rollback();
            throw new HibernateException("Something went wrong");
        }
    }

    public List<Product> findByPrice(int price, int delta) throws HibernateException {
        return findByPriceGeneral(price, delta, FIND_BY_PRICE_DELTA);
    }

    public List<Product> findByNameSortedAsc(String name) throws HibernateException {

        return findByNameGeneral(name, FIND_BY_NAME_ASC);
    }

    public List<Product> findByNameSortedDesc(String name) throws HibernateException {

        return findByNameGeneral(name, FIND_BY_NAME_DESC);
    }

    public List<Product> findByPriceSortedDesc(int price, int delta) throws HibernateException {

        return findByPriceGeneral(price, delta, FIND_BY_PRICE_RANGE_DESC);
    }

    public List<Product> findByPriceGeneral(int price, int delta, String request) throws HibernateException {

        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            Query query = session.createQuery(request);
            query.setParameter(0, price - delta);
            query.setParameter(1, price + delta);
            List<Product> list = query.list();

            tr.commit();
            return list;

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            if (tr != null)
                tr.rollback();
            throw new HibernateException("Something went wrong");
        }
    }

    public List<Product> findByNameGeneral(String name, String request) throws HibernateException {

        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            Query query = session.createQuery(request);
            query.setParameter("NAME", name);

            List<Product> list = query.list();

            tr.commit();
            return list;

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            if (tr != null)
                tr.rollback();
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
