<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Enrollments</title>
</head>
<form>
<body>
<h2>Enrollment List</h2>
<table border="1">
    <thead>
    <tr>
        <th>No.</th>
        <th>User</th>
        <th>Course</th>
        <th>Status</th>
        <th>Note</th>
        <th>Enrolled At</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="e" items="${enrollments}" varStatus="i">
        <tr>
            <td>${i.index + 1}</td>
            
           <td>${e.user.getFullName()}</td>
            <td>${e.course.getCourseName()}</td>
            <td>${e.status.getStatusName()}</td>
            <td>${e.getNote()}</td>
            <td>${e.getEnrolledAt()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</form>
</body>
</html>
