package hibernate.lesson4.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Objects;

/**
 * Created by user on 27.05.2018.
 */
public class Filter {

    int numberOfGuests;
    private double price;
    private boolean breakfastIncluded;
    private boolean petsAllowed;
    private Date dateAvailableFrom;
    private String country;
    private String city;


    public Filter() {
    }

    public Filter(int numberOfGuests, double price, boolean breakfastIncluded, boolean petsAllowed,
                  Date dateAvailableFrom, String country, String city) {
        this.numberOfGuests = numberOfGuests;
        this.price = price;
        this.breakfastIncluded = breakfastIncluded;
        this.petsAllowed = petsAllowed;
        this.dateAvailableFrom = dateAvailableFrom;
        this.country = country;
        this.city = city;
    }


    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public double getPrice() {
        return price;
    }

    public boolean getBreakfastIncluded() {
        return breakfastIncluded;
    }

    public boolean getPetsAllowed() {
        return petsAllowed;
    }

    public Date getDateAvailableFrom() {
        return dateAvailableFrom;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
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

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /*@Override
    public String toString() {
        return "Filter{" +
                "numberOfGuests=" + numberOfGuests +
                ", price=" + price +
                ", breakfastIncluded=" + breakfastIncluded +
                ", petsAllowed=" + petsAllowed +
                ", dateAvailableFrom=" + dateAvailableFrom +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }*/


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Filter{");
        sb.append("numberOfGuests=").append(numberOfGuests);
        sb.append(", price=").append(price);
        sb.append(", breakfastIncluded=").append(breakfastIncluded);
        sb.append(", petsAllowed=").append(petsAllowed);
        sb.append(", dateAvailableFrom=").append(dateAvailableFrom);
        sb.append(", country='").append(country).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String sqlSelect() {
int count = 0;
        Field[] fields = getClass().getDeclaredFields();
        String str = "FROM Room WHERE ";
        for (Field fl : fields) {
            try {
                Object fieldValue = fl.get(this);
                if (fieldValue != null) {
                    count++;
                    str += fl.getName() + " = " + '\'' + fieldValue + '\'' + " AND ";
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        System.out.println(count);
        return str;
    }


    public String toString2() {
        return
                "FROM Room WHERE " +
                        "numberOfGuests=" + numberOfGuests + " AND " +
                        "price=" + price + " AND " +
                        "breakfastIncluded=" + breakfastIncluded + " AND " +
                        "petsAllowed=" + petsAllowed + " AND " +
                        "dateAvailableFrom=" + dateAvailableFrom + " AND " +
                        "hotel.country =\'" + country + '\'' + " AND " +
                        "hotel.city='" + city + '\'';
    }
}
