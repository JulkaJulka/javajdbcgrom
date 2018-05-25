package hibernate.lesson4.repository;

import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static hibernate.lesson4.Utils.*;

/**
 * Created by user on 30.11.2017.
 */
public class OrderRepository extends GeneralRepository<Order> {

    private RoomRepository roomRepository = new RoomRepository();
    private UserRepository userRepository = new UserRepository();
    private HotelRepository hotelRepository = new HotelRepository();

    public static final String FIND_OR_BY_ID_OR = "FROM Order WHERE ID = :ID ";
    public static final String DELETE_OR_BY_ID_OR = "DELETE FROM Order WHERE ID = :ID ";
    public static final String DELETE_OR_BY_USRM_OR = "DELETE FROM Order WHERE ID = :ID ";
    public static final String FIND_OR_BY_USRM_OR = "SELECT o FROM Order o JOIN FETCH o.user u JOIN FETCH o.room r " +
            " WHERE u.id = :idUs AND r.id = :idRm";

    public Order findById(Long id) {
        return findById(FIND_OR_BY_ID_OR, id);
    }

    public void delete(long id) {
        delete(DELETE_OR_BY_ID_OR, id);
    }

    public void bookRoom(long roomId, long userId, long hotelId) throws Exception {

        try (Session session = createSessionFactory().openSession()) {

            User user = userRepository.findById(userId);
            Hotel hotel = hotelRepository.findById(hotelId);
            Room room = roomRepository.findById(roomId);

            Order order = new Order();

            order.setRoom(room);
            order.setUser(user);
            order.setDateFrom(new Date());
            order.setDateTo(new Date());
            order.setMoneyPaid(1000d);

            save(order);

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            throw new HibernateException("Something went wrong");

        }
    }

    public void cancelReservation(long roomId, long userId) throws Exception {
        try (Session session = createSessionFactory().openSession()) {

            Query<Order> query = session.createQuery(FIND_OR_BY_USRM_OR);
            query.setParameter("idUs", userId);
            query.setParameter("idRm", roomId);
            Order order = query.getSingleResult();

            delete(order.getId());

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            throw new HibernateException("Something went wrong");

        }
    }
}
