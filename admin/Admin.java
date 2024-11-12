// FILE: admin/Admin.java
package BooksApp.admin;

import BooksApp.models.User;

public class Admin extends User {
    public Admin(int userId, String name, String email, String password) {
        super(userId, name, email, password);
    }

    @Override
    public String getUserType() {
        return "Admin";
    }
}