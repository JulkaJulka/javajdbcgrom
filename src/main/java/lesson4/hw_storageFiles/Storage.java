package lesson4.hw_storageFiles;

import java.util.Arrays;

/**
 * Created by user on 20.02.2018.
 */
public class Storage {
    private long id;
    private File[] files;
    private String[] formatsSupported;
    private String storageCountry;
    private long storageSize;

    public Storage() {
    }

    public Storage(long id, String[] formatsSupported, String storageCountry, long storageSize) {
        this.id = id;
        this.formatsSupported = formatsSupported;
        this.storageCountry = storageCountry;
        this.storageSize = storageSize;
    }

    public Storage(long id, File[] files, String[] formatsSupported, String storageCountry, long storageSize) {
        this.id = id;
        this.files = files;
        this.formatsSupported = formatsSupported;
        this.storageCountry = storageCountry;
        this.storageSize = storageSize;

    }

    public long getId() {
        return id;
    }

    public File[] getFiles() {
        return files;
    }


    public String[] getFormatsSupported() {
        return formatsSupported;
    }
    public String[] getFormatsSupported(String str) {
        String[] format = str.split(",");
        return format;
    }

    public String getFormatsSupportedString() {
        String string = new String("");
        for (String el : this.getFormatsSupported()) {
            string = string + el + ",";
        }
        return string.substring(0, string.length() - 1);
    }

    public String getStorageCountry() {
        return storageCountry;
    }

    public long getStorageSize() {
        return storageSize;
    }

    public void setFiles(File[] files) {
        this.files = files;
    }

    public void setStorageSize(long storageSize) {
        this.storageSize = storageSize;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", files=" + Arrays.toString(files) +
                ", formatsSupported=" + Arrays.toString(formatsSupported) +
                ", storageCountry='" + storageCountry + '\'' +
                ", storageSize=" + storageSize +
                '}';
    }
}
