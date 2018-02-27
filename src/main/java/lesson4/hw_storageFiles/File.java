package lesson4.hw_storageFiles;

import java.util.Random;

/**
 * Created by user on 20.02.2018.
 */
public class File {
    private long id;
    private String name;
    private String format;
    private long size;
    private long storageId;

    public File() {
    }

    public File(String name, String format, long size, long storageId) {
        Random rm = new Random();
        this.id = Math.abs(rm.nextLong());
        this.name = name;
        this.format = format;
        this.size = size;
        this.storageId = storageId;
    }

    public File(long id, String name, String format, long size) {
        this.id = id;
        this.name = name;
        this.format = format;
        this.size = size;
    }

    public File(long id, String name, String format, long size, long storageId) {
        this.id = id;
        this.name = name;
        this.format = format;
        this.size = size;
        this.storageId = storageId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFormat() {
        return format;
    }

    public long getSize() {
        return size;
    }

    public long getStorageId() {
        return storageId;
    }

    public void setStorageId(long storageId) {
        this.storageId = storageId;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", format='" + format + '\'' +
                ", size=" + size +
                ", storageId=" + storageId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        File file = (File) o;

        if (id != file.id) return false;
        return name != null ? name.equals(file.name) : file.name == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
