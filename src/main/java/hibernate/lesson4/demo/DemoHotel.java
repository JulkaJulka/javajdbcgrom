package hibernate.lesson4.demo;

import hibernate.lesson4.controller.HotelController;
import hibernate.lesson4.model.Hotel;
import hibernate.lesson4.model.Room;
import hibernate.lesson4.model.User;
import hibernate.lesson4.model.UserType;
import hibernate.lesson4.repository.HotelRepository;
import hibernate.lesson4.service.HotelService;

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
        Hotel hotel1 = new Hotel();
      //  hotel1.setId(261l);
        hotel1.setName(":::<<>");
        hotel1.setCountry("Country");
        hotel1.setCity("Lviv");
        hotel1.setStreet("Crntal");
       // System.out.println(hotel1.getRooms());
        List rooms = new LinkedList<>();

       // System.out.println( hotelRepository.findById(123l));
      // System.out.println(hotelRepository.findHotelByName("fffffffffffffffffff"));
      //  Hotel hotel3 = hotelRepository.findById(123l);
        //System.out.println(hotel3.getRooms());
        User user = new User();
        user.setUserType(UserType.ADMIN);
      // System.out.println(hotelRepository.findHotelByCity("Lviv"));
        HotelService hotelService = new HotelService();
       // hotelService.deleteHotel(263l,user);
        HotelController hotelController = new HotelController();
      // System.out.println(hotelController.addHotel(hotel1,user));
       // System.out.println(hotelRepository.findById(261l).getRooms().toString());
        System.out.println();
        System.out.println();
       // List<Hotel> hotels = hotelRepository.findHotelByName("Rixos");
       // for(Hotel ht : hotels){
       //     System.out.println(ht.getRooms().toString());
       // }

       // System.out.println(hotelRepository.findHotelByStrDescrip("COUNTRY","Country"));
        System.out.println(hotelRepository.findHotelByName("Schac"));
        System.out.println(hotelRepository.findHotelByCity("Kyiv"));

    }
}
