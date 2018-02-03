package lesson1and2;

import java.util.Date;

/**
 * Created by user on 25.01.2018.
 */
public class Order {
    private long id;
private String productName;
    private int price;
    private Date dateOrdered;
    private Date dateConfirmed;

    public Order(long id, String productName, int price, Date dateOrdered, Date dateConfirmed) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.dateOrdered = dateOrdered;
        this.dateConfirmed = dateConfirmed;
    }

    public long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    public Date getDateOrdered() {
        return dateOrdered;
    }

    public Date getDateConfirmed() {
        return dateConfirmed;
    }

    @Override
    public String toString() {
        return "lesson1and2.Order{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", dateOrdered=" + dateOrdered +
                ", dateConfirmed=" + dateConfirmed +
                '}';
    }
}
