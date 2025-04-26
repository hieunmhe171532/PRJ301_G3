package UserController;

import DAO.CourseDAO;
import Model.Course;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class DetailCourse extends HttpServlet {

  @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
    HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("userlogin");
            return;
        }

        request.setAttribute("user", user);
    
    String courseID_raw = request.getParameter("courseID");
    
    try {
        int courseID = Integer.parseInt(courseID_raw);
        CourseDAO dao = new CourseDAO();
        Course course = dao.getCourseById(courseID);

        if (course != null) {
            request.setAttribute("courseDetail", course);
            request.getRequestDispatcher("/UserView/courseDetail.jsp").forward(request, response);
        } else {
            // Gửi lỗi nếu không tìm thấy khóa học
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Course not found");
        }
        
    } catch (NumberFormatException e) {
        // Gửi lỗi nếu ID khóa học không hợp lệ
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Course ID");
    }
}
    }
    
