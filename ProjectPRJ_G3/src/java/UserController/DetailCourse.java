package UserController;

import DAO.CourseDAO;
import Model.Course;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class DetailCourse extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String courseID_raw = request.getParameter("courseID");
        
        try {
            int courseID = Integer.parseInt(courseID_raw);
            CourseDAO dao = new CourseDAO();
            Course course = dao.getCourseById(courseID);

            if (course != null) {
                request.setAttribute("courseDetail", course);
                request.getRequestDispatcher("/UserView/courseDetail.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Course not found");
            }

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Course ID");
        }
        request.getRequestDispatcher("/UserView/courseDetail.jsp").forward(request, response);
}
    }
    
