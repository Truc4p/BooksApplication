// FILE: models/OrderBuddy.java
package BooksApp.models;

import java.util.Date;
import java.util.Scanner;
import BooksApp.adt.ArrayListADT;
import BooksApp.customer.Customer;
import BooksApp.adt.QueueADT;
import BooksApp.algo.LinearSearch;
import BooksApp.algo.BinarySearch;
import BooksApp.algo.QuickSort;

public class OrderBuddy {
    private ArrayListADT<Order> orders; // List to store order objects

    // Constructor to initialize the orders list
    public OrderBuddy() {
        orders = new ArrayListADT<>();
    }

    // Method to create a new order
    public Order createOrder(Date date, Customer customer, String status, ArrayListADT<CartItem> items) {
        int orderId = generateOrderId();
        Order order = new Order(orderId, date, customer, status, items);
        return order;
    }

    // Method to add an order to the list
    public void addOrder(Order order) {
        orders.add(order);
    }

    // Method to get all orders as a queue
    public QueueADT<Order> getOrders() {
        QueueADT<Order> orderQueue = new QueueADT<>();
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            orderQueue.offer(order);
        }
        return orderQueue;
    }

    // Method to generate a unique order ID
    private int generateOrderId() {
        return orders.size() + 1;
    }

    // Method to view the order history for a logged-in user
    public void viewOrdersHistory(User loggedInUser) {
        System.out.println("----------------------");
        System.out.println("Orders History:");
        QueueADT<Order> orders = getOrders();
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            boolean orderFound = false;
            QueueADT<Order> tempQueue = new QueueADT<>();
            while (!orders.isEmpty()) {
                Order order = orders.poll();
                if (order.getCustomerId() == loggedInUser.getUserId()) {
                    System.out.println(order);
                    orderFound = true;
                }
                tempQueue.offer(order);
            }
            // Restore the original queue
            while (!tempQueue.isEmpty()) {
                orders.offer(tempQueue.poll());
            }
            if (!orderFound) {
                System.out.println("No orders found for your account.");
            }
        }
    }

    // Method to search orders as a customer
    public void searchOrdersAsCustomer(User loggedInUser, Scanner scanner) {
        System.out.println("----------------------");
        System.out.println("1. Search by Order ID");
        System.out.println("2. Search by Book Title");
        System.out.print("Enter your choice: ");
        String searchChoice = scanner.nextLine();

        if (searchChoice.equals("1")) {
            System.out.print("Enter the order ID to search: ");
            String searchOrderQuery = scanner.nextLine();
            int orderId;
            try {
                orderId = Integer.parseInt(searchOrderQuery); // Check if the input is a valid number
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid order ID.");
                return;
            }
            System.out.println("Searching for orders matching: " + searchOrderQuery);

            LinearSearch<Order> linearSearch = new LinearSearch<>();
            QueueADT<Order> ordersQueue = getOrders();
            QueueADT<Order> tempQueue = new QueueADT<>();

            // Convert QueueADT to ArrayListADT for searching
            ArrayListADT<Order> orderList = new ArrayListADT<>();
            while (!ordersQueue.isEmpty()) {
                Order order = ordersQueue.poll();
                orderList.add(order);
                tempQueue.offer(order);
            }

            // Restore the original queue
            while (!tempQueue.isEmpty()) {
                ordersQueue.offer(tempQueue.poll());
            }

            // Perform the search
            int index = linearSearch.search(orderList, null,
                    order -> order.getCustomerId() == loggedInUser.getUserId()
                            && order.getOrderId() == orderId);

            if (index != -1) {
                System.out.println(orderList.get(index));
            } else {
                System.out.println("No orders found matching: " + searchOrderQuery);
            }
        } else if (searchChoice.equals("2")) {
            System.out.print("Enter the book title to search: ");
            String bookTitle = scanner.nextLine();
            System.out.println("Searching for orders containing book title: " + bookTitle);

            QueueADT<Order> ordersQueue = getOrders();
            QueueADT<Order> tempQueue = new QueueADT<>();

            // Convert QueueADT to ArrayListADT for searching
            ArrayListADT<Order> orderList = new ArrayListADT<>();
            while (!ordersQueue.isEmpty()) {
                Order order = ordersQueue.poll();
                orderList.add(order);
                tempQueue.offer(order);
            }

            // Restore the original queue
            while (!tempQueue.isEmpty()) {
                ordersQueue.offer(tempQueue.poll());
            }

            // Perform the search
            ArrayListADT<Order> matchingOrders = new ArrayListADT<>();
            for (int i = 0; i < orderList.size(); i++) {
                Order order = orderList.get(i);
                if (order.getCustomerId() == loggedInUser.getUserId()
                        && order.containsBookWithTitle(bookTitle)) {
                    matchingOrders.add(order);
                }
            }

            if (!matchingOrders.isEmpty()) {
                for (int i = 0; i < matchingOrders.size(); i++) {
                    System.out.println(matchingOrders.get(i));
                }
            } else {
                System.out.println("No orders found containing book title: " + bookTitle);
            }
        }
    }

    // Method to view all orders as an admin
    public void viewAllOrdersAsAdmin(Scanner scanner) {
        System.out.println("----------------------");
        System.out.println("All Orders:");
        QueueADT<Order> ordersQueue = getOrders();
        QueueADT<Order> tempQueue = new QueueADT<>();

        if (ordersQueue.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            // Convert QueueADT to ArrayListADT for displaying, sorting, and searching
            ArrayListADT<Order> orderList = new ArrayListADT<>();
            while (!ordersQueue.isEmpty()) {
                Order order = ordersQueue.poll();
                orderList.add(order);
                tempQueue.offer(order);
            }

            // Restore the original queue
            while (!tempQueue.isEmpty()) {
                ordersQueue.offer(tempQueue.poll());
            }

            // Display all orders
            for (int i = 0; i < orderList.size(); i++) {
                System.out.println(orderList.get(i));
            }

            // Search Orders as admin
            System.out.println("----------------------");
            System.out.print("Enter the order ID to search: ");
            String searchOrderQuery = scanner.nextLine();
            int orderId;
            try {
                orderId = Integer.parseInt(searchOrderQuery); // Check if the input is a valid number
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid order ID.");
                return;
            }
            System.out.println("Searching for orders matching: " + searchOrderQuery);

            // Sort the orders by order ID using QuickSort
            QuickSort<Order> sorter = new QuickSort<>();
            sorter.sort(orderList);

            // Perform the binary search
            BinarySearch<Order> binarySearch = new BinarySearch<>();
            int index = binarySearch.search(orderList, new Order(orderId, null, null, null, null));

            if (index != -1) {
                Order foundOrder = orderList.get(index);
                System.out.println(foundOrder);

                // Change order status
                System.out.print("Enter the new status for the order: ");
                String newStatus = scanner.nextLine();
                foundOrder.setStatus(newStatus);
                System.out.println("Order status updated successfully!");
            } else {
                System.out.println("No orders found matching: " + searchOrderQuery);
            }
        }
    }
}