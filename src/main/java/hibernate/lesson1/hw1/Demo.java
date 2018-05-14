package hibernate.lesson1.hw1;

import hibernate.lesson1.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by user on 14.04.2018.
 */
public class Demo {
    public static void main(String[] args) {
        Product product = new Product();

        product.setId(300);
        product.setName("fridgeTest");
        product.setDescription("black & white");
        product.setPrice(1000);

        save(product);
    }
    public static void save(Product product){
        Session session = createSessionFactory().openSession();
        session.getTransaction().begin();

        session.save(product);

        session.getTransaction().commit();
        session.close();
    }
    public static SessionFactory createSessionFactory(){
        return new Configuration().configure().buildSessionFactory();
    }
}
