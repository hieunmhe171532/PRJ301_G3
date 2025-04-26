<%-- 
    Document   : AddCourse
    Created on : Apr 24, 2025, 8:01:15 PM
    Author     : hieum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Add Course</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">
        <jsp:include page="/AdminView/AdminHeader.jsp" />

        <a href="getlist">
            <input type="button" value="Back to list Course" />
        </a>
    
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-md-8 col-lg-6">
                <div class="card shadow-lg rounded-4">
                    <div class="card-header bg-primary text-white text-center">
                        <h4 class="mb-0">Add New Course</h4>
                    </div>
                    
                    <div class="card-body">
                        <form action="addcourse" method="POST">
                            <div style="color: greenyellow">${good}</div>
                            <div class="mb-3">
                                <label for="CourseName" class="form-label">Course Name</label>
                                <input type="text" class="form-control" name="CourseName" id="CourseName" value="${CourseName}" required>
                            </div>
                            <div class="mb-3">
                                <label for="Description" class="form-label">Description</label>
                                <input type="text" class="form-control" name="Description" id="Description" value="${Description}" required>
                            </div>
                            <div class="mb-3">
                                <label for="Department" class="form-label">Department</label>
                                <input type="text" class="form-control" name="Department" id="Department" value="${Department}" required>
                            </div>
                            <div class="mb-3">
                                <label for="Credit" class="form-label">Credit  (Number only)</label>
                                <input type="number" class="form-control" name="Credit" id="Credit" value="${Credit}" required>
                            </div>
                            <div class="mb-3">
                                <label for="Semester" class="form-label">Semester  (Number only)</label>
                                <input type="number" class="form-control" name="Semester" id="Semester" value="${Semester}" required>
                            </div>
                            <div class="mb-3">
                                <label for="MaxStudent" class="form-label">Max Students  (Number only)</label>
                                <input type="number" class="form-control" name="MaxStudent" id="MaxStudent" value="${MaxStudent}" required>
                            </div>
                            <div class="d-flex justify-content-between">
                                <button type="submit" class="btn btn-success">Add Course</button>
                                <button type="reset" class="btn btn-secondary">Reset</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
