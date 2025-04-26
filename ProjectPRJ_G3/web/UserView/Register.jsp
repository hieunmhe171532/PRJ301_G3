<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="css/owl.carousel.min.css">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" 
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

        <!-- Style -->
        <!--        <link rel="stylesheet" type="text/css" href="css/signIn.css">
                <link rel="stylesheet" type="text/css" href="css/signUp.css">-->

        <title>Register</title>
    </head>
    <body>


        <div class="half">
            <div class="bg order-1 order-md-2" style="background-image: url('images/banner-01.jpg');"></div>
            <div class="contents order-2 order-md-1">

                <div class="container">
                    <div class="row align-items-center justify-content-center">
                        <div class="col-md-6">
                            <div class="form-block">
                                <div class="text-center mb-5">

                                    <h3>Register<strong></strong></h3>
                                    <!-- <p class="mb-4">Lorem ipsum dolor sit amet elit. Sapiente sit aut eos consectetur adipisicing.</p> -->
                                </div>
                                <form class="register-form" name="register-form" action="register" method="post" onsubmit="return validateForm()">

                                    <div class="form-group">
                                        <input type="text" class="form-control" name="fullname" id="fullname" value="${param.fullname}" placeholder="Fullname" >
                                        <div style="color: red" id="fullnameFail"></div>
                                    </div>

                                    <div class="form-group">
                                        <input type="text" class="form-control" name="username" id="username" value="${param.username}" placeholder="Username" >
                                        <div style="color: red" id="userFail"></div>
                                    </div>

                                    <div class="form-group">
                                        <input type="email" class="form-control" name="email" id="email" value="${param.email}" placeholder="Email" >
                                        <div style="color: red" id="emailFail"></div>

                                    </div>

                                    <div class="form-group">
                                        <select class="form-control" name="gender" >
                                            <option value="">Select Gender</option>
                                            <option value="true" ${param.gender == 'true' ? 'selected' : ''}>Male</option>
                                            <option value="false" ${param.gender == 'true' ? 'selected' : ''}>Female</option>
                                        </select>
                                        <div style="color: red" id="genderFail"></div>                                        
                                    </div>

                                    <div class="form-group">
                                        <input type="date" class="form-control" name="dob" value="${param.dob}" >
                                        <div style="color: red" id="dobFail"></div>
                                    </div>                                    

                                    <div class="row md-12">
                                        <div class="form-group col-md-6">
                                            <input type="password" class="form-control" name="password" id="password" placeholder="Password" >
                                            <div style="color: red" id="passwordFail"></div>
                                        </div>

                                        <div class="form-group col-md-6">
                                            <input type="password" class="form-control" name="repassword" id="repassword" placeholder="Re-password" >
                                            <div style="color: red" id="repassFail"></div>
                                        </div>
                                    </div>
                                    <div style="color: red">${error}</div>
                                    <br>

                                    <button type="submit" class="btn btn-block btn-outline-danger submit"> Register </button>
                                    <span class="ml-auto"><a href="userlogin" class="forgot-pass">Login</a></span> 
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


        </div>



        <!--        <script src="js/jquery-3.2.1.min.js"></script>
                <script src="js/popper.min.js"></script>
                <script src="js/bootstrap.min.js"></script>
                        <script src="js/main.js"></script>
                <script src="js/registerValidate.js"></script>-->
    </body>
    <script>
        function validateForm() {
            var fullname = document.getElementById('fullname').value.trim();
            var username = document.getElementById('username').value.trim();
            var email = document.getElementById('email').value.trim();
            var gender = document.getElementsByName('gender')[0].value;
            var dob = document.getElementsByName('dob')[0].value;
            var password = document.getElementById('password').value.trim();
            var repassword = document.getElementById('repassword').value.trim();

            var isValid = true;

            clearErrors();

            document.getElementById('fullnameFail').innerText = '';
            document.getElementById('userFail').innerText = '';
            document.getElementById('emailFail').innerText = '';
            document.getElementById('passwordFail').innerText = '';
            document.getElementById('repassFail').innerText = '';

            if (fullname === '') {
                document.getElementById('fullnameFail').innerText = 'Fullname không được để trống';
                isValid = false;
            } else {
                var namePattern = /^[A-Za-z\s]+$/;
                if (!namePattern.test(fullname)) {
                    document.getElementById('fullnameFail').innerText = 'Fullname chỉ được chứa chữ cái và khoảng trắng';
                    isValid = false;
                }
            }

            var usernamePattern = /^[a-zA-Z0-9]{4,16}$/;
            if (username === '') {
                document.getElementById('userFail').innerText = 'username không được để trống';
                isValid = false;
            } else if (!usernamePattern.test(username)) {
                document.getElementById('userFail').innerText = 'Username chỉ chứa chữ, số và từ 4-16 ký tự';
                isValid = false;
            }

            if (email === '') {
                document.getElementById('emailFail').innerText = 'Email không được để trống';
                isValid = false;
            }

            if (gender === '') {
                document.getElementById('genderFail').innerText = 'Vui lòng chọn giới tính';
                isValid = false;
            }

            if (dob === '') {
                document.getElementById('dobFail').innerText = 'Vui lòng chọn ngày sinh';
                isValid = false;
            }

            var passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{4,16}$/;
            if (password === '') {
                document.getElementById('passwordFail').innerText = 'Vui lòng nhập mật khẩu';
                isValid = false;
            } else if (!passwordPattern.test(password)) {
                document.getElementById('passwordFail').innerText = 'Password phải chứa chữ và số, từ 4-16 ký tự';
                isValid = false;
            }


            if (repassword === '') {
                document.getElementById('repassFail').innerText = 'Vui lòng nhập lại mật khẩu';
                isValid = false;
            } else if (password !== repassword) {
                document.getElementById('repassFail').innerText = 'Mật khẩu không trùng khớp';
                isValid = false;
            }

            return isValid;
        }

////live validate
        function clearErrors() {
            document.getElementById('fullnameFail').innerText = '';
            document.getElementById('userFail').innerText = '';
            document.getElementById('emailFail').innerText = '';
            document.getElementById('genderFail').innerText = '';
            document.getElementById('dobFail').innerText = '';
            document.getElementById('passwordFail').innerText = '';
            document.getElementById('repassFail').innerText = '';
        }

        document.getElementById('fullname').addEventListener('input', function () {
            if (this.value.trim() !== '') {
                document.getElementById('fullnameFail').innerText = '';
            }
        });

        document.getElementById('username').addEventListener('input', function () {
            if (this.value.trim() !== '') {
                document.getElementById('userFail').innerText = '';
            }
        });

        document.getElementById('email').addEventListener('input', function () {
            if (this.value.trim() !== '') {
                document.getElementById('emailFail').innerText = '';
            }
        });

        document.getElementsByName('gender')[0].addEventListener('change', function () {
            if (this.value !== '') {
                document.getElementById('genderFail').innerText = '';
            }
        });

        document.getElementsByName('dob')[0].addEventListener('input', function () {
            if (this.value !== '') {
                document.getElementById('dobFail').innerText = '';
            }
        });

        document.getElementById('password').addEventListener('input', function () {
            if (this.value.trim() !== '') {
                document.getElementById('passwordFail').innerText = '';
            }
        });

        document.getElementById('repassword').addEventListener('input', function () {
            var password = document.getElementById('password').value.trim();
            var repassword = this.value.trim();
            if (repassword === password) {
                document.getElementById('repassFail').innerText = '';
            }
        });
    </script>
</html>