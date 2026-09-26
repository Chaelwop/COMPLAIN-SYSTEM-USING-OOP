package service;

import model.Admin;
import model.Student;
import model.User;

import java.util.ArrayList;

public class UserManager {

    private static ArrayList<User> users = new ArrayList<User>();

    public static void initialize() {

        if (users.size() == 0) {

            Admin admin = new Admin(
                "admin",
                "admin123",
                "System Administrator"
            );

            users.add(admin);
        }
    }

    public static boolean usernameExists(String username) {

        for (User user : users) {

            if (user.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }

        return false;
    }

    public static boolean addStudent(
            String username,
            String password,
            String fullName,
            String studentId) {

        if (usernameExists(username)) {
            return false;
        }

        Student student = new Student(
            username,
            password,
            fullName,
            studentId
        );

        users.add(student);

        return true;
    }

    public static User login(String username, String password) {

        for (User user : users) {

            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {

                return user;
            }
        }

        return null;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }
}