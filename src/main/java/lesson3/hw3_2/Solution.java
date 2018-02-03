package lesson3.hw3_2;

import java.sql.*;
import java.util.Locale;
import java.util.Random;

/**
 * Created by user on 29.01.2018.
 */
public class Solution {
    private static final String DB_URL = "jdbc:oracle:thin:@solnalarmlessons.caixfggxri5y.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "sysadmin";
    private static final String PASS = "sysadmin";

    public Long testSavePerformance() {
        // time execute 246651ms;236434
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            long start = System.currentTimeMillis();

            for (int i = 0; i < 1000; i++) {
                Random rm = new Random();
                int id = Math.abs(rm.nextInt());
                String someString = randomString();
                int someNumber = Math.abs(rm.nextInt());
                statement.executeUpdate("INSERT INTO TEST_SPEED(ID,SOME_STRING,SOME_NUMBER) VALUES('" + id + "','" + someString + "','" + someNumber + "')");
            }

            long finish = System.currentTimeMillis();
            long time = finish - start;

            return time;

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();

        }
        return null;
    }

    public String randomString() {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = upper.toLowerCase(Locale.ENGLISH);
        String digits = "0123456789";
        String alphanum = upper + lower + digits;
        char[] buf = new char[20];
        String string = "";
        char[] symbols = alphanum.toCharArray();
        for (int idx = 0; idx < buf.length; ++idx) {
            Random random = new Random();
            buf[idx] = symbols[random.nextInt(symbols.length)];
            string = string + buf[idx];

        }
        return string;
    }

    public Long testDeleteByIdPerformance() {
        //time execute 278102ms
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            long start = System.currentTimeMillis();
            String sql = "DELETE FROM TEST_SPEED WHERE ID = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet res = statement.executeQuery("SELECT * FROM TEST_SPEED");

            while (res.next()) {
                Long id = res.getLong(1);
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            }
            long finish = System.currentTimeMillis();
            long time = finish - start;

            return time;

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;
    }

    public Long testDeletePerformance() {
        //time execute 465ms
        try (Connection connection = getConnection()) {
            long start = System.currentTimeMillis();
            String sql = "DELETE FROM TEST_SPEED";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

            long finish = System.currentTimeMillis();
            long time = finish - start;

            return time;

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;
    }

    public Long testSelectByIdPerformance() {
        //time execute 513516ms
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            long start = System.currentTimeMillis();
            String sql = "SELECT * FROM TEST_SPEED WHERE ID = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet res = statement.executeQuery("SELECT * FROM TEST_SPEED");
            while (res.next()) {
                Long id = res.getLong(1);
                preparedStatement.setLong(1, id);
                preparedStatement.executeQuery();
            }
            long finish = System.currentTimeMillis();
            long time = finish - start;

            return time;

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();

        }
        return null;
    }

    public Long testSelectPerformance() {
        //time execute 441ms
        try (Connection connection = getConnection()) {
            long start = System.currentTimeMillis();
            String sql = "SELECT * FROM TEST_SPEED";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeQuery();

            long finish = System.currentTimeMillis();
            long time = finish - start;

            return time;

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
