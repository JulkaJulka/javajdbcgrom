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
        hotel1.setName("Schac");
        hotel1.setCountry("Country");
        hotel1.setCity("Lviv");
        hotel1.setStreet("Crntal");
        List rooms = new LinkedList<>();
       // System.out.println(hotelRepository.save(hotel1));
        System.out.println( hotelRepository.findById(123l));
       //System.out.println(hotelRepository.findHotelByName("Schac"));
        User user = new User();
        user.setUserType(UserType.ADMIN);
       // System.out.println(hotelRepository.findHotelByCity("Lviv"));
        HotelService hotelService = new HotelService();
       // hotelService.deleteHotel(263l,user);
        HotelController hotelController = new HotelController();
       // System.out.println(hotelController.addHotel(hotel1,user));
        System.out.println(hotelRepository.findById(265l));

    }
}
