package hibernate.lesson4.service;

import hibernate.lesson4.model.User;
import hibernate.lesson4.repository.UserRepository;
import org.hibernate.HibernateException;

import static hibernate.lesson4.Utils.checkWordOnLetAndDigts;
import static hibernate.lesson4.Utils.checkWordOnLetters;

/**
 * Created by user on 23.05.2018.
 */
public class UserService {
    private UserRepository userRepository = new UserRepository();

    public User save(User user) throws Exception {
        validateInputData(user);

        if (!(userRepository.findUserByUserName(user) == null)) {
            throw new Exception("User with userName " + user.getUserName() +
                    " has registered already. Try another userName");
        }
        return userRepository.save(user);
    }

    public User update(User user) throws Exception {
        validateInputData(user);
        return userRepository.update(user);
    }

    public User delete(Long id) throws Exception {
        userRepository.validateUser(id);
        return userRepository.delete(id);
    }

    public User findById(Long id) throws Exception {
        userRepository.validateUser(id);
        return userRepository.findById(id);
    }

    public boolean validateInputData(User user) throws Exception {
        if (user == null || user.getUserName().isEmpty() || user.getPassword().isEmpty() ||
                user.getCountry().isEmpty()) {
            throw new Exception("You input wrong data. Try again, please");
        }
        if (!(checkWordOnLetAndDigts(user.getUserName())))
            throw new Exception("UserName " + user.getUserName() + " is wrong");
        if (!(checkWordOnLetAndDigts(user.getPassword())))
            throw new Exception("Password must have only letters and digits");
        if (!(checkWordOnLetters(user.getCountry()))) {
            throw new Exception("Country " + user.getCountry() + " must have only letters");
        }
        return true;
    }
}
