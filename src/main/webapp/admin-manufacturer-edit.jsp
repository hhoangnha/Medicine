<%@page import="Model.ManufacturerModel"%>
<%@page import="Model.BrandModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Edit manufacturer</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

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
            ManufacturerModel manu = (ManufacturerModel) request.getAttribute("detail");

            String existName = (String) request.getAttribute("existName");
            String validPhone = (String) request.getAttribute("validPhone");
            String manuPhone = (String) request.getAttribute("manuPhone");
            // Tiến hành sử dụng giá trị 'manu' ở đây...
%>

        <div class="container mt-5">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Update Manufacturer</h5>
                    <!-- Horizontal Form -->
                    <form name="productForm" method="post" action='/ManufacturerController?edit=<%= manu.getManuID()%>' onsubmit="return validateForm()">
                        <div class="row mb-3">
                            <div class="col-sm-2"><p>Manufacturer ID</p></div>
                            <div class="col-sm-8"><input class="form-control" readonly disabled type="number" name="ManuName" value="<%=  manu.getManuID()%>" /></div>
                        </div>
                        <div class="row mb-3">
                            <label for="ManuName" class="col-sm-2 col-form-label">Manufacturer name  <span style="color: red">*</span></label>
                            <div class="col-sm-10">
                                <input type="text" id="ManuName" required  name="manuName" value="<%=  manu.getManuName()%>" class="form-control">
                                <span id="nameWarning" style="color: red; "><%= existName != null ? existName : ""%></span>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="ManuLicense" class="col-sm-2 col-form-label">License  <span style="color: red">*</span></label>
                            <div class="col-sm-10">
                                <input type="text" id="ManuLicense" name="manuLicense" value="<%=  manu.getMfgLicense()%>" class="form-control" required>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="ManuAddress" class="col-sm-2 col-form-label">Address  <span style="color: red">*</span></label>
                            <div class="col-sm-10">
                                <input type="text" id="ManuAddress" name="manuAddress" value="<%=  manu.getManuAddress()%>" class="form-control" required>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="Phone" class="col-sm-2 col-form-label">Phone  <span style="color: red">*</span></label>
                            <div class="col-sm-10">
                                <input type="number" id="Phone" name="phone" value="<%= (manuPhone != null ? manuPhone : manu.getPhone())%>" class="form-control" required>
                                <span  style="color: red; "><%= validPhone != null ? validPhone : ""%></span>
                            </div>
                        </div>
                        <div class="text-left">
                            <input class="btn btn-primary" id="submit" type="submit" name="btnBrandUpdate" value="Update" />
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
                            var ManuName = document.getElementById("ManuName").value;
                            if (ManuName === "") {
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
