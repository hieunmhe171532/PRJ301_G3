
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Edit Profile</title>
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
    </head>
    <body>
        <jsp:include page="/UserView/UserHeader.jsp" />
        <div class="half">
            <div class="bg order-1 order-md-2" style="background-image: url('images/banner-01.jpg');"></div>
            <div class="contents order-2 order-md-1">

                <div class="container">
                    <div class="row align-items-center justify-content-center">
                        <div class="col-md-6">
                            <div class="form-block">
                                <div class="text-center mb-5">
                                    <h3>Edit Your Profile</h3>
                                </div>
                                <form class="update-form" name="update-form" action="editprofile" method="post" onsubmit="return validateForm()">

                                    <div class="form-group">
                                        <label>Full Name:</label>
                                        <input type="text" class="form-control" id="fullName" name="fullName" value="${user.fullName}" placeholder="Full Name">
                                        <div style="color: red" id="fullnameFail"></div>
                                    </div>

                                    <div class="form-group">
                                        <label>Username:</label>
                                        <input type="text" class="form-control" id="username" name="username" value="${user.userName}" readonly placeholder="Username">
                                    </div>

                                    <div class="form-group">
                                        <label>Email:</label>
                                        <input type="email" class="form-control" id="email" name="email" value="${user.email}" placeholder="Email">
                                        <div style="color: red" id="emailFail"></div>
                                    </div>

                                    <div class="form-group">
                                        <label>Gender:</label>
                                        <select class="form-control" id="gender" name="gender">
                                            <option value="Male" ${user.gender ? 'selected' : ''}>Male</option>
                                            <option value="Female" ${user.gender ? 'selected' : ''}>Female</option>
                                        </select>
                                        <div style="color: red" id="genderFail"></div>
                                    </div>

                                    <div class="form-group text-center">
                                        <button type="submit" class="btn btn-block btn-outline-danger submit">Save Changes</button>
                                    </div>
                                </form>
                                <div class="form-group text-center">
                                    <a href="profile" class="btn btn-outline-secondary">Back to Profile</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <script>
            function validateForm() {
                var fullname = document.getElementById('fullname').value.trim();
                var email = document.getElementById('email').value.trim();
                var gender = document.getElementById('gender').value.trim();
                var dob = document.getElementById('dob').value.trim();

                var isValid = true;
                clearErrors();

                if (fullname === '') {
                    document.getElementById('fullnameFail').innerText = 'Full name cannot be empty';
                    isValid = false;
                }

                if (email === '') {
                    document.getElementById('emailFail').innerText = 'Email cannot be empty';
                    isValid = false;
                }

                if (gender === '') {
                    document.getElementById('genderFail').innerText = 'Please select gender';
                    isValid = false;
                }

                if (dob === '') {
                    document.getElementById('dobFail').innerText = 'Date of birth cannot be empty';
                    isValid = false;
                }

                return isValid;
            }

            function clearErrors() {
                document.getElementById('fullnameFail').innerText = '';
                document.getElementById('emailFail').innerText = '';
                document.getElementById('genderFail').innerText = '';
                document.getElementById('dobFail').innerText = '';
            }
        </script>

    </body>
</html>
