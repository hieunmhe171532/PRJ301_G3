<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Course List</title>
        <!-- Bootstrap 5 CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">

        <div class="container my-5">
            <h2 class="mb-4 text-center">List of Courses</h2>
            <div class="table-responsive">
                <table class="table table-striped table-hover table-bordered align-middle shadow-sm">
                    <thead class="table-dark text-center">
                        <tr>
                            <th>#</th>
                            <th>Course ID</th>
                            <th>Course Name</th>
                            <th>Semester</th>
                            <th>Department</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="co" items="${listofCourse}" varStatus="status">
                            <tr class="text-center">
                                <td>${status.index + 1}</td>
                                <td>${co.courseID}</td>
                                <td>${co.courseName}</td>                                                                                         
                                <td>${co.semester}</td>    
                                <td>${co.department}</td>
                                <td>
                                    <a   name ="detail" href="courseDetail.jsp?courseID=${co.courseID}" class="btn btn-sm btn-outline-primary">
                                        Detail
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Bootstrap 5 JS Bundle -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
