package BooksApp.customer;

import BooksApp.adt.ArrayListADT;

public class CustomerBuddy {
    private ArrayListADT<Customer> customers;
    private static int customerIdCounter = 1;

    public CustomerBuddy() {
        customers = new ArrayListADT<>();
    }

    public void registerCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer loginCustomer(String email, String password) {
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                return customer;
            }
        }
        return null;
    }

    private static int generateCustomerId() {
        return customerIdCounter++;
    }

    public Customer createCustomer(String name, String email, String password, String address) {
        int customerId = generateCustomerId();
        return new Customer(customerId, name, email, password, address);
    }
}