package BooksApp.models;

import BooksApp.adt.ArrayListADT;
import BooksApp.algo.QuickSort;
import BooksApp.algo.BinarySearch;
import BooksApp.algo.LinearSearch;
import java.util.Scanner;

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

        // Sort books using QuickSort
        QuickSort<Book> sorter = new QuickSort<>();
        sorter.sort(books);

        // Search by ID using BinarySearch
        try {
            int bookId = Integer.parseInt(query);
            BinarySearch<Book> binarySearch = new BinarySearch<>();
            int index = binarySearch.search(books, new Book(bookId, "", "", 0, 0));
            if (index != -1) {
                System.out.println(books.get(index));
                found = true;
            }
        } catch (NumberFormatException e) {
            // If query is not an integer, continue to search by title or author
        }

        // Search by title or author using LinearSearch
        if (!found) {
            LinearSearch<Book> linearSearch = new LinearSearch<>();
            int index = linearSearch.search(books, null, book -> 
                book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                book.getAuthor().toLowerCase().contains(query.toLowerCase())
            );
            if (index != -1) {
                System.out.println(books.get(index));
                found = true;
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

    // Method to update book details
    public void updateBookDetails(Scanner scanner) {
        System.out.println("----------------------");
        System.out.print("Enter the book ID to update: ");
        int bookIdToUpdate = Integer.parseInt(scanner.nextLine());
        Book bookToUpdate = getBookById(bookIdToUpdate);
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
    }

    // Method to add a new book
    public void addNewBook(Scanner scanner) {
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
        Book newBook = new Book(books.size() + 1, title, author, price, stockQuantity);
        addBook(newBook);
        System.out.println("Book added successfully!");
    }

    // Method to remove a book
    public void removeBook(Scanner scanner) {
        System.out.println("----------------------");
        System.out.print("Enter the book ID to remove: ");
        int bookIdToRemove = Integer.parseInt(scanner.nextLine());
        if (removeBook(bookIdToRemove)) {
            System.out.println("Book removed successfully!");
        } else {
            System.out.println("No book found with ID: " + bookIdToRemove);
        }
    }

    // Method to get all books
    public ArrayListADT<Book> getBooks() {
        return books;
    }

    // Method to add a book to the list
    public void addBook(Book book) {
        books.add(book);
    }

    public boolean removeBook(int bookId) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookId() == bookId) {
                books.remove(i);
                return true;
            }
        }
        return false;
    }
}