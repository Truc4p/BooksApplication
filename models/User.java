package BooksApp.models;

public abstract class User {
    protected int userId; // Unique identifier for the user
    protected String name; // Name of the user
    protected String email; // Email of the user
    protected String password; // Password of the user

    // Constructor to initialize the user object
    public User(int userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getter method for userId
    public int getUserId() {
        return userId;
    }

    // Getter method for name
    public String getName() {
        return name;
    }

    // Getter method for email
    public String getEmail() {
        return email;
    }

    // Getter method for password
    public String getPassword() {
        return password;
    }

    // Abstract method to get the user type
    public abstract String getUserType();
}