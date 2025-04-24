package DAO;

<<<<<<< Updated upstream
import Dal.DBContext;
=======
>>>>>>> Stashed changes
import Model.Admin;
import Model.Course;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

<<<<<<< Updated upstream
public class CourseDAO extends DBContext {

    public List<Course> getAllCourse() {
        List<Course> list = new ArrayList<>();

        String sql = "SELECT course_id AS id, course_name AS name, description AS des, department, credits, semester, "
                + "max_students AS max, c.created_at AS date, a.admin_id AS adid, a.full_name AS fname "
                + "FROM Courses c JOIN Admins a ON a.admin_id = c.created_by";

        try (PreparedStatement stm = connection.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {
=======
public class CourseDAO {

    private Connection connection;

    public CourseDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/your_database", // Thay bằng DB thật
                "username",                                   // Thay bằng username
                "password"                                    // Thay bằng password
            );
        } catch (Exception e) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public List<Course> getAllCo() {
        List<Course> list = new ArrayList<>();

        String sql = "select course_id id,course_name name,description des,department ,credits, semester,max_students max,a.created_at date,created_by createby,a.admin_id adid  from Courses\n" +
"join Admins  a on a.admin_id = Courses.created_by";

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
>>>>>>> Stashed changes

            while (rs.next()) {
                Admin admin = new Admin(rs.getInt("adid"), rs.getString("fname"));
                Course course = new Course(
<<<<<<< Updated upstream
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("des"),
                        rs.getString("department"),
                        rs.getInt("credits"),
                        rs.getString("semester"),
                        rs.getInt("max"),
                        rs.getDate("date"),
                        admin
=======
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("des"),
                    rs.getString("department"),
                    rs.getInt("credits"),
                    rs.getString("semester"),
                    rs.getInt("max"),
                    rs.getDate("date"),
                    admin // truyền đối tượng Admin vào Course
>>>>>>> Stashed changes
                );

                list.add(course);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
<<<<<<< Updated upstream
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

=======

    public static void main(String[] args) {
        List<Course> list = new CourseDAO().getAllCo();
        for (Course course : list) {
            System.out.println(course.getCourseName());
        }
    }
>>>>>>> Stashed changes
}
