package hibernate.lesson2;

import hibernate.lesson1.HibernateUtils;
import hibernate.lesson1.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 17.04.2018.
 */
public class ProductDAO {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        Product product = new Product();
       // product.setId(99);
        product.setName("table  New");
        product.setDescription("grey & blue");
        product.setPrice(70);

        Product product1 = new Product();
       // product1.setId(99);
        product1.setName("table new111");
        product1.setDescription("grey & blue");
        product1.setPrice(70);

        Product product2 = new Product();
       // product2.setId(99);
        product2.setName("table new222");
        product2.setDescription("grey & blue");
        product2.setPrice(70);

        Product product3 = new Product();
       // product3.setId(99);
        product3.setName("table new333");
        product3.setDescription("grey & blue");
        product3.setPrice(70);

        List<Product> products = Arrays.asList(product1,product2,product3);
        saveProducts(products);


       // save(product);
    }

    public static void saveProducts(List<Product> products){
        //create session/ tr
        Session session = null;
        Transaction tr = null;
        try {
            session = createSessionFactory().openSession();

            tr =  session.getTransaction();
            tr.begin();

            //action
            for (Product pr : products){
                session.save(pr);
            }

            //close session/ tr
            tr.commit();

        } catch (HibernateException e){
            System.err.println("Save is failed");
            System.err.println(e.getMessage());
            if(tr != null)
                tr.rollback();
        } finally {
            if(session != null)
                session.close();
        }

        System.out.println("Save is done");
    }

    public static void save(Product product) {

        //create session/ tr
        Session session = null;
        Transaction tr = null;
        try {
            session = createSessionFactory().openSession();

          tr =  session.getTransaction();
            tr.begin();

            //action
            session.save(product);

            //close session/ tr
            tr.commit();

        } catch (HibernateException e){
            System.err.println("Save is failed");
            System.err.println(e.getMessage());
            if(tr != null)
                tr.rollback();
        } finally {
            if(session != null)
                session.close();
        }

        System.out.println("Save is done");
    }

    public static SessionFactory createSessionFactory() {

        //singleton pattern
        if (sessionFactory == null) {
             sessionFactory =  new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
