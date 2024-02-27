<%@page import="Daos.cate_ad"%>
<%@page import="Daos.BrandDAO"%>
<%@page import="java.sql.ResultSet"%>
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
        <div class="col-lg-12  container mt-5">

            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Add new Category</h5>
                    <form method="post" onsubmit="return validateForm()" enctype="multipart/form-data" action="/ProductController" >
                        <br>
                        <div class="row">     
                            <br>
                            <div class="col-sm-2"> <p>Product Name</p></div>
                            <div class="col-sm-8"><input class="form-control" type="text" id="name" name="name"  /></div>

                        </div>
                        <div class="row">
                            <br>
                            <div class="col-sm-2"><p>Description</p></div>
                            <div class="col-sm-8"><input class="form-control" type="text" id="des" name="des"/></div>

                        </div>
                        <div class="row">
                            <br>
                            <div class="col-sm-2"><p>Price</p></div>
                            <div class="col-sm-8"><input class="form-control" type="number" id="price" name="price"  /></div>                       
                        </div>
                        <div class="row">
                            <br>
                            <div class="col-sm-2"><p>Quantity</p></div>
                            <div class="col-sm-8"><input class="form-control" type="number" id="quantity" name="quantity" /></div>                       
                        </div>
                        <div class="row">
                            <br>
                            <div class="col-sm-2"><p>Size</p></div>
                            <div class="col-sm-8"><input class="form-control" type="number" id="size" name="size" /></div>                       
                        </div>
                        <div class="row"><br/>
                            <div class="col-sm-2"><p>Image</p></div>
                            <div class="col-sm-8"><input class="form-control" type="file" id="image" name="image" size="50"  /></div>                        
                        </div>
                        <div class="row">
                            <div class="col-sm-2"><p>Categories ID</p></div>
                            <div class="col-sm-8">
                                <select name="catid" id="catid" class="form-control">
                                    <%
                                        cate_ad cDAO = new cate_ad();
                                        ResultSet rs = cDAO.getAll();
                                        while (rs.next()) {

                                    %>
                                    <option value="<%= rs.getInt("CateID")%>"><%= rs.getString("CateName")%></option>

                                    <%                             }
                                    %>
                                </select></div>            
                        </div>

                        <div class ="row">
                            <div class="col-sm-2"><p>Brand ID</p></div>
                            <div class="col-sm-8">
                                <select name="brandid" id="brandid" class="form-control">
                                    <%
                                        BrandDAO cDAO2 = new BrandDAO();
                                        ResultSet r = cDAO2.getAll();
                                        while (r.next()) {
                                    %>
                                    <option value="<%= r.getInt("BrandID")%>"><%= r.getString("BrandName")%></option>
                                    <%
                                        }
                                    %>

                                </select>
                            </div>
                        </div>
                        <div class="row"><br/>
                            <div class="col-sm-2"><p>Color</p></div>
                            <div class="col-sm-8"><input class="form-control" id="color" type="text" name="color"/></div>                        
                        </div>

                        <div class="col-sm-2"></div>
                        <div class="col-sm-8" id="lbtn">
                            <input class="btn btn-primary" id="submit" type="submit" name="btnAddNew" value="Add New"/>
                            <a href='/ProductController' class="btn btn-secondary" id="btl">Back to List</a>  
                        </div>

                    </form>

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
        <script >
            function validateForm() {

                var name = document.getElementById("name").value;
                var des = document.getElementById("des").value;
                var price = document.getElementById("price").value;
                var quantity = document.getElementById("quantity").value;
                var size = document.getElementById("size").value;
                var image = document.getElementById("image").value;
                var catid = document.getElementById("catid").value;
                var brandid = document.getElementById("brandid").value;
                var color = document.getElementById("color").value;
                if (name === "" || des === "" || price === "" || quantity === "" || size === "" || catid === "" || brandid === "" || color === "") {
                    alert("Please enter full data!");
                    return false;
                }

                if (!image) {
                    alert("Please, choose a picture!");
                    return false;
                }

                // Code ki?m tra gi� tr? c?a c�c tr??ng s?
                if (price <= 0 || quantity <= 0 || size <= 0) {

                    alert("Accept only positive number greater than 0!");
                    return false;
                }
                if (isNaN(price) || isNaN(quantity) || isNaN(size)) {
                    alert("Input must be number.");
                    return false;
                }
                return true;
            }

        </script>
        <!-- Template Main JS File -->
        <script src="/resources/AdminAssets/js/main.js"></script>

    </body>

</html>