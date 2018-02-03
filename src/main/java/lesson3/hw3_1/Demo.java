package lesson3.hw3_1;

import lesson3.ProductDAO;

/**
 * Created by user on 27.01.2018.
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        Product product = new Product(999l, "toy1", "testdescription", 40);
       // System.out.println(solution.getProducts().toString());
    //   System.out.println(solution.findProductsByPrice(170,10));
       // System.out.println(solution.findProductsByName("toy2"));
        System.out.println(solution.findProductsWithEmptyDescription());
      // productDAO.save(product);
        //System.out.println(productDAO.deleteProduct(-8l));
      //  System.out.println(productDAO.update(product));
      //  System.out.println(productDAO.getProducts());
    }
}
