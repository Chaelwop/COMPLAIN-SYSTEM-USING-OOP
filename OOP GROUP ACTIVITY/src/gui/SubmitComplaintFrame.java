package gui;

import model.Complaint;
import model.Student;

import service.ComplaintManager;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SubmitComplaintFrame extends JFrame {

    private Student student;
    private StudentDashboard dashboard;

    private JComboBox<String> categoryBox;
    private JTextField subjectField;
    private JTextArea descriptionArea;

    public SubmitComplaintFrame(
            StudentDashboard dashboard,
            Student student) {

        this.dashboard = dashboard;
        this.student = student;

        setTitle("Submit Complaint");
        setSize(550, 500);
        setLocationRelativeTo(dashboard);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("Submit Complaint");

        title.setBounds(180, 20, 250, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        panel.add(title);

        JLabel studentIdLabel =
            new JLabel("Student ID:");

        studentIdLabel.setBounds(40, 70, 100, 25);

        panel.add(studentIdLabel);

        JLabel studentIdValue =
            new JLabel(student.getStudentId());

        studentIdValue.setBounds(150, 70, 300, 25);

        panel.add(studentIdValue);

        JLabel nameLabel =
            new JLabel("Student Name:");

        nameLabel.setBounds(40, 105, 100, 25);

        panel.add(nameLabel);

        JLabel nameValue =
            new JLabel(student.getFullName());

        nameValue.setBounds(150, 105, 300, 25);

        panel.add(nameValue);

        JLabel categoryLabel =
            new JLabel("Category:");

        categoryLabel.setBounds(40, 140, 100, 25);

        panel.add(categoryLabel);

        String[] categories = {
            "Academic",
            "Faculty",
            "Facilities",
            "Laboratory",
            "Internet",
            "Bullying",
            "Other"
        };

        categoryBox = new JComboBox<>(categories);

        categoryBox.setBounds(150, 140, 300, 25);

        panel.add(categoryBox);

        JLabel subjectLabel =
            new JLabel("Subject:");

        subjectLabel.setBounds(40, 180, 100, 25);

        panel.add(subjectLabel);

        subjectField = new JTextField();

        subjectField.setBounds(150, 180, 300, 25);

        panel.add(subjectField);

        JLabel descriptionLabel =
            new JLabel("Description:");

        descriptionLabel.setBounds(40, 220, 100, 25);

        panel.add(descriptionLabel);

        descriptionArea = new JTextArea();

        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        JScrollPane scrollPane =
            new JScrollPane(descriptionArea);

        scrollPane.setBounds(150, 220, 300, 130);

        panel.add(scrollPane);

        JButton submitButton =
            new JButton("Submit");

        submitButton.setBounds(150, 380, 120, 30);

        panel.add(submitButton);

        JButton cancelButton =
            new JButton("Cancel");

        cancelButton.setBounds(280, 380, 120, 30);

        panel.add(cancelButton);

        submitButton.addActionListener(e -> submitComplaint());

        cancelButton.addActionListener(e -> dispose());

        add(panel);
    }

    private void submitComplaint() {

        String subject = subjectField.getText().trim();

        String description =
            descriptionArea.getText().trim();

        String category =
            categoryBox.getSelectedItem().toString();

        if (subject.isEmpty() || description.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter subject and description."
            );

            return;
        }

        String complaintId =
            ComplaintManager.generateComplaintId();

        String date =
            new SimpleDateFormat(
                "MMMM dd, yyyy"
            ).format(new Date());

        Complaint complaint = new Complaint(
            complaintId,
            student.getStudentId(),
            student.getFullName(),
            category,
            subject,
            description,
            date
        );

        ComplaintManager.addComplaint(complaint);

        JOptionPane.showMessageDialog(
            this,
            "Complaint submitted successfully!\n\n"
            + "Complaint ID: " + complaintId
        );

        dashboard.refreshTable();

        dispose();
    }
}