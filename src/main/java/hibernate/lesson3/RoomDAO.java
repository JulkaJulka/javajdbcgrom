package hibernate.lesson3;

public class RoomDAO extends GeneralDao<Room> {

    public static final String FIND_RM_BY_ID_ROOM = "FROM Room WHERE ID = :ID ";
    public static final String DELETE_RM_BY_ID_ROOM = "DELETE FROM Room WHERE ID = :ID ";


}

