package lesson4.hw_storageFiles;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 22.02.2018.
 */
public class FileDAO {
    private static final String DB_URL = "jdbc:oracle:thin:@solnalarmlessons.caixfggxri5y.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "sysadmin";
    private static final String PASS = "sysadmin";

    public File findById(long id) throws Exception {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM FILES WHERE ID = ? ");

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            File file;
            while (resultSet.next()) {
                file = createFileObject(resultSet);
                return file;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong");
        }
    }

    public File save(File file) throws Exception {
        if (file == null)
            throw new Exception("You enter wrong data");
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO FILES (ID, NAME, \"FORMAT\", \"SIZE\", STORAGE_ID) VALUES(?, ?, ?, ?, ?)")) {


            preparedStatement.setLong(1, file.getId());
            preparedStatement.setString(2, file.getName());
            preparedStatement.setString(3, file.getFormat());
            preparedStatement.setLong(4, file.getSize());
            preparedStatement.setLong(5, 0l);

            int res = preparedStatement.executeUpdate();
            System.out.println("save was finished with result " + res);
            file.setStorageId(0l);
            return file;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong");
        }
    }

    public File update(File file) throws Exception {
        if (file == null)
            throw new Exception("You enter wrong data");

        try (Connection connection = getConnection()) {
            String sql = "UPDATE FILES SET NAME = ?, \"FORMAT\" = ?, \"SIZE\" = ?, STORAGE_ID = ?  WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(5, file.getId());
            statement.setString(1, file.getName());
            statement.setString(2, file.getFormat());
            statement.setLong(3, file.getSize());
            statement.setLong(4, file.getStorageId());

                statement.executeUpdate();

                return file;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong");
        }
    }

    public void deleteFile(Long id) throws Exception {
        if (id <= 0)
            throw new Exception("Id " + id + " is wrong");
        try (Connection connection = getConnection()) {

            String sql = "DELETE FROM FILES WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            int res = statement.executeUpdate();
            System.out.println("Delete was finished with result " + res);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong");
        }
    }

    public File createFileObject(ResultSet resultSet) throws SQLException {
        File file = new File(resultSet.getLong(1), resultSet.getString(2),
                resultSet.getString(3), resultSet.getLong(4), resultSet.getLong(5));

        return file;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
