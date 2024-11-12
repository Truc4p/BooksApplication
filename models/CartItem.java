package BooksApp.models;

public class CartItem implements Comparable<CartItem> {
    private int bookId;
    private String title;
    private String author;
    private double price;
    private int quantity;

    public CartItem(int bookId, String title, String author, double price, int quantity) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and setters
    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /* public Book getBook() {
        return new Book(bookId, title, author, price, quantity);
    } */

    @Override
    public int compareTo(CartItem other) {
        return this.title.compareTo(other.title);
    }

    @Override
    public String toString() {
        return String.format("Book ID: %d\tTitle: %s\tAuthor: %s\tPrice: %.2f\tQuantity: %d",
                bookId, title, author, price, quantity);
    }
}