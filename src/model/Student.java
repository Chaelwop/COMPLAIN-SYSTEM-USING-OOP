package model;

public class Student extends User {

    private String studentId;

    public Student(String username, String password,
                   String fullName, String studentId) {

        super(username, password, fullName);
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    // Polymorphism
    @Override
    public String getDashboardTitle() {
        return "Student Dashboard";
    }

    @Override
    public String getUserType() {
        return "Student";
    }
}