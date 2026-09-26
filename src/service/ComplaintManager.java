package service;

import model.Complaint;

import java.util.ArrayList;

public class ComplaintManager {

    private static ArrayList<Complaint> complaints =
        new ArrayList<Complaint>();

    public static String generateComplaintId() {

        int number = 1;

        while (true) {

            String id = String.format("C%03d", number);

            boolean exists = false;

            for (Complaint complaint : complaints) {

                if (complaint.getComplaintId().equals(id)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                return id;
            }

            number++;
        }
    }

    public static void addComplaint(Complaint complaint) {
        complaints.add(complaint);
    }

    public static ArrayList<Complaint> getAllComplaints() {
        return complaints;
    }

    public static ArrayList<Complaint> getStudentComplaints(
            String studentId) {

        ArrayList<Complaint> result =
            new ArrayList<Complaint>();

        for (Complaint complaint : complaints) {

            if (complaint.getStudentId().equals(studentId)) {
                result.add(complaint);
            }
        }

        return result;
    }

    public static Complaint findComplaint(String complaintId) {

        for (Complaint complaint : complaints) {

            if (complaint.getComplaintId().equals(complaintId)) {
                return complaint;
            }
        }

        return null;
    }

    public static void deleteComplaint(String complaintId) {

        Complaint complaint = findComplaint(complaintId);

        if (complaint != null) {
            complaints.remove(complaint);
        }
    }

    public static ArrayList<Complaint> search(String keyword) {

        ArrayList<Complaint> result =
            new ArrayList<Complaint>();

        keyword = keyword.toLowerCase();

        for (Complaint complaint : complaints) {

            if (complaint.getComplaintId().toLowerCase().contains(keyword)
                    || complaint.getStudentId().toLowerCase().contains(keyword)
                    || complaint.getStudentName().toLowerCase().contains(keyword)
                    || complaint.getCategory().toLowerCase().contains(keyword)
                    || complaint.getSubject().toLowerCase().contains(keyword)
                    || complaint.getStatus().toLowerCase().contains(keyword)) {

                result.add(complaint);
            }
        }

        return result;
    }
}