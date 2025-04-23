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

    public List<Course> getAllCo() {
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
                        admin
                );

                list.add(course);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

}
