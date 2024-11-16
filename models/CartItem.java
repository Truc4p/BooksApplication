// FILE: models/CartItem.java
package BooksApplication.models;

public class CartItem implements Comparable<CartItem> {
    private int bookId; // Unique identifier for the book
    private String title; // Title of the book
    private String author; // Author of the book
    private double price; // Price of the book
    private int quantity; // Quantity of the book in the cart

    // Constructor to initialize the cart item object
    public CartItem(int bookId, String title, String author, double price, int quantity) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
    }

    // Getter method for bookId
    public int getBookId() {
        return bookId;
    }

    // Getter method for title
    public String getTitle() {
        return title;
    }

    // Getter method for author
    public String getAuthor() {
        return author;
    }

    // Getter method for price
    public double getPrice() {
        return price;
    }

    // Getter method for quantity
    public int getQuantity() {
        return quantity;
    }

    // Setter method for quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Override compareTo method to compare cart items by title
    @Override
    public int compareTo(CartItem other) {
        return this.title.compareTo(other.title);
    }

    // Override toString method to return a string representation of the cart item object
    @Override
    public String toString() {
        return String.format("Book ID: %d\tTitle: %s\tAuthor: %s\tPrice: %.2f\tQuantity: %d",
                bookId, title, author, price, quantity);
    }
}