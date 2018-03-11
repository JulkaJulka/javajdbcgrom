package lesson4.hw_storageFiles;

import java.sql.*;

/**
 * Created by user on 01.03.2018.
 */
public abstract class GeneralDAO<T> {
    private static final String DB_URL = "jdbc:oracle:thin:@solnalarmlessons.caixfggxri5y.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "sysadmin";
    private static final String PASS = "sysadmin";

    public static long SIZEMAX_STORAGE = 3000l;
    public static String nameTableDB = "";
    public static String insertObjectToDBRow = "";
    public static String updateObjectToDBRow = "";

    public abstract String setNameTableDB(String nameTableDB);

    public static   String setIn(String insertObjectToDBRow){
        GeneralDAO.insertObjectToDBRow = insertObjectToDBRow;
        return insertObjectToDBRow;
    }

    public abstract String setInsertObjectToDBRow(String insertObjectToDBRow) ;

    public abstract String setUpdateObjectToDBRow(String updateObjectToDBRow) ;

    public T findById(long id) throws Exception {
        String sql = "SELECT * FROM " + setNameTableDB(nameTableDB) + " WHERE ID = ? ";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            T t;
            while (resultSet.next()) {
                t = createObject(resultSet);
                return t;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong");
        }
    }


    public T save(T t) throws Exception {
        if (t == null)
            throw new Exception("You enter wrong data");

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(setInsertObjectToDBRow(insertObjectToDBRow));
            insertObjectToDB(t, preparedStatement);
int res = preparedStatement.executeUpdate();
            return t;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong");
        }
    }

    public T update(T t) throws Exception {
        if (t == null)
            throw new Exception("You enter wrong data");
        Connection connection = getConnection();

        try (PreparedStatement statement = connection.prepareStatement(setUpdateObjectToDBRow(updateObjectToDBRow));) {

            updateObjectToDB(t, statement);
            statement.executeUpdate();
            return t;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong");
        }
    }

    public void delete(Long id) throws Exception {
        if (id <= 0)
            throw new Exception("Id " + id + " is wrong");
        Connection connection = getConnection();
        String sql = "DELETE FROM " + setNameTableDB(nameTableDB) + " WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            int res = statement.executeUpdate();
            System.out.println("Delete was finished with result " + res);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong");
        }
    }

    public abstract T createObject(ResultSet resultSet) throws SQLException;

    public abstract void insertObjectToDB(T t, PreparedStatement preparedStatement) throws SQLException;

    public abstract void updateObjectToDB(T t, PreparedStatement preparedStatement) throws SQLException;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
