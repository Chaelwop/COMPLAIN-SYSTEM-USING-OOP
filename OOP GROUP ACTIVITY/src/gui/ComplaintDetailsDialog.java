package gui;

import model.Complaint;

import javax.swing.*;
import java.awt.*;

public class ComplaintDetailsDialog extends JDialog {

    public ComplaintDetailsDialog(
            Frame parent,
            Complaint complaint) {

        super(
            parent,
            "Complaint Details",
            true
        );

        setSize(600, 550);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title =
            new JLabel("Complaint Details");

        title.setBounds(200, 20, 250, 30);
        title.setFont(
            new Font("Arial", Font.BOLD, 20)
        );

        panel.add(title);

        addLabel(
            panel,
            "Complaint ID:",
            complaint.getComplaintId(),
            70
        );

        addLabel(
            panel,
            "Student ID:",
            complaint.getStudentId(),
            105
        );

        addLabel(
            panel,
            "Student Name:",
            complaint.getStudentName(),
            140
        );

        addLabel(
            panel,
            "Category:",
            complaint.getCategory(),
            175
        );

        addLabel(
            panel,
            "Subject:",
            complaint.getSubject(),
            210
        );

        addLabel(
            panel,
            "Date Submitted:",
            complaint.getDateSubmitted(),
            245
        );

        addLabel(
            panel,
            "Status:",
            complaint.getStatus(),
            280
        );

        JLabel descriptionLabel =
            new JLabel("Description:");

        descriptionLabel.setBounds(
            50, 320, 100, 25
        );

        panel.add(descriptionLabel);

        JTextArea description =
            new JTextArea(
                complaint.getDescription()
            );

        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setEditable(false);

        JScrollPane descriptionScroll =
            new JScrollPane(description);

        descriptionScroll.setBounds(
            150, 320, 370, 70
        );

        panel.add(descriptionScroll);

        JLabel remarksLabel =
            new JLabel("Admin Remarks:");

        remarksLabel.setBounds(
            50, 405, 100, 25
        );

        panel.add(remarksLabel);

        String remarks =
            complaint.getAdminRemarks();

        if (remarks.isEmpty()) {
            remarks = "No remarks yet.";
        }

        JTextArea remarksArea =
            new JTextArea(remarks);

        remarksArea.setLineWrap(true);
        remarksArea.setWrapStyleWord(true);
        remarksArea.setEditable(false);

        JScrollPane remarksScroll =
            new JScrollPane(remarksArea);

        remarksScroll.setBounds(
            150, 405, 370, 70
        );

        panel.add(remarksScroll);

        JButton closeButton =
            new JButton("Close");

        closeButton.setBounds(
            240, 490, 120, 30
        );

        panel.add(closeButton);

        closeButton.addActionListener(
            e -> dispose()
        );

        add(panel);
    }

    private void addLabel(
            JPanel panel,
            String label,
            String value,
            int y) {

        JLabel labelText =
            new JLabel(label);

        labelText.setBounds(
            50, y, 100, 25
        );

        panel.add(labelText);

        JLabel valueText =
            new JLabel(value);

        valueText.setBounds(
            150, y, 370, 25
        );

        panel.add(valueText);
    }
}