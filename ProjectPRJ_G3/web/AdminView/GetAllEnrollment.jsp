<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All Enrollments</title>
    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background-color: #f8f9fa;
        }
        h2 {
            font-weight: 600;
            color: #0d6efd;
        }
        .custom-table {
            background: #ffffff;
            border-radius: 12px;
            box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
        }
        .table thead th {
            background-color: #0d6efd;
            color: white;
            text-align: center;
            vertical-align: middle;
        }
        .table tbody td {
            vertical-align: middle;
            text-align: center;
        }
    </style>
</head>

<body>

<div class="container py-5">
    <h2 class="text-center mb-5">Enrollment List</h2>

    <div class="table-responsive">
        <table class="table table-bordered table-hover custom-table">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Enrollment ID</th>
                    <th>User ID</th>
                    <th>User Full Name</th>
                    <th>Course ID</th>
                    <th>Course Name</th>
                    <th>Note</th>
                    <th>Enrolled At</th>
                    <th>Status ID</th>
                    <th>Status Name</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="e" items="${enrollments}" varStatus="i">
                    <tr>
                        <td>${i.index + 1}</td>
                        <td>${e.enrollmentID}</td>
                        <td>${e.user.userID}</td>
                        <td>${e.user.fullName}</td> 
                        <td>${e.course.courseID}</td>
                        <td>${e.course.courseName}</td>
                        <td>${e.note}</td>
                        <td>${e.enrolledAt}</td>
                        <td>${e.status.statusID}</td>
                        <td>${e.status.statusName}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
