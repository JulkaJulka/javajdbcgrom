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

    public List<Product> findProductsByPrice(int price, int delta) throws SQLException {
        try (Connection connection = getConnection()) {

            String sql = "SELECT * FROM PRODUCT WHERE PRICE >= ? AND PRICE <= ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, price - delta);
            statement.setInt(2, price + delta);
            ResultSet resultSet = statement.executeQuery();


            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                products.add(createProduct(resultSet));
            }
            return products;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong");
        }

    }

    public List<Product> findProductsByName(String word) throws Exception {
        validateWord(word);
        try (Connection connection = getConnection()) {

            String sql = "SELECT * FROM PRODUCT WHERE NAME = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, word);
            ResultSet resultSet = statement.executeQuery();


            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                products.add(createProduct(resultSet));
            }
            return products;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong");
    }
}
    public List<Product> findProductsWithEmptyDescription() throws SQLException {
        try (Connection connection = getConnection()) {

            String sql = "SELECT * FROM PRODUCT WHERE DESCRIPTION IS NULL";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                products.add(createProduct(resultSet));
            }
            return products;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong");
        }
    }


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    boolean validateWord(String body) throws Exception {
        if (body.isEmpty() || body.length() < 3)
            throw new Exception("You enter wrong name " + body);
        String[] words = body.split(" ");
        if (words.length != 1)
            throw new Exception("You enter wrong name " + body + "More than one word");
        char[] chars = body.toCharArray();
        for (char ch : chars) {
            if (!(Character.isLetterOrDigit(ch)))
                throw new Exception("You enter wrong name " + body + " contains not only letters and digits");
        }
        return true;
    }

    private Product createProduct(ResultSet resultSet) throws SQLException{
        Product product = new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
        return product;
    }
}
