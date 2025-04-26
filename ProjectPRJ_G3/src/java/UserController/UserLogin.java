/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package UserController;

import DAO.UserDAO;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author hieum
 */
public class UserLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            request.getRequestDispatcher("/UserView/Login.jsp").forward(request, response);        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String err = "";

        UserDAO dao = new UserDAO();
        User user = dao.getUserByUserName(username);

        if (user == null) {
            err = "Ko tim thay tai khoan";
        } else if (!user.getPassword().equals(password)) {
            err = "Sai mat khau!";
        }
        if (err.isEmpty()) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            response.sendRedirect("courseList");
        } else {
            request.setAttribute("err", err);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.getRequestDispatcher("/UserView/Login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
