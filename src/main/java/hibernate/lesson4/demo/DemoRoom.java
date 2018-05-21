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

        Hotel hotel1 = new Hotel( "Schastie", "Ukraine", "Lviv", "Test");
        Hotel hotel2 = new Hotel( "Schastie", "Ukraine", "Lviv", "Test");
        hotel2.setId(123l);
        Room room1 = new Room( 2, 1800.00d, true, false, new Date(), hotel2);
       // room1.setId(102l);
        Room room3 = new Room( 2, 1800d, true, false, new Date(), hotel2);

       // room4.setId(-78l);
       // Filter filter = new Filter(2, 1000d, true, false, dateF, "Ukraine", "Rovno");
       // System.out.println(roomRepository.findEntityById(RoomRepository.FIND_RM_BY_ID_ROOM,102l));
        System.out.println(roomRepository.save(room1));

    }

}
