package UserController;

import DAO.EnrollmentDAO;
import DAO.EnrollmentStatusDAO;
import Model.Enrollment;
import Model.EnrollmentStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class AllEnrollment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String statusID = request.getParameter("status");

        if (statusID == null) {
            statusID = "0";
        }

        EnrollmentDAO dao = new EnrollmentDAO();
        EnrollmentStatusDAO dao1 = new EnrollmentStatusDAO();
        List<Enrollment> enrollments = dao.getAllEnrollments();
        List<EnrollmentStatus> enrollmentstatus = dao1.getAllStatuses();

        // SỬA: dùng request.setAttribute (không phải getServletContext)
        request.setAttribute("enrollments", enrollments);
        request.setAttribute("listofstatus", enrollmentstatus);
        request.setAttribute("statusID", Integer.parseInt(statusID)); // parse về int

        request.getRequestDispatcher("/AdminView/GetAllEnrollment.jsp").forward(request, response);
    }

}
