// FILE: customer/CustomerBuddy.java
package BooksApplication.customer;

import BooksApplication.adt.ArrayListADT;

public class CustomerBuddy {
    // List to store customer objects
    private ArrayListADT<Customer> customers;
    // Static counter to generate unique customer IDs
    private static int customerIdCounter = 1;

    // Constructor to initialize the customer list
    public CustomerBuddy() {
        customers = new ArrayListADT<>();
    }

    // Method to register a new customer
    public void registerCustomer(Customer customer) {
        customers.add(customer);
    }

    // Method to login a customer by email and password
    public Customer loginCustomer(String email, String password) {
        // Iterate through the customer list
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            // Check if the email and password match
            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                return customer; // Customer found
            }
        }
        return null; // Customer not found
    }

    // Static method to generate a unique customer ID
    private static int generateCustomerId() {
        return customerIdCounter++;
    }

    // Method to create a new customer with the given details
    public Customer createCustomer(String name, String email, String password, String address) {
        int customerId = generateCustomerId();
        return new Customer(customerId, name, email, password, address);
    }
}