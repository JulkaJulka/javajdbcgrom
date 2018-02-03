package lesson3;

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

    public Product save(Product product) {

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PRODUCT (ID, NAME, DESCRIPTION, PRICE) VALUES(?, ?, ?, ?)");

            preparedStatement.setLong(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setInt(4, product.getPrice());


            int res = preparedStatement.executeUpdate();

            System.out.println("save was finished with result " + res);
            //return product;

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return product;
    }

    public Product update(Product product) {

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT");

            while (resultSet.next()) {
                Long idDB = resultSet.getLong(1);

                if (idDB == product.getId()) {
                    int res = statement.executeUpdate("UPDATE PRODUCT SET NAME = '" + product.getName() + "', DESCRIPTION = '" + product.getDescription() + "', PRICE = '" + product.getPrice() + "' WHERE ID = \'" + product.getId() + "\'");
                    System.out.println("Update was finished with result " + res);
                    return product;
                }
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;
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

    public Product deleteProduct(Long id) {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT");
            while (resultSet.next()) {
                Long idDB = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                int price = resultSet.getInt(4);

                if (id > 0 && idDB == id) {
                    statement.executeUpdate("DELETE FROM PRODUCT WHERE ID = \'" + id + "\'");
                    return new Product(idDB, name, description, price);
                }
            }


        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
