package hibernate.lesson3;

import org.hibernate.HibernateException;

public class RoomDAO extends GeneralDao<Room> {

    public static final String FIND_RM_BY_ID_ROOM = "FROM Room WHERE ID = :ID ";
    public static final String DELETE_RM_BY_ID_ROOM = "DELETE FROM Room WHERE ID = :ID ";

    public Room delete(long id){
                 return delete(DELETE_RM_BY_ID_ROOM, id);
    }

    public Room findById( long id) throws HibernateException {
        return findById(FIND_RM_BY_ID_ROOM, id);
    }

}

