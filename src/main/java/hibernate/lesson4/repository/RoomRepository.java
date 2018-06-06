package hibernate.lesson4.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import hibernate.lesson4.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.criteria.*;
import java.util.*;


public class RoomRepository extends GeneralRepository<Room> {
    public static final String FIND_RM_BY_ID_ROOM = "FROM Room WHERE ID = :ID ";
    public static final String DELETE_RM_BY_ID_ROOM = "DELETE FROM Room WHERE ID = :ID ";

    public Room findById(Long id) {
        return findById(FIND_RM_BY_ID_ROOM, id);
    }

    public void delete(long id) {
        delete(DELETE_RM_BY_ID_ROOM, id);
    }

    public List<Room> findRooms(Filter f) {
        try (Session session = createSessionFactory().openSession()) {

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> filterParams = objectMapper.convertValue(f, Map.class);

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Room> roomCriteria = criteriaBuilder.createQuery(Room.class);

            Root<Room> roomRoot = roomCriteria.from(Room.class);

            Predicate predicate = criteriaBuilder.conjunction();

            for (String param : filterParams.keySet()) {
                if (filterParams.get(param) != null) {

                    if (param.equals("country") || param.equals("city")) {
                        predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(
                                roomRoot.get("hotel").get(param), filterParams.get(param)));
                    } else {
                        predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(
                                roomRoot.get(param), filterParams.get(param)));
                    }
                }
            }

            roomCriteria.select(roomRoot).where(predicate);

            return session.createQuery(roomCriteria).getResultList();

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            throw new HibernateException("Something went wrong");

        }
    }

    /*public String hqlFindRmsByFilter(Filter f){
        String findFilter = "FROM Room WHERE ";
        if (f.getNumberOfGuests() != 0) {
            findFilter = findFilter + "numberOfGuests = " + f.getNumberOfGuests() + ",";
        }
        if (f.getPrice() != 0) {
            findFilter = findFilter + " price = " + f.getPrice() + ",";
        }
        if (f.getDateAvailableFrom() != null) {
            findFilter = findFilter + "dateAvailableFrom  = " + f.getDateAvailableFrom() + ",";
        }
        if (f.getCountry() != null) {
            findFilter = findFilter + "hotel.country = '" + f.getCountry() + "',";

        }
        if (f.getCity() != null) {
            findFilter = findFilter + "hotel.city = '" + f.getCity() + "',";
        }

        findFilter = findFilter + "breakfastIncluded = " + f.getBreakfastIncluded() +
                " AND petsAllowed = " + f.getPetsAllowed();

        String sqlRmThrowFilter = findFilter.replace(",", " AND ");

        return sqlRmThrowFilter;
    }*/
}
