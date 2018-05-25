package hibernate.lesson4.repository;

import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.text.SimpleDateFormat;
import java.util.*;

import static hibernate.lesson4.Utils.*;


public class RoomRepository extends GeneralRepository<Room> {
    public static final String FIND_RM_BY_ID_ROOM = "FROM Room WHERE ID = :ID ";
    public static final String DELETE_RM_BY_ID_ROOM = "DELETE FROM Room WHERE ID = :ID ";
    public static final String SELECT_ALL_RMS = "FROM Room";

    public Room findById(Long id) {
        return findById(FIND_RM_BY_ID_ROOM, id);
    }

    public void delete(long id) {
        delete(DELETE_RM_BY_ID_ROOM, id);
    }

    public List<Room> findRooms(Filter filter) {
        try (Session session = createSessionFactory().openSession()) {

            Query query = session.createQuery(SELECT_ALL_RMS);
            List<Room> rms = query.list();
            List<Room> findRms = new LinkedList<>();
            if(rms.size() == 0)
                return rms;

            for (Room rm : rms){
                if(conformityFilter(rm, filter)){
                    findRms.add(rm);
                }
            }

            return findRms;

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            throw new HibernateException("Something went wrong");

        }
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
