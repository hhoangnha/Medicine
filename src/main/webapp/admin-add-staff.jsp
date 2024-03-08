<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Add staff</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <!-- Favicons -->
<!--        <link href="/resources/AdminAssets/img/favicon.png" rel="icon">
        <link href="/resources/AdminAssets/img/apple-touch-icon.png" rel="apple-touch-icon">-->

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
            String exist = (String) session.getAttribute("exist");
        %>
        <div class="col-lg-12 container mt-5">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Add new Staff</h5>

                    <!-- Horizontal Form -->
                    <h5 style="color: red"> <%= exist != null ? exist : "" %></h5>
                    <form name="productForm" method="post" action='/ManageStaffController' onsubmit="return validateFormOrder()">
                        <div class="row mb-3">
                            <div class="col-sm-6">

                                <label for="inputEmail3" class="col-form-label">Username</label>
                                <input type="text" name='username' class="form-control" id="inputText">
                            </div>
                            <div class="col-sm-6"> 
                                <label for="Password" class="col-form-label">Password</label>
                                <input type="password" name='password' class="form-control" id="Password">
                            </div>
                        </div>
                       
                        <div class="row mb-3">
                            <div class="col-sm-6">
                                <label for="Fullname" class="col-form-label">Fullname</label>
                                <input type="text" name='fullname' class="form-control" id="Fullname">
                            </div>
                            <div class="col-sm-6">
                                <label for="Email" class="col-form-label">Email</label>
                                <input type="email" name='email' class="form-control" id="Email">
                            </div>
                        </div>
                        <!-- C?c d?ng kh?c c? th? th?m t??ng t? -->
                        <div class="row mb-3">
                            <div class="col-sm-6">
                                <label for="Address" class="col-form-label">Address</label>
                                <input type="text" name='address' class="form-control" id="Address">
                            </div>
                            <div class="col-sm-6">
                                <label for="Birthday" class="col-form-label">Birthday</label>
                                <input type="date" name='birthday' class="form-control" id="Birthday">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-6">
                                <label for="Phone" class="col-form-label">Phone</label>
                                <input type="text" name='phone' class="form-control" id="Phone">
                            </div>
                            <div class="col-sm-6">
                                <label for="sex" class="col-form-label">Gender</label>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="sex" id="male" checked="" value="1">
                                    <label class="form-check-label" for="male">Male</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="sex" id="female" value="0">
                                    <label class="form-check-label" for="female">Female</label>
                                </div>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-6">
                                <label for="idNumber" class="col-form-label">ID number</label>
                                <input type="text" name='idNumber' class="form-control" id="idNumber">
                            </div>
                            <div class="col-sm-6">
                                <label for="issuedBy" class="col-form-label">Issued BY</label>
                                <input type="text" name='issuedBy' class="form-control" id="issuedBy">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-6">
                                <label for="LicenseDate" class="col-form-label">License Date</label>
                                <input type="date" name='licenseDate' class="form-control" id="LicenseDate">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-12 text-left"> <!-- S? d?ng 12 c?t cho n?t v? ??t n? ? b?n tr?i -->
                                <input class="btn btn-primary" type="submit" value="Create" name="add" />
                                <a href='/AdminCateController' class="btn btn-secondary">Back to list</a>
                            </div>
                        </div>
                    </form><!-- K?t th?c Form Ngang -->
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