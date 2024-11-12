package BooksApp.models;

import BooksApp.adt.ArrayListADT;

public class CustomerBuddy {
    private List<Customer> customers;

    public CustomerBuddy() {
        customers = new ArrayListADT<>();
        initializeCustomers();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer getCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }
}