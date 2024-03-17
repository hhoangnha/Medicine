<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Dashboard - NiceAdmin Bootstrap Template</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <!-- Favicons -->
        <link href="/resources/AdminAssets/img/favicon.png" rel="icon">
        <link href="/resources/AdminAssets/img/apple-touch-icon.png" rel="apple-touch-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.gstatic.com" rel="preconnect">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

        <!-- Vendor CSS Files -->
        <link href="/resources/AdminAssets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="/resources/AdminAssets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="/resources/AdminAssets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="/resources/AdminAssets/vendor/quill/quill.snow.css" rel="stylesheet">
        <link href="/resources/AdminAssets/vendor/quill/quill.bubble.css" rel="stylesheet">
        <link href="/resources/AdminAssets/vendor/remixicon/remixicon.css" rel="stylesheet">
        <link href="/resources/AdminAssets/vendor/simple-datatables/style.css" rel="stylesheet">

        <!-- Template Main CSS File -->
        <link href="/resources/AdminAssets/css/style.css" rel="stylesheet">

        <!-- =======================================================
        * Template Name: NiceAdmin
        * Updated: Sep 18 2023 with Bootstrap v5.3.2
        * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
        * Author: BootstrapMade.com
        * License: https://bootstrapmade.com/license/
        ======================================================== -->
    </head>

    <body>
        <%
            String exist = (String) request.getAttribute("exist");
             String existID = (String) request.getAttribute("existID");
            String invalid = (String) request.getAttribute("invalid");
            String invalidID = (String) request.getAttribute("invalidID");
            String invalidBirthday = (String) request.getAttribute("invalidBirthday");
            String invalidLicense = (String) request.getAttribute("invalidLicense");
            
        %>
        <div class="col-lg-12 container mt-5">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Add new Staff</h5>

                    <!-- Horizontal Form -->
                    <h5 style="color: red"> <%= exist != null ? exist : ""%></h5>
                    <form name="productForm" method="post" action='/ManageStaffController' onsubmit="return validateFormOrder()">
                        <div class="row mb-3">
                            <div class="col-sm-6"> <!-- Chia thành 2 c?t (50%) -->

                                <label for="inputEmail3" class="col-form-label" >Username  <span style="color: red"> * </span></label>
                                <input type="text" name='username' class="form-control" id="inputText" required value="<%= (request.getAttribute("username") != null) ? request.getAttribute("username") : ""%>">
                            </div>
                            <div class="col-sm-6"> <!-- Chia thành 2 c?t (50%) -->
                                <label for="Password" class="col-form-label" required>Password <span style="color: red"> * </span></label>
                                <input type="password" name='password' class="form-control" id="Password" value="<%= (request.getAttribute("password") != null) ? request.getAttribute("password") : ""%>">
                            </div>
                        </div>
                        <!-- Thêm ph?n còn l?i c?a form t??ng t? -->
                        <div class="row mb-3">
                            <div class="col-sm-6">
                                <label for="Fullname" class="col-form-label">Fullname</label>
                                <input type="text" name='fullname' class="form-control" id="Fullname" value="<%= (request.getAttribute("fullname") != null) ? request.getAttribute("fullname") : ""%>">
                            </div>
                            <div class="col-sm-6">
                                <label for="Email" class="col-form-label" required>Email <span style="color: red"> * </span></label>
                                <input type="email" name='email' class="form-control" id="Email" required value="<%= (request.getAttribute("email") != null) ? request.getAttribute("email") : ""%>">
                            </div>
                        </div>
                        <!-- Các dòng khác có th? thêm t??ng t? -->
                        <div class="row mb-3">
                            <div class="col-sm-6">
                                <label for="Address" class="col-form-label">Address</label>
                                <input type="text" name='address' class="form-control" id="Address" value="<%= (request.getAttribute("address") != null) ? request.getAttribute("address") : ""%>">
                            </div>
                            <div class="col-sm-6">
                                <label for="Birthday" class="col-form-label">Birthday  <h5 style="color: red"> <%= invalidBirthday != null ? invalidBirthday : ""%></h5></label>
                                <input type="date" name='birthday' class="form-control" id="Birthday" value="<%= (request.getAttribute("birthday") != null) ? request.getAttribute("birthday") : ""%>">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-6">
                                <label for="Phone" class="col-form-label">Phone   <h5 style="color: red"> <%= invalid != null ? invalid : ""%></h5></label>
                                <input type="number" name="phone" class="form-control" id="Phone" value="<%= (request.getAttribute("phone") != null) ? request.getAttribute("phone") : ""%>">

                            </div>
                            <div class="col-sm-6">
                                <label for="sex" class="col-form-label">Gender</label>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="sex" id="male" checked value="<%= (request.getAttribute("sex") != null) ? request.getAttribute("sex") : ""%>">
                                    <label class="form-check-label" for="male">Male</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="sex" id="female" value="<%= (request.getAttribute("sex") != null) ? request.getAttribute("sex") : ""%>">
                                    <label class="form-check-label" for="female">Female</label>
                                </div>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-6">
                                <label for="idNumber" class="col-form-label">ID number  
                                       <h5 style="color: red"> <%= existID != null ? existID : ""%></h5>
                                    <h5 style="color: red"> <%= invalidID != null ? invalidID : ""%></h5></label>
                                <input type="number" name='idNumber' class="form-control" id="idNumber" value="<%= (request.getAttribute("idNumber") != null) ? request.getAttribute("idNumber") : ""%>">
                            </div>
                            <div class="col-sm-6">
                                <label for="issuedBy" class="col-form-label">Issued BY</label>
                                <input type="text" name='issuedBy' class="form-control" id="issuedBy" value="<%= (request.getAttribute("issuedBy") != null) ? request.getAttribute("issuedBy") : ""%>" >
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-6">
                                <label for="LicenseDate" class="col-form-label">License Date   <h5 style="color: red"> <%= invalidLicense != null ? invalidLicense : ""%></h5></label>
                                <input type="date" name='licenseDate' class="form-control" id="LicenseDate" value="<%= (request.getAttribute("licenseDate") != null) ? request.getAttribute("licenseDate") : ""%>" >
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-12 text-left"> <!-- S? d?ng 12 c?t cho nút và ??t nó ? bên trái -->
                                <input class="btn btn-primary" type="submit" value="Create" name="add" />
                                <a href='/ManageStaffController' class="btn btn-secondary">Back to list</a>
                            </div>
                        </div>
                    </form><!-- K?t thúc Form Ngang -->
                </div>
            </div>
        </div>





        <!-- Vendor JS Files -->
        <script src="/resources/AdminAssets/vendor/apexcharts/apexcharts.min.js"></script>
        <script src="/resources/AdminAssets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="/resources/AdminAssets/vendor/chart.js/chart.umd.js"></script>
        <script src="/resources/AdminAssets/vendor/echarts/echarts.min.js"></script>
        <script src="/resources/AdminAssets/vendor/quill/quill.min.js"></script>
        <script src="/resources/AdminAssets/vendor/simple-datatables/simple-datatables.js"></script>
        <script src="/resources/AdminAssets/vendor/tinymce/tinymce.min.js"></script>
        <script src="/resources/AdminAssets/vendor/php-email-form/validate.js"></script>
        <script>
                        function validateFormOrder() {


                            var productname = document.forms["productForm"]["name"].value;
                            var prodes = document.forms["productForm"]["des"].value;

                            //  var date = document.forms["productForm"]["txtdate"].value;
                            if (productname === "" || prodes === "") {
                                alert("Please enter all flies");
                                return false;
                            }
                            return true;
                        }
        </script>
        <!-- Template Main JS File -->
        <script src="/resources/AdminAssets/js/main.js"></script>

    </body>

</html>