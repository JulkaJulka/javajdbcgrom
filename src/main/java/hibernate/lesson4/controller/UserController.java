package hibernate.lesson4.controller;

import hibernate.lesson4.model.User;
import hibernate.lesson4.service.UserService;

/**
 * Created by user on 23.05.2018.
 */
public class UserController {
    private UserService userService = new UserService();

    public User register(User user) throws Exception{
        return userService.save(user);
    }

}
