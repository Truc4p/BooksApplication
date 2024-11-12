package BooksApp.models;

import BooksApp.adt.ArrayListADT;
import BooksApp.algo.QuickSort;

public class BookBuddy {
    private ArrayListADT<Book> books;

    public BookBuddy() {
        // Initialize the books list with some sample data
        this.books = new ArrayListADT<>();
        books.add(new Book(1, "The Catcher in the Rye", "J.D. Salinger", 10.99, 5));
        books.add(new Book(2, "1984", "George Orwell", 8.99, 3));
        books.add(new Book(3, "To Kill a Mockingbird", "Harper Lee", 12.99, 7));
        books.add(new Book(4, "The Great Gatsby", "F. Scott Fitzgerald", 7.99, 2));
    }

    // Method to display books
    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books to display.");
        } else {
            // Sort books using QuickSort
            QuickSort<Book> sorter = new QuickSort<>();
            sorter.sort(books);

            // Display sorted books
            System.out.println("Book List:");
            for (int i = 0; i < books.size(); i++) {
                Book currentBook = books.get(i);
                System.out.println(currentBook);
            }
        }
    }

    // Method to search for books by ID, title, or author
    public void searchBook(String query) {
        System.out.println("Searching for books matching: " + query);
        boolean found = false;
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                book.getAuthor().toLowerCase().contains(query.toLowerCase()) ||
                Integer.toString(book.getBookId()).equals(query)) {
                found = true;
                System.out.println(book);
            }
        }
        if (!found) {
            System.out.println("No books found matching: " + query);
        }
    }

    // Method to display a book's details by its ID
    public void displayBook(int bookId) {
        Book book = getBookById(bookId);
        if (book != null) {
            System.out.println(book);
        } else {
            System.out.println("No book found with ID: " + bookId);
        }
    }

    // Method to get a book by its ID
    public Book getBookById(int bookId) {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null; // If no book found, return null
    }
}