package gui;

import model.Admin;
import model.Complaint;

import service.ComplaintManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class AdminDashboard extends JFrame {

    private Admin admin;

    private JTable table;
    private DefaultTableModel model;

    private JTextField searchField;

    public AdminDashboard(Admin admin) {

        this.admin = admin;

        setTitle("Admin Dashboard");
        setSize(1100, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel topPanel = new JPanel(new BorderLayout());

        JLabel title =
            new JLabel("CEC Complaint Management System - Admin");

        title.setFont(new Font("Arial", Font.BOLD, 18));

        topPanel.add(title, BorderLayout.WEST);

        JButton logoutButton =
            new JButton("Logout");

        topPanel.add(logoutButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel();

        searchField = new JTextField(25);

        JButton searchButton =
            new JButton("Search");

        JButton showAllButton =
            new JButton("Show All");

        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(showAllButton);

        add(searchPanel, BorderLayout.SOUTH);

        String[] columns = {
            "Complaint ID",
            "Student ID",
            "Student Name",
            "Category",
            "Subject",
            "Date",
            "Status"
        };

        model = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(
                    int row,
                    int column) {

                return false;
            }
        };

        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton viewButton =
            new JButton("View Details");

        JButton updateButton =
            new JButton("Update Status");

        JButton deleteButton =
            new JButton("Delete");

        buttonPanel.add(viewButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.WEST);

        searchButton.addActionListener(e -> search());

        showAllButton.addActionListener(e -> refreshTable());

        viewButton.addActionListener(e -> viewDetails());

        updateButton.addActionListener(e -> updateComplaint());

        deleteButton.addActionListener(e -> deleteComplaint());

        logoutButton.addActionListener(e -> logout());

        refreshTable();
    }

    private void refreshTable() {

        model.setRowCount(0);

        ArrayList<Complaint> complaints =
            ComplaintManager.getAllComplaints();

        for (Complaint complaint : complaints) {

            addComplaintToTable(complaint);
        }
    }

    private void addComplaintToTable(
            Complaint complaint) {

        model.addRow(new Object[] {

            complaint.getComplaintId(),
            complaint.getStudentId(),
            complaint.getStudentName(),
            complaint.getCategory(),
            complaint.getSubject(),
            complaint.getDateSubmitted(),
            complaint.getStatus()
        });
    }

    private void search() {

        String keyword =
            searchField.getText().trim();

        if (keyword.isEmpty()) {

            refreshTable();
            return;
        }

        model.setRowCount(0);

        ArrayList<Complaint> results =
            ComplaintManager.search(keyword);

        for (Complaint complaint : results) {

            addComplaintToTable(complaint);
        }
    }

    private Complaint getSelectedComplaint() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Please select a complaint."
            );

            return null;
        }

        String complaintId =
            model.getValueAt(row, 0).toString();

        return ComplaintManager.findComplaint(
            complaintId
        );
    }

    private void viewDetails() {

        Complaint complaint =
            getSelectedComplaint();

        if (complaint != null) {

            new ComplaintDetailsDialog(
                this,
                complaint
            ).setVisible(true);
        }
    }

    private void updateComplaint() {

        Complaint complaint =
            getSelectedComplaint();

        if (complaint == null) {
            return;
        }

        String[] statuses = {
            "Pending",
            "Under Review",
            "Resolved",
            "Rejected"
        };

        JComboBox<String> statusBox =
            new JComboBox<>(statuses);

        statusBox.setSelectedItem(
            complaint.getStatus()
        );

        JTextArea remarksArea =
            new JTextArea(5, 30);

        remarksArea.setLineWrap(true);
        remarksArea.setWrapStyleWord(true);

        remarksArea.setText(
            complaint.getAdminRemarks()
        );

        JPanel panel = new JPanel(
            new BorderLayout(5, 5)
        );

        panel.add(
            new JLabel("Status:"),
            BorderLayout.NORTH
        );

        panel.add(
            statusBox,
            BorderLayout.CENTER
        );

        JPanel remarksPanel =
            new JPanel(new BorderLayout());

        remarksPanel.add(
            new JLabel("Admin Remarks:"),
            BorderLayout.NORTH
        );

        remarksPanel.add(
            new JScrollPane(remarksArea),
            BorderLayout.CENTER
        );

        JPanel mainPanel =
            new JPanel(new BorderLayout(5, 5));

        mainPanel.add(panel, BorderLayout.NORTH);
        mainPanel.add(remarksPanel, BorderLayout.CENTER);

        int result = JOptionPane.showConfirmDialog(
            this,
            mainPanel,
            "Update Complaint",
            JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {

            complaint.setStatus(
                statusBox.getSelectedItem().toString()
            );

            complaint.setAdminRemarks(
                remarksArea.getText().trim()
            );

            refreshTable();

            JOptionPane.showMessageDialog(
                this,
                "Complaint updated successfully."
            );
        }
    }

    private void deleteComplaint() {

        Complaint complaint =
            getSelectedComplaint();

        if (complaint == null) {
            return;
        }

        int answer = JOptionPane.showConfirmDialog(
            this,
            "Delete complaint "
            + complaint.getComplaintId()
            + "?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION
        );

        if (answer == JOptionPane.YES_OPTION) {

            ComplaintManager.deleteComplaint(
                complaint.getComplaintId()
            );

            refreshTable();

            JOptionPane.showMessageDialog(
                this,
                "Complaint deleted."
            );
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