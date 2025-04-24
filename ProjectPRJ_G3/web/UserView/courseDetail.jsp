<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Course Detail</title>
    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

    <div class="container py-5">
        <h2 class="text-center mb-5 text-primary">📘 Course Detail</h2>

        <c:if test="${not empty courseDetail}">
            <div class="card shadow-lg border-0">
                <div class="card-body">
                    <h4 class="card-title text-center mb-3 text-success">${courseDetail.courseName}</h4>
                    <p class="card-text text-muted text-center mb-4">${courseDetail.description}</p>

                    <table class="table table-striped table-hover table-bordered">
                        <tbody>
                            <tr>
                                <th>Course ID</th>
                                <td>${courseDetail.courseID}</td>
                            </tr>
                            <tr>
                                <th>Department</th>
                                <td>${courseDetail.department}</td>
                            </tr>
                            <tr>
                                <th>Credit</th>
                                <td>${courseDetail.credit}</td>
                            </tr>
                            <tr>
                                <th>Max Students</th>
                                <td>${courseDetail.maxStudent}</td>
                            </tr>
                            <tr>
                                <th>Created At</th>
                                <td>${courseDetail.createdAt}</td>
                            </tr>
                            <tr>
                                <th>Created By</th>
                                <td>${courseDetail.admin.fullName}</td>
                            </tr>
                        </tbody>
                    </table>

                    <!-- Nút Enroll -->
                    <div class="text-center mt-4">
                        <a href="enroll?courseID=${courseDetail.courseID}" class="btn btn-success btn-lg me-3">
                            ✅ Enroll
                        </a>
                        <a href="courseList" class="btn btn-outline-primary btn-lg">
                            ⬅ Back to Course List
                        </a>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${empty courseDetail}">
            <div class="alert alert-danger text-center p-4 shadow-sm">
                ❌ Course not found or an error occurred.
            </div>
            <div class="text-center mt-3">
                <a href="courseList" class="btn btn-outline-secondary btn-lg">⬅ Back to Course List</a>
            </div>
        </c:if>
    </div>

    <!-- Bootstrap 5 JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
