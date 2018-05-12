package hibernate.lesson3;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

import static hibernate.lesson3.RoomDAO.DELETE_BY_RMID_HQL;
import static hibernate.lesson3.RoomDAO.FIND_RMS_BY_HTID_HQL;


public class HotelDAO extends GeneralDao<Hotel> {

    public static final String FIND_HT_BY_ID_HOTEL = "FROM Hotel WHERE ID = :ID ";
    public static final String DELETE_HT_BY_HTID_HQL = "DELETE FROM Hotel WHERE ID = :ID";

    public Hotel delete(long id) throws HibernateException {

        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            Hotel deleteEntity = findById(FIND_HT_BY_ID_HOTEL, id);

            if (deleteEntity == null)
                return null;

            List<Room> deleteRms = findRmsByHotelId(id);
            if (deleteRms.size() != 0) {

                for (Room rm : deleteRms) {
                    Query queryDelRms = session.createQuery(DELETE_BY_RMID_HQL);
                    queryDelRms.setParameter("ID", rm.getId());
                    queryDelRms.executeUpdate();
                }
            }
            Query queryDelHt = session.createQuery(DELETE_HT_BY_HTID_HQL);
            queryDelHt.setParameter("ID", id);
            queryDelHt.executeUpdate();

            tr.commit();

            return deleteEntity;

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            if (tr != null)
                tr.rollback();
            throw new HibernateException("Delete is failed");
        }
    }

    public List<Room> findRmsByHotelId(Long hotelId) throws HibernateException {
        try (Session session = createSessionFactory().openSession()) {

            Query query = session.createQuery(FIND_RMS_BY_HTID_HQL);
            Hotel hotel = new Hotel();
            hotel.setId(hotelId);
            query.setParameter("HOTEL", hotel);
            List<Room> entities = query.getResultList();
            if (entities == null)
                return null;

            return entities;

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            throw new HibernateException("Something went wrong");
        }
    }
}
