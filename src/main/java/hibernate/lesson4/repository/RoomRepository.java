package hibernate.lesson4.repository;

import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.model.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static hibernate.lesson4.Utils.*;


public class RoomRepository extends GeneralRepository<Room> {
    public static final String FIND_RM_BY_ID_ROOM = "FROM Room WHERE ID = :ID ";
    public static final String DELETE_RM_BY_ID_ROOM = "DELETE FROM Room WHERE ID = :ID ";

    public Room findById(Long id) {
        return findById(FIND_RM_BY_ID_ROOM, id);
    }

    public void delete(long id) {
         delete( DELETE_RM_BY_ID_ROOM, id);
    }
}
