package hibernate.lesson4.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 27.05.2018.
 */
public class Filter {

    private Integer numberOfGuests;
    private Double price;
    private boolean breakfastIncluded;
    private boolean petsAllowed;
    private Date dateAvailableFrom;
    private String country;
    private String city;


    public Filter() {
    }



    public Integer getNumberOfGuests() {
        return numberOfGuests;
    }

    public Double getPrice() {
        return price;
    }

    public Boolean getBreakfastIncluded() {
        return breakfastIncluded;
    }

    public Boolean getPetsAllowed() {
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

    public void setNumberOfGuests(Integer numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setBreakfastIncluded(Boolean breakfastIncluded) {
        this.breakfastIncluded = breakfastIncluded;
    }

    public void setPetsAllowed(Boolean petsAllowed) {
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
        List listObj = new ArrayList();
        for (Field fl : fields) {
            try {

                Object fieldValue = fl.get(this);
                if (fieldValue != null) {
                    listObj.add(fl);
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        int sizeList = listObj.size();
        if(listObj.size() == 0)
            return "SELECT * FROM Room";
        for (int i = 0; i < listObj.size(); i ++) {
            try {

                Field field = (Field) listObj.get(i);
                Object fieldValue = field.get(this);
                String nameParam = field.getName();
                if(field.getName()== "city" || field.getName() == "country"){
                    nameParam = "hotel." + field.getName();
                }
                if(listObj.indexOf(field) == sizeList - 1) {
                    System.out.println(field.getClass());
                    str +=  nameParam + " = " + '\'' + fieldValue + '\'';
                } else {
                    str += nameParam + " = " + '\'' + fieldValue + '\'' + " AND ";
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

}
