// FILE: models/Order.java
package BooksApp.models;

import java.util.Date;
import BooksApp.customer.Customer;
import BooksApp.adt.ArrayListADT;

public class Order implements Comparable<Order>  {
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

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayListADT<CartItem> getItems() {
        return items;
    }

    public boolean containsBookWithTitle(String title) {
        for (CartItem item : items) {
            if (item.getTitle().equalsIgnoreCase(title)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Order other) {
        return Integer.compare(this.orderId, other.orderId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order Details:\n");
        sb.append("---------------\n");
        sb.append("Order ID: ").append(orderId).append("\n");
        sb.append("Date: ").append(date).append("\n");
        sb.append("Customer: ").append(customer).append("\n");
        sb.append("Status: ").append(status).append("\n");
        sb.append("Items:\n");

        for (CartItem item : items) {
            sb.append("\t").append(item).append("\n");
        }

        return sb.toString();
    }
}