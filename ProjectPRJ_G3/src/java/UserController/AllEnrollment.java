package UserController;

import DAO.EnrollmentDAO;
import Model.Enrollment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/getenrollments")
public class AllEnrollment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        EnrollmentDAO dao = new EnrollmentDAO();
        List<Enrollment> enrollments = dao.getAllEnrollments();
        request.getServletContext().setAttribute("enrollments", enrollments);

        request.getRequestDispatcher("/AdminView/GetAllEnrollment.jsp").forward(request, response);
    }
}
