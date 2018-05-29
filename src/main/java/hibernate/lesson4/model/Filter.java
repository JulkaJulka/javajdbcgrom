package hibernate.lesson4.model;

import java.util.Date;

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

}
