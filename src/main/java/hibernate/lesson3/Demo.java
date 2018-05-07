package hibernate.lesson3;

import lesson4.hw_storageFiles.FileDAO;

import java.util.Date;

/**
 * Created by user on 26.04.2018.
 */
public class Demo {
    public static void main(String[] args) {
       RoomDAO roomDAO = new RoomDAO();
        HotelDAO hotelDAO = new HotelDAO();

        Hotel hotel1 = new Hotel(81l,"Rixosh", "Ukraine", "Antalia","Central 30");
        Hotel hotel2 = new Hotel("Lebid", "Ukraine", "Antalia","Central 30");
       // hotel1.setId(11);
      //  System.out.println(hotel1);
       // System.out.println(hotelDAO.save(hotel1));
        Room room1 = new Room(81l,2,55.0,0,1,new Date(),hotel1);
       // System.out.println(room1);
     //  System.out.println(roomDAO.delete(51l));
      //  System.out.println(roomDAO.update(room1));
       // System.out.println(hotelDAO.save(hotel2));
      //  System.out.println(hotelDAO.findById(93l));
        //System.out.println(hotelDAO.findById(91l));
       // System.out.println(hotelDAO.update(hotel1));
        System.out.println(hotelDAO.delete(93l));
      //  System.out.println(roomDAO.delete(7));
       // System.out.println(roomDAO.delete(9));
       // System.out.println(roomDAO.delete(11));
       // System.out.println(roomDAO.delete(1));
       // System.out.println(roomDAO.findById(11l));
      //  System.out.println();

    }
}
