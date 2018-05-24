package hibernate.lesson4.service;

import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.model.Hotel;
import hibernate.lesson4.model.User;
import hibernate.lesson4.model.UserType;
import hibernate.lesson4.repository.HotelRepository;

import java.util.List;

import static hibernate.lesson4.Utils.checkWordOnLetters;

/**
 * Created by user on 23.05.2018.
 */
public class HotelService {
    private HotelRepository hotelRepository = new HotelRepository();

    public List<Hotel> findHotelByName(String name) throws Exception {
        checkWordOnLetters(name);
        return hotelRepository.findHotelByName(name);
    }

    public List<Hotel> findHotelByCity(String city) throws Exception {
        checkWordOnLetters(city);
        return hotelRepository.findHotelByCity(city);
    }

    public Hotel addHotel(Hotel hotel, User user) throws Exception {
        validateHotel(hotel);
        if (user.getUserType() != UserType.ADMIN)
            throw new Exception("You have not rights to add hotel");
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(Long hotelId, User user) throws Exception {
        validateHotel(hotelId);
        if (user.getUserType() != UserType.ADMIN)
            throw new Exception("You have not rights to delete hotel");
        hotelRepository.delete(hotelId);
    }

    private boolean checkHotelName(String name) throws Exception {
        if (name == null || name.isEmpty())
            throw new Exception("Enter name of Hotel");
        if (!checkWordOnLetters(name))
            throw new Exception("Name of hotel " + name + " is wrong. Try again, please");
        return true;
    }

    private boolean checkCityOfHotel(String city) throws Exception {
        if (city == null || city.isEmpty())
            throw new Exception("Enter city of Hotel");
        if (!checkWordOnLetters(city))
            throw new Exception("City " + city + " is wrong. Try again, please");
        return true;
    }

    public static void validateHotel(Hotel hotel)throws Exception{
        if(hotel == null)
            throw new BadRequestException("Wrong data hotel");
    }
    public  void validateHotel(Long hotelId)throws Exception{
        if(hotelId <= 0)
            throw new BadRequestException("You enter wrong hotelId. Please, try again");
        if(hotelRepository.findById(hotelId) == null)
            throw new Exception("Hotel with id " + hotelId + " does not exist in DB Hotel");
    }

}
