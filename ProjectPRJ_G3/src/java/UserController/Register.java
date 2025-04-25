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
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Calendar;

/**
 *
 * @author admin
 */
public class Register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/UserView/Register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullname");
        String email = request.getParameter("email");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        String dobStr = request.getParameter("dob");

// Kiểm tra độ dài username
        if (userName == null || userName.length() < 4) {
            request.setAttribute("error", "Username phải có ít nhất 4 ký tự.");
            request.getRequestDispatcher("/UserView/Register.jsp").forward(request, response);
            return;
        }

// Kiểm tra độ dài password
        if (password == null || password.length() < 4 || password.length() > 16) {
            request.setAttribute("error", "Password phải từ 4 đến 16 ký tự.");
            request.getRequestDispatcher("/UserView/Register.jsp").forward(request, response);
            return;
        }

// Kiểm tra mật khẩu nhập lại
        String rePassword = request.getParameter("repassword");
        if (!password.equals(rePassword)) {
            request.setAttribute("error", "Mật khẩu nhập lại không khớp.");
            request.getRequestDispatcher("/UserView/Register.jsp").forward(request, response);
            return;
        }

// Kiểm tra fullname không chứa số
        if (fullName == null || !fullName.matches("^[^\\d]+$")) {
            request.setAttribute("error", "Fullname không được chứa số.");
            request.getRequestDispatcher("/UserView/Register.jsp").forward(request, response);
            return;
        }

        if (dobStr == null || dobStr.isEmpty()) {
            request.setAttribute("err", "Ngày sinh không được để trống.");
            request.getRequestDispatcher("/UserView/Register.jsp").forward(request, response);
            return;
        }

        Date dob = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
        } catch (IllegalArgumentException e) {

            request.setAttribute("error", "Ngày sinh không hợp lệ.");
            request.getRequestDispatcher("/UserView/Register.jsp").forward(request, response);
            return;
        }

        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setEmail(email);
        user.setGender(gender);
        user.setDob(dob);
        user.setCreateAt(new Date(Calendar.getInstance().getTimeInMillis()));

        UserDAO userDAO = new UserDAO();
        boolean isSuccess = userDAO.insertUser(user);

        if (isSuccess == true) {
            response.sendRedirect("UserView/Login.jsp");
        } else {
            request.setAttribute("error", "Tài khoản đã tồn tại");
            request.getRequestDispatcher("/UserView/Register.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
