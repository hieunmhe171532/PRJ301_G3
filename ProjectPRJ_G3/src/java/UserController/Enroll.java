package UserController;

import DAO.CourseDAO;
import DAO.EnrollmentDAO;
import Model.Course;
import Model.Enrollment;
import Model.EnrollmentStatus;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Date;

public class Enroll extends HttpServlet {

   @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    String courseID_raw = request.getParameter("courseID");
    HttpSession session = request.getSession();
   
    User currentUser = (User) session.getAttribute("user");

    String message = "";
    if (currentUser == null) {
//        message = "❌ You are not logged in!";
//        request.setAttribute("message", message);
        request.getRequestDispatcher("/UserView/courseDetail.jsp").forward(request, response);
        return;
    }
    
    try {
        int courseID = Integer.parseInt(courseID_raw);
        CourseDAO cdao = new CourseDAO();
        Course course = cdao.getCourseById(courseID);

        if (course == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Course not found");
            return;
        }

        EnrollmentDAO edao = new EnrollmentDAO();
        boolean alreadyEnrolled = edao.isUserAlreadyEnrolled(currentUser.getUserID(), courseID);

        if (alreadyEnrolled) {
            message = "❌ You have already enrolled in this course!";
        } else {
            Enrollment enrollment = new Enrollment();
            enrollment.setUser(currentUser);
            enrollment.setCourse(course);
            enrollment.setEnrolledAt(new Date());
            enrollment.setNote("Pending enrollment");
            enrollment.setStatus(new EnrollmentStatus(1, "Pending")); // Pending status

            boolean success = edao.insertEnrollment(enrollment);
            message = success ? "✅ Successfully enrolled in course!" : "❌ Failed to enroll.";
        }

    } catch (NumberFormatException e) {
        message = "⚠ Invalid course ID.";
    }

    // Gửi thông báo và chuyển tiếp đến trang chi tiết khóa học
    request.setAttribute("message", message);
    request.getRequestDispatcher("/UserView/courseDetail.jsp").forward(request, response);
}
}
