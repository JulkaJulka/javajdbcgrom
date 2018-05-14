package hibernate.lesson1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * Created by user on 14.04.2018.
 */
public class ProductRepository {

    public void save(Product product) {
        Session session = createSessionFactory().openSession();
        session.getTransaction().begin();

        session.save(product);

        session.getTransaction().commit();
        session.close();
    }

    public void update(Product product) {
        Session session = createSessionFactory().openSession();
        session.getTransaction().begin();

        session.update(product);

        session.getTransaction().commit();
        session.close();
    }
    public void delete(long id) {
        Session session = createSessionFactory().openSession();
        session.getTransaction().begin();

        Product product = session.load(Product.class, id);
        session.delete(product);

        session.getTransaction().commit();
        session.close();
    }

    public void delete2(long id) {
        Session session = createSessionFactory().openSession();
        session.getTransaction().begin();

        Query query = session.createQuery("DELETE  Product WHERE ID = :ID ");
        query.setParameter("ID", id);
        query.executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    public SessionFactory createSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }
}
