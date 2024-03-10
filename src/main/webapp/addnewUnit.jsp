<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Add New Unit</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <!-- Favicons -->

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

        <div class="col-lg-12  container mt-5">

            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Add new Unit</h5>

                    <!-- Horizontal Form -->
                    <form id="formCreateUnit" method="post" action="UnitController" onsubmit="return validateFormOrder()">
                        <div class="row mb-3">
                            <%
                                String errorMessage = (String) request.getSession().getAttribute("errorMessage");
                                if (errorMessage != null) {
                            %>
                            <div class="alert alert-danger" role="alert">
                                <%= errorMessage%>
                            </div>
                            <%-- X?a th?ng b?o l?i kh?i session ?? tr?nh hi?n th? nhi?u l?n --%>
                            <% request.getSession().removeAttribute("errorMessage"); %>
                            <% }%>
                            <label class="col-sm-2 col-form-label">Unit Name</label>
                            <div class="col-sm-10">
                                <input type="text" name='UnitName' class="form-control" id="UnitName">
                            </div>
                        </div>
                        <div class="text-left">
                            <input class="btn btn-primary" type="submit" value="Create" name ="btnAddUnit" />
                            <a href='/UnitController' class="btn btn-secondary">Back to list</a>
                        </div>
                    </form><!-- End Horizontal Form -->
                </div>
            </div></div>



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