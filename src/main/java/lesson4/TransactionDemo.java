package lesson4;

import lesson3.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11.02.2018.
 */
public class TransactionDemo {
    private static final String DB_URL = "jdbc:oracle:thin:@solnalarmlessons.caixfggxri5y.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "sysadmin";
    private static final String PASS = "sysadmin";

    public static void main(String[] args) throws SQLException{
        Product product1 = new Product(55, "!!!", "!!!", 7777);
        Product product2 = new Product(66, "!!!", "!!!", 7777);
        Product product3 = new Product(67, "!!!", "!!!", 7777);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);

        save(products);

    }

    public static void save(List<Product> products) throws SQLException  {

        try (Connection connection = getConnection()) {
            saveList(products, connection);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong");
        }
    }

    private static void saveList(List<Product> products, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PRODUCT (ID, NAME, DESCRIPTION, PRICE) VALUES(?, ?, ?, ?)")) {

            connection.setAutoCommit(false);

            for (Product product : products) {
                preparedStatement.setLong(1, product.getId());
                preparedStatement.setString(2, product.getName());
                preparedStatement.setString(3, product.getDescription());
                preparedStatement.setInt(4, product.getPrice());

                int res = preparedStatement.executeUpdate();
                System.out.println("save was finished with result " + res);
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
