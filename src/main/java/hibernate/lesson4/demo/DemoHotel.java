package hibernate.lesson4.demo;

import hibernate.lesson4.model.Hotel;
import hibernate.lesson4.model.Room;
import hibernate.lesson4.repository.HotelRepository;
import static hibernate.lesson4.repository.HotelRepository.FIND_HT_BY_ID_HOTEL;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by user on 30.11.2017.
 */
public class DemoHotel {
    public static void main(String[] args) throws Exception {
        HotelRepository hotelRepository = new HotelRepository();
        //System.out.println(hotelRepository.findEntityById(HotelRepository.FIND_HT_BY_ID_HOTEL,83l));
        //System.out.println( hotelRepository.findHotelByName("Riviera"));
       // System.out.println("-----");
        //System.out.println( hotelRepository.findHotelByCity("Kiev"));
       // Set<Order> orders = new HashSet<Order>();
       // User user1 = new User("Oleg", "poiuyt", "Ukraine", UserType.USER, true, orders);
      // user1.setUserType(UserType.ADMIN);
        Hotel hotel1 = new Hotel("Riviera", "Belarus", "Minsk2", "Test2");
        List rooms = new LinkedList<>();
        Hotel hotel3 = new Hotel( "jkl","FFFFFF", "Thailand", "Phuket",rooms);
        System.out.println(hotelRepository.save(hotel3));

    }
}
