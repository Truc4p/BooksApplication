// FILE: Main.java
package BooksApp;

import java.util.Scanner;
import java.util.Date;

import BooksApp.models.BookBuddy;
import BooksApp.models.CartItemBuddy;
import BooksApp.models.OrderBuddy;
import BooksApp.models.User;
import BooksApp.admin.Admin;
import BooksApp.admin.AdminBuddy;
import BooksApp.customer.Customer;
import BooksApp.customer.CustomerBuddy;
import BooksApp.adt.StackADT;
import BooksApp.algo.BinarySearch;
import BooksApp.algo.LinearSearch;
import BooksApp.algo.QuickSort;
import BooksApp.models.Book;
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
        AdminBuddy adminBuddy = new AdminBuddy();
        CustomerBuddy customerBuddy = new CustomerBuddy();
        StackADT<String> searchHistory = new StackADT<>(); // Stack to store search queries
        boolean running = true;
        User loggedInUser = null;

        // ArrayListADT to simulate database
        ArrayListADT<User> usersDatabase = new ArrayListADT<>();

        while (running) {
            System.out.println("----------------------");
            System.out.println("Book Store Application");
            System.out.println("----------------------");

            if (loggedInUser == null) {
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.println("----------------------");
                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        // Register
                        System.out.println("1. Register as Customer");
                        System.out.println("2. Register as Admin");
                        System.out.print("Enter your choice: ");
                        int registerChoice = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter your name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter your email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter your password: ");
                        String password = scanner.nextLine();

                        if (registerChoice == 1) {
                            System.out.print("Enter your address: ");
                            String address = scanner.nextLine();
                            Customer customer = customerBuddy.createCustomer(name, email, password, address);
                            customerBuddy.registerCustomer(customer);
                            usersDatabase.add(customer); // Save to database
                            System.out.println("Customer registered successfully!");
                        } else if (registerChoice == 2) {
                            System.out.print("Enter the admin key: ");
                            String adminKey = scanner.nextLine();
                            if (adminKey.equals("abc")) {
                                Admin admin = adminBuddy.createAdmin(name, email, password);
                                adminBuddy.registerAdmin(admin);
                                usersDatabase.add(admin); // Save to database
                                System.out.println("Admin registered successfully!");
                            } else {
                                System.out.println("Invalid admin key. Registration as Admin failed.");
                            }
                        } else {
                            System.out.println("Invalid choice.");
                        }
                        break;

                    case 2:
                        // Login
                        System.out.println("1. Login as Customer");
                        System.out.println("2. Login as Admin");
                        System.out.print("Enter your choice: ");
                        int loginChoice = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter your email: ");
                        String loginEmail = scanner.nextLine();
                        System.out.print("Enter your password: ");
                        String loginPassword = scanner.nextLine();

                        if (loginChoice == 1) {
                            loggedInUser = customerBuddy.loginCustomer(loginEmail, loginPassword);
                        } else if (loginChoice == 2) {
                            loggedInUser = adminBuddy.loginAdmin(loginEmail, loginPassword);
                        } else {
                            System.out.println("Invalid choice.");
                        }

                        if (loggedInUser != null) {
                            System.out.println("Login successful! Welcome, " + loggedInUser.getName());
                        } else {
                            System.out.println("Invalid email or password.");
                        }
                        break;

                    case 3:
                        // Exit
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter your choice again!");
                        break;
                }
            } else {
                if (loggedInUser instanceof Customer) {
                    // Customer menu
                    int choice = 0;
                    while (true) {
                        System.out.println("1. Display all books");
                        System.out.println("2. Add book to cart");
                        System.out.println("3. Search book");
                        System.out.println("4. Go to cart");
                        System.out.println("5. Orders History");
                        System.out.println("6. Search book history");
                        System.out.println("7. Logout");
                        System.out.println("----------------------");
                        System.out.print("Enter your choice: ");
                        try {
                            choice = Integer.parseInt(scanner.nextLine());
                            if (choice >= 1 && choice <= 7) {
                                break;
                            } else {
                                System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a number between 1 and 7.");
                        }
                    }

                    switch (choice) {
                        case 1:
                            // Display all books
                            bookBuddy.displayBooks();
                            break;

                        case 2:
                            // Add book to cart
                            System.out.println("----------------------");
                            int bookIdToAdd = 0;
                            while (true) {
                                try {
                                    System.out.print("Enter the book ID to add to cart: ");
                                    bookIdToAdd = Integer.parseInt(scanner.nextLine());
                                    break;
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid input. Please enter a valid book ID.");
                                }
                            }
                            Book book = bookBuddy.getBookById(bookIdToAdd); // Assume this method retrieves the book by
                                                                            // ID
                            if (book != null) {
                                int currentQuantityInCart = cartItemBuddy.getQuantityInCart(bookIdToAdd);
                                int availableQuantity = book.getStockQuantity() - currentQuantityInCart;

                                if (availableQuantity <= 0) {
                                    System.out.println(
                                            "This book is out of stock.");
                                } else {
                                    System.out.print("Enter the quantity to add to cart (available: "
                                            + availableQuantity + "): ");
                                    int quantity = 0;
                                    boolean validQuantity = false;

                                    while (!validQuantity) {
                                        try {
                                            quantity = Integer.parseInt(scanner.nextLine());
                                            if (quantity > 0 && quantity <= availableQuantity) {
                                                validQuantity = true;
                                            } else {
                                                System.out.println(
                                                        "Invalid quantity. Please enter a quantity between 1 and "
                                                                + availableQuantity + ".");
                                            }
                                        } catch (NumberFormatException e) {
                                            System.out.println("Invalid input. Please enter a valid quantity.");
                                        }
                                    }
                                    cartItemBuddy.addToCart(book, quantity); // Add to cart with specified quantity
                                    System.out.println("Book(s) added to cart!");
                                }
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
                            bookBuddy.searchBook(searchQuery); // Assume searchBook handles searching by ID, title, or
                                                               // author
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
                                        System.out.println(
                                                "Your cart is empty. Add some books to the cart before checking out.");
                                    } else {
                                        Customer customer = (Customer) loggedInUser;
                                        ArrayListADT<CartItem> booksInCart = new ArrayListADT<>();
                                        for (int i = 0; i < cartItemBuddy.getBooksInCart().size(); i++) {
                                            booksInCart.add(cartItemBuddy.getBooksInCart().get(i));
                                        }
                                        Order order = orderBuddy.createOrder(new Date(), customer, "Pending",
                                                booksInCart);
                                        orderBuddy.addOrder(order);

                                        // Subtract stock quantity of the books
                                        for (int i = 0; i < booksInCart.size(); i++) {
                                            CartItem cartItem = booksInCart.get(i);
                                            Book bookToBuy = bookBuddy.getBookById(cartItem.getBookId());
                                            bookToBuy.setStockQuantity(
                                                    bookToBuy.getStockQuantity() - cartItem.getQuantity());
                                        }

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
                                    // Remove book from cart
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
                                    // Change quantity
                                    System.out.println("----------------------");
                                    System.out.print("Enter the book ID to change quantity or 'n' to cancel: ");
                                    String inputCart2 = scanner.nextLine();
                                    if (!inputCart2.equalsIgnoreCase("n")) {
                                        try {
                                            int bookId = Integer.parseInt(inputCart2);
                                            CartItem cartItem = cartItemBuddy.getCartItemById(bookId);
                                            if (cartItem != null) {
                                                Book bookQuantityChange = bookBuddy.getBookById(bookId); // Assume this
                                                                                                         // method
                                                // retrieves the book by ID
                                                if (bookQuantityChange != null) {
                                                    System.out.print("Enter the new quantity (available: "
                                                            + bookQuantityChange.getStockQuantity() + "): ");
                                                    int newQuantity = 0;
                                                    boolean validQuantity = false;

                                                    while (!validQuantity) {
                                                        try {
                                                            newQuantity = Integer.parseInt(scanner.nextLine());
                                                            if (newQuantity > 0
                                                                    && newQuantity <= bookQuantityChange
                                                                            .getStockQuantity()) {
                                                                validQuantity = true;
                                                            } else {
                                                                System.out.println(
                                                                        "Invalid quantity. Please enter a quantity between 1 and "
                                                                                + bookQuantityChange.getStockQuantity()
                                                                                + ".");
                                                            }
                                                        } catch (NumberFormatException e) {
                                                            System.out.println(
                                                                    "Invalid input. Please enter a valid quantity.");
                                                        }
                                                    }
                                                    cartItem.setQuantity(newQuantity);
                                                    System.out.println("Quantity updated successfully!");
                                                } else {
                                                    System.out.println("No book found with ID: " + bookId);
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
                            // view Orders History as customer
                            System.out.println("----------------------");
                            System.out.println("Orders History:");
                            QueueADT<Order> orders = orderBuddy.getOrders();
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

                            // Search Orders as customer
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
                                    orderId = Integer.parseInt(searchOrderQuery); // Check if the input is a valid
                                                                                  // number
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid input. Please enter a valid order ID.");
                                    break;
                                }
                                System.out.println("Searching for orders matching: " + searchOrderQuery);

                                LinearSearch<Order> linearSearch = new LinearSearch<>();
                                QueueADT<Order> ordersQueue = orderBuddy.getOrders();
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

                                // Make loggedInUser final
                                final User finalLoggedInUser = loggedInUser;

                                // Perform the search
                                int index = linearSearch.search(orderList, null,
                                        order -> order.getCustomerId() == finalLoggedInUser.getUserId()
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

                                QueueADT<Order> ordersQueue = orderBuddy.getOrders();
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
                            // Logout
                            loggedInUser = null;
                            System.out.println("Logged out successfully.");
                            break;

                        default:
                            System.out.println("Invalid choice. Please enter your choice again!");
                            break;
                    }
                } else if (loggedInUser instanceof Admin) {
                    // Admin menu
                    System.out.println("1. Display all books");
                    System.out.println("2. Add new book");
                    System.out.println("3. Update book details");
                    System.out.println("4. Remove book");
                    System.out.println("5. View all orders");
                    System.out.println("6. Logout");
                    System.out.println("----------------------");

                    int choice = 0;
                    while (true) {
                        try {
                            System.out.print("Enter your choice: ");
                            choice = Integer.parseInt(scanner.nextLine());
                            if (choice < 1 || choice > 6) {
                                throw new NumberFormatException();
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                        }
                    }

                    switch (choice) {
                        case 1:
                            // Display all books
                            bookBuddy.displayBooks();
                            break;

                        case 2:
                            // Add new book
                            System.out.println("----------------------");
                            System.out.print("Enter the book title: ");
                            String title = scanner.nextLine();
                            System.out.print("Enter the book author: ");
                            String author = scanner.nextLine();
                            double price = 0;
                            while (true) {
                                try {
                                    System.out.print("Enter the book price: ");
                                    price = Double.parseDouble(scanner.nextLine());
                                    break;
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid input. Please enter a valid price.");
                                }
                            }
                            int stockQuantity = 0;
                            while (true) {
                                try {
                                    System.out.print("Enter the stock quantity: ");
                                    stockQuantity = Integer.parseInt(scanner.nextLine());
                                    break;
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid input. Please enter a valid stock quantity.");
                                }
                            }
                            Book newBook = new Book(bookBuddy.getBooks().size() + 1, title, author, price,
                                    stockQuantity);
                            bookBuddy.addBook(newBook);
                            System.out.println("Book added successfully!");
                            break;

                        case 3:
                            // Update book details
                            System.out.println("----------------------");
                            System.out.print("Enter the book ID to update: ");
                            int bookIdToUpdate = Integer.parseInt(scanner.nextLine());
                            Book bookToUpdate = bookBuddy.getBookById(bookIdToUpdate);
                            if (bookToUpdate != null) {
                                System.out.print("Enter the new title (leave blank to keep current): ");
                                String newTitle = scanner.nextLine();
                                System.out.print("Enter the new author (leave blank to keep current): ");
                                String newAuthor = scanner.nextLine();
                                System.out.print("Enter the new price (leave blank to keep current): ");
                                String newPriceStr = scanner.nextLine();
                                System.out.print("Enter the new stock quantity (leave blank to keep current): ");
                                String newStockQuantityStr = scanner.nextLine();

                                if (!newTitle.isEmpty()) {
                                    bookToUpdate.setTitle(newTitle);
                                }
                                if (!newAuthor.isEmpty()) {
                                    bookToUpdate.setAuthor(newAuthor);
                                }
                                if (!newPriceStr.isEmpty()) {
                                    double newPrice = Double.parseDouble(newPriceStr);
                                    bookToUpdate.setPrice(newPrice);
                                }
                                if (!newStockQuantityStr.isEmpty()) {
                                    int newStockQuantity = Integer.parseInt(newStockQuantityStr);
                                    bookToUpdate.setStockQuantity(newStockQuantity);
                                }

                                System.out.println("Book details updated successfully!");
                            } else {
                                System.out.println("No book found with ID: " + bookIdToUpdate);
                            }
                            break;

                        case 4:
                            // Remove book
                            System.out.println("----------------------");
                            System.out.print("Enter the book ID to remove: ");
                            int bookIdToRemove = Integer.parseInt(scanner.nextLine());
                            if (bookBuddy.removeBook(bookIdToRemove)) {
                                System.out.println("Book removed successfully!");
                            } else {
                                System.out.println("No book found with ID: " + bookIdToRemove);
                            }
                            break;

                        case 5:
                            // View all orders as admin
                            System.out.println("----------------------");
                            System.out.println("All Orders:");
                            QueueADT<Order> ordersQueue = orderBuddy.getOrders();
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
                                    orderId = Integer.parseInt(searchOrderQuery); // Check if the input is a valid
                                                                                  // number
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid input. Please enter a valid order ID.");
                                    break;
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
                            break;

                        case 6:
                            // Logout
                            loggedInUser = null;
                            System.out.println("Logged out successfully.");
                            break;

                        default:
                            System.out.println("Invalid choice. Please enter your choice again!");
                            break;
                    }
                }
            }
        }

        scanner.close();
        System.out.println("Exiting ...");
    }
}