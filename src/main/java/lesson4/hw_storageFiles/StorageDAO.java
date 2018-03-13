package lesson4.hw_storageFiles;

import java.sql.*;

/**
 * Created by user on 22.02.2018.
 */
public class StorageDAO extends GeneralDAO {

    @Override
    public String setNameTableDB(String nameTableDB) {

        return "STORAGE";
    }

    @Override
    public String setInsertObjectToDBRow(String insertObjectToDBRow) {
        return "INSERT INTO STORAGE (ID, FORMATS_SUPPORTED, STORAGE_COUNTRY, STORAGE_SIZE) VALUES(?, ?, ?, ?)";
    }

    @Override
    public String setUpdateObjectToDBRow(String updateObjectToDBRow) {
        return "UPDATE STORAGE SET FORMATS_SUPPORTED = ?, STORAGE_COUNTRY = ?, STORAGE_SIZE = ?  WHERE ID = ?";
    }

    @Override
    public Storage createObject(ResultSet resultSet) throws SQLException {
        Storage storage = new Storage(resultSet.getLong(1), createArrayFromString(resultSet.getString(2)),
                resultSet.getString(3), resultSet.getLong(4));

        return storage;
    }

    @Override
    public void insertObjectToDB(Object o, PreparedStatement preparedStatement) throws SQLException {
        Storage storage = (Storage) o;
        preparedStatement.setLong(1, storage.getId());
        preparedStatement.setString(2, storage.getFormatsSupportedString());
        preparedStatement.setString(3, storage.getStorageCountry());
        preparedStatement.setLong(4, storage.getStorageSize());

    }

    @Override
    public void updateObjectToDB(Object o, PreparedStatement statement) throws SQLException {
        Storage storage = (Storage) o;
        statement.setLong(4, storage.getId());
        statement.setString(1, storage.getFormatsSupportedString());
        statement.setString(2, storage.getStorageCountry());
        statement.setLong(3, storage.getStorageSize());
    }

    public String[] createArrayFromString(String str) {
        String[] format = str.split(",");
        return format;
    }
}
