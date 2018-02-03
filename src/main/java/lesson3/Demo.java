package lesson3;

/**
 * Created by user on 27.01.2018.
 */
public class Demo {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        Product product = new Product(1009l, "car", "", 171);
        System.out.println(productDAO.save(product));
        //System.out.println(productDAO.deleteProduct(-8l));
       // System.out.println(productDAO.update(product));
      //  System.out.println(productDAO.getProducts());
    }
}
