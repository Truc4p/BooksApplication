package BooksApp.models;

import BooksApp.adt.ArrayListADT;

public class UserBuddy {
    private ArrayListADT<User> users;
    private static int userIdCounter = 1;

    public UserBuddy() {
        users = new ArrayListADT<>();
    }

    public void registerUser(User user) {
        users.add(user);
    }

    public User loginUser(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private static int generateUserId() {
        return userIdCounter++;
    }

    public User createCustomer(String name, String email, String password, String address) {
        int userId = generateUserId();
        return new Customer(userId, name, email, password, address);
    }

    public User createAdmin(String name, String email, String password) {
        int userId = generateUserId();
        return new Admin(userId, name, email, password);
    }
}