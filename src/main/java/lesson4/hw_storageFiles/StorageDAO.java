package lesson4.hw_storageFiles;

import java.sql.*;

/**
 * Created by user on 22.02.2018.
 */
public class StorageDAO {
    private static final String DB_URL = "jdbc:oracle:thin:@solnalarmlessons.caixfggxri5y.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "sysadmin";
    private static final String PASS = "sysadmin";

    public StorageDAO() {
    }

    public Storage findById(long id) throws Exception {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM STORAGE WHERE ID = ? ");

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            Storage storage ;
            while (resultSet.next()) {
                storage = createStorageObject(resultSet);
                return storage;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong");
        }
    }

    public Storage save(Storage storage) throws Exception {
        if (storage == null)
            throw new Exception("You enter wrong data");


        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO STORAGE (ID, FORMATS_SUPPORTED, STORAGE_COUNTRY, STORAGE_SIZE) VALUES(?, ?, ?, ?)");

            Storage storageDB = findById(storage.getId());
            if(storageDB != null)
                throw new Exception("Storage with id " + storage.getId() + " already exist in DB");

            preparedStatement.setLong(1, storage.getId());
            preparedStatement.setString(2, storage.getFormatsSupportedString());
            preparedStatement.setString(3, storage.getStorageCountry());
            preparedStatement.setLong(4, storage.getStorageSize());

            int res = preparedStatement.executeUpdate();

            System.out.println("save was finished with result " + res);

            return storage;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong");
        }
    }

    public Storage update(Storage storage) throws Exception {
        if (storage == null)
            throw new Exception("You enter wrong data");

        try (Connection connection = getConnection()) {
            String sql = "UPDATE STORAGE SET FORMATS_SUPPORTED = ?, STORAGE_COUNTRY = ?, STORAGE_SIZE = ?  WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(4, storage.getId());
            statement.setString(1, storage.getFormatsSupportedString());
            statement.setString(2, storage.getStorageCountry());
            statement.setLong(3, storage.getStorageSize());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int res = statement.executeUpdate();
                System.out.println("Update was finished with result " + res);

                return storage;

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong");
        }
        return null;
    }

    public void deleteStorage(Long id) throws Exception {
        if (id <= 0)
            throw new Exception("Id " + id + " is wrong");

        try (Connection connection = getConnection()) {
            Storage foundStorageDB = findById(id);
            if(foundStorageDB == null)
                throw new Exception("Storage id " + id + " doesn't exist in DB");
            String sql = "DELETE FROM STORAGE WHERE ID = ?";
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

    public Storage createStorageObject(ResultSet resultSet) throws SQLException {
        Storage storage = new Storage(resultSet.getLong(1), createArrayFromString(resultSet.getString(2)),
                resultSet.getString(3), resultSet.getLong(4));

        return storage;
    }

    public String[] createArrayFromString(String str) {
        String[] format = str.split(",");
        return format;
    }
}
