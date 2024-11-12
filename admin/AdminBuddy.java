package BooksApp.admin;

import BooksApp.adt.ArrayListADT;

public class AdminBuddy {
    private ArrayListADT<Admin> admins;
    private static int adminIdCounter = 1;

    public AdminBuddy() {
        admins = new ArrayListADT<>();
    }

    public void registerAdmin(Admin admin) {
        admins.add(admin);
    }

    public Admin loginAdmin(String email, String password) {
        for (Admin admin : admins) {
            if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
                return admin;
            }
        }
        return null;
    }

    private static int generateAdminId() {
        return adminIdCounter++;
    }

    public Admin createAdmin(String name, String email, String password) {
        int adminId = generateAdminId();
        return new Admin(adminId, name, email, password);
    }
}