package BooksApp.models;

public class Book implements Comparable<Book> {
    private int bookId;
    private String title;
    private String author;
    private double price;
    private int stockQuantity;

    public Book(int bookId, String title, String author, double price, int stockQuantity) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    @Override
    public int compareTo(Book other) {
        return Integer.compare(this.bookId, other.bookId);
    }

    @Override
    public String toString() {
        return String.format("Book ID: %d\tTitle: %s\tAuthor: %s\tPrice: %.2f\tStock Quantity: %d",
                bookId, title, author, price, stockQuantity);
    }
}