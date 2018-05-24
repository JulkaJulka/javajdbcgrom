package hibernate.lesson4.controller;

import hibernate.lesson4.model.Hotel;
import hibernate.lesson4.model.User;
import hibernate.lesson4.service.HotelService;

import java.util.List;

/**
 * Created by user on 23.05.2018.
 */
public class HotelController {
    HotelService hotelService = new HotelService();

    public HotelService getHotelService() {
        return hotelService;
    }

    public List<Hotel> findHotelByName(String name) throws Exception {
        return hotelService.findHotelByName(name);
    }

    public List<Hotel> findHotelByCity(String city) throws Exception {
        return hotelService.findHotelByCity(city);
    }

    public Hotel addHotel(Hotel hotel, User user) throws Exception {
        return hotelService.addHotel(hotel, user);
    }

    public void deleteHotel(Long hotelId, User user) throws Exception {
        hotelService.deleteHotel(hotelId, user);
    }
}
