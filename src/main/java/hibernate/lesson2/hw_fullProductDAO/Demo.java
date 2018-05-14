package hibernate.lesson2.hw_fullProductDAO;

import hibernate.lesson1.Product;

import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 17.04.2018.
 */
public class Demo {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();

        Product product = new Product();
      //  product.setId(99);
        product.setName("bed");
        product.setDescription("grey & blue");
        product.setPrice(70);

        Product product1 = new Product();
      //  product1.setId(99);
        product1.setName("table new111");
        product1.setDescription("grey & blue");
        product1.setPrice(70);

        Product product2 = new Product();
        product2.setId(9);
        product2.setName("table new222");
        product2.setDescription("grey & blue");
        product2.setPrice(70);

        Product product3 = new Product();
       // product3.setId(13);
        product3.setName("Lev2");
        product3.setDescription("red");
        product3.setPrice(8000);

        Product product4 = new Product();
         product4.setId(23);
        product4.setName("Lev2");
        product4.setDescription("red");
        product4.setPrice(8000);

        Product product5 = new Product();
        product5.setId(15);
        product5.setName("Lev2");
        product5.setPrice(10000);

        Product product6 = new Product();
        product6.setId(19);
        product6.setName("Lev2");
        product6.setPrice(10000);

        Product product7 = new Product();
        product7.setId(21);
        product7.setName("Lev2");
        product7.setPrice(1);

       System.out.println(productDAO.save(product));
       // System.out.println(productDAO.update(product4));

      //  System.out.println(productDAO.delete(product2));
        List<Product> products = Arrays.asList(product1,product2,product3);
        List<Product> productsUpdate = Arrays.asList(product5, product6, product7);

        //productDAO.updateAll(productsUpdate);
       // productDAO.deleteAll(productsUpdate);
       // productDAO.saveProducts(products);
    }
}
