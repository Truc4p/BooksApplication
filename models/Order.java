// FILE: models/Order.java
package BooksApp.models;

import java.util.Date;
import BooksApp.customer.Customer;
import BooksApp.adt.ArrayListADT;

public class Order implements Comparable<Order>  {
    private int orderId; // Unique identifier for the order
    private Date date; // Date of the order
    private Customer customer; // Customer who placed the order
    private String status; // Status of the order (e.g., "Pending", "Shipped")
    private ArrayListADT<CartItem> items; // List of items in the order

    // Constructor to initialize the order object
    public Order(int orderId, Date date, Customer customer, String status, ArrayListADT<CartItem> items) {
        this.orderId = orderId;
        this.date = date;
        this.customer = customer;
        this.status = status;
        this.items = items;
    }

    // Getter method for orderId
    public int getOrderId() {
        return orderId;
    }

    // Getter method for date
    public Date getDate() {
        return date;
    }

    // Getter method for customer
    public Customer getCustomer() {
        return customer;
    }

    // Getter method for customer ID
    public int getCustomerId() {
        return customer.getUserId();
    }

    // Getter method for status
    public String getStatus() {
        return status;
    }

    // Setter method for status
    public void setStatus(String status) {
        this.status = status;
    }

    // Getter method for items
    public ArrayListADT<CartItem> getItems() {
        return items;
    }

    // Method to check if the order contains a book with a specific title
    public boolean containsBookWithTitle(String title) {
        for (int i = 0; i < items.size(); i++) {
            CartItem item = items.get(i);
            if (item.getTitle().equalsIgnoreCase(title)) {
                return true;
            }
        }
        return false;
    }

    // Override compareTo method to compare orders by orderId
    @Override
    public int compareTo(Order other) {
        return Integer.compare(this.orderId, other.orderId);
    }

    // Override toString method to return a string representation of the order object
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

        for (int i = 0; i < items.size(); i++) {
            CartItem item = items.get(i);
            sb.append("\t").append(item).append("\n");
        }

        return sb.toString();
    }
}