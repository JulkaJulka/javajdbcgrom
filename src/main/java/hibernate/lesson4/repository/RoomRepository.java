package hibernate.lesson4.repository;

import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.model.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static hibernate.lesson4.Utils.*;


public class RoomRepository extends GeneralRepository {
    public static final String FIND_RM_BY_ID_ROOM = "FROM Room WHERE ID = :ID ";
    public static final String DELETE_RM_BY_ID_ROOM = "DELETE FROM Room WHERE ID = :ID ";
    /*static {
        setPathDB("D:/Ubuntu_backup/dev/RoomDB");
    }*/

    /*public Collection findRooms(Filter filter) throws Exception {
        ArrayList<Room> roomArrayList = entityToArrayList();
        ArrayList<Room> findRooms = new ArrayList<>();
        for (Room rm : roomArrayList) {
            if (conformityFilter(rm, filter)) {
                findRooms.add(rm);
            }
        }
        return findRooms;
    }

    @Override
    public Object formEntity(String[] str) throws Exception {
        if (str == null || str.length == 0 || str.length != 7)
            throw new Exception("Error of reading: Incorrect data");
        for (String el : str) {
            if (el == null) {
                throw new Exception("Error of reading: Incorrect data");
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date dateAvailableFrom = simpleDateFormat.parse(str[5]);

        HotelRepository hotelRepository = new HotelRepository();
        setPathDB(getPathHotelDB());
        Hotel hotel = (Hotel) hotelRepository.findEntityById(Long.parseLong(str[6]));
        if(hotel == null)
            throw new Exception("Hotel with id " + Long.parseLong(str[6]) + " does not exist in DB" );

        setPathDB(getPathRoomDB());
        Room room = new Room(Integer.parseInt(str[1]), Double.parseDouble(str[2]),
                Boolean.parseBoolean(str[3]), Boolean.parseBoolean(str[4]), dateAvailableFrom, hotel);
        room.setId(Long.parseLong(str[0]));

        return room;
    }

    @Override
    public boolean checkLine(String line, int numberLine) throws Exception {
        if (line == null || line.isEmpty())
            throw new BadRequestException("Wrong data in DB " + pathDB + " Line number: " + numberLine);

        String[] str = line.split(",");
        if (str.length != 7)
            throw new BadRequestException("Wrong data in DB " + pathDB + " Line number: " + numberLine);
        for (String el : str) {
            if (el == null || el.isEmpty())
                throw new BadRequestException("Wrong data in DB " + pathDB + " Line number: " + numberLine);
        }
        if (!checkWordOnDigts(str[0]))
            throw new BadRequestException("Wrong data in DB " + pathDB + " Line number: " + numberLine);

        return true;
    }

    public boolean conformityFilter(Room room, Filter filter) {
        if (filter == null)
            return false;
        if (filter.getNumberOfGuests() != 0 && room.getNumberOfGuests() != filter.getNumberOfGuests())
            return false;
        if (filter.getPrice() != 0 && room.getPrice() != filter.getPrice())
            return false;
        if (filter.isBreakfastIncluded() != room.isBreakfastIncluded())
            return false;
        if (filter.isPetsAllowed() != room.isPetsAllowed())
            return false;
        if (filter.getDateAvailableFrom() != null && filter.getDateAvailableFrom().compareTo(room.getDateAvailableFrom()) < 0)
            return false;
        if (room.getHotel() == null || room.getHotel().getCountry() == null || room.getHotel().getCity() == null)
            return false;
        if (filter.getCountry() != null && (room.getHotel() != null && !filter.getCountry().equals(room.getHotel().getCountry())))
            return false;
        if (filter.getCity() != null && (room.getHotel() != null && !filter.getCity().equals(room.getHotel().getCity())))
            return false;
        return true;
    }

    public static void validateRoom(Room room)throws Exception{
        if(room == null)
            throw new BadRequestException("Wrong data room");
    }
    public  void validateRoom(Long id)throws Exception{
        if(id <= 0)
            throw new BadRequestException("You enter wrong roomId. Please, try again");
        if(findEntityById(id) == null)
            throw new Exception("Room with id " + id + " does not exist in DB Room");
    }*/

}
