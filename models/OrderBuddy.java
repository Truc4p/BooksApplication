package BooksApp.models;

import java.util.Date;
import BooksApp.adt.ArrayListADT;
import BooksApp.adt.QueueADT;

import BooksApp.models.CartItem;

public class OrderBuddy {
    private QueueADT<Order> orders;
    private static int orderIdCounter = 1;

    public OrderBuddy() {
        this.orders = new QueueADT<>();
    }

    public void addOrder(Order order) {
        orders.offer(order);
    }

    public QueueADT<Order> getOrders() {
        return orders;
    }

    private static int generateOrderId() {
        return orderIdCounter++;
    }

    public Order createOrder(Date orderDate, Customer customer, String status, ArrayListADT<CartItem> booksInCart) {
        int orderId = generateOrderId();
        return new Order(orderId, orderDate, customer.getCustomerId(), status, booksInCart);
    }
}