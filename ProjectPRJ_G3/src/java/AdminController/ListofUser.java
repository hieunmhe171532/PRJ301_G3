package AdminController;

import DAO.UserDAO;
import DAO.EnrollmentDAO;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public class ListofUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListofUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListofUser at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO udao = new UserDAO();
        EnrollmentDAO edao = new EnrollmentDAO();

        List<User> listofUser = udao.getAllUser();
        Map<Integer, Integer> enrollmentsMap = new HashMap<>(); // Map to store enrollments for each user

        // Lặp qua các user và lấy số lượng enrollments cho từng user
        for (User user : listofUser) {
            int enrollNum = edao.countAllEnrollbyUserID(user.getUserID());
            enrollmentsMap.put(user.getUserID(), enrollNum); // Lưu enrollments cho mỗi user
        }

        request.setAttribute("enrollmentsMap", enrollmentsMap);
        request.setAttribute("listofUser", listofUser);
        request.getRequestDispatcher("/AdminView/ListOfUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
