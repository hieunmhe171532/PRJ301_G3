<%-- 
    Document   : Sidebar
    Created on : Apr 24, 2025, 7:45:25 PM
    Author     : hieum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Admin Header</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <nav class="navbar navbar-expand-lg navbar-dark bg-dark px-4">
            <a class="navbar-brand" href="#">Admin Panel</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#adminNavbar" aria-controls="adminNavbar" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="adminNavbar">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="getlist">Course</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="AllEnrollment">Enrollment</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="listofuser">Student</a>
                    </li>
                </ul>
                <form class="d-flex">
                    <a href="logout" class="btn btn-outline-light">Logout</a>
                </form>
            </div>
        </nav>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
