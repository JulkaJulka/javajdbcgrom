package hibernate.lesson4.demo;

import hibernate.lesson4.controller.UserController;
import hibernate.lesson4.model.Order;
import hibernate.lesson4.model.User;
import hibernate.lesson4.model.UserType;
import hibernate.lesson4.repository.UserRepository;
import hibernate.lesson4.service.UserService;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 30.11.2017.
 */
public class DemoUser {
    public static void main(String[] args) throws Exception{
       // UserService userService = new UserService();
        UserRepository userRepository = new UserRepository();

        User user3 = new User();
       user3.setId(221l);
        user3.setUserName("fff");
        user3.setPassword("PRIVET");
        user3.setCountry("Ukr");

        user3.setUserType(UserType.USER);
       //System.out.println(userRepository.findById(221l));
       // System.out.println(userRepository.findUserByUserName(user3));

      // userRepository.delete(221l);

        UserService userService = new UserService();
       // System.out.println(userService.save(user3));

        UserController userController = new UserController();
        userController.register(user3);

    }
}
