<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Enrollment</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<jsp:include page="/AdminView/AdminHeader.jsp" />

<div class="container py-5">
  <div class="row justify-content-center">
    <div class="col-lg-6">
      <div class="card shadow-sm">
        <div class="card-header bg-primary text-white">
          <h5 class="mb-0">Edit Enrollment</h5>
        </div>
        <div class="card-body">

          <!-- Hiển thị thông báo -->
          <c:if test="${not empty message}">
            <div class="alert alert-info">${message}</div>
          </c:if>
          <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
          </c:if>

          <form action="editenroll" method="POST">
            <!-- Enrollment ID -->
            <div class="mb-3">
              <label for="EnrollID" class="form-label">Enrollment ID</label>
              <input type="text" class="form-control" id="EnrollID" name="enrollID"
                     value="${enrollment.enrollmentID}" readonly>
            </div>

            <!-- Course Name -->
            <div class="mb-3">
              <label for="CourseName" class="form-label">Course Name</label>
              <input type="text" class="form-control" id="CourseName" 
                     value="${enrollment.course.courseName}" readonly>
            </div>

            <!-- Student Name -->
            <div class="mb-3">
              <label for="StudentName" class="form-label">Student Name</label>
              <input type="text" class="form-control" id="StudentName" 
                     value="${enrollment.user.fullName}" readonly>
            </div>

            <!-- Note -->
            <div class="mb-3">
              <label for="Note" class="form-label">Note</label>
              <input type="text" class="form-control" id="Note" name="Note"
                     value="${enrollment.note}">
            </div>

            <!-- Status -->
            <div class="mb-3">
              <label for="Status" class="form-label">Status</label>
              <select class="form-select" id="Status" name="statusID">
                <option value="1" ${enrollment.status.statusID == 1 ? 'selected' : ''}>Pending</option>
                <option value="2" ${enrollment.status.statusID == 2 ? 'selected' : ''}>Approved</option>
                <option value="3" ${enrollment.status.statusID == 3 ? 'selected' : ''}>Rejected</option>
              </select>
            </div>

            <!-- Enroll At -->
            <div class="mb-3">
              <label for="EnrollAt" class="form-label">Enroll At</label>
              <input type="text" class="form-control" id="EnrollAt"
                     value="${enrollment.enrolledAt}" readonly>
            </div>

            <div class="d-flex justify-content-between">
              <button type="submit" class="btn btn-success">Save Changes</button>
              <a href="AllEnrollment" class="btn btn-secondary">Cancel</a>
            </div>

          </form>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- Bootstrap 5 JS bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
