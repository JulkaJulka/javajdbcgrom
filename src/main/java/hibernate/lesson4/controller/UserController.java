package hibernate.lesson4.controller;

import hibernate.lesson4.model.User;
import hibernate.lesson4.service.UserService;

/**
 * Created by user on 23.05.2018.
 */
public class UserController {
    private UserService userService;

    public User save(User user) throws Exception{
        return userService.save(user);
    }

    public User update(User user) throws Exception{
        return userService.update(user);
    }
    public User delete(Long id) throws Exception{
        return userService.delete(id);
    }

    public User findById(Long id) throws Exception{
        return userService.findById(id);
    }
}
