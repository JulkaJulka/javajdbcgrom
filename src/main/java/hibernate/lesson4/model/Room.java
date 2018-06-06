package hibernate.lesson4.model;

import com.sun.istack.internal.NotNull;
import hibernate.lesson4.repository.GeneralRepository;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.util.Date;

/**
 * Created by user on 30.11.2017.
 */
@Entity
@Table(name = "ROOM")
public class Room {

   private long id;
   private int numberOfGuests;
   private double price;
   private boolean breakfastIncluded;
   private boolean petsAllowed;
   private Date dateAvailableFrom;

   private Hotel hotel;

    public Room() {
    }

    @Id
    @SequenceGenerator(name = "RM_SEQ", sequenceName = "ROOM_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RM_SEQ")
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    @Column(name = "NUMBER_OF_GUEST")
    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    @Column(name = "PRICE")
    public double getPrice() {
        return price;
    }


    @NotNull
    @Column(name = "BREAKFAST_INCLUDED", nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    public boolean getBreakfastIncluded() {
        return breakfastIncluded;
    }

    @NotNull
    @Column(name = "PETS_ALLOWED", nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    public boolean getPetsAllowed() {
        return petsAllowed;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_AVAILABLE_FROM")
    public Date getDateAvailableFrom() {
        return dateAvailableFrom;
    }

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "HOTEL_ID")
    public Hotel getHotel() {
        return hotel;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBreakfastIncluded(boolean breakfastIncluded) {
        this.breakfastIncluded = breakfastIncluded;
    }

    public void setPetsAllowed(boolean petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    public void setDateAvailableFrom(Date dateAvailableFrom) {
        this.dateAvailableFrom = dateAvailableFrom;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }


    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", numberOfGuests=" + numberOfGuests +
                ", price=" + price +
                ", breakfastIncluded=" + breakfastIncluded +
                ", petsAllowed=" + petsAllowed +
                ", dateAvailableFrom=" + dateAvailableFrom +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (id != room.id) return false;
        if (numberOfGuests != room.numberOfGuests) return false;
        if (Double.compare(room.price, price) != 0) return false;
        if (breakfastIncluded != room.breakfastIncluded) return false;
        if (petsAllowed != room.petsAllowed) return false;
        return dateAvailableFrom != null ? dateAvailableFrom.equals(room.dateAvailableFrom) : room.dateAvailableFrom == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + numberOfGuests;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (breakfastIncluded ? 1 : 0);
        result = 31 * result + (petsAllowed ? 1 : 0);
        result = 31 * result + (dateAvailableFrom != null ? dateAvailableFrom.hashCode() : 0);
        return result;
    }
}
