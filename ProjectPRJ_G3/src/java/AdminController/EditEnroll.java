/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminController;

import DAO.EnrollmentDAO;
import Model.Admin;
import Model.Enrollment;
import Model.EnrollmentStatus;
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
public class EditEnroll extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String erollID = request.getParameter("eid");
        try {
            int erollID1 = Integer.parseInt(erollID);
            Enrollment enrollment = new EnrollmentDAO().getEnrollByID(erollID1);

            request.setAttribute("enrollment", enrollment);
        } catch (Exception e) {
            response.sendRedirect("AllEnrollment");
        }

        request.getRequestDispatcher("/AdminView/EditEnroll.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String enrollID = request.getParameter("enrollID");
        String note = request.getParameter("Note");
        String statusID = request.getParameter("statusID");

        try {
            int enrollID1 = Integer.parseInt(enrollID);
            int statusID1 = Integer.parseInt(statusID);
            EnrollmentStatus status = new EnrollmentStatus(statusID1);
            HttpSession session = request.getSession();
            Admin admin = (Admin) session.getAttribute("admin");

            if (admin == null) {
                response.sendRedirect("adminlogin");
                return;
            }

            Enrollment e = new Enrollment();
            e.setEnrollmentID(enrollID1);
            e.setNote(note);
            e.setAdmin(admin);
            e.setStatus(status);

            EnrollmentDAO dao = new EnrollmentDAO();
            boolean success = dao.updateEnroll(e);

            if (success) {
                request.getSession().setAttribute("successMessage", "Cập nhật thành công!");
                response.sendRedirect("editenroll?eid=" + enrollID1);
            } else {
                request.setAttribute("error", "Cập nhật thất bại. Vui lòng thử lại.");
                request.getRequestDispatcher("/AdminView/EditEnroll.jsp").forward(request, response);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("error", "Có lỗi xảy ra: " + ex.getMessage());
            request.getRequestDispatcher("/AdminView/EditEnroll.jsp").forward(request, response);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
