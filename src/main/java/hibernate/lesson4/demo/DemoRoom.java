package hibernate.lesson4.demo;

import hibernate.lesson4.model.*;
import hibernate.lesson4.repository.RoomRepository;

import java.util.Date;

/**
 * Created by user on 30.11.2017.
 */
public class DemoRoom {
    public static void main(String[] args) throws Exception {
        RoomRepository roomRepository = new RoomRepository();
       // System.out.println(roomRepository.readFile());
       // RoomRepository roomRepository = new RoomRepository();

        Hotel hotel1 = new Hotel();
        hotel1.setName("Schac");
        hotel1.setCountry("Country");
        hotel1.setCity("Lviv");
        hotel1.setStreet("Crntal");
       Hotel hotel2 = new Hotel();
       hotel2.setId(123l);
        Room room1 = new Room( );
        room1.setId(265l);
        room1.setNumberOfGuests(2);
        room1.setBreakfastIncluded(false);
        room1.setDateAvailableFrom(new Date());
        room1.setPetsAllowed(false);
        room1.setHotel(hotel2);
        room1.setPrice(899d);
        //Room room3 = new Room( 2, 1800d, true, false, new Date(), hotel2);

       // room4.setId(-78l);


       // Filter filter = new Filter(2, 1000d, true, false, dateF, "Ukraine", "Rovno");
       // System.out.println(roomRepository.findEntityById(RoomRepository.FIND_RM_BY_ID_ROOM,102l));
       // System.out.println(roomRepository.save(room1));
        RoomRepository roomRepository1 = new RoomRepository();
       // System.out.println(roomRepository.findById(267l));
       // Filter filter = new Filter(0,0d,false,false,null,null,"Kyiv");
        Filter filter1 = new Filter();
        filter1.setPrice(899d);
        filter1.setCity("jklhbb");
        //filter1.setPrice(0d);
       // filter1.setBreakfastIncluded(true);
       // System.out.println(filter.sqlSelect());
       // System.out.println(filter.toString2());
       // filter.toString();
      //  System.out.println(roomRepository.findRooms(filter));
       // System.out.println(roomRepository.findRooms2(filter));
        //System.out.println(roomRepository.findRooms(filter));
       //System.out.println(filter1.sqlSelect());
       // System.out.println(filter1.toString());
        System.out.println(roomRepository.findRooms(filter1));
       // System.out.println(filter1.toString2());
      //  System.out.println(filter.toString2());
    }

}
