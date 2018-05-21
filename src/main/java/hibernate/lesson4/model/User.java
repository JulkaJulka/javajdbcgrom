package hibernate.lesson4.model;

import com.sun.istack.internal.NotNull;
import hibernate.lesson4.repository.GeneralRepository;
import org.hibernate.annotations.Type;
import org.hibernate.type.NumericBooleanType;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

/**
 * Created by user on 30.11.2017.
 */
@Entity
@Table(name = "USER_AP")
public class User {

    private long id;
    private String userName;
    private String password;
    private String country;
    private UserType userType;
    private boolean loginStatus;

    private List<Order> orders;


    public User() {
    }

    public User(String userName, String password, String country, UserType userType, boolean loginStatus) {
        this.userName = userName;
        this.password = password;
        this.country = country;
        this.userType = userType;
        this.loginStatus = loginStatus;
    }

    public User(String userName, String password, String country, UserType userType, boolean loginStatus, List<Order> orders) {
        this.userName = userName;
        this.password = password;
        this.country = country;
        this.userType = userType;
        this.loginStatus = loginStatus;
        this.orders = orders;
    }

    @Id
    @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    @Column(name = "USER_NAME")
    public String getUserName() {
        return userName;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_TYPE")
    public UserType getUserType() {
        return userType;
    }


    @NotNull
    @Column(name = "LOGIN_STATUS", nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    public boolean isLoginStatus() {
        return loginStatus;
    }

    @OneToMany(targetEntity=Order.class, mappedBy = "user", fetch = FetchType.LAZY)
    public List<Order> getOrders() {
        return orders;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public void ListOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", country='" + country + '\'' +
                ", userType=" + userType +
                ", loginStatus=" + loginStatus +
                ", orders=" + orders +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (loginStatus != user.loginStatus) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (country != null ? !country.equals(user.country) : user.country != null) return false;
        if (userType != user.userType) return false;
        return orders != null ? orders.equals(user.orders) : user.orders == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        result = 31 * result + (loginStatus ? 1 : 0);
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        return result;
    }
}
