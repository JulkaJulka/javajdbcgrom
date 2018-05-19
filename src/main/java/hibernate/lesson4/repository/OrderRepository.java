package hibernate.lesson4.repository;

import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.model.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static hibernate.lesson4.Utils.*;

/**
 * Created by user on 30.11.2017.
 */
public class OrderRepository extends GeneralRepository {

   /* static {
        setPathDB("D:/Ubuntu_backup/dev/OrderDB.txt");
    }*/

    /*public void bookRoom(long roomId, long userId, long hotelId) throws Exception {
        String[] str = new String[]{generateId().toString(), String.valueOf(userId), String.valueOf(roomId), "10-09-2017", "23-09-2017",
                String.valueOf(1000 * 3)};
        Order order = (Order) formEntity(str);

        HotelRepository hotelRepository = new HotelRepository();
        setPathDB(pathHotelDB);

        Hotel hotel = (Hotel) hotelRepository.findEntityById(hotelId);
       /* if (hotel == null)
            throw new Exception("Hotel with id " + hotelId + " does not exist in DB Hotel");*/

       /*if (order.getRoom().getHotel().getId() != hotel.getId())
            throw new Exception("Room with id " + roomId + " does not exist in hotel with id " + hotel.getId());

        setPathDB(pathOrderDB);
        setCountFieldsOfObject(6);
        addEntity(order);
    }

    public void cancelReservation(long roomId, long userId) throws Exception {
        if (roomId == 0)
            throw new Exception("Room with id " + roomId + " does not exist in dB");
        if (userId == 0)
            throw new Exception("User with id " + userId + " does not exist in dB");

        ArrayList<Order> orders = entityToArrayList();
        int count = 0;
        for (Order or : orders) {
            if (or.getRoom().getId() == roomId && or.getUser().getId() == userId) {
                count++;
                deleteEntity(or.getId());
            }
        }
        if (count == 0)
            throw new BadRequestException("Order of userId " + userId + " with roomId " + roomId +
                    " does not exist in DB");
    }




    @Override
    public Object formEntity(String[] str) throws Exception {
        if (str == null || str.length == 0 || str.length != 6)
            throw new Exception("Error of reading: Incorrect data");
        for (String el : str) {
            if (el == null) {
                throw new Exception("Error of reading: Incorrect data");
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Date dateFrom = simpleDateFormat.parse(str[3]);
        Date dateTo = simpleDateFormat.parse(str[4]);

        RoomRepository roomRepository = new RoomRepository();
        setPathDB(pathRoomDB);

        Room needRoom = (Room) roomRepository.findEntityById(Long.parseLong(str[2]));

        if (needRoom == null)
            throw new Exception("Room with id " + str[2] + " does not exist in DB Room");
        UserRepository userRepository = new UserRepository();
        setPathDB(pathUserDB);

        User user = (User) userRepository.findEntityById(Long.parseLong(str[1]));
        if (user == null)
            throw new Exception("User with id " + str[1] + " does not exist in DB User");

        setPathDB(pathOrderDB);

        Order order = new Order( user, needRoom, dateFrom, dateTo, Double.parseDouble(str[5]));
        order.setId(Long.parseLong(str[0]));

        return order;
    }

    @Override
    public boolean checkLine(String line, int numberLine) throws Exception {
        if (line == null || line.isEmpty())
            throw new BadRequestException("Wrong data in DB " + pathDB + " Line number: " + numberLine);

        String[] str = line.split(",");
        if (str.length != 6)
            throw new BadRequestException("Wrong data in DB " + pathDB + " Line number: " + numberLine);
        for (String el : str) {
            if (el == null || el.isEmpty())
                throw new BadRequestException("Wrong data in DB " + pathDB + " Line number: " + numberLine);
        }
        if (!checkWordOnDigts(str[0]))
            throw new BadRequestException("Wrong data in DB " + pathDB + " Line number: " + numberLine);

        return true;
    }
    public static void validateOrder(Order order)throws Exception{
        if(order == null)
            throw new BadRequestException("Wrong data order");
    }
    public  void validateOrder(Long id)throws Exception{
        if(id <= 0)
            throw new BadRequestException("You enter wrong orderId. Please, try again");
        if(findEntityById(id) == null)
            throw new Exception("Order with id " + id + " does not exist in DB Order");
    }
    public  void validateOrder(Order order, Hotel hotel, Long roomId)throws Exception {
        if (order.getRoom().getHotel().getId() != hotel.getId())
            throw new Exception("Room with id " + roomId + " does not exist in hotel with id " + hotel.getId());
    }*/
}
