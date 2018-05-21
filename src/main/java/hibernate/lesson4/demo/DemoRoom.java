package hibernate.lesson4.demo;

import hibernate.lesson4.model.*;
import hibernate.lesson4.repository.RoomRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by user on 30.11.2017.
 */
public class DemoRoom {
    public static void main(String[] args) throws Exception {
        RoomRepository roomRepository = new RoomRepository();
       // System.out.println(roomRepository.readFile());
       // RoomRepository roomRepository = new RoomRepository();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateStr = new String("25-09-2017");
        String dateFiltr = new String("25-10-2017");
        Date date = simpleDateFormat.parse(dateStr);
        Date dateF = simpleDateFormat.parse(dateFiltr);
       // System.out.println(date);
       // User user1 = new User("Julia", "qwerty", "Ukraine", UserType.ADMIN);
      // user1.setUserType(UserType.ADMIN);
        Hotel hotel1 = new Hotel( "Schastie", "Ukraine", "Lviv", "Test");
        Hotel hotel2 = new Hotel( "Schastie", "Ukraine", "Lviv", "Test");
        hotel2.setId(221l);
        Room room1 = new Room( 2, 1800.00d, true, false, date, null);
        room1.setId(102l);
        Room room3 = new Room( 2, 1800d, true, false, date, hotel2);
        Room room2 = new Room( 2, 1800.00, true, false, dateF, hotel2);
        Room room4 = new Room( -12, 1800.00, true, false, dateF, hotel2);
       // room4.setId(-78l);
        Filter filter = new Filter(2, 1000d, true, false, dateF, "Ukraine", "Rovno");
        System.out.println(roomRepository.findEntityById(RoomRepository.FIND_RM_BY_ID_ROOM,102l));
      //  RoomService roomService = new RoomService();
     //   roomService.deleteRoom(333l,user1);
    //  roomRepository.deleteEntity(6844416740395250473l);
       //roomRepository.addEntity(room1);

     // roomRepository.deleteRoom(room3,user1);
       // System.out.println( roomRepository.findHotelById(333l));
      //  System.out.println(roomRepository.roomToArrayList("D:/Ubuntu_backup/dev/RoomDB.txt"));
   //  System.out.println(roomRepository.findEntityById(3263465472588390554l));
      //  GeneralRepository<Room> generalRepository = new GeneralRepository<>();
      //  generalRepository.deleteEntity(room4,user1);
    //  roomRepository.addEntity(room4);
     //   System.out.println(roomRepository.findRoomById(3263465472588390554l));

      // System.out.println(roomRepository.entityToArrayList());
      //  System.out.println(generalRepository.entityToArrayList());
      // System.out.println(roomRepository.findRooms(filter));
//roomRepository.deleteEntity(4685240930022847027l,user1);
     /*   RoomController roomController = new RoomController();
        System.out.println("-----");
  //      System.out.println(roomController.addRoom(room3,user1));
        System.out.println("----");

      //  roomController.deleteRoom(1043920767642118223l,user1);
        System.out.println(roomRepository.readFile());
        System.out.println("-----");*/
      //  System.out.println(roomRepository.entityToArrayList());
    }

}
