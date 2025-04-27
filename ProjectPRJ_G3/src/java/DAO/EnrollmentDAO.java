package DAO;

import Dal.DBContext;
import Model.Course;
import Model.Enrollment;
import Model.EnrollmentStatus;
import Model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnrollmentDAO extends DBContext {

//    public boolean insertEnrollment(Enrollment e) {
//        // Cập nhật câu lệnh SQL để loại bỏ tham số null cho approved_by
//        String sql = "INSERT INTO Enrollments (user_id, course_id, note, enrolled_at, status_id) "
//                + "VALUES (?, ?, ?, GETDATE(), ?)"; // Sửa câu lệnh SQL
//
//        try (PreparedStatement stm = connection.prepareStatement(sql)) {
//            // Gán giá trị cho các tham số
//            stm.setInt(1, e.getUser().getUserID());  // user_id
//            stm.setInt(2, e.getCourse().getCourseID());  // course_id
//            stm.setString(3, e.getNote());  // note
//            stm.setInt(4, e.getStatus().getStatusID());  // status_id
//
//            // Thực thi câu lệnh SQL và trả về kết quả
//            return stm.executeUpdate() > 0;
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//        return false;
//    }
    public Enrollment insertEnrollment(Enrollment e) {
        String sql = "INSERT INTO Enrollments (user_id, course_id, enrolled_at, status_id) "
                + "VALUES (?, ?, GETDATE(), 1)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, e.getUser().getUserID());
            stm.setInt(2, e.getCourse().getCourseID());

            int rowsAffected = stm.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    e.setEnrollmentID(generatedId);
                    return e;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnrollmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
        String sql = "SELECT e.enrollment_id enrollid, u.user_id userid, u.full_name fname, "
                + "c.course_id courseid, c.course_name coursename, e.note note, "
                + "e.enrolled_at enrolledat, en.status_id statusid, en.status_name statusname "
                + "FROM Enrollments e "
                + "JOIN EnrollmentStatuses en ON en.status_id = e.status_id "
                + "JOIN Users u ON u.user_id = e.user_id "
                + "JOIN Courses c ON c.course_id = e.course_id";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt("userid"), rs.getString("fname"));
                Course course = new Course(rs.getInt("courseid"), rs.getString("coursename"));
                EnrollmentStatus enrollmentStatus = new EnrollmentStatus(rs.getInt("statusid"), rs.getString("statusname"));

                Enrollment enrollment = new Enrollment(
                        rs.getInt("enrollid"),
                        user,
                        course,
                        rs.getString("note"),
                        rs.getDate("enrolledat"), // Sửa ở đây
                        null,
                        enrollmentStatus
                );
                list.add(enrollment);
            }
        } catch (SQLException e) {
            Logger.getLogger(EnrollmentDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    public boolean updateEnroll(Enrollment e) {
        String sql = "update Enrollments set"
                + "note = ?, "
                + "approved_by = ?,"
                + "status_id = ?"
                + "where enrollment_id = ?";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, e.getNote());
            stm.setInt(2, e.getAdmin().getAdminID());
            stm.setInt(3, e.getStatus().getStatusID());
            stm.setInt(4, e.getEnrollmentID());

            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException ex) {
            Logger.getLogger(EnrollmentDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public int countAllEnrollbyUserID(int UserID) {
        String sql = "SELECT COUNT(*) AS enrollment_count "
                + "FROM Enrollments "
                + "WHERE user_id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, UserID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("enrollment_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
