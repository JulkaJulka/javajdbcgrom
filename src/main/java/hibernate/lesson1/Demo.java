package hibernate.lesson1;

import org.hibernate.Session;

/**
 * Created by user on 14.04.2018.
 */
public class Demo {
    public static void main(String[] args) {
        Session session = new HibernateUtils().createSessionFactory().openSession();
session.getTransaction().begin();
        Product product = new Product();
        product.setId(99);
        product.setName("table");
        product.setDescription("grey & blue");
        product.setPrice(2000);
        session.save(product);

        session.update(product);

        session.getTransaction().commit();
        System.out.println("Done");
        session.close();
    }
}
