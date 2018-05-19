package hibernate.lesson4.repository;

import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.model.User;
import hibernate.lesson4.model.UserType;

import java.util.ArrayList;

import static hibernate.lesson4.Utils.checkWordOnDigts;

/**
 * Created by user on 30.11.2017.
 */
public class UserRepository extends GeneralRepository<User> {

    /*static {
        setPathDB("D:/Ubuntu_backup/dev/UserDB");
    }*/


  /*  @Override
    public Object formEntity(String[] str) throws Exception {
        if (str.length != 5 || str.length == 0 || str == null)
            throw new Exception("Error of reading: Incorrect data");
        for (String el : str) {
            if (el == null) {
                throw new BadRequestException("Error of reading: Incorrect data");
            }
        }
        User user = new User();
        user.setId(Long.parseLong(str[0]));
        user.setUserName(str[1]);
        user.setPassword(str[2]);
        user.setCountry(str[3]);
        if (str[4].equals("USER")) {
            user.setUserType(UserType.USER);
        } else if (str[4].equals("ADMIN")) {
            user.setUserType(UserType.ADMIN);
        } else {
            throw new BadRequestException("Error of reading: Incorrect data");
        }

        return user;

    }

    public User findUserByUserName(User user) throws Exception {
        ArrayList<User> userArrayList = entityToArrayList();
        for (User us : userArrayList) {
            if (us.getUserName().equals(user.getUserName())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean checkLine(String line, int numberLine) throws Exception {
        if (line == null || line.isEmpty())
            throw new BadRequestException("Wrong data in DB " + pathDB + " Line number: " + numberLine);

        String[] str = line.split(",");
        if (str.length != 5)
            throw new BadRequestException("Wrong data in DB " + pathDB + " Line number: " + numberLine);
        for (String el : str) {
            if (el == null || el.isEmpty())
                throw new BadRequestException("Wrong data in DB " + pathDB + " Line number: " + numberLine);
        }
        if (!checkWordOnDigts(str[0]))
            throw new BadRequestException("Wrong data in DB " + pathDB + " Line number: " + numberLine);

        return true;
    }
    public static void validateUser(User user)throws Exception{
        if(user == null)
            throw new BadRequestException("Wrong data room");
    }
    public  void validateUser(Long id)throws Exception{
        if(id <= 0)
            throw new BadRequestException("You enter wrong userId. Please, try again");
        if(findEntityById(id) == null)
            throw new Exception("User with id " + id + " does not exist in DB Room");
    }*/
}
