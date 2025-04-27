package DAO;

import Dal.DBContext;
import Model.Admin;
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
        String sql = "SELECT e.enrollment_id AS enrollid, u.user_id AS userid, u.full_name AS fname, "
                + "c.course_id AS courseid, c.course_name AS coursename, "
                + "e.note AS note, e.enrolled_at AS enrolledat, e.approved_by, "
                + "en.status_id AS statusid, en.status_name AS statusname, "
                + "a.admin_id AS adminid, a.full_name AS adminname "
                + "FROM Enrollments e "
                + "JOIN EnrollmentStatuses en ON en.status_id = e.status_id "
                + "JOIN Users u ON u.user_id = e.user_id "
                + "JOIN Courses c ON c.course_id = e.course_id "
                + "LEFT JOIN Admins a ON a.admin_id = e.approved_by";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt("userid"), rs.getString("fname"));
                Course course = new Course(rs.getInt("courseid"), rs.getString("coursename"));

                // Cẩn thận: Admin có thể NULL nếu chưa approved
                Admin admin = null;
                int adminId = rs.getInt("adminid");
                if (!rs.wasNull()) {
                    admin = new Admin(adminId, rs.getString("adminname"));
                }

                EnrollmentStatus enrollmentStatus = new EnrollmentStatus(rs.getInt("statusid"), rs.getString("statusname"));

                Enrollment enrollment = new Enrollment(
                        rs.getInt("enrollid"),
                        user,
                        course,
                        rs.getString("note"),
                        rs.getDate("enrolledat"),
                        admin,
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
         String sql = "UPDATE Enrollments SET "
               + "note = ?, "
               + "approved_by = ?, "
               + "status_id = ? "
               + "WHERE enrollment_id = ?";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, e.getNote());
            stm.setInt(2, e.getAdmin().getAdminID());
            stm.setInt(3, e.getStatus().getStatusID());
            stm.setInt(4, e.getEnrollmentID());

            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException ex) {
            Logger.getLogger(EnrollmentDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public List<Enrollment> getEnrollmentsByUserID(User user) {
        List<Enrollment> list = new ArrayList<>();
        String sql = "SELECT e.enrollment_id AS enrollid, u.user_id AS userid, u.full_name AS fname, "
                + "c.course_id AS courseid, c.course_name AS coursename, "
                + "e.note AS note, e.enrolled_at AS enrolledat, e.approved_by, "
                + "en.status_id AS statusid, en.status_name AS statusname, "
                + "a.admin_id AS adminid, a.full_name AS adminname "
                + "FROM Enrollments e "
                + "JOIN EnrollmentStatuses en ON en.status_id = e.status_id "
                + "JOIN Users u ON u.user_id = e.user_id "
                + "JOIN Courses c ON c.course_id = e.course_id "
                + "LEFT JOIN Admins a ON a.admin_id = e.approved_by "
                + "WHERE u.user_id = ?";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, user.getUserID());  // Sử dụng user.getUserID() để lấy userID
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User currentUser = new User(rs.getInt("userid"), rs.getString("fname"));
                Course course = new Course(rs.getInt("courseid"), rs.getString("coursename"));

                Admin admin = null;
                int adminId = rs.getInt("adminid");
                if (!rs.wasNull()) {
                    admin = new Admin(adminId, rs.getString("adminname"));
                }

                EnrollmentStatus enrollmentStatus = new EnrollmentStatus(rs.getInt("statusid"), rs.getString("statusname"));

                Enrollment enrollment = new Enrollment(
                        rs.getInt("enrollid"),
                        currentUser,
                        course,
                        rs.getString("note"),
                        rs.getDate("enrolledat"),
                        admin,
                        enrollmentStatus
                );
                list.add(enrollment);
            }
        } catch (SQLException e) {
            
        }
        return list;
    }

    public Enrollment getEnrollByID(int enrollID) {
        String sql = "SELECT \n"
                + "    e.enrollment_id AS enrollid,\n"
                + "    u.user_id AS userid,\n"
                + "    u.full_name AS fname,\n"
                + "    c.course_id AS courseid,\n"
                + "    c.course_name AS coursename,\n"
                + "    e.note AS note,\n"
                + "    e.enrolled_at AS enrolledat,\n"
                + "    e.approved_by,\n"
                + "    en.status_id AS statusid,\n"
                + "    en.status_name AS statusname,\n"
                + "    a.admin_id AS adminid,\n"
                + "    a.full_name AS adminname\n"
                + "FROM Enrollments e\n"
                + "JOIN EnrollmentStatuses en ON en.status_id = e.status_id\n"
                + "JOIN Users u ON u.user_id = e.user_id\n"
                + "JOIN Courses c ON c.course_id = e.course_id\n"
                + "LEFT JOIN Admins a ON a.admin_id = e.approved_by\n"
                + "WHERE e.enrollment_id = ?";
        
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, enrollID);
            ResultSet rs = stm.executeQuery();
            
            if (rs.next()) {
                User user = new User(rs.getInt("userid"), rs.getString("fname"));
                Course course = new Course(rs.getInt("courseid"), rs.getString("coursename"));
                EnrollmentStatus es = new EnrollmentStatus(rs.getInt("statusid"), rs.getString("statusname"));
                Admin admin = new Admin(rs.getInt("adminid"), rs.getString("adminname"));
                
                return new Enrollment(rs.getInt("enrollid"), user, course,
                        rs.getString("note"), rs.getDate("enrolledat"), admin, es);
            }
                    
        } catch (SQLException ex) {
            Logger.getLogger(EnrollmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void main(String[] args) {
        Enrollment enrollment = new EnrollmentDAO().getEnrollByID(1);
        System.out.println(enrollment.getCourse().getCourseName());
    }

}
