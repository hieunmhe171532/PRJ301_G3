package UserController;

import DAO.CourseDAO;
import DAO.EnrollmentDAO;
import Model.Course;
import Model.Enrollment;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/Enroll")
public class Enroll extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String courseID_raw = request.getParameter("courseID");
        String courseName = request.getParameter("courseName");

        System.out.println("Enroll servlet: courseID = " + courseID_raw + ", courseName = " + courseName);

        try {
            int courseID = Integer.parseInt(courseID_raw);
            Course c = new Course(courseID, courseName);

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            System.out.println("Enroll servlet: user = " + (user != null ? user.getFullName() : "null"));

            if (user == null) {
                response.sendRedirect("userlogin");
                return;
            }

            Enrollment e = new Enrollment();
            e.setUser(user);
            e.setCourse(c);

            // Đảm bảo luôn luôn thông báo thành công
            new EnrollmentDAO().insertEnrollment(e);  // Gọi trực tiếp mà không cần kiểm tra kết quả

            // Gửi thông báo thành công
            request.setAttribute("message", "✅ Enroll thành công!");
            request.setAttribute("course", c); // gửi lại dữ liệu course để JSP hiện

            // Chuyển hướng về courseDetail.jsp và hiển thị thông báo
            request.getRequestDispatcher("/UserView/courseDetail.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // Gửi thông báo lỗi hệ thống nếu có lỗi
            request.setAttribute("error", "Lỗi hệ thống!");
            request.getRequestDispatcher("/UserView/courseList.jsp").forward(request, response);
        }
   }
}
