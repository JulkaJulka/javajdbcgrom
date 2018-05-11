package hibernate.lesson3;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class RoomDAO extends GeneralDao<Room> {

    static {
        setFindByIdHql(FIND_BY_ID_ROOM);
    }

    @Override
    public Room save(Room room) throws Exception {
        if (room.getHotel() == null)
            throw new Exception("Room with ID " + room.getId() + " have to have Hotel");

        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            HotelDAO hotelDAO = new HotelDAO();
            setFindByIdHql(FIND_BY_ID_HOTEL);

            if (hotelDAO.findById(room.getHotel().getId()) == null){
                return null;}

            session.save(room);

            tr.commit();
            return room;

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            if (tr != null)
                tr.rollback();
            throw new HibernateException("Save is failed");
        }
    }

    public Room delete(long id) throws HibernateException {

        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            Room deleteEntity = findById(id);
            if(deleteEntity == null)
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

