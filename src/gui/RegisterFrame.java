package gui;

import service.UserManager;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {

    private JTextField fullNameField;
    private JTextField studentIdField;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public RegisterFrame() {

        setTitle("Student Registration");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("Student Registration");

        title.setBounds(130, 25, 250, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        panel.add(title);

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(50, 80, 100, 25);

        panel.add(nameLabel);

        fullNameField = new JTextField();
        fullNameField.setBounds(160, 80, 220, 25);

        panel.add(fullNameField);

        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setBounds(50, 120, 100, 25);

        panel.add(idLabel);

        studentIdField = new JTextField();
        studentIdField.setBounds(160, 120, 220, 25);

        panel.add(studentIdField);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 160, 100, 25);

        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(160, 160, 220, 25);

        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 200, 100, 25);

        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(160, 200, 220, 25);

        panel.add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(160, 250, 100, 30);

        panel.add(registerButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(270, 250, 110, 30);

        panel.add(backButton);

        registerButton.addActionListener(e -> register());

        backButton.addActionListener(e -> {

            new LoginFrame().setVisible(true);
            dispose();

        });

        add(panel);
    }

    private void register() {

        String fullName = fullNameField.getText().trim();
        String studentId = studentIdField.getText().trim();
        String username = usernameField.getText().trim();

        String password =
            new String(passwordField.getPassword());

        if (fullName.isEmpty()
                || studentId.isEmpty()
                || username.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Please complete all fields."
            );

            return;
        }

        if (UserManager.usernameExists(username)) {

            JOptionPane.showMessageDialog(
                this,
                "Username already exists."
            );

            return;
        }

        boolean success = UserManager.addStudent(
            username,
            password,
            fullName,
            studentId
        );

        if (success) {

            JOptionPane.showMessageDialog(
                this,
                "Registration successful!"
            );

            new LoginFrame().setVisible(true);
            dispose();
        }
    }
}