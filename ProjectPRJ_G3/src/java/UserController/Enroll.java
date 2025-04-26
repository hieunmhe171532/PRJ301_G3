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
        String coureID = request.getParameter("courseID");
        String courseName = request.getParameter("courseName");
        try {

            Course c = new Course(Integer.parseInt(coureID), courseName);

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            if (user == null) {
                response.sendRedirect("userlogin");
                return;
            }

            Enrollment e = new Enrollment();
            e.setUser(user);
            e.setCourse(c);

            Enrollment addEnroll = new EnrollmentDAO().insertEnrollment(e);

            if (addEnroll != null) {
                request.getRequestDispatcher("/UserView/courseDetail.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Thêm khóa học thất bại!");
                request.getRequestDispatcher("/UserView/courseDetail.jsp").forward(request, response);
            }

        } catch (Exception e) {
            request.getRequestDispatcher("/UserView/courseDetail.jsp").forward(request, response);

        }

    }
}
