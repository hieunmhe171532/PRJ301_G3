<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Course List</title>
</head>
<body>
    <h2>List of Courses</h2>
    <table border="1">
        <thead>
            <tr>
                <th>TT</th>
                <th>Course ID</th>
                <th>Course Name</th>
                <th>Description</th>
                <th>Department</th>
                <th>Credits</th>
                <th>Semester</th>
                <th>Max Students</th>
                <th>Created At</th>
                <th>Created By</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="co" items="${listofCourse}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${co.courseID}</td>
                    <td>${co.courseName}</td>
                    <td>${co.description}</td>
                    <td>${co.department}</td>
                    <td>${co.credit}</td>
                    <td>${co.semester}</td>
                    <td>${co.maxStudent}</td>
                    <td>${co.createdAt}</td>
                    <td>${co.admin.fullName}</td> <!-- Sửa để hiển thị tên Admin -->
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
