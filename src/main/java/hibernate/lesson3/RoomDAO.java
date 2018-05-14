package hibernate.lesson3;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class RoomDAO extends GeneralDao<Room> {

    public static final String FIND_RM_BY_ID_ROOM = "FROM Room WHERE ID = :ID ";
    public static final String FIND_RMS_BY_HTID_HQL = "FROM Room WHERE HOTEL_ID = :HOTEL";
    public static final String DELETE_BY_RMID_HQL = "DELETE FROM Room WHERE ID = :ID";

}

