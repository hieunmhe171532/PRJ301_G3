package DAO;

import Dal.DBContext;
import Model.Course;
import Model.Enrollment;
import Model.EnrollmentStatus;
import Model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO extends DBContext {

   public boolean insertEnrollment(Enrollment e) {
    // Cập nhật câu lệnh SQL để loại bỏ tham số null cho approved_by
    String sql = "INSERT INTO Enrollments (user_id, course_id, note, enrolled_at, status_id) "
            + "VALUES (?, ?, ?, GETDATE(), ?)"; // Sửa câu lệnh SQL

    try (PreparedStatement stm = connection.prepareStatement(sql)) {
        // Gán giá trị cho các tham số
        stm.setInt(1, e.getUser().getUserID());  // user_id
        stm.setInt(2, e.getCourse().getCourseID());  // course_id
        stm.setString(3, e.getNote());  // note
        stm.setTimestamp(4, new Timestamp(e.getEnrolledAt().getTime()));  // enrolled_at
        stm.setInt(5, e.getStatus().getStatusID());  // status_id

        // Thực thi câu lệnh SQL và trả về kết quả
        return stm.executeUpdate() > 0;

    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return false;
}


    public boolean isUserAlreadyEnrolled(int userID, int courseID) {
        String sql = "SELECT 1 FROM Enrollments WHERE user_id = ? AND course_id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, userID);
            stm.setInt(2, courseID);
            ResultSet rs = stm.executeQuery();
            return rs.next(); // Nếu có bản ghi => đã đăng ký
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<Enrollment> getAllEnrollments() {
    List<Enrollment> list = new ArrayList<>();
    String sql = "SELECT e.enrollment_id, e.note, e.enrolled_at, " +
                 "u.user_id, u.full_name, " +
                 "c.course_id, c.course_name, " +
                 "s.status_id, s.status_name " +
                 "FROM Enrollments e " +
                 "JOIN Users u ON e.user_id = u.user_id " +
                 "JOIN Courses c ON e.course_id = c.course_id " +
                 "JOIN EnrollmentStatus s ON e.status_id = s.status_id";

    try (PreparedStatement stm = connection.prepareStatement(sql);
         ResultSet rs = stm.executeQuery()) {

        while (rs.next()) {
            Enrollment e = new Enrollment();

            // Set user
            User u = new User();
            u.setUserID(rs.getInt("user_id"));
            u.setFullName(rs.getString("full_name"));
            e.setUser(u);

            // Set course
            Course c = new Course();
            c.setCourseID(rs.getInt("course_id"));
            c.setCourseName(rs.getString("course_name"));
            e.setCourse(c);

            // Set status
            EnrollmentStatus status = new EnrollmentStatus();
            status.setStatusID(rs.getInt("status_id"));
            status.setStatusName(rs.getString("status_name"));
            e.setStatus(status);

            e.setEnrollmentID(rs.getInt("enrollment_id"));
            e.setNote(rs.getString("note"));
            e.setEnrolledAt(rs.getTimestamp("enrolled_at"));

            list.add(e);
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return list;
}
}
