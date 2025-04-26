<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>List of Courses</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">


        <jsp:include page="/AdminView/AdminHeader.jsp" />


        <div class="container">
            <h2 class="text-center mb-4">Danh sách khóa học</h2>
            <div class="table-responsive">
                <a href="addcourse">
                    <input type="button" value="Add Course" />
                </a>
                <table class="table table-bordered table-striped table-hover align-middle">
                    <thead class="table-dark text-center">
                        <tr>
                            <th>TT</th>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Department</th>
                            <th>Credits</th>
                            <th>Semester</th>
                            <th>Max Student</th>
                            <th>Created By</th>
                            <th>Created At</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="c" items="${listofCourse}" varStatus="stt">

                            <tr>
                                <td>${stt.index+1}</td>
                                <td>${c.courseID}</td>
                                <td>${c.getCourseName()}</td>
                                <td>${c.getDescription()}</td>
                                <td>${c.getDepartment()}</td>
                                <td>${c.getCredit()}</td>
                                <td>${c.getSemester()}</td>
                                <td>${c.getMaxStudent()}</td>
                                <td>${c.admin.getFullName()}</td>
                                <td>${c.getCreatedAt()}</td>
                                <td>
                                    <a href="editcourse?id=${c.courseID}"">
                                        <input type="button" value="Edit" />
                                    </a>
                                </td>
                                <td>
                                    <a href="deletecourse?id=${c.courseID}"
                                       onclick="return confirm('Delete ${c.courseID} ?')"">
                                        <input type="button" value="Delete" />
                                    </a>
                                </td>



                            </tr>

                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Bootstrap JS (optional for navbar toggle on mobile) -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
