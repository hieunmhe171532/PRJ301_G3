<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
          <!-- Nơi hiển thị thông báo nếu có -->
          <c:if test="${not empty message}">
            <div class="alert alert-info">${message}</div>
          </c:if>
          <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
          </c:if>

          <form action="editenroll" method="POST">
            <!-- Enroll ID (readonly nếu không muốn sửa) -->
            <div class="mb-3">
              <label for="EnrollID" class="form-label">Enrollment ID</label>
              <input type="text" class="form-control" id="EnrollID" name="EnrollID"
                     value="${enroll.enrollID}" readonly>
            </div>

            <!-- Course Name -->
            <div class="mb-3">
              <label for="CourseName" class="form-label">Course Name</label>
              <input type="text" class="form-control" id="CourseName" name="CourseName"
                     value="${enroll.course.courseName}" required>
            </div>

            <!-- Student Name -->
            <div class="mb-3">
              <label for="StudentName" class="form-label">Student Name</label>
              <input type="text" class="form-control" id="StudentName" name="StudentName"
                     value="${enroll.user.fullName}" required>
            </div>

            <!-- Note -->
            <div class="mb-3">
              <label for="Note" class="form-label">Note</label>
              <input type="text" class="form-control" id="Note" name="Note"
                     value="${enroll.note}">
            </div>

            <!-- Status -->
            <div class="mb-3">
              <label for="Status" class="form-label">Status</label>
              <select class="form-select" id="Status" name="status">
                <option value="Pending"   ${enroll.status == 'Pending'   ? 'selected' : ''}>Pending</option>
                <option value="Approved"  ${enroll.status == 'Approved'  ? 'selected' : ''}>Approved</option>
                <option value="Rejected"  ${enroll.status == 'Rejected'  ? 'selected' : ''}>Rejected</option>
              </select>
            </div>

            <!-- Enroll At -->
            <div class="mb-3">
              <label for="EnrollAt" class="form-label">Enroll At</label>
              <input type="date" class="form-control" id="EnrollAt" name="EnrollAt"
                     value="${enroll.enrolledAt}">
            </div>

            <div class="d-flex justify-content-between">
              <button type="submit" class="btn btn-success">Save Changes</button>
              <a href="getenrollments" class="btn btn-secondary">Cancel</a>
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
