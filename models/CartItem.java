// FILE: models/CartItem.java
package BooksApplication.models;

public class CartItem implements Comparable<CartItem> {
    private Book book; // Reference to the Book object
    private int quantity; // Quantity of the book in the cart

    // Constructor to initialize the cart item object with a Book reference
    public CartItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    // Getter method for bookId
    public int getBookId() {
        return book.getBookId();
    }

    // Getter method for title
    public String getTitle() {
        return book.getTitle();
    }

    // Getter method for author
    public String getAuthor() {
        return book.getAuthor();
    }

    // Getter method for price
    public double getPrice() {
        return book.getPrice();
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
        return this.getTitle().compareTo(other.getTitle());
    }

    // Override toString method to return a string representation of the cart item object
    @Override
    public String toString() {
        return String.format("Book ID: %d\tTitle: %s\tAuthor: %s\tPrice: %.2f\tQuantity: %d",
                getBookId(), getTitle(), getAuthor(), getPrice(), quantity);
    }
}