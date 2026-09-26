package gui;

import model.Complaint;
import model.Student;

import service.ComplaintManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class StudentDashboard extends JFrame {

    private Student student;
    private JTable table;
    private DefaultTableModel model;

    public StudentDashboard(Student student) {

        this.student = student;

        setTitle("Student Dashboard");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel topPanel = new JPanel(new BorderLayout());

        JLabel welcome = new JLabel(
            "Welcome, " + student.getFullName()
        );

        welcome.setFont(new Font("Arial", Font.BOLD, 18));

        topPanel.add(welcome, BorderLayout.WEST);

        JButton logoutButton = new JButton("Logout");

        topPanel.add(logoutButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        String[] columns = {
            "Complaint ID",
            "Category",
            "Subject",
            "Date Submitted",
            "Status"
        };

        model = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();

        JButton submitButton =
            new JButton("Submit Complaint");

        JButton viewButton =
            new JButton("View Details");

        bottomPanel.add(submitButton);
        bottomPanel.add(viewButton);

        add(bottomPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(e -> {

            new SubmitComplaintFrame(this, student)
                .setVisible(true);

        });

        viewButton.addActionListener(e -> viewDetails());

        logoutButton.addActionListener(e -> logout());

        refreshTable();
    }

    public void refreshTable() {

        model.setRowCount(0);

        ArrayList<Complaint> complaints =
            ComplaintManager.getStudentComplaints(
                student.getStudentId()
            );

        for (Complaint complaint : complaints) {

            model.addRow(new Object[] {
                complaint.getComplaintId(),
                complaint.getCategory(),
                complaint.getSubject(),
                complaint.getDateSubmitted(),
                complaint.getStatus()
            });
        }
    }

    private void viewDetails() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Please select a complaint."
            );

            return;
        }

        String complaintId =
            model.getValueAt(row, 0).toString();

        Complaint complaint =
            ComplaintManager.findComplaint(complaintId);

        if (complaint != null) {

            new ComplaintDetailsDialog(
                this,
                complaint
            ).setVisible(true);
        }
    }

    private void logout() {

        int answer = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to logout?",
            "Logout",
            JOptionPane.YES_NO_OPTION
        );

        if (answer == JOptionPane.YES_OPTION) {

            new LoginFrame().setVisible(true);
            dispose();
        }
    }
}