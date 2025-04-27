/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author hieum
 */
public class Enrollment {
    private int enrollmentID;
    private User user;
    private Course course;
    private String note;
    private Date enrolledAt;
    private Admin admin;
    private EnrollmentStatus status;

    public Enrollment() {
    }

    public Enrollment(int enrollmentID, String note, Date enrolledAt) {
        this.enrollmentID = enrollmentID;
        this.note = note;
        this.enrolledAt = enrolledAt;
    }
    
    public Enrollment(int enrollmentID, User user, Course course, String note, Date enrolledAt, Admin admin, EnrollmentStatus status) {
        this.enrollmentID = enrollmentID;
        this.user = user;
        this.course = course;
        this.note = note;
        this.enrolledAt = enrolledAt;
        this.admin = admin;
        this.status = status;
    }

    public Enrollment(int enrollmentID, String note, Admin admin, EnrollmentStatus status) {
        this.enrollmentID = enrollmentID;
        this.note = note;
        this.admin = admin;
        this.status = status;
    }
    
    public Enrollment(User user, Course course) {
        this.user = user;
        this.course = course;
    }

    public int getEnrollmentID() {
        return enrollmentID;
    }

    public void setEnrollmentID(int enrollmentID) {
        this.enrollmentID = enrollmentID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getEnrolledAt() {
        return enrolledAt;
    }

    public void setEnrolledAt(Date enrolledAt) {
        this.enrolledAt = enrolledAt;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }
    
    
    
    
    
    
    
    
    
}
