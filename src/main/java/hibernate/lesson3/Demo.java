package hibernate.lesson3;

import java.util.Date;

public class Demo {
    public static void main(String[] args) throws Exception {
     //  RoomDAO roomDAO = new RoomDAO();
        HotelDAO hotelDAO = new HotelDAO();

        Hotel hotel1 = new Hotel("Rixosh", "Ukraine", "Antalia","Central 30");
        Hotel hotel2 = new Hotel("saveGeneralLebidddU", "Ukraine", "Antalia","Central 50");
       hotel2.setId(97l);
     //  System.out.println(hotelDAO.findById(89l));
       // hotel1.setId(11);
      //  System.out.println(hotel1);
       System.out.println(hotelDAO.delete(97l));
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
       //System.out.println(roomDAO.save(room1));
    //  System.out.println(roomDAO.findById(FIND_RM_BY_ID_ROOM,23l));
       // System.out.println(roomDAO.update(room1));
       // System.out.println(hotelDAO.save(hotel2));
     //   System.out.println(hotelDAO.findById(95l));
        //System.out.println(hotelDAO.findById(91l));
       // System.out.println(hotelDAO.update(hotel1));
     // System.out.println(roomDAO.delete2(145l));
      //  System.out.println(roomDAO.delete(7));
       // System.out.println(roomDAO.delete(9));
       // System.out.println(roomDAO.delete(11));
       // System.out.println(roomDAO.delete(1));
       // System.out.println(roomDAO.findById(11l));
      //  System.out.println();

    }
}
