package lesson4.hw_storageFiles;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lesson4.hw_storageFiles.GeneralDAO.*;


/**
 * Created by user on 20.02.2018.
 */
public class Controller {


    private FileDAO fileDAO = new FileDAO();
    private StorageDAO storageDAO = new StorageDAO();


    public File put(Storage storage, File file) throws Exception {
        if (file == null)
            throw new Exception("Putted file  is not detected");
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);

            Storage foundStorage = storageDAO.findById(storage.getId());

            if (foundStorage == null)
                throw new Exception("Storage with id " + storage.getId() + " doesn't exist in DB");

            File foundFile = fileDAO.findById(file.getId());

            if (foundFile == null) {
                checkLimitation(foundStorage, file);
                if (file.getStorageId() == 0) {
                    file.setStorageId(storage.getId());
                    fileDAO.save(file);
                    foundStorage.setStorageSize(foundStorage.getStorageSize() + file.getSize());
                    storageDAO.update(foundStorage);
                    connection.commit();
                    return file;
                }
            }

            checkLimitation(foundStorage, foundFile);
            if (foundFile.getStorageId() != 0) {
                return null;
            }
            foundFile.setStorageId(storage.getId());
            fileDAO.update(foundFile);

            foundStorage.setStorageSize(foundStorage.getStorageSize() + foundFile.getSize());
            storageDAO.update(foundStorage);

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
        try {
            connection.setAutoCommit(false);

            File deleteFile = fileDAO.findById(storage, file.getId());
            if (deleteFile.equals(file)) {
                deleteFile.setStorageId(0);
                fileDAO.update(deleteFile);

                Storage storageFrom = storageDAO.findById(storage.getId());
                storageFrom.setStorageSize(storageFrom.getStorageSize() - deleteFile.getSize());
                storageDAO.update(storageFrom);
            } else {
                throw new Exception("File id " + file.getId() + " name " + file.getName() + " doesn't exist in storage id " + storage.getId());
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    public File transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {

        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            File transferFile = fileDAO.findById(storageFrom, id);
            if (transferFile == null)
                throw new Exception("File with id " + id + " is not found in DB");


            transferFile.setStorageId(storageTo.getId());
            fileDAO.update(transferFile);

            storageFrom.setStorageSize(storageFrom.getStorageSize() - transferFile.getSize());
            storageDAO.update(storageFrom);

            storageTo.setStorageSize(storageTo.getStorageSize() + transferFile.getSize());
            storageDAO.update(storageTo);

            connection.commit();

            return transferFile;

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    public List<File> transferAll(Storage storageFrom, Storage storageTo) throws Exception {
        if (storageFrom == null || storageTo == null)
            throw new Exception("You enter wrong data");

        Connection connection = getConnection();

        try (PreparedStatement statementFilesDBFrom = connection.prepareStatement("SELECT * FROM \"FILE\" WHERE STORAGE_ID = ?")) {
            connection.setAutoCommit(false);

            Storage storageDBFrom = storageDAO.findById(storageFrom.getId());
            Storage storageDBTo = storageDAO.findById(storageTo.getId());

            if (storageDBFrom == null)
                throw new Exception("Storage id " + storageFrom.getId() + " is not found in DB");
            if (storageDBTo == null)
                throw new Exception("Storage id " + storageTo.getId() + " is not found in DB");

            if (storageDBFrom.getStorageSize() > SIZEMAX_STORAGE - storageDBTo.getStorageSize())
                throw new Exception("Size of transfer files exceeds free size of storageTo " + storageTo.getId());

            statementFilesDBFrom.setLong(1, storageDBFrom.getId());
            ResultSet resultSetFilesDBFrom = statementFilesDBFrom.executeQuery();

            List<File> filesStorageFrom = new ArrayList<>();


            while (resultSetFilesDBFrom.next()) {
                File file = fileDAO.createObject(resultSetFilesDBFrom);
                if (fileDAO.findById(storageDBTo, file.getId()) != null)
                    throw new Exception("Storage id " + storageDBTo.getId() + " can't contains the same files id " + file.getId());
                checkLimitation(storageDBTo, file);

                filesStorageFrom.add(transferFile(storageDBFrom, storageDBTo, file.getId()));

            }

            connection.commit();
            return filesStorageFrom;

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

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
        if (file.getStorageId() == storage.getId())
            throw new Exception("File id " + file.getId() + " already exist in Storage id " + file.getStorageId());
        if (storage.getStorageSize() + file.getSize() > SIZEMAX_STORAGE)
            throw new Exception("Not enough space in storage id " + storage.getId());
        if (!checkFormatsSupported(storage, file))
            throw new Exception("Format " + file.getFormat() + " is not supported by storage " + storage.getId());
        if (!checkIdStorage(storage))
            throw new Exception("Storage Id " + storage.getId() + " is wrong.");
        return true;
    }


}
