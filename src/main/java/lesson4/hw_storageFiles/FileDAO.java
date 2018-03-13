package lesson4.hw_storageFiles;

import java.sql.*;


/**
 * Created by user on 22.02.2018.
 */
public class FileDAO extends GeneralDAO {
    static {
        setIn("INSERT INTO \"FILE\" (ID, NAME, \"FORMAT\", \"SIZE\", STORAGE_ID) VALUES(?, ?, ?, ?, ?)");
    }

   /* @Override
    public File findById(long id) throws Exception {
        return (File)super.findById(id);
    }*/

    public File findById(Storage storage, long id) throws Exception {
        if (storage == null)
            throw new Exception("You enter wrong data");

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM \"FILE\" WHERE ID = ? ");

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getLong(5) == storage.getId()) {
                    return createFileObject(resultSet);
                }
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong");
        }
    }

    private File createFileObject(ResultSet resultSet) throws SQLException {
        File file = new File(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getLong(4), resultSet.getLong(5));
        return file;
    }


    @Override
    public String setNameTableDB(String nameTableDB) {

        return "\"FILE\"";
    }

    @Override
    public String setInsertObjectToDBRow(String insertObjectToDBRow) {
        return "INSERT INTO \"FILE\" (ID, NAME, \"FORMAT\", \"SIZE\", STORAGE_ID) VALUES(?, ?, ?, ?, ?)";
    }

    public String setInsertObjectToDBRow() {
        return "INSERT INTO \"FILE\" (ID, NAME, \"FORMAT\", \"SIZE\", STORAGE_ID) VALUES(?, ?, ?, ?, ?)";
    }

    @Override
    public String setUpdateObjectToDBRow(String updateObjectToDBRow) {
        return "UPDATE \"FILE\" SET NAME = ?, \"FORMAT\"  = ?, \"SIZE\" = ?, STORAGE_ID = ?  WHERE ID = ?";
    }

    @Override
    public File createObject(ResultSet resultSet) throws SQLException {
        File file = new File(resultSet.getLong(1), resultSet.getString(2),
                resultSet.getString(3), resultSet.getLong(4), resultSet.getLong(5));
//Object o = (Object) file;
        return file;
    }


    @Override
    public void insertObjectToDB(Object o,PreparedStatement preparedStatement) throws SQLException {
        File file = (File) o;
            preparedStatement.setLong(1, file.getId());
            preparedStatement.setString(2, file.getName());
            preparedStatement.setString(3, file.getFormat());
            preparedStatement.setLong(4, file.getSize());
            preparedStatement.setLong(5, file.getStorageId());
       // preparedStatement.executeUpdate();
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
