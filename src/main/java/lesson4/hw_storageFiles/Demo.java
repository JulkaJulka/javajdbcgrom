package lesson4.hw_storageFiles;

import java.sql.ResultSet;

/**
 * Created by user on 22.02.2018.
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        FileDAO fileDAO = new FileDAO();
        File file2 = new File(1846656661860262572l,"Test2","txt", 230l, 122l );
        File file3 = new File(8073459684986963155l,"Test26","txt", 111l, 0l );
        File file4 = new File(1846656661860262572l,"Test2","xml", 100l, 101l );
        File file6 = new File(5442761823368985217l,"Test4444","xml", 120l, 0l );
        File file7 = new File(5926747254620248688l,"Transfer5","xml",10,0l);
        File file5 = new File("Terrr","txt", 100l, 0l );
        File[] files = {file2, file3, file4, file5};
        String[] formats = {"txt", "xml"};
     //   fileDAO.save(file6);
       // System.out.println(file1.getId());
     //  System.out.println(fileDAO.update(file3));
     //System.out.println((fileDAO.findById(8073459684986963718l)).getClass());
   //    System.out.println(fileDAO.save(file5));
      // fileDAO.delete(6825953426171707583l);
       // fileDAO.insertObjectToDB(file2);
        //System.out.println((file7));
        StorageDAO storageDAO = new StorageDAO();
        Storage storage1 = new Storage(12l,null, formats, "UK", 2000l);
        Storage storage2 = new Storage(122l, formats, "Ukraine", 3000l);
        Storage storage3 = new Storage(144l, formats, "Ukraine", 3000l);
        Storage storage13 = new Storage(13l,null, formats, "UK", 2000l);
        Storage storage14 = new Storage(14l, formats, "Ukraine", 3000l);
      // storageDAO.save(storage3);
//storageDAO.delete(144l);
     //  System.out.println(storageDAO.findById(122l));
      //  System.out.println( storageDAO.update(storage2));
       // storageDAO.deleteStorage(133l);
        Controller controller = new Controller();
     //  System.out.println(fileDAO.save(file7));
   //  System.out.println(controller.put(storage2,file6));
       //System.out.println(controller.findById(storage2,8556355635284043560l));
   //  System.out.println(controller.transferFile(storage2, storage1,950097981352981114l));

      //controller.delete(storage1, file3);
     //System.out.println(controller.transferAll(storage2, storage1));

     // System.out.println(controller.transferFile( storage1,storage2, 6443883591460032763l));
        System.out.println(controller.transferAll(storage13,storage2));
    }
}
