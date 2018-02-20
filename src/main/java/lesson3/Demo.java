package lesson3;

import java.sql.SQLException;

/**
 * Created by user on 27.01.2018.
 */
public class Demo {
    public static void main(String[] args) throws Exception{
        ProductDAO productDAO = new ProductDAO();
        Product product = new Product(106l, "cat", "ooops", 140);
       System.out.println(productDAO.save(product));
       // productDAO.deleteProduct(-32l);
      // System.out.println(productDAO.update(product));
      //  productDAO.deleteProduct(1009l);
      //  System.out.println(productDAO.getProducts());
    }
}
