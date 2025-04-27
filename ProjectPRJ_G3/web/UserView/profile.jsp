<%-- 
    Document   : profile
    Created on : Apr 26, 2025, 8:57:41 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" 
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <title>Profile</title>
    </head>
    <body>
        <jsp:include page="/UserView/UserHeader.jsp" />
        <div class="half">
            <div class="bg order-1 order-md-2" style="background-image: url('images/banner-01.jpg');"></div>
            <div class="contents order-2 order-md-1">

                <div class="container">
                    <div class="row align-items-center justify-content-center">
                        <div class="col-md-8">
                            <div class="form-block">
                                <div class="text-center mb-5">
                                    <h3>Profile</h3>
                                </div>

                                <div class="form-group">
                                    <label>Full Name:</label>
                                    <input type="text" class="form-control" value="${user.fullName}" readonly>
                                </div>

                                <div class="form-group">
                                    <label>Username:</label>
                                    <input type="text" class="form-control" value="${user.userName}" readonly>
                                </div>

                                <div class="form-group">
                                    <label>Email:</label>
                                    <input type="email" class="form-control" value="${user.email}" readonly>
                                </div>

                                <div class="form-group">
                                    <label>Gender:</label>
                                    <input type="text" class="form-control" value="${sessionScope.user.gender == true ? 'Male' : 'Female'}" readonly>
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Date of Birth:</label>
                                    <input type="text" class="form-control" value="${sessionScope.user.dob}" readonly>
                                </div>                        

                                <div class="text-center">
                                    <a href="editprofile" class="btn btn-outline-primary mr-2">Edit Profile</a>
                                    <a href="changepassword" class="btn btn-outline-warning">Change Password</a>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>

    </body>
</html>
