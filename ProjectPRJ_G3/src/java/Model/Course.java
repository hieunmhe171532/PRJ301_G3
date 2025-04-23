/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author hieum
 */
public class Course {
    private int courseID;
    private String courseName;
    private String description;
    private String department;
    private int credit;
    private String semester;
    private int maxStudent;
    private Date createdAt;
    private Admin admin;

    public Course() {
    }

    public Course(int courseID, String courseName, String description, String department, int credit, String semester, int maxStudent, Date createdAt, Admin admin) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.description = description;
        this.department = department;
        this.credit = credit;
        this.semester = semester;
        this.maxStudent = maxStudent;
        this.createdAt = createdAt;
        this.admin = admin;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getMaxStudent() {
        return maxStudent;
    }

    public void setMaxStudent(int maxStudent) {
        this.maxStudent = maxStudent;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    
    
    
    
}
