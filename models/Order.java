package BooksApp.models;

import java.util.Date;
import BooksApp.adt.ArrayListADT;

public class Order {
    private int orderId;
    private Date orderDate;
    private int customerId;
    private String status;
    private ArrayListADT<CartItem> booksInOrder;

    // Constructor
    public Order(int orderId, Date orderDate, int customerId, String status, ArrayListADT<CartItem> booksInOrder) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.status = status;
        this.booksInOrder = booksInOrder;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayListADT<CartItem> getBooksInOrder() {
        return booksInOrder;
    }
    
    // toString method for easy printing
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", orderDate=").append(orderDate);
        sb.append(", customerId=").append(customerId);
        sb.append(", status='").append(status).append('\'');
        sb.append(", booksInOrder=[");
        for (int i = 0; i < booksInOrder.size(); i++) {
            sb.append("\n  ").append(booksInOrder.get(i).toString());
            if (i < booksInOrder.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("\n]}");
        return sb.toString();
    }
}