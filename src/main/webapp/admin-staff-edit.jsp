<%@page import="Model.StaffModel"%>
<%@page import="Model.ManufacturerModel"%>
<%@page import="Model.BrandModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            StaffModel staff = (StaffModel) request.getAttribute("detail");
            String exist = (String) session.getAttribute("exist");
        %>
        <div class="col-lg-12 container mt-5">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Update Staff Information</h5>
                    <!-- Horizontal Form -->
                    <h5 style="color: red"> <%= exist != null ? exist : ""%></h5>
                    <form name="productForm" method="post" action='/ManageStaffController?edit=<%= staff.getUserID()%>' onsubmit="return validateForm()">
                        <div class="row mb-3">
                            <div class="col-sm-2"><p>Staff ID</p></div>
                            <div class="col-sm-8"><input class="form-control" disabled  readonly="" type="number" name="staffID" id="staffID" value="<%= staff.getUserID()%>"  /></div>
                        </div>

                        <div class="row mb-3">
                            <label for="username" class="col-sm-2 col-form-label">Username</label>
                            <div class="col-sm-10">
                                <input type="text" id="username" name="username" value="<%= staff.getUsername()%>" class="form-control">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="password" class="col-sm-2 col-form-label">Password</label>
                            <div class="col-sm-10">
                                <input type="password" id="password" name="password" value="" class="form-control">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="fullname" class="col-sm-2 col-form-label">Fullname</label>
                            <div class="col-sm-10">
                                <input type="text" id="fullname" name="fullname" value="<%= staff.getFullname()%>" class="form-control">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="email" class="col-sm-2 col-form-label">Email</label>
                            <div class="col-sm-10">
                                <input type="text" id="email" name="email" value="<%= staff.getEmail()%>" class="form-control">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="phone" class="col-sm-2 col-form-label">Phone</label>
                            <div class="col-sm-10">
                                <input type="text" id="phone" name="phone" value="<%= staff.getPhone()%>" class="form-control">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="address" class="col-sm-2 col-form-label">Address</label>
                            <div class="col-sm-10">
                                <input type="text" id="address" name="address" value="<%= staff.getAddress()%>" class="form-control">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="birthday" class="col-sm-2 col-form-label">Birthday</label>
                            <div class="col-sm-10">
                                <input type="date" id="birthday" name="birthday" value="<%= staff.getBirthday()%>" class="form-control">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="gender" class="col-sm-2 col-form-label">Gender</label>
                            <div class="col-sm-10">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="gender" id="male" value="1" <%= "1".equals(staff.getGender()) ? "checked" : ""%>>
                                    <label class="form-check-label" for="male">Male</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="gender" id="female" value="0" <%= "0".equals(staff.getGender()) ? "checked" : ""%>>
                                    <label class="form-check-label" for="female">Female</label>
                                </div>
                            </div>
                        </div>


                        <div class="row mb-3">
                            <label for="idNumber" class="col-sm-2 col-form-label">ID Number</label>
                            <div class="col-sm-10">
                                <input type="text" id="idNumber" name="idNumber" value="<%= staff.getIdNumber()%>" class="form-control">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="issuedBy" class="col-sm-2 col-form-label">Issued By</label>
                            <div class="col-sm-10">
                                <input type="text" id="issuedBy" name="issuedBy" value="<%= staff.getIssuedBy()%>" class="form-control">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="licenseDate" class="col-sm-2 col-form-label">License date</label>
                            <div class="col-sm-10">
                                <input type="text" id="licenseDate" name="licenseDate" value="<%= staff.getLicenseDate()%>" class="form-control">
                            </div>
                        </div>
                        <!-- comment -->
                        <div class="text-left">
                            <input class="btn btn-primary" id="submit" type="submit" name="btnBrandUpdate" value="Update"/>
                            <a href='/ManageStaffController' class="btn btn-secondary">Back to list</a>
                        </div>
                    </form><!-- End Horizontal Form -->
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
                        function validateForm() {
                            var staffName = document.getElementById("staffName").value;
                            if (staffName === "") {
                                alert("Please enter all data");
                                return false;
                            }
                            return true;
                        }
        </script>
        <!-- Template Main JS File -->
        <script src="/resources/AdminAssets/js/main.js"></script>
    </body>
</html>
