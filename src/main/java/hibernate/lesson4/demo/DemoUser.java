package hibernate.lesson4.demo;

import hibernate.lesson4.model.Order;
import hibernate.lesson4.model.User;
import hibernate.lesson4.model.UserType;
import hibernate.lesson4.repository.UserRepository;

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
        user3.setUserName("Julka");
        user3.setPassword("Julk");
        user3.setCountry("Ukr");
        user3.setUserType(UserType.USER);

        userRepository.save(user3);

    }
}
