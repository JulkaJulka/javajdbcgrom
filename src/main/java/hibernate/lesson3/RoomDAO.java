package hibernate.lesson3;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class RoomDAO extends GeneralDao<Room> {

    public static final String FIND_RM_BY_ID_ROOM = "FROM Room WHERE ID = :ID ";
    public static final String FIND_RMS_BY_HTID_HQL = "FROM Room WHERE HOTEL_ID = :HOTEL";
    public static final String DELETE_BY_RMID_HQL = "DELETE FROM Room WHERE ID = :ID";

    public Room delete(long id) throws HibernateException {

        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            Room deleteEntity = findById(FIND_RM_BY_ID_ROOM, id);
            if (deleteEntity == null)
                return null;
            Query query = session.createQuery(DELETE_BY_RMID_HQL);
            query.setParameter("ID", id);
            query.executeUpdate();

            tr.commit();

           return deleteEntity;

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            if (tr != null)
                tr.rollback();
            throw new HibernateException("Delete is failed");
        }
    }
}

