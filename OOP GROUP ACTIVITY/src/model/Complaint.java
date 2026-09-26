package model;

public class Complaint {

    private String complaintId;
    private String studentId;
    private String studentName;
    private String category;
    private String subject;
    private String description;
    private String dateSubmitted;
    private String status;
    private String adminRemarks;

    public Complaint(String complaintId,
                     String studentId,
                     String studentName,
                     String category,
                     String subject,
                     String description,
                     String dateSubmitted) {

        this.complaintId = complaintId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.category = category;
        this.subject = subject;
        this.description = description;
        this.dateSubmitted = dateSubmitted;
        this.status = "Pending";
        this.adminRemarks = "";
    }

    public String getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(String complaintId) {
        this.complaintId = complaintId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(String dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdminRemarks() {
        return adminRemarks;
    }

    public void setAdminRemarks(String adminRemarks) {
        this.adminRemarks = adminRemarks;
    }
}