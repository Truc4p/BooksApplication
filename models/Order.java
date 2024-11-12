// FILE: models/Order.java
package BooksApp.models;

import java.util.Date;
import BooksApp.customer.Customer;
import BooksApp.adt.ArrayListADT;

public class Order {
    private int orderId;
    private Date date;
    private Customer customer;
    private String status;
    private ArrayListADT<CartItem> items;

    public Order(int orderId, Date date, Customer customer, String status, ArrayListADT<CartItem> items) {
        this.orderId = orderId;
        this.date = date;
        this.customer = customer;
        this.status = status;
        this.items = items;
    }

    public int getOrderId() {
        return orderId;
    }

    public Date getDate() {
        return date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getCustomerId() {
        return customer.getUserId();
    }

    public String getStatus() {
        return status;
    }

    public ArrayListADT<CartItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", date=" + date +
                ", customer=" + customer +
                ", status='" + status + '\'' +
                ", items=" + items +
                '}';
    }
}