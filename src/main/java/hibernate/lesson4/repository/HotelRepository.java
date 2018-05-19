package hibernate.lesson4.repository;

import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.model.Hotel;

import java.util.List;

import static hibernate.lesson4.Utils.checkWordOnDigts;
import static hibernate.lesson4.Utils.checkWordOnLetters;

/**
 * Created by user on 30.11.2017.
 */
public class HotelRepository extends GeneralRepository<Hotel> {


    public static final String FIND_HT_BY_ID_HOTEL = "FROM Hotel WHERE ID = :ID ";
    public static final String DELETE_HT_BY_ID_HOTEL = "DELETE FROM Hotel WHERE ID = :ID";

    private final String pathHotelDB = "D:/Ubuntu_backup/dev/HotelDB";

  /*  static {
        setPathDB("D:/Ubuntu_backup/dev/HotelDB");
    }*/

   /* public ArrayList<Hotel> findHotelByName(String name) throws Exception {
        checkHotelName(name);
        ArrayList<Hotel> findHotels = new ArrayList<>();
        ArrayList<Hotel> hotelToArrayList = entityToArrayList();
        for (Hotel ht : hotelToArrayList) {
            if (ht.getHotelName().equals(name)) {
                findHotels.add(ht);
            }
        }
        return findHotels;
    }

    public ArrayList<Hotel> findHotelByCity(String city) throws Exception {
        checkCityOfHotel(city);
        ArrayList<Hotel> hotelFindArrayList = new ArrayList<>();
        ArrayList<Hotel> hotelToArrayList = entityToArrayList();
        for (Hotel ht : hotelToArrayList) {
            if (ht.getCity().equals(city)) {
                hotelFindArrayList.add(ht);
            }
        }
        return hotelFindArrayList;
    }

    @Override
    public Object formEntity(String[] str) throws Exception {
        if (str == null || str.length == 0 || str.length != 5)
            throw new Exception("Error of reading: Incorrect data");
        for (String el : str) {
            if (el == null) {
                throw new BadRequestException("Error of reading: Incorrect data");
            }
        }
        Hotel hotel = new Hotel();
        hotel.setId(Long.parseLong(str[0]));
        hotel.setHotelName(str[1]);
        hotel.setCountry(str[2]);
        hotel.setCity(str[3]);
        hotel.setStreet(str[4]);

        return hotel;

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

    @Override
    public boolean checkLine(String line, int numberLine) throws Exception {
        if (line == null || line.isEmpty())
            throw new BadRequestException("Wrong data in DB " + pathDB + " Line number: " + numberLine);

        String[] str = line.split(",");
        if (str.length != 5)
            throw new BadRequestException("Wrong data in DB " + pathDB + " Line number: " + numberLine);
        for (String el : str) {
            if (el == null || el.isEmpty())
                throw new BadRequestException("Wrong data in DB " + pathDB + " Line number: " + numberLine);
        }
        if (!checkWordOnDigts(str[0]))
            throw new BadRequestException("Wrong data in DB " + pathDB + " Line number: " + numberLine);

        return true;
    }
    public static void validateHotel(Hotel hotel)throws Exception{
        if(hotel == null)
            throw new BadRequestException("Wrong data hotel");
    }
    public  void validateHotel(Long hotelId)throws Exception{
        if(hotelId <= 0)
            throw new BadRequestException("You enter wrong hotelId. Please, try again");
        if(findEntityById(hotelId) == null)
            throw new Exception("Hotel with id " + hotelId + " does not exist in DB Hotel");
    }*/

}
