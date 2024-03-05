<%@page import="Model.CategorieModel"%>
<%@page import="Daos.cate_ad"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Category</title>
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
<div class="col-lg-12  container mt-5">
<%
            cate_ad cd = new cate_ad();
            int cateid = (int) session.getAttribute("cateid");
            CategorieModel ct = cd.getCate(cateid);
            int catei = ct.getCateID();
            String catename = ct.getCateName();
            String des = ct.getCateDescription();
         


        %>
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Edit Category</h5>

              <!-- Horizontal Form -->
              <form  name="productForm" method="post" action='/AdminCateController' onsubmit="return validateFormOrder()">
                <div class="row mb-3">
                  <label for="inputEmail3" class="col-sm-2 col-form-label">ID</label>
                  <div class="col-sm-10">
                      <input type="text"  value="<%= catei%>"  name='oid' readonly="" class="form-control" >
                  </div>
                </div>
                  <div class="row mb-3">
                  <label for="inputEmail3" class="col-sm-2 col-form-label">Name</label>
                  <div class="col-sm-10">
                    <input type="text" name='name' value="<%= catename%>" class="form-control" id="inputText">
                  </div>
                </div>
                <div class="row mb-3">
                  <label for="inputEmail3" class="col-sm-2 col-form-label"> Description</label>
                  <div class="col-sm-10">
                      <input type="text" name='des' value="<%= des%>" class="form-control" id="inputEmail">
                  </div>
                </div>
              
                <div class="text-left">
                <input class="btn btn-primary" type="submit" value="Update" name ="edit" />
                  <a href='/AdminCateController' class="btn btn-secondary">Back to list</a>
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

                var productID = document.forms["productForm"]["oid"].value;
                var productname = document.forms["productForm"]["name"].value;
                var prodes = document.forms["productForm"]["des"].value;
                var s = document.forms["productForm"]["st"].value;
                //  var date = document.forms["productForm"]["txtdate"].value;
                if (productID === "" || productname === "" || prodes === "" || s === "") {
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