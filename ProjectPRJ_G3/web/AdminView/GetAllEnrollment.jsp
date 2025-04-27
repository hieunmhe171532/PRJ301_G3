<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All Enrollments</title>
    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
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
            transition: transform 0.2s;
        }
        .custom-table:hover {
            transform: scale(1.01);
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
        .input-group {
            margin-bottom: 30px;
            max-width: 400px;
            margin-left: auto;
            margin-right: auto;
        }
    </style>
</head>

<body>
<jsp:include page="/AdminView/AdminHeader.jsp" />

<div class="container py-5">
    <h2 class="text-center">Enrollment Management</h2>

    <form action="getenrollments" method="get" class="mb-4">
        <div class="input-group">
            <label class="input-group-text" for="statusSelect">Choose a status:</label>
            <select class="form-select" id="statusSelect" name="status" onchange="this.form.submit()">
                <option value="0" ${statusID == 0 ? 'selected' : ''}>All status</option>
                <c:forEach var="statu" items="${listofstatus}">
                    <option value="${statu.statusID}" ${statu.statusID == statusID ? 'selected' : ''}>
                        ${statu.statusName}
                    </option>
                </c:forEach>
            </select>
        </div>
    </form>

    <div class="table-responsive">
        <table class="table table-bordered table-hover custom-table" id="enrollmentTable">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Enrollment ID</th>
                    <th>User Full Name</th>
                    <th>Course Name</th>
                    <th>Note</th>
                    <th>Enrolled At</th>
                    <th>Status Name</th>
                    <th>Approved By (Admin)</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="e" items="${enrollments}" varStatus="i">
                    <tr>
                        <td>${i.index + 1}</td>
                        <td>${e.enrollmentID}</td>
                        <td>${e.user.fullName}</td>
                        <td>${e.course.courseName}</td>
                        <td>${e.note}</td>
                        <td>${e.enrolledAt}</td>
                        <td>${e.status.statusName}</td>
                        <td>${e.admin.fullName}</td> <!-- Admin Name -->
                        <td>
                            <a href="editenroll?eid=${e.enrollmentID}" class="btn btn-sm btn-warning">Edit</a>
                        </td>
                    </tr>
                </c:forEach> <!-- Đây là chỗ cần sửa để đóng thẻ -->
            </tbody>
        </table>
    </div>
</div>

<!-- Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
