package hibernate.lesson4.demo;

import hibernate.lesson4.repository.OrderRepository;
import hibernate.lesson4.service.OrderService;

/**
 * Created by user on 30.11.2017.
 */
public class DemoOrder {
    public static void main(String[] args) throws Exception {
        OrderRepository orderRepository = new OrderRepository();
        //orderRepository.bookRoom(263, 237 ,261);
        //orderRepository.cancelReservation(263, 237);

        OrderService orderService = new OrderService();
      //  orderService.bookRoom(263,237,261);
        orderService.cancelReservation(263,237);
    }
}
