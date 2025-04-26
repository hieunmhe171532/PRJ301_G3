/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminController;

import DAO.CourseDAO;
import Model.Course;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author hieum
 */
public class EditCourse extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String courseID = request.getParameter("id");
        try {
            int courseID1 = Integer.parseInt(courseID);
            Course course = new CourseDAO().getCourseById(courseID1);
            request.setAttribute("course", course);
        } catch (Exception e) {
            response.sendRedirect("getlist");
        }

        request.getRequestDispatcher("/AdminView/EditCourse.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String courseID = request.getParameter("CourseID");
        String name = request.getParameter("CourseName");
        String description = request.getParameter("Description");
        String department = request.getParameter("Department");
        String credit = request.getParameter("Credit");
        String semester = request.getParameter("Semester");
        String maxStu = request.getParameter("MaxStudent");

        try {
            int courseID1 = Integer.parseInt(courseID);
            int credit1 = Integer.parseInt(credit);
            int maxStu1 = Integer.parseInt(maxStu);

            Course course = new Course(courseID1, name, description, department, credit1, semester, maxStu1);
            boolean updated = new CourseDAO().updateCourse(course);

            if (updated) {
                response.sendRedirect("getlist");
            } else {
                request.setAttribute("error", "Update failed!");
                request.getRequestDispatcher("/AdminView/EditCourse.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace(); // luôn in lỗi ra
            request.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
            request.getRequestDispatcher("/AdminView/EditCourse.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
