package hibernate.lesson4.repository;

import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.model.User;
import hibernate.lesson4.model.UserType;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

import static hibernate.lesson4.Utils.checkWordOnDigts;

/**
 * Created by user on 30.11.2017.
 */
public class UserRepository extends GeneralRepository<User> {
    public static final String FIND_US_BY_ID_US = "FROM User WHERE ID = :ID ";
    public static final String FIND_US_BY_USNAME = "FROM User WHERE userName = :NAME";
    public static final String DELETE_US_BY_ID_US = "DELETE FROM User WHERE ID = :ID";

    public void delete(long id) {
         delete(DELETE_US_BY_ID_US, id);
    }

    public User findById(Long id) {
        return findById(FIND_US_BY_ID_US, id);
    }

    public User findUserByUserName(User user){
        try (Session session = createSessionFactory().openSession()) {

            Query query = session.createQuery(FIND_US_BY_USNAME);
            query.setParameter("NAME", user.getUserName());

            if (query.uniqueResult() == null)
                return null;
            User findUser = (User) query.getSingleResult();

            return findUser;

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            throw new HibernateException("Something went wrong");
        }
    }

    public  void validateUser(Long id)throws Exception{
        if(id <= 0)
            throw new BadRequestException("You enter wrong userId. Please, try again");
        if(findById(id) == null)
            throw new Exception("User with id " + id + " does not exist in DB User");
    }
}
