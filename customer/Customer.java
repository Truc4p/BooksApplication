// FILE: customer/Customer.java
package BooksApp.customer;

import BooksApp.models.User;

public class Customer extends User {
    private String address;

    public Customer(int userId, String name, String email, String password, String address) {
        super(userId, name, email, password);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String getUserType() {
        return "Customer";
    }
}