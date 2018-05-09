package hibernate.lesson3;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


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
}
