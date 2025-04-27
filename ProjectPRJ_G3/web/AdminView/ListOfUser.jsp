<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>List of User</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">


        <jsp:include page="/AdminView/AdminHeader.jsp" />


        <div class="container">
            <h2 class="text-center mb-4">Danh sách học sinh</h2>
            <div class="table-responsive">

                <table class="table table-bordered table-striped table-hover align-middle">
                    <thead class="table-dark text-center">
                        <tr>
                            <th>TT</th>
                            <th>UserID</th>
                            <th>Full Name</th>
                            <th>Email</th>
                            <th>Gender</th>
                            <th>Enrollments</th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="c" items="${listofUser}" varStatus="stt">

                            <tr>
                                <td>${stt.index+1}</td>
                                <td>${c.getUserID()}</td>
                                <td>${c.getFullName()}</td>
                                <td>${c.getEmail()}</td>
                                <td class="text-center">${c.gender ? 'Male' : 'Female'}</td>
                                <td>
                                    <!-- Lấy enrollments cho user từ enrollmentsMap -->
                                    ${enrollmentsMap[c.getUserID()]}
                                </td>



                            </tr>

                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Bootstrap JS (optional for navbar toggle on mobile) -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
