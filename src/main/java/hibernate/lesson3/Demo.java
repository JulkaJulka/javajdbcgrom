package hibernate.lesson3;

import java.util.Date;

import static hibernate.lesson3.RoomDAO.DELETE_RM_BY_ID_ROOM;

public class Demo {
    public static void main(String[] args) throws Exception {
     //  RoomDAO roomDAO = new RoomDAO();
        HotelDAO hotelDAO = new HotelDAO();

        Hotel hotel1 = new Hotel("Rixosh", "Ukraine", "Antalia","Central 30");
        Hotel hotel2 = new Hotel("saveGeneralLebidddU", "Ukraine", "Antalia","Central 50");
       hotel2.setId(181l);
     //  System.out.println(hotelDAO.findById(89l));
       // hotel1.setId(11);
      //  System.out.println(hotel1);
      // System.out.println(hotelDAO.delete(hotelDAO.FIND_HT_BY_ID_HOTEL,105l));
     //  System.out.println(hotelDAO.findById(FIND_HT_BY_ID_HOTEL,97l));
    //    System.out.println(hotelDAO.save(hotel2));
      //  System.out.println(hotelDAO.findByIdRms(95l));
       // HotelDAO hotelDAO1 = new HotelDAO();
      // System.out.println(hotelDAO.save(hotel2));
        Room room1 = new Room(2,2000.0,0,1,new Date(),hotel2);
        room1.setId(103l);
       // System.out.println(room1);
        RoomDAO roomDAO = new RoomDAO();
        //System. x out.println(hotelDAO.findRmsByHotelId(100l));
       // System.out.println(hotelDAO.findById(95l));
     // System.out.println(roomDAO.save(room1));
  //   System.out.println(roomDAO.findById(187l));
       // System.out.println(roomDAO.update(room);
   //     System.out.println(hotelDAO.save(hotel2));
     //  System.out.println(hotelDAO.findById(95l));
        //System.out.println(hotelDAO.findById(91l));
       // System.out.println(hotelDAO.update(hotel1));
   //  System.out.println(roomDAO.delete(25l));
       //System.out.println(roomDAO.delete(191l));
       // System.out.println(roomDAO.delete(9));
       // System.out.println(roomDAO.delete(11));
       // System.out.println(roomDAO.delete(1));
       // System.out.println(roomDAO.findById(11l));
      //  System.out.println();

    }
}
