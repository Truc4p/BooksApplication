// FILE: admin/Admin.java
package BooksApp.admin;

import BooksApp.models.User;

public class Admin extends User {
    
    // Constructor for the Admin class, which calls the superclass (User) constructor
    public Admin(int userId, String name, String email, String password) {
        super(userId, name, email, password);
    }

    // Overrides the getUserType method from the User class to return "Admin"
    @Override
    public String getUserType() {
        return "Admin";
    }
}