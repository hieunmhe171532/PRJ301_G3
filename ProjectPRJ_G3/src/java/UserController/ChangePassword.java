/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package UserController;

import DAO.UserDAO;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class ChangePassword extends HttpServlet {
   

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangePassword</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePassword at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       request.getRequestDispatcher("/UserView/changepassword.jsp").forward(request, response);
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String currentPassword = request.getParameter("currentpass");
        String newPassword = request.getParameter("newpass");
        String confirmPassword = request.getParameter("renewpassword");

        if (!user.getPassword().equals(currentPassword)) {
            request.setAttribute("error", "Current password is incorrect.");
            request.getRequestDispatcher("/UserView/changepassword.jsp").forward(request, response);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "New passwords do not match.");
            request.getRequestDispatcher("/UserView/changepassword.jsp").forward(request, response);
            return;
        }

        try {
            UserDAO d = new UserDAO();
            d.changePassword(user.getUserID(), newPassword);
            user.setPassword(newPassword);
            request.getSession().setAttribute("user", user);
            request.setAttribute("success", "Password changed successfully.");
            request.getRequestDispatcher("/UserView/changepassword.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
