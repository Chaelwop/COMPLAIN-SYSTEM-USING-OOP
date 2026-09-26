package model;

public class Admin extends User {

    public Admin(String username, String password, String fullName) {
        super(username, password, fullName);
    }

    // Polymorphism
    @Override
    public String getDashboardTitle() {
        return "Admin Dashboard";
    }

    @Override
    public String getUserType() {
        return "Admin";
    }
}