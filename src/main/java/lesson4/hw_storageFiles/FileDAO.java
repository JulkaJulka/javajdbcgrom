package lesson4.hw_storageFiles;

import java.sql.*;


/**
 * Created by user on 22.02.2018.
 */
public class FileDAO extends GeneralDAO {

    @Override
    public String setNameTableDB(String nameTableDB) {

        return "\"FILE\"";
    }

    @Override
    public String setInsertObjectToDBRow(String insertObjectToDBRow) {
        return "INSERT INTO \"FILE\" (ID, NAME, \"FORMAT\", \"SIZE\", STORAGE_ID) VALUES(?, ?, ?, ?, ?)";
    }

    @Override
    public String setUpdateObjectToDBRow(String updateObjectToDBRow) {
        return "UPDATE \"FILE\" SET NAME = ?, \"FORMAT\"  = ?, \"SIZE\" = ?, STORAGE_ID = ?  WHERE ID = ?";
    }

    @Override
    public Object createObject(ResultSet resultSet) throws SQLException{
        File file = new File(resultSet.getLong(1), resultSet.getString(2),
                resultSet.getString(3), resultSet.getLong(4), resultSet.getLong(5));

        return file;
    }

    @Override
    public void insertObjectToDB(Object o,PreparedStatement preparedStatement) throws SQLException {
        File file = (File) o;
        preparedStatement.setLong(1, file.getId());
        preparedStatement.setString(2, file.getName());
        preparedStatement.setString(3, file.getFormat());
        preparedStatement.setLong(4, file.getSize());
        preparedStatement.setLong(5, 0l);

    }

    @Override
    public void updateObjectToDB(Object o, PreparedStatement statement) throws SQLException {
        File file = (File) o;
        statement.setLong(5, file.getId());
        statement.setString(1, file.getName());
        statement.setString(2, file.getFormat());
        statement.setLong(3, file.getSize());
        statement.setLong(4, file.getStorageId());
    }

}
