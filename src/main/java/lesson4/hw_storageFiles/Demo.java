package lesson4.hw_storageFiles;

import java.sql.ResultSet;

/**
 * Created by user on 22.02.2018.
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        FileDAO fileDAO = new FileDAO();
        File file2 = new File(1846656661860262572l,"Test2","txt", 230l, 122l );
        File file3 = new File(8556355635284043560l,"Test3","xml", 100l, 12l );
        File file4 = new File(1846656661860262572l,"Test2","xml", 100l, 101l );
        File file6 = new File(1l,"Tes27","xml", 100l, 12l );
        File file5 = new File("Test4444","txt", 100l, 12l );
        File[] files = {file2, file3, file4, file5};
        String[] formats = {"txt", "xml"};
        //fileDAO.save(file6);
       // System.out.println(file1.getId());
       // System.out.println(fileDAO.update(file5));
      // System.out.println(fileDAO.findById(184l));
      // System.out.println(fileDAO.save(file5));
       // fileDAO.deleteFile(64888029727l);

        StorageDAO storageDAO = new StorageDAO();
        Storage storage1 = new Storage(12l,null, formats, "UK", 2000l);
        Storage storage2 = new Storage(122l, formats, "Ukraine", 3000l);
        Storage storage3 = new Storage(133l, formats, "Ukraine", 3000l);
      // storageDAO.save(storage3);

       // System.out.println(storageDAO.findById(122l));
       //storageDAO.update(storage2);
       // storageDAO.deleteStorage(133l);
        Controller controller = new Controller();
     //  System.out.println(controller.findById(storage2,8556355635284043560l));
      // System.out.println(controller.put(storage2,file5));
      // controller.delete(storage2, file6);
     System.out.println(controller.transferAll(storage2, storage1));

     // System.out.println(controller.transferFile( storage1,storage2, 6071262062469104214l));

    }
}
