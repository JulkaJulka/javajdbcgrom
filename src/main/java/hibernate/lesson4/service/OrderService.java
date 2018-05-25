package hibernate.lesson4.service;

import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.model.Hotel;
import hibernate.lesson4.model.Order;
import hibernate.lesson4.model.User;
import hibernate.lesson4.model.UserType;
import hibernate.lesson4.repository.HotelRepository;
import hibernate.lesson4.repository.OrderRepository;
import hibernate.lesson4.repository.RoomRepository;
import hibernate.lesson4.repository.UserRepository;

/**
 * Created by user on 25.05.2018.
 */
public class OrderService {
    private UserRepository userRepository = new UserRepository();
    private RoomRepository roomRepository = new RoomRepository();
    private OrderRepository orderRepository = new OrderRepository();
    private HotelRepository hotelRepository = new HotelRepository();
    private RoomService roomService = new RoomService();
    private HotelService hotelService = new HotelService();
    private UserService userService = new UserService();

    public void bookRoom(long roomId, long userId, long hotelId) throws Exception {
        userService.validateUser(userId);
        roomService.validateRoom(roomId);
        hotelService.validateHotel(hotelId);

        User user = userRepository.findById(userId);

         if(user.getUserType()!= UserType.valueOf("ADMIN") && user.getUserType()!= UserType.valueOf("USER") )
             throw new Exception("Booking room not available to you. Please sign in.");
        orderRepository.bookRoom(roomId, userId, hotelId);
    }

    public void cancelReservation(long roomId, long userId) throws Exception {
       roomService.validateRoom(roomId);
        userService.validateUser(userId);

        User user = userRepository.findById(userId);
        userService.validateUser(user);

        if(user.getUserType()!= UserType.valueOf("ADMIN") && user.getUserType()!= UserType.valueOf("USER") )
            throw new Exception("Cancel reservation not available to you. Please sign in.");

        orderRepository.cancelReservation(roomId, userId);
    }

    public static void validateOrder(Order order) throws Exception {
        if (order == null)
            throw new BadRequestException("Wrong data order");
    }

    public void validateOrder(Long id) throws Exception {
        if (id <= 0)
            throw new BadRequestException("You enter wrong orderId. Please, try again");
        if (orderRepository.findById(id) == null)
            throw new Exception("Order with id " + id + " does not exist in DB Order");
    }

    public void validateOrder(Order order, Hotel hotel, Long roomId) throws Exception {
        if (order.getRoom().getHotel().getId() != hotel.getId())
            throw new Exception("Room with id " + roomId + " does not exist in hotel with id " + hotel.getId());
    }
}
