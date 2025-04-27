package UserController;


import DAO.EnrollmentDAO;
import Model.Enrollment;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserEnrollmentList", urlPatterns = {"/userenrollments"})
public class UserEnrollmentList extends HttpServlet {

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

        EnrollmentDAO dao = new EnrollmentDAO();
        // Gọi phương thức getEnrollmentsByUserID và truyền đối tượng User vào
        List<Enrollment> enrollments = dao.getEnrollmentsByUserID(user);

        request.setAttribute("enrollments", enrollments);
        request.getRequestDispatcher("/UserView/UserEnrollments.jsp").forward(request, response);
    }
}
