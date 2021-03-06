package lesson1and2.hw2;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 25.01.2018.
 */
public class Solution {
    private static final String DB_URL = "jdbc:oracle:thin:@solnalarmlessons.caixfggxri5y.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "sysadmin";
    private static final String PASS = "sysadmin";
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";

    public List<Product> getAllProducts() throws SQLException {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT");

            return createListProducts(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong");

        }
    }

    public List<Product> getAllProductsByPrice() throws SQLException {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT WHERE PRICE >= 100");

            return createListProducts(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong");
        }
    }

    public List<Product> getProductByDescription() throws SQLException {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT WHERE LENGTH(DESCRIPTION) > 50");

            return createListProducts(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong");
        }
    }
    private List<Product> createListProducts(ResultSet resultSet) throws SQLException {
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4));
                products.add(product);
            }
            return products;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public void saveProduct() throws SQLException {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            boolean res = statement.execute("INSERT INTO PRODUCT (ID, NAME, DESCRIPTION, PRICE) VALUES(999, 'toy', 'for children', 60)");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong. Product was not saved");


        }
    }

    public void deleteProducts() throws SQLException {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            boolean res = statement.execute("DELETE FROM PRODUCT WHERE NAME != 'toy' ");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong");

        }
    }

    public void increasePrice() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
            int res = statement.executeUpdate("UPDATE PRODUCT SET PRICE = PRICE + 100 where PRICE < 970");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong");
        }
    }

   /* public void changeDescription() throws SQLException {
        String sql ="SELECT * FROM PRODUCT WHERE LENGTH(DESCRIPTION) > 40";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
             ResultSet resultSet = statement.executeQuery("SELECT ID, NAME, DESCRIPTION, PRICE FROM PRODUCT WHERE LENGTH(DESCRIPTION) > 40")) {


            while (resultSet.next()) {
                System.out.println(resultSet);
                String description = resultSet.getString(3);
                String needDescription = description.substring(0, 40);
                int indexLastPoint = needDescription.lastIndexOf('.');
                String outputDescription = needDescription.substring(0, indexLastPoint + 1);
                resultSet.absolute(resultSet.getInt(1));
            //    resultSet.updateString("DESCRIPTION", "\"" + outputDescription + "\"");
                resultSet.updateRow();
                  String sql1 = "UPDATE PRODUCT SET DESCRIPTION = \'" + outputDescription + "\' WHERE LENGTH(DESCRIPTION) > 40";
                System.out.println(sql1);
                  // statement.executeUpdate(sql);

            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong");
        }
    }*/
    public void changeDescription3() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement())
           {
               ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT ");

                while (resultSet.next()) {
                    String description = resultSet.getString(3);
                    String needDescription = description.substring(0, 40);
                    Long id = resultSet.getLong(1);
                    int indexLastPoint = needDescription.lastIndexOf('.');
                    String outputDescription = needDescription.substring(0, indexLastPoint + 1);

                     String sql = "UPDATE PRODUCT SET DESCRIPTION = \'" + outputDescription + "\' WHERE LENGTH(DESCRIPTION) > 40 AND ID = " + id ;
                    System.out.println(sql);
                      statement.executeUpdate(sql);
                }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong");
        }
    }

    public void changeDescription2() throws Exception {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.prepareStatement("SELECT * FROM PRODUCT WHERE LENGTH(DESCRIPTION) > 100")) {
            // try (ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT WHERE LENGTH(DESCRIPTION) > 100"))
            {
                String sql = "UPDATE PRODUCT SET DESCRIPTION = ?  WHERE LENGTH(DESCRIPTION) > 40 AND ID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT WHERE LENGTH(DESCRIPTION) > 40");

                while (resultSet.next()) {
                    Long id = resultSet.getLong(1);
                    String description = resultSet.getString(3);
                    String needDescription = description.substring(0, 40);
                    int indexLastPoint = needDescription.lastIndexOf('.');
                    String outputDescription = needDescription.substring(0, indexLastPoint + 1);

                    preparedStatement.setString(1, outputDescription);
                    preparedStatement.setLong(2, id);
                    preparedStatement.executeUpdate();
                    // statement.executeUpdate(sql);


                }
                preparedStatement.executeUpdate();
               /* resultSet.close();
                statement.close();
                connection.close();*/
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Update did not execute");
        }
    }




}
