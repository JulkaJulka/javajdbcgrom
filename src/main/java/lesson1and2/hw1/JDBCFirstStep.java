package lesson1and2.hw1;

import java.sql.*;

/**
 * Created by user on 17.01.2018.
 */
public class JDBCFirstStep {

    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@solnalarmlessons.caixfggxri5y.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "sysadmin";
    private static final String PASS = "sysadmin";


  public   ResultSet jdbcStart() throws Exception{
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
            //1. DB driver
            //2. create connection
            //3. create query
            //4. execute query
            //5. work with result
            //6. close all the connections
            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                System.out.println("Class " + JDBC_DRIVER + " not found");
                //return;
            }
            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM Orders")) {
                while (resultSet.next()) {
                   return resultSet;
                }
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
     throw new Exception ("Something went wrong");

      }
}

