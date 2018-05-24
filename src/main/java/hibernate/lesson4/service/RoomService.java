package hibernate.lesson4.service;

import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.model.Filter;
import hibernate.lesson4.model.Room;
import hibernate.lesson4.model.User;
import hibernate.lesson4.model.UserType;
import hibernate.lesson4.repository.RoomRepository;

import java.util.List;

/**
 * Created by user on 23.05.2018.
 */
public class RoomService {

    private RoomRepository roomRepository = new RoomRepository();

    public Room addRoom(Room room, User user) throws Exception {
        validateRoom(room);
        if (user.getUserType() != UserType.ADMIN)
            throw new Exception("You have not rights to add room");
        return roomRepository.save(room);
    }

    public void deleteRoom(long roomId, User user) throws Exception {
        validateRoom(roomId);
        if (user.getUserType() != UserType.ADMIN)
            throw new Exception("You have not rights to delete room");
        roomRepository.delete(roomId);
    }

    public static void validateRoom(Room room)throws Exception{
        if(room == null)
            throw new BadRequestException("Wrong data room");
    }
    public  void validateRoom(long id)throws Exception{
        if(id <= 0)
            throw new BadRequestException("You enter wrong roomId. Please, try again");
        if(roomRepository.findById(id) == null)
            throw new Exception("Room with id " + id + " does not exist in DB Room");
    }

    public boolean conformityFilter(Room room, Filter filter) {
        if (filter == null)
            return false;
        if (filter.getNumberOfGuests() != 0 && room.getNumberOfGuests() != filter.getNumberOfGuests())
            return false;
        if (filter.getPrice() != 0 && room.getPrice() != filter.getPrice())
            return false;
        if (filter.isBreakfastIncluded() != room.getBreakfastIncluded())
            return false;
        if (filter.isPetsAllowed() != room.getPetsAllowed())
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
}
