package hibernate.lesson3;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


public class HotelDAO extends GeneralDao<Hotel> {

    static {
        setFindByIdHql(FIND_BY_ID_HOTEL);
    }

    @Override
    public Hotel save(Hotel hotel) throws Exception {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            Query query = session.createQuery(FIND_EQUALS_HOTEL);

            query.setParameter("NAME", hotel.getName());
            query.setParameter("COUNTRY", hotel.getCountry());
            query.setParameter("CITY", hotel.getCity());
            query.setParameter("STREET", hotel.getStreet());

            if (query.uniqueResult() != null)
                return null;

            session.save(hotel);
            tr.commit();

            return hotel;

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            if (tr != null)
                tr.rollback();
            throw new HibernateException("Save is failed");
        }

    }

    public Hotel delete(long id) throws HibernateException {

        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            Hotel deleteEntity = findById(id);

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
