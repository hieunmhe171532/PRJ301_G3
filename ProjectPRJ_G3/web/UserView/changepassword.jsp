<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" 
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <title></title>
    </head>
    <body>
        <div class="container">

            <form action="changepassword" method="post" onsubmit="return validateForm();">
                <div class="half">
                    <div class="bg order-1 order-md-2" style="background-image: url('images/banner-01.jpg');"></div>
                    <div class="contents order-2 order-md-1">

                        <div class="container">
                            <div class="row align-items-center justify-content-center">
                                <div class="col-md-8">
                                    <div class="form-block">
                                        <div class="text-center mb-5">
                                            <h3>Change Password</h3>
                                        </div>

                                        <div class="form-group">
                                            <label>Current password:</label>
                                            <input type="password" class="form-control" name="currentpass" placeholder="Enter current password">
                                            <div style="color: red" id="currentpassFail"></div>
                                        </div>

                                        <div class="form-group">
                                            <label>New password:</label>
                                            <input type="password" class="form-control" name="newpass" placeholder="Enter new password">
                                            <div style="color: red" id="newpassFail"></div>
                                        </div>

                                        <div class="form-group">
                                            <label>Re-New password:</label>
                                            <input type="password" class="form-control" name="renewpass" placeholder="Enter re-new password">
                                            <div style="color: red" id="renewpassFail"></div>
                                        </div>
                                        
                                        
                                        <button href="profile" type="submit" class="btn btn-primary">Save Change</button>
                                        <a href="profile" class="btn btn-secondary">Back to Profile</a>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>


            </form>
        </div>

        <script>
            function validateForm() {
            var currentPassword = document.forms["changePasswordForm"]["currentpassFail"].value.trim();
            var newPassword = document.forms["changePasswordForm"]["newpassFail"].value.trim();
            var renewPassword = document.forms["changePasswordForm"]["renewpassFail"].value.trim();
            var valid = true;

            if (currentPassword === "") {
                document.getElementById("currentpassFail").innerText = "Old password is required.";
                valid = false;
            } else {
                document.getElementById("currentpassFail").innerText = "";
            }

            if (newPassword === "") {
                document.getElementById("newpassFail").innerText = "New password is required.";
                valid = false;
            } else {
                document.getElementById("newpassFail").innerText = "";
            }

            if (renewPassword !== newPassword) {
                document.getElementById("renewpassFail").innerText = "Passwords do not match.";
                valid = false;
            } else {
                document.getElementById("renewpassFail").innerText = "";
            }

            return valid;
        }
        </script>
    </body>
</html>
