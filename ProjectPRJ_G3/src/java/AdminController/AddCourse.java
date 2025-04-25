/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminController;

import DAO.CourseDAO;
import Model.Admin;
import Model.Course;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author hieum
 */
public class AddCourse extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");

        if (admin == null) {
            response.sendRedirect("adminlogin");
            return;
        }
        
        request.getRequestDispatcher("/AdminView/AddCourse.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String CourseName = request.getParameter("CourseName");
        String Description = request.getParameter("Description");
        String Department = request.getParameter("Department");
        String Credit = request.getParameter("Credit");
        String Semester = request.getParameter("Semester");
        String MaxStudent = request.getParameter("MaxStudent");
        String err = "";
        try {
            int credit = Integer.parseInt(Credit);
            int maxStudent = Integer.parseInt(MaxStudent);

            HttpSession session = request.getSession();
            Admin admin = (Admin) session.getAttribute("admin");

            if (admin == null) {
                response.sendRedirect("adminlogin");
                return;
            }
            Course c = new Course();
            c.setCourseName(CourseName);
            c.setDescription(Description);
            c.setDepartment(Department);
            c.setCredit(credit);
            c.setSemester(Semester);
            c.setMaxStudent(maxStudent);
            c.setAdmin(admin);

            CourseDAO dao = new CourseDAO();
            Course addedCourse = dao.addCourse(c);

            if (addedCourse != null) {
                // Thành công -> chuyển về trang danh sách hoặc thông báo
                response.sendRedirect("courselist.jsp");
            } else {
                // Thêm thất bại -> forward đến trang lỗi hoặc hiển thị thông báo
                request.setAttribute("error", "Thêm khóa học thất bại!");
                request.getRequestDispatcher("/AdminView/   AddCourse.jsp").forward(request, response);
            }

        } catch (Exception e) {
            err = "Credit and Max Student are number";
            request.getRequestDispatcher("/AdminView/AddCourse.jsp").forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
