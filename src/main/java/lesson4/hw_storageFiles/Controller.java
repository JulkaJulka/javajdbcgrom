package lesson4.hw_storageFiles;

import java.sql.*;
import java.util.Arrays;

import static lesson4.hw_storageFiles.GeneralDAO.SIZEMAX_STORAGE;
import static lesson4.hw_storageFiles.GeneralDAO.getConnection;


/**
 * Created by user on 20.02.2018.
 */
public class Controller {


    private FileDAO fileDAO = new FileDAO();
    private StorageDAO storageDAO = new StorageDAO();

    public File findById(Storage storage, long id) throws Exception {
        if (storage == null)
            throw new Exception("You enter wrong data");

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM FILES WHERE ID = ? ");

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getLong(5) == storage.getId()) {
                    return createFileObject(resultSet);
                } else {
                    return null;
                }
            }
            throw new Exception("File with id " + id + " doesn't exist in Storage with id " + storage.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong");
        }
    }

    public File put(Storage storage, File file) throws Exception {
        if (file == null)
            throw new Exception("Putted file  is not detected");
        if (file.getId() <= 0)
            throw new Exception("Id " + file.getId() +
                    " isn't unacceptable. File can't put to storage with Id " + storage.getId());
        Connection connection = getConnection();
        connection.setAutoCommit(false);

        Object storageObject = storageDAO.findById(storage.getId());
        Storage foundStorage = (Storage) storageObject;
        if(foundStorage == null)
          throw new Exception("Storage with id " + storage.getId() + " doesn't exist in DB");

        Object fileObject = fileDAO.findById(file.getId());
        File foundFile = (File) fileObject;
        if (foundFile == null)
            throw new Exception("File id " + file.getId() + " doesn't exist in DB");

        checkLimitation(foundStorage, foundFile);
        if (foundFile.getStorageId() == 0) {
            foundFile.setStorageId(storage.getId());
            fileDAO.update(foundFile);

            foundStorage.setStorageSize(foundStorage.getStorageSize() + foundFile.getSize());
            storageDAO.update(foundStorage);

            connection.commit();
            return foundFile;
        }
        throw new Exception("File with " + file.getId() + " already exist in storage id " + storage.getId());
    }

    /* public File put(Storage storage, File file) throws Exception {
         if (file == null)
             throw new Exception("Putted file  is not detected");
         if (file.getId() <= 0)
             throw new Exception("Id " + file.getId() +
                     " isn't unacceptable. File can't put to storage with Id " + storage.getId());
         Connection connection = getConnection();
         try (PreparedStatement statementFiles = connection.prepareStatement("SELECT * FROM FILES WHERE ID = ? ");) {
             connection.setAutoCommit(false);

             statementFiles.setLong(1, file.getId());

             PreparedStatement statementStorage = connection.prepareStatement("SELECT * FROM STORAGE WHERE ID = ? ");
             statementStorage.setLong(1, storage.getId());

             ResultSet resultSetFiles = statementFiles.executeQuery();
             ResultSet resultSetStorage = statementStorage.executeQuery();

             File foundFile = new File();
             while (resultSetFiles.next()) {
                 foundFile = createFileObject(resultSetFiles);
                 if (!(foundFile.getId() == file.getId() && foundFile.getName().equals(file.getName())))
                     throw new Exception("File id " + file.getId() + " name " + file.getName() + " doesn't exist in DB");
             }

             Storage foundStorage = new Storage();
             while (resultSetStorage.next()) {
                 foundStorage = storageDAO.createStorageObject(resultSetStorage);
             }

             if (!checkLimitation(foundStorage, foundFile))
                 throw new Exception("Storage " + foundStorage.getId() + "is not empty");
             if (foundFile.getStorageId() != 0)
                 throw new Exception("File id " + file.getId() + " already busy by storage id " + storage.getId());
             foundFile.setStorageId(storage.getId());

             PreparedStatement statementPutFiles = connection.prepareStatement("UPDATE FILES SET STORAGE_ID = ? WHERE ID = ? ");
             statementPutFiles.setLong(1, storage.getId());
             statementPutFiles.setLong(2, file.getId());

             statementPutFiles.executeUpdate();

             PreparedStatement statementUpdateStorage = connection.prepareStatement("UPDATE STORAGE SET STORAGE_SIZE = ?  WHERE ID = ? ");
             statementUpdateStorage.setLong(1, foundStorage.getStorageSize() - foundFile.getSize());
             statementUpdateStorage.setLong(2, foundStorage.getId());

             statementUpdateStorage.executeUpdate();

             connection.commit();
             return foundFile;

         } catch (SQLException e) {
             connection.rollback();
             throw e;
         }
     }

     public void delete(Storage storage, File file) throws Exception {
         if (file == null)
             throw new Exception("Deleted file with Id " + file.getId() + " is null.");

         if (storage == null)
             throw new Exception("Storage with Id " + storage.getId() + " is not detected.");

         Connection connection = getConnection();
         try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE FILES SET STORAGE_ID = ?  WHERE ID = ? ");
              PreparedStatement preparedStatementStorage = connection.prepareStatement("UPDATE STORAGE SET STORAGE_SIZE = STORAGE_SIZE - ?  WHERE ID = ? ");) {

             connection.setAutoCommit(false);
             File deleteFile = findById(storage, file.getId());
             if (deleteFile == null || !deleteFile.getName().equals(file.getName()))
                 throw new Exception("File id " + file.getId() + " with name " + file.getName() + " doesn't exist in storage id " + storage.getId());

             if (deleteFile.getName().equals(file.getName())) {
                 preparedStatement.setLong(2, deleteFile.getId());
                 preparedStatement.setLong(1, 0);

                 preparedStatement.executeUpdate();

                 preparedStatementStorage.setLong(2, storage.getId());
                 preparedStatementStorage.setLong(1, deleteFile.getSize());

                 preparedStatementStorage.executeUpdate();
             }
             connection.commit();
         } catch (SQLException e) {
             connection.rollback();
             throw e;
         }
     }

     public File transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
         File transferFile = findById(storageFrom, id);
         if (transferFile == null)
             throw new Exception("File with id " + id + " is not found in DB");
         Connection connection = getConnection();
         try (PreparedStatement statementUpdateStorTo = connection.prepareStatement("UPDATE STORAGE SET STORAGE_SIZE = STORAGE_SIZE + ? WHERE ID = ?");
              PreparedStatement statementUpdateStorFrom = connection.prepareStatement("UPDATE STORAGE SET STORAGE_SIZE = STORAGE_SIZE - ? WHERE ID = ?");
              PreparedStatement statementUpdateFile = connection.prepareStatement("UPDATE FILES SET STORAGE_ID = ? WHERE ID = ?")) {
             connection.setAutoCommit(false);
             Storage storageToDB = storageDAO.findById(storageTo.getId());
             Storage storageFromDB = storageDAO.findById(storageFrom.getId());
             checkLimitation(storageToDB, transferFile);

             statementUpdateStorTo.setLong(2, storageToDB.getId());
             statementUpdateStorTo.setLong(1, transferFile.getSize());
             statementUpdateStorTo.executeUpdate();

             transferFile.setStorageId(storageTo.getId());
             statementUpdateStorFrom.setLong(2, storageFromDB.getId());
             statementUpdateStorFrom.setLong(1, transferFile.getSize());
             statementUpdateStorFrom.executeUpdate();

             statementUpdateFile.setLong(2, transferFile.getId());
             statementUpdateFile.setLong(1, storageToDB.getId());
             statementUpdateFile.executeUpdate();

             connection.commit();
             return transferFile;
         } catch (SQLException e) {
             connection.rollback();
             throw e;
         }
     }
 */
   /* public List<File> transferAll(Storage storageFrom, Storage storageTo) throws Exception {
        if (storageFrom == null || storageTo == null)
            throw new Exception("You enter wrong data");

        Connection connection = getConnection();

        try (PreparedStatement statementFilesDBFrom = connection.prepareStatement("SELECT * FROM FILES WHERE STORAGE_ID = ?");
             PreparedStatement statementUpFilesDBFrom = connection.prepareStatement("UPDATE FILES SET STORAGE_ID =? WHERE STORAGE_ID = ?");
             PreparedStatement statementUpStorageFrom = connection.prepareStatement("UPDATE STORAGE SET STORAGE_SIZE = 0 WHERE ID = ?");
             PreparedStatement statementUpStorageTo = connection.prepareStatement("UPDATE STORAGE SET STORAGE_SIZE = STORAGE_SIZE + ? WHERE ID = ?")) {
            connection.setAutoCommit(false);

            Storage storageDBFrom = storageDAO.findById(storageFrom.getId());
            Storage storageDBTo = storageDAO.findById(storageTo.getId());

            if (storageDBFrom == null || storageDBTo == null)
                throw new Exception("Storage id " + storageDBFrom.getId() + " is not found in DB");
            if (storageDBTo == null)
                throw new Exception("Storage id " + storageDBTo.getId() + " is not found in DB");
            if (storageDBFrom.getStorageSize() > SIZEMAX_STORAGE - storageDBTo.getStorageSize())
                throw new Exception("Size of transfer files exceeds free size of storageTo " + storageTo.getId());

            statementFilesDBFrom.setLong(1, storageDBFrom.getId());
            ResultSet resultSetFilesDBFrom = statementFilesDBFrom.executeQuery();

            List<File> files = new ArrayList<>();

            while (resultSetFilesDBFrom.next()) {
                File file = fileDAO.createFileObject(resultSetFilesDBFrom);
                checkLimitation(storageDBTo, file);

                statementUpFilesDBFrom.setLong(2, storageDBFrom.getId());
                statementUpFilesDBFrom.setLong(1, storageDBTo.getId());
                statementUpFilesDBFrom.executeUpdate();

                files.add(file);
            }

            statementUpStorageTo.setLong(2, storageDBTo.getId());
            statementUpStorageTo.setLong(1, storageDBFrom.getStorageSize());

            statementUpStorageFrom.setLong(1, storageDBFrom.getId());
            statementUpStorageFrom.executeUpdate();
            statementUpStorageTo.executeUpdate();

            for (File file : files) {
                file.setStorageId(storageDBTo.getId());
            }

            connection.commit();
            return files;

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }
*/

    public boolean checkFormatsSupported(Storage storage, File file) throws Exception {

        if (file == null || storage.getFormatsSupported() == null)
            return false;
        boolean status = true;
        for (String el : storage.getFormatsSupported()) {
            if (el.contains(file.getFormat())) {
                status = true;
                break;
            } else {
                status = false;
            }
        }
        return status;
    }

    public static boolean checkIdStorage(Storage storage) {
        if (storage == null)
            return false;
        if (storage.getId() <= 0)
            return false;
        return true;

    }

    public boolean checkLimitation(Storage storage, File file) throws Exception {
        if (file == null)
            throw new Exception("Putted file  is not detected");
        if (storage == null)
            throw new Exception("Wrong data of storage");
        if (storage.getStorageSize() + file.getSize() > SIZEMAX_STORAGE)
            throw new Exception("Not enough space in storage id " + storage.getId());
        if (!checkFormatsSupported(storage, file))
            throw new Exception("Format " + file.getFormat() + " is not supported by storage " + storage.getId());
        if (!checkIdStorage(storage))
            throw new Exception("Storage Id " + storage.getId() + " is wrong.");
        return true;
    }

    /*private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }*/

    private File createFileObject(ResultSet resultSet) throws SQLException {
        File file = new File(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getLong(4), resultSet.getLong(5));
        return file;
    }
}
