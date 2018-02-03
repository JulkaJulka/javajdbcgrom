package lesson1and2;

import java.sql.*;
import java.util.Date;

/**
 * Created by user on 25.01.2018.
 */
public class JDBCExamples {
    private static final String DB_URL = "jdbc:oracle:thin:@solnalarmlessons.caixfggxri5y.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "sysadmin";
    private static final String PASS = "sysadmin";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
           // boolean res = statement.execute("INSERT INTO PRODUCT (ID, NAME, DESCRIPTION, PRICE) VALUES(2, 'toy111', 'for children', 60)");
           // System.out.println(res);

       //     boolean res = statement.execute("DELETE FROM PRODUCT WHERE NAME = 'toy111'");
       //      System.out.println(res);

      //      int response = statement.executeUpdate("INSERT INTO PRODUCT (ID, NAME, DESCRIPTION, PRICE) VALUES(2, 'car', 'for children', 70)");
     //       System.out.println(response);

         //   int response = statement.executeUpdate("DELETE FROM PRODUCT WHERE NAME = 'car'");
         //   System.out.println(response);
            int res = statement.executeUpdate("SELECT * FROM PRODUCT");
            System.out.println(res);

        } catch (SQLException e){
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

    }
}
