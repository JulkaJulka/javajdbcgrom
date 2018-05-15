package hibernate.lesson3;


import org.hibernate.HibernateException;

public class HotelDAO extends GeneralDao<Hotel> {

    public static final String FIND_HT_BY_ID_HOTEL = "FROM Hotel WHERE ID = :ID ";
    public static final String DELETE_HT_BY_ID_HOTEL = "DELETE FROM Hotel WHERE ID = :ID";

    public Hotel delete(long id){
        return delete(FIND_HT_BY_ID_HOTEL,DELETE_HT_BY_ID_HOTEL, id);
    }

    public Hotel findById( long id) throws HibernateException {
        return findById(FIND_HT_BY_ID_HOTEL, id);
    }
}
