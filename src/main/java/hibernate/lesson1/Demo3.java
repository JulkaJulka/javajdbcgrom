package hibernate.lesson1;

/**
 * Created by user on 14.04.2018.
 */
public class Demo3 {
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();
        Product product = new Product();

        product.setId(301l);
        product.setName("fridgeTestRepo");
        product.setDescription("50 grey");
        product.setPrice(1000);
        //productRepository.update(product);
        productRepository.delete(1004l);



    }
}
