// FILE: admin/AdminBuddy.java
package BooksApplication.admin;

import BooksApplication.adt.ArrayListADT;
import BooksApplication.customer.Customer;

public class AdminBuddy {
    // List to store admin objects
    private ArrayListADT<Admin> admins;
    // Static counter to generate unique admin IDs
    private static int adminIdCounter = 1;

    // Constructor to initialize the admin list
    public AdminBuddy() {
        admins = new ArrayListADT<>();
    }

    // Method to register a new admin
    public void registerAdmin(Admin admin) {
        admins.add(admin);
    }

    // Method to login an admin by email and password
    public Admin loginAdmin(String email, String password) {
        for (int i = 0; i < admins.size(); i++) {
            Admin admin = admins.get(i);
            if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
                return admin;
            }
        }
        return null;
    }

    // Static method to generate a unique admin ID
    private static int generateAdminId() {
        return adminIdCounter++;
    }

    // Method to create a new admin with the given details
    public Admin createAdmin(String name, String email, String password) {
        int adminId = generateAdminId();
        return new Admin(adminId, name, email, password);
    }
}