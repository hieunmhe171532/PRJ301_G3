<%-- 
    Document   : EditCourse
    Created on : Apr 24, 2025, 8:39:32 PM
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

        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-md-8 col-lg-6">
                    <div class="card shadow-lg rounded-4">
                        <div class="card-header bg-primary text-white text-center">
                            <h4 class="mb-0">Edit Course</h4>
                        </div>
                        <div class="card-body">
                            <form action="editcourse" method="POST">
                                <input type="hidden" name="CourseID" value="${course.courseID}" />
                                
                                <div class="mb-3">
                                    <label for="CourseName" class="form-label">Course Name</label>
                                    <input type="text" class="form-control" name="CourseName" id="CourseName" value="${course.getCourseName()}" required>
                                </div>
                                <div class="mb-3">
                                    <label for="Description" class="form-label">Description</label>
                                    <input type="text" class="form-control" name="Description" id="Description" value="${course.getDescription()}" required>
                                </div>
                                <div class="mb-3">
                                    <label for="Department" class="form-label">Department</label>
                                    <input type="text" class="form-control" name="Department" id="Department" value="${course.getDepartment()}" required>
                                </div>
                                <div class="mb-3">
                                    <label for="Credit" class="form-label">Credit (Number only)</label>
                                    <input type="number" class="form-control" name="Credit" id="Credit" value="${course.getCredit()}" required>
                                </div>
                                <div class="mb-3">
                                    <label for="Semester" class="form-label">Semester  (Number only)</label>
                                    <input type="number" class="form-control" name="Semester" id="Semester" value="${course.getSemester()}" required>
                                </div>
                                <div class="mb-3">
                                    <label for="MaxStudent" class="form-label">Max Students  (Number only)</label>
                                    <input type="number" class="form-control" name="MaxStudent" id="MaxStudent" value="${course.getMaxStudent()}" required>
                                </div>
                                <div class="d-flex justify-content-between">
                                    <button type="submit" class="btn btn-success">Update Course</button>
                                    <a href="getlist" class="btn btn-secondary">Cancel</a>
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
