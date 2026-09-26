package gui;

import model.Admin;
import model.Student;
import model.User;

import service.UserManager;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {

        setTitle("Cebu Eastern College Complaint Management System");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel(
            "CEC Complaint Management System"
        );

        title.setBounds(70, 30, 320, 30);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        panel.add(title);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(60, 90, 100, 25);

        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(160, 90, 220, 25);

        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(60, 130, 100, 25);

        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(160, 130, 220, 25);

        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(160, 175, 100, 30);

        panel.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(270, 175, 110, 30);

        panel.add(registerButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(160, 220, 220, 30);

        panel.add(exitButton);

        JLabel info = new JLabel(
            "Admin: admin / admin123"
        );

        info.setBounds(130, 270, 250, 25);

        panel.add(info);

        loginButton.addActionListener(e -> login());

        registerButton.addActionListener(e -> {

            new RegisterFrame().setVisible(true);
            dispose();

        });

        exitButton.addActionListener(e -> System.exit(0));

        add(panel);
    }

    private void login() {

        String username = usernameField.getText().trim();

        String password =
            new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter username and password."
            );

            return;
        }

        User user = UserManager.login(username, password);

        if (user == null) {

            JOptionPane.showMessageDialog(
                this,
                "Invalid username or password.",
                "Login Failed",
                JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        JOptionPane.showMessageDialog(
            this,
            "Login successful!"
        );

        dispose();

        if (user instanceof Admin) {

            new AdminDashboard((Admin) user).setVisible(true);

        } else if (user instanceof Student) {

            new StudentDashboard((Student) user).setVisible(true);
        }
    }
}