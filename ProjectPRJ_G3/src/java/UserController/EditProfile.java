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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class EditProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("UserView/editprofile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String genderStr = request.getParameter("gender");
        boolean gender = genderStr.equalsIgnoreCase("Male"); 
        String dob = request.getParameter("dob");

        try{
        user.setFullName(fullName);
        user.setEmail(email);
        user.setGender(gender);
//        user.setDob(dob);

        UserDAO userDAO = new UserDAO();
        userDAO.updateUser(user);


            session.setAttribute("user", user); // Update session data
            response.sendRedirect("profile"); // Redirect to the profile page

        }catch(NumberFormatException e) {
            
    }   catch (Exception ex) {
            Logger.getLogger(EditProfile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//
//        String fullname = request.getParameter("fullname");
//        String email = request.getParameter("email");
//        String gender = request.getParameter("gender");
//        
//        try {
//            user.setFullName(fullname);
//            user.setEmail(email);
//            user.setGender(Boolean.parseBoolean(gender));
//
//            UserDAO userDAO = new UserDAO();
//            userDAO.updateUser(user);
//
//            session.setAttribute("user", user); // Cập nhật session luôn
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        response.sendRedirect("profile");
        
        
        
        
           
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//
//        if (user != null) {
//            String fullName = request.getParameter("fullname");
//            String userName = request.getParameter("username");
//            String email = request.getParameter("email");
//            String dobStr = request.getParameter("dob");
//            String password = request.getParameter("password");
//            String genderStr = request.getParameter("gender");
//            boolean gender = "true".equals(genderStr);
//
//            Date dob = null;
//            // Validate and update the user details
//            // You can add more validation as needed
//            if (fullName != null && !fullName.isEmpty()) {
//                user.setFullName(fullName);
//            }
//            if (userName != null && !userName.isEmpty()) {
//                user.setUserName(userName);
//            }
//            if (email != null && !email.isEmpty()) {
//                user.setEmail(email);
//            }
//
//            if (password != null && !password.isEmpty()) {
//                user.setPassword(password);  // Hash password before saving
//            }
//            try {
//                dob = java.sql.Date.valueOf(dobStr);
//            } catch (Exception e) {
//
//            }
//
//            user.setGender(gender);
//            if (dob != null) {
//                user.setDob(dob);
//            }
//            // Update user in the database (using DAO)
//            UserDAO userDAO = new UserDAO();
//            try {
//                userDAO.updateUser(user);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            session.setAttribute("user", user);  // Update session with new details
//            response.sendRedirect("profile"); // Redirect to profile page
//        } else {
//            response.sendRedirect("login");
//        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
