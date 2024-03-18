<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Add new manufacturer</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

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

    </head>

    <body>
        <%
            String existName = (String) request.getAttribute("existName");
            String validPhone = (String) request.getAttribute("validPhone");
            //--------------------
            String manuName = (String) session.getAttribute("manuName");
            String manuLicense = (String) session.getAttribute("manuLicense");
            String manuAddress = (String) session.getAttribute("manuAddress");
            String manuPhone = (String) session.getAttribute("manuPhone");
        %>

        <div class="col-lg-12 container mt-5">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Add new Manufacturer</h5>
                    <!-- Horizontal Form -->
                    <form name="productForm" method="post" action='/ManufacturerController' onsubmit="return validateForm()">
                        <div class="row mb-3">
                            <label for="brandName" class="col-sm-2 col-form-label">Manufacturer name<span style="color: red">*</span></label>
                            <div class="col-sm-10">
                                <input type="text" id="brandName" name="manuName" class="form-control" required value="<%= manuName!= null ? manuName : "" %>">
                                <span id="nameWarning" style="color: red; "><%= existName != null ? existName : ""%></span>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="brandLicense" class="col-sm-2 col-form-label">License <span style="color: red">*</span></label>
                            <div class="col-sm-10">
                                <input type="text" id="brandLicense" name="manuLicense" class="form-control" required  value="<%= (manuLicense != null && !manuLicense.isEmpty()) ? manuLicense : ""%>">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="brandAddress" class="col-sm-2 col-form-label">Address <span style="color: red">*</span></label>
                            <div class="col-sm-10">
                                <input type="text" id="brandAddress" name="manuAddress" class="form-control" required  value="<%= manuAddress != null ? manuAddress : "" %>">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="brandPhone" class="col-sm-2 col-form-label">Phone <span style="color: red">*</span></label>
                            <div class="col-sm-10">
                                <input type="number" id="brandPhone" name="manuPhone" class="form-control" required value="<%= manuPhone%>">
                                <span  style="color: red; "><%= validPhone != null ? validPhone : "" %></span>
                            </div>
                        </div>
                        <div class="text-left">
                            <input class="btn btn-primary" id="submit" type="submit" name="btnManuAddNew" value="Add New" />
                            <a href='/ManufacturerController' class="btn btn-secondary">Back to list</a>
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
                            var brandName = document.getElementById("brandName").value;
                            if (brandName === "") {
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