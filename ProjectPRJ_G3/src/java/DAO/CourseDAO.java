package DAO;

import Dal.DBContext;
import Model.Admin;
import Model.Course;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseDAO extends DBContext {

    public List<Course> getAllCourse() {
        List<Course> list = new ArrayList<>();

        String sql = "SELECT course_id AS id, course_name AS name, description AS des, department, credits, semester, "
                + "max_students AS max, c.created_at AS date, a.admin_id AS adid, a.full_name AS fname "
                + "FROM Courses c JOIN Admins a ON a.admin_id = c.created_by";

        try (PreparedStatement stm = connection.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Admin admin = new Admin(rs.getInt("adid"), rs.getString("fname"));

                Course course = new Course(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("des"),
                        rs.getString("department"),
                        rs.getInt("credits"),
                        rs.getString("semester"),
                        rs.getInt("max"),
                        rs.getDate("date"),
                        admin // truyền đối tượng Admin vào Course

                );

                list.add(course);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public Course getCourseById(int courseID) {
        String sql = "SELECT course_id AS id, course_name AS name, description AS des, department, credits, semester, "
                + "max_students AS max, c.created_at AS date, a.admin_id AS adid, a.full_name AS fname "
                + "FROM Courses c JOIN Admins a ON a.admin_id = c.created_by "
                + "WHERE c.course_id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, courseID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Admin admin = new Admin(rs.getInt("adid"), rs.getString("fname"));
                return new Course(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("des"),
                        rs.getString("department"),
                        rs.getInt("credits"),
                        rs.getString("semester"),
                        rs.getInt("max"),
                        rs.getDate("date"),
                        admin
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Course addCourse(Course c) {
        String sql = "INSERT INTO Courses (course_name, description, department, "
                + "credits, semester, max_students, created_at, created_by)VALUES (?, ?, ?, ?, ?, ?, GETDATE(), ?);";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, c.getCourseName());
            stm.setString(2, c.getDescription());
            stm.setString(3, c.getDepartment());
            stm.setInt(4, c.getCredit());
            stm.setString(5, c.getSemester());
            stm.setInt(6, c.getMaxStudent());
            stm.setInt(7, c.getAdmin().getAdminID());

            int rowsAffected = stm.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    c.setCourseID(generatedId);
                    return c;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateCourse(Course c) {
        String sql = "UPDATE Courses SET "
                + "course_name = ?, "
                + "description = ?, "
                + "department = ?, "
                + "credits = ?, "
                + "semester = ?, "
                + "max_students = ? "
                + "WHERE course_id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, c.getCourseName());
            stm.setString(2, c.getDescription());
            stm.setString(3, c.getDepartment());
            stm.setInt(4, c.getCredit());
            stm.setString(5, c.getSemester());
            stm.setInt(6, c.getMaxStudent());
            stm.setInt(7, c.getCourseID());

            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void main(String[] args) {
        List<Course> list = new CourseDAO().getAllCourse();
        for (Course course : list) {
            System.out.println(course.getCourseName());
        }
    }

}
