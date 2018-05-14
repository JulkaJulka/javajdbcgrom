package hibernate.lesson3;


public class HotelDAO extends GeneralDao<Hotel> {

    public static final String FIND_HT_BY_ID_HOTEL = "FROM Hotel WHERE ID = :ID ";
    public static final String DELETE_HT_BY_ID_HOTEL = "DELETE FROM Hotel WHERE ID = :ID";

}
