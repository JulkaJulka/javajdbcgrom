package hibernate.lesson4.controller;

import hibernate.lesson4.service.OrderService;
import hibernate.lesson4.service.UserService;

/**
 * Created by user on 25.05.2018.
 */
public class OrderController {
    private OrderService orderService = new OrderService();

    public void bookRoom(long roomId, long userId, long hotelId) throws Exception {
        orderService.bookRoom(roomId, userId,hotelId);
    }
    public  void cancelReservation(long roomId, long userId)throws  Exception{
        orderService.cancelReservation(roomId, userId);
    }
}
