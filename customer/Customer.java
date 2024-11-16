// FILE: customer/Customer.java
package BooksApp.customer;

import BooksApp.models.User;

public class Customer extends User {
    private String address; // Address of the customer

    // Constructor to initialize the customer object
    public Customer(int userId, String name, String email, String password, String address) {
        super(userId, name, email, password); // Call the superclass constructor
        this.address = address; // Set the address
    }

    // Getter method to retrieve the address
    public String getAddress() {
        return address;
    }

    // Override method to return the user type
    @Override
    public String getUserType() {
        return "Customer";
    }

    // Override method to return a string representation of the customer object
    @Override
    public String toString() {
        return "Customer{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}