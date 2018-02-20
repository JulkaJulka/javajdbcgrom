package lesson3;

import oracle.jdbc.proxy.annotation.Pre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 27.01.2018.
 */
public class ProductDAO {
    //create,read,update,delete
    private static final String DB_URL = "jdbc:oracle:thin:@solnalarmlessons.caixfggxri5y.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "sysadmin";
    private static final String PASS = "sysadmin";

    public Product save(Product product) throws Exception {
        if (product == null)
            throw new Exception("You enter wrong data");

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PRODUCT (ID, NAME, DESCRIPTION, PRICE) VALUES(?, ?, ?, ?)");

            preparedStatement.setLong(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setInt(4, product.getPrice());

            int res = preparedStatement.executeUpdate();

            System.out.println("save was finished with result " + res);

            return product;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong");
        }
    }

    public Product update(Product product) throws Exception {
        if (product == null)
            throw new Exception("You enter wrong data");

        try (Connection connection = getConnection()) {
            String sql = "UPDATE PRODUCT SET NAME = ?, DESCRIPTION = ?, PRICE = ?  WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setInt(3, product.getPrice());
            statement.setLong(4, product.getId());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int res = statement.executeUpdate();
                System.out.println("Update was finished with result " + res);

                return product;

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong");
        }
        return null;
    }

    public List<Product> getProducts() throws SQLException {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT");

            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
                products.add(product);
            }
            return products;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong");
        }
    }

    public void deleteProduct(Long id) throws Exception {
        if (id <= 0)
            throw new Exception("Id " + id + " is wrong");
        try (Connection connection = getConnection()) {

            String sql = "DELETE FROM PRODUCT WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong");
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
