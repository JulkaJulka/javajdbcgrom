package hibernate.lesson4.repository;

import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.model.Hotel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

import static hibernate.lesson4.Utils.checkWordOnDigts;
import static hibernate.lesson4.Utils.checkWordOnLetters;

/**
 * Created by user on 30.11.2017.
 */
public class HotelRepository extends GeneralRepository<Hotel> {


    public static final String FIND_HT_BY_ID_HOTEL = "FROM Hotel WHERE ID = :ID ";
    public static final String FIND_HTS_BY_NAME_HOTEL = "FROM Hotel WHERE NAME = :NAME ";
    public static final String FIND_HTS_BY_CITY_HOTEL = "FROM Hotel WHERE CITY = :CITY ";
    public static final String DELETE_HT_BY_ID_HOTEL = "DELETE FROM Hotel WHERE ID = :ID";


    public Hotel findById( Long id) {
        return findById(FIND_HT_BY_ID_HOTEL, id);
    }

    public void delete(long id) {
         delete(DELETE_HT_BY_ID_HOTEL, id);
    }

    public List<Hotel> findHotelByName(String name){
        try (Session session = createSessionFactory().openSession()) {

            Query query = session.createQuery(FIND_HTS_BY_NAME_HOTEL);
            query.setParameter("NAME", name);

            List<Hotel> findHtls =  query.list();

            return findHtls;

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            throw new HibernateException("Something went wrong");
        }
    }

    public List<Hotel> findHotelByCity(String name){
        try (Session session = createSessionFactory().openSession()) {

            Query query = session.createQuery(FIND_HTS_BY_CITY_HOTEL);
            query.setParameter("CITY", name);

             if (query.list() == null)
                return null;
            List<Hotel> findHtls =  query.list();

            return findHtls;

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            throw new HibernateException("Something went wrong");
        }
    }
    }



