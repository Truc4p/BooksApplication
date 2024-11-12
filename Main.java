package BooksApp;

import java.util.Scanner;
import java.util.Date;

import BooksApp.models.BookBuddy;
import BooksApp.models.CartItemBuddy;
import BooksApp.models.OrderBuddy;
import BooksApp.adt.StackADT;
import BooksApp.models.Book;
import BooksApp.models.Customer;
import BooksApp.adt.ArrayListADT;
import BooksApp.models.CartItem;
import BooksApp.models.Order;
import BooksApp.adt.QueueADT;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BookBuddy bookBuddy = new BookBuddy();
        CartItemBuddy cartItemBuddy = new CartItemBuddy();
        OrderBuddy orderBuddy = new OrderBuddy();
        StackADT<String> searchHistory = new StackADT<>(); // Stack to store search queries

        boolean running = true;

        while (running) {
            System.out.println("----------------------");
            System.out.println("Book Store Application");
            System.out.println("----------------------");

            System.out.println("1. Display all books");
            System.out.println("2. Add book to cart");
            System.out.println("3. Search book");
            System.out.println("4. Go to cart");
            System.out.println("5. Orders History");
            System.out.println("6. Search book history");
            System.out.println("7. Exit");

            System.out.println("----------------------");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    // Display all books
                    bookBuddy.displayBooks();
                    break;

                case 2:
                    // Add book to cart
                    System.out.println("----------------------");
                    System.out.print("Enter the book ID to add to cart: ");
                    int bookIdToAdd = Integer.parseInt(scanner.nextLine());
                    Book book = bookBuddy.getBookById(bookIdToAdd); // Assume this method retrieves the book by ID
                    if (book != null) {
                        System.out.print("Enter the quantity to add to cart: ");
                        int quantity = 0;
                        boolean validQuantity = false;

                        while (!validQuantity) {
                            try {
                                quantity = Integer.parseInt(scanner.nextLine());
                                if (quantity > 0 && quantity <= book.getStockQuantity()) {
                                    validQuantity = true;
                                } else {
                                    System.out.println("Invalid quantity. Please enter a quantity between 1 and "
                                            + book.getStockQuantity() + ".");
                                    System.out.print("Enter the quantity to add to cart: ");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid number.");
                                System.out.print("Enter the quantity to add to cart: ");
                            }
                        }
                        cartItemBuddy.addToCart(book, quantity); // Add to cart with specified quantity
                        System.out.println("Book(s) added to cart!");
                    } else {
                        System.out.println("No book found with ID: " + bookIdToAdd);
                    }
                    break;

                case 3:
                    // Search book
                    System.out.println("----------------------");
                    System.out.print("Enter the ID, title or author of the book: ");
                    String searchQuery = scanner.nextLine();
                    searchHistory.push(searchQuery); // Save search query to history
                    bookBuddy.searchBook(searchQuery); // Assume searchBook handles searching by ID, title, or author
                    break;

                case 4:
                    // View cart
                    System.out.println("----------------------");
                    cartItemBuddy.viewCart();

                    System.out.println("1. Checkout");
                    System.out.println("2. Go back");
                    System.out.println("3. Remove book from cart");
                    System.out.println("4. Change quantity");

                    System.out.println("----------------------");
                    System.out.print("Enter your choice: ");
                    int choiceCart = Integer.parseInt(scanner.nextLine());

                    switch (choiceCart) {
                        case 1:
                            // Checkout
                            if (cartItemBuddy.isEmpty()) {
                                System.out
                                        .println("Your cart is empty. Add some books to the cart before checking out.");
                            } else {
                                System.out.print("Enter your customer ID: ");
                                int customerId = Integer.parseInt(scanner.nextLine());
                                System.out.print("Enter your name: ");
                                String name = scanner.nextLine();
                                System.out.print("Enter your email: ");
                                String email = scanner.nextLine();
                                System.out.print("Enter your address: ");
                                String address = scanner.nextLine();

                                Customer customer = new Customer(customerId, name, email, address);
                                ArrayListADT<CartItem> booksInCart = new ArrayListADT<>();
                                for (int i = 0; i < cartItemBuddy.getBooksInCart().size(); i++) {
                                    booksInCart.add(cartItemBuddy.getBooksInCart().get(i));
                                }
                                Order order = orderBuddy.createOrder(new Date(), customer, "Pending", booksInCart);

                                // Save the order (this part depends on how you manage orders in your
                                // application)
                                // For example, you might have an OrderBuddy class to handle orders
                                orderBuddy.addOrder(order);

                                System.out.println("----------------------");
                                System.out.println("Order placed successfully!");
                                System.out.println(order);

                                // Clear the cart after checkout
                                cartItemBuddy.clearCart();
                            }
                            break;

                        case 2:
                            // Go back
                            break;

                        case 3:
                            System.out.println("----------------------");
                            System.out.print("Enter the book ID to remove from cart or 'n' to cancel: ");
                            String inputCart = scanner.nextLine();
                            if (!inputCart.equalsIgnoreCase("n")) {
                                try {
                                    int bookIdToRemove = Integer.parseInt(inputCart);
                                    cartItemBuddy.removeFromCart(bookIdToRemove); // Remove from cart
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid input. Please enter a valid book ID.");
                                }
                            }
                            break;

                        case 4:
                            System.out.println("----------------------");
                            System.out.print("Enter the book ID to change quantity or 'n' to cancel: ");
                            String inputCart2 = scanner.nextLine();
                            if (!inputCart2.equalsIgnoreCase("n")) {
                                try {
                                    int bookId = Integer.parseInt(inputCart2);
                                    CartItem cartItem = cartItemBuddy.getCartItemById(bookId);
                                    Book bookQuantityChangeInCart = bookBuddy.getBookById(bookId); // Assume this method
                                                                                                   // retrieves the book
                                                                                                   // by ID
                                    if (cartItem != null) {
                                        System.out.print("Enter the new quantity: ");
                                        int newQuantity = Integer.parseInt(scanner.nextLine());

                                        if (newQuantity > 0
                                                && newQuantity <= bookQuantityChangeInCart.getStockQuantity()) {
                                            cartItem.setQuantity(newQuantity);
                                            System.out.println("Quantity updated!");
                                        } else {
                                            System.out
                                                    .println("Invalid quantity. Please enter a quantity between 1 and "
                                                            + bookQuantityChangeInCart.getStockQuantity() + ".");
                                        }
                                    } else {
                                        System.out.println("No book found with ID: " + bookId);
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid input. Please enter a valid book ID.");
                                }
                            }
                            break;
                    }
                    break;

                case 5:
                    // Orders History
                    System.out.println("----------------------");
                    System.out.println("Orders History:");
                    QueueADT<Order> orders = orderBuddy.getOrders();
                    if (orders.isEmpty()) {
                        System.out.println("No orders found.");
                    } else {
                        for (int i = 0; i < orders.size(); i++) {
                            Order order = orders.poll();
                            System.out.println(order);
                            orders.offer(order); // Re-add the order to maintain the queue
                        }
                    }

                    // Search Orders
                    System.out.println("----------------------");
                    System.out.print("Enter the order number or customer ID to search: ");
                    String searchOrderQuery = scanner.nextLine();
                    System.out.println("Searching for orders matching: " + searchOrderQuery);
                    boolean orderFound = false;
                    QueueADT<Order> tempQueue = new QueueADT<>();
                    while (!orders.isEmpty()) {
                        Order order = orders.poll();
                        if (Integer.toString(order.getOrderId()).equals(searchOrderQuery) ||
                                Integer.toString(order.getCustomerId()).equals(searchOrderQuery)) {
                            orderFound = true;
                            System.out.println(order);
                        }
                        tempQueue.offer(order);
                    }
                    // Restore the original queue
                    while (!tempQueue.isEmpty()) {
                        orders.offer(tempQueue.poll());
                    }
                    if (!orderFound) {
                        System.out.println("No orders found matching: " + searchOrderQuery);
                    }
                    break;

                case 6:
                    // Search book history
                    System.out.println("----------------------");
                    System.out.println("Search History:");
                    if (searchHistory.isEmpty()) {
                        System.out.println("No search history found.");
                    } else {
                        StackADT<String> tempStack = new StackADT<>();
                        while (!searchHistory.isEmpty()) {
                            String query = searchHistory.pop();
                            System.out.println(query);
                            tempStack.push(query);
                        }
                        // Restore the original stack
                        while (!tempStack.isEmpty()) {
                            searchHistory.push(tempStack.pop());
                        }
                    }
                    break;

                case 7:
                    // Exit
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter your choice again!");
                    break;
            }
        }

        scanner.close();
        System.out.println("Exiting ...");

    }

}