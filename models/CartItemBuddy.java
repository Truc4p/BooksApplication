// FILE: models/CartItemBuddy.java
package BooksApplication.models;

import BooksApplication.adt.ArrayListADT;
import BooksApplication.algo.QuickSort;
import java.util.Scanner;

public class CartItemBuddy {
    private ArrayListADT<CartItem> cart; // List to store cart items

    // Constructor
    public CartItemBuddy() {
        this.cart = new ArrayListADT<>();
    }

    // Method to add a CartItem to the cart
    public void addToCart(CartItem cartItem) {
        cart.add(cartItem);
    }

    // Method to add a CartItem to the cart
    public void addToCart(Book book, int quantity) {
        // Check if the book is already in the cart
        for (int i = 0; i < cart.size(); i++) {
            CartItem cartItem = cart.get(i);
            if (cartItem.getBookId() == book.getBookId()) {
                // If the book is already in the cart, update the quantity
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                return;
            }
        }
        // If the book is not in the cart, add a new CartItem
        CartItem newCartItem = new CartItem(book.getBookId(), book.getTitle(), book.getAuthor(), book.getPrice(),
                quantity);
        cart.add(newCartItem);
    }

    // Method to view the cart, sort and display the cart items
    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            QuickSort<CartItem> sorter = new QuickSort<>();
            sorter.sort(cart); // Sort cart items by title before displaying
            System.out.println("Sorted Cart:");
            for (int i = 0; i < cart.size(); i++) {
                CartItem cartItem = cart.get(i);
                System.out.println(cartItem);
            }
        }
    }

    // Method to remove a CartItem from the cart by book ID
    public void removeFromCart(int bookId) {
        CartItem itemToRemove = null;
        // Find the CartItem with the given book ID
        for (int i = 0; i < cart.size(); i++) {
            CartItem cartItem = cart.get(i);
            if (cartItem.getBookId() == bookId) {
                itemToRemove = cartItem;
                break;
            }
        }
        // If the CartItem is found, remove it from the cart
        if (itemToRemove != null) {
            cart.remove(itemToRemove);
            System.out.println("Book removed from cart!");
        } else {
            System.out.println("Book not found in cart.");
        }
    }

    // Method to remove a book from the cart with user input
    public void removeBookFromCart(Scanner scanner) {
        System.out.println("----------------------");
        System.out.print("Enter the book ID to remove from cart or 'n' to cancel: ");
        String inputCart = scanner.nextLine();
        if (!inputCart.equalsIgnoreCase("n")) {
            try {
                int bookIdToRemove = Integer.parseInt(inputCart);
                removeFromCart(bookIdToRemove); // Remove from cart
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid book ID.");
            }
        }
    }

    // Method to get a CartItem by book ID
    public CartItem getCartItemById(int bookId) {
        // Iterate through the cart to find the CartItem with the given book ID
        for (int i = 0; i < cart.size(); i++) {
            CartItem cartItem = cart.get(i);
            if (cartItem.getBookId() == bookId) {
                return cartItem;
            }
        }
        return null; // If no CartItem found, return null
    }

    // Method to check if the cart is empty
    public boolean isEmpty() {
        return cart.isEmpty();
    }

    // Method to get all books in the cart
    public ArrayListADT<CartItem> getBooksInCart() {
        return cart;
    }

    // Method to get the quantity of a specific book in the cart
    public int getQuantityInCart(int bookId) {
        // Iterate through the cart to find the CartItem with the given book ID
        for (int i = 0; i < cart.size(); i++) {
            CartItem cartItem = cart.get(i);
            if (cartItem.getBookId() == bookId) {
                return cartItem.getQuantity();
            }
        }
        return 0; // If no CartItem found, return 0
    }

    // Method to clear the cart
    public void clearCart() {
        cart.clear();
    }
}