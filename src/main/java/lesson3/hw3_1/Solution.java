package lesson3.hw3_1;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 28.01.2018.
 */
public class Solution {

    private static final String DB_URL = "jdbc:oracle:thin:@solnalarmlessons.caixfggxri5y.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "sysadmin";
    private static final String PASS = "sysadmin";

public List<Product> findProductsByPrice(int price, int delta){
    List<Product> products = getProducts();
    List<Product> findProductsByPrice = new ArrayList<>();
    for (Product product: products){
        if(product.getPrice() >= (price - delta) && product.getPrice() <= (price + delta)){
            findProductsByPrice.add(product);

        }
    }
    return findProductsByPrice;
}

public List<Product> findProductsByName(String word) throws Exception{
    validateWord(word);
    List<Product> products = getProducts();
    List<Product> findProductsByPrice = new ArrayList<>();
    for (Product product: products){
        if(product.getName().equals(word)){
            findProductsByPrice.add(product);

        }
    }
    return findProductsByPrice;

}
public List<Product> findProductsWithEmptyDescription(){
    List<Product> products = getProducts();
    List<Product> findProducts = new ArrayList<>();
    for (Product product : products){
       try{ if(product.getDescription().isEmpty()){
            findProducts.add(product);
        }} catch (Exception e){
            findProducts.add(product);
       }
    }
    return findProducts;
}
    public List<Product> getProducts() {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT");

            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
                products.add(product);
            }
            return products;

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;
    }
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    boolean validateWord(String body) throws Exception{
        if(body.isEmpty() || body.length() < 3)
            throw new Exception("You enter wrong name " + body);
        String[] words = body.split(" ");
        if(words.length != 1)
            throw new Exception("You enter wrong name " + body + "More than one word");
        char[] chars = body.toCharArray();
        for (char ch : chars){
            if(!(Character.isLetterOrDigit(ch)))
                throw new Exception("You enter wrong name " + body + " contains not only letters and digits");
        }
        return true;
    }
}
