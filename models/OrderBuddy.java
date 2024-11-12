// FILE: models/OrderBuddy.java
package BooksApp.models;

import java.util.Date;
import BooksApp.adt.ArrayListADT;
import BooksApp.customer.Customer;
import BooksApp.adt.QueueADT;

public class OrderBuddy {
    private ArrayListADT<Order> orders;

    public OrderBuddy() {
        orders = new ArrayListADT<>();
    }

    public Order createOrder(Date date, Customer customer, String status, ArrayListADT<CartItem> items) {
        int orderId = generateOrderId();
        Order order = new Order(orderId, date, customer, status, items);
        return order;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public QueueADT<Order> getOrders() {
        QueueADT<Order> orderQueue = new QueueADT<>();
        for (Order order : orders) {
            orderQueue.offer(order);
        }
        return orderQueue;
    }

    private int generateOrderId() {
        return orders.size() + 1;
    }
}