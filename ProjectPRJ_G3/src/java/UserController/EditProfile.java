/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package UserController;

import DAO.UserDAO;
import Model.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;

/**
 *
 * @author admin
 */
public class EditProfile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditProfile</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditProfile at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            request.setAttribute("user", user);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("UserView/editprofile.jsp");
//            dispatcher.forward(request, response);
            request.getRequestDispatcher("/UserView/editprofile.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            String fullName = request.getParameter("fullname");
            String userName = request.getParameter("username");
            String email = request.getParameter("email");
            String dobStr = request.getParameter("dob");
            String password = request.getParameter("password");
            String genderStr = request.getParameter("gender");
            boolean gender = "true".equals(genderStr);

            Date dob = null;
            // Validate and update the user details
            // You can add more validation as needed
            if (fullName != null && !fullName.isEmpty()) {
                user.setFullName(fullName);
            }
            if (userName != null && !userName.isEmpty()) {
                user.setUserName(userName);
            }
            if (email != null && !email.isEmpty()) {
                user.setEmail(email);
            }

            if (password != null && !password.isEmpty()) {
                user.setPassword(password);  // Hash password before saving
            }
            try {
                dob = java.sql.Date.valueOf(dobStr);
            } catch (Exception e) {

            }

            user.setGender(gender);
            if (dob != null) {
                user.setDob(dob);
            }
            // Update user in the database (using DAO)
            UserDAO userDAO = new UserDAO();
            try {
                userDAO.updateUser(user);
            } catch (Exception e) {
                e.printStackTrace();
            }

            session.setAttribute("user", user);  // Update session with new details
            response.sendRedirect("profile"); // Redirect to profile page
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
