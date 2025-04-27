<%-- 
    Document   : UserEnrollments
    Created on : Apr 27, 2025, 1:11:09 PM
    Author     : le huy
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Enrollments</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(to right, #f8f9fa, #e9ecef);
            min-height: 100vh;
        }
        h2 {
            font-weight: 700;
            color: #0d6efd;
            margin-bottom: 30px;
        }
        .custom-table {
            background: #ffffff;
            border-radius: 15px;
            overflow: hidden;
            box-shadow: 0px 8px 24px rgba(0, 0, 0, 0.1);
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
    <h2 class="text-center">My Enrollments</h2>

    <div class="table-responsive">
        <table class="table table-bordered table-hover custom-table">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Enrollment ID</th>
                    <th>Course Name</th>
                    <th>Note</th>
                    <th>Enrolled At</th>
                    <th>Status</th>
                    <th>Approved By (Admin)</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="e" items="${enrollments}" varStatus="i">
                    <tr>
                        <td>${i.index + 1}</td>
                        <td>${e.enrollmentID}</td>
                        <td>${e.course.courseName}</td>
                        <td>${e.note}</td>
                        <td>${e.enrolledAt}</td>
                        <td>${e.status.statusName}</td>
                        <td>
                            <c:choose>
                                <c:when test="${e.admin != null}">
                                    ${e.admin.fullName}
                                </c:when>
                                <c:otherwise>
                                    Not Approved Yet
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty enrollments}">
                    <tr>
                        <td colspan="7">No enrollments found.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

