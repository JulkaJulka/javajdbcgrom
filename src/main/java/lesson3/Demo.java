package lesson3;

import java.sql.SQLException;

/**
 * Created by user on 27.01.2018.
 */
public class Demo {
    public static void main(String[] args) throws Exception{
        ProductDAO productDAO = new ProductDAO();
        Product product = new Product(1004l, "car", "", 0);
      //  System.out.println(productDAO.save(product));
        System.out.println(productDAO.deleteProduct(-32l));
       // System.out.println(productDAO.update(product));
      //  System.out.println(productDAO.getProducts());
    }
}
