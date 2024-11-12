package BooksApp.models;

import BooksApp.adt.ArrayListADT;
import BooksApp.algo.QuickSort;

public class CartItemBuddy {
    private ArrayListADT<CartItem> cart;

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
        for (int i = 0; i < cart.size(); i++) {
            CartItem cartItem = cart.get(i);
            if (cartItem.getBookId() == book.getBookId()) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                return;
            }
        }
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
        for (int i = 0; i < cart.size(); i++) {
            CartItem cartItem = cart.get(i);
            if (cartItem.getBookId() == bookId) {
                itemToRemove = cartItem;
                break;
            }
        }
        if (itemToRemove != null) {
            cart.remove(itemToRemove);
            System.out.println("Book removed from cart!");
        } else {
            System.out.println("Book not found in cart.");
        }
    }

    // Method to get a CartItem by book ID
    public CartItem getCartItemById(int bookId) {
        for (int i = 0; i < cart.size(); i++) {
            CartItem cartItem = cart.get(i);
            if (cartItem.getBookId() == bookId) {
                return cartItem;
            }
        }
        return null; // If no CartItem found, return null
    }

    public boolean isEmpty() {
        return cart.isEmpty();
    }

    public ArrayListADT<CartItem> getBooksInCart() {
        return cart;
    }

    public int getQuantityInCart(int bookId) {
        for (int i = 0; i < cart.size(); i++) {
            CartItem cartItem = cart.get(i);
            if (cartItem.getBookId() == bookId) {
                return cartItem.getQuantity();
            }
        }
        return 0;
    }

    public void clearCart() {
        cart.clear();
    }
}