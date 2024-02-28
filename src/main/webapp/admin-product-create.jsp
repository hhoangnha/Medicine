<%@page import="Model.ManufacturerModel"%>
<%@page import="java.util.List"%>
<%@page import="Daos.ManufacturerDAO"%>
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
                    <h5 class="card-title">Add new Product</h5>
                    <form class="d-flex flex-row justify-content-center" method="post" onsubmit="return validateForm()" enctype="multipart/form-data" action="/ProductController" >
                        <br>
                        <div class="w-50 p-3">
                            <div class="row">     
                                <br>
                                <div class="col-sm-4 col-xs-8"> <p>Product Name</p></div>
                                <div class="col-sm-8"><input class="form-control" type="text" id="name" name="name"  /></div>

                            </div>
                            <div class="row">
                                <br>
                                <div class="col-sm-4 col-xs-8"><p>Price</p></div>
                                <div class="col-sm-8"><input class="form-control" type="number" id="price" name="price"  /></div>                       
                            </div>
                            <div class="row">
                                <br>
                                <div class="col-sm-4 col-xs-8"><p>Manufacture Date</p></div>
                                <div class="col-sm-8"><input class="form-control" type="date" id="manufacturedate" name="manufacturedate" /></div>                       
                            </div>
                            <div class="row">
                                <br>
                                <div class="col-sm-4 col-xs-8"><p>Expiration Date</p></div>
                                <div class="col-sm-8"><input class="form-control" type="date" id="expirationdate" name="expirationdate" /></div>                       
                            </div>
                            <div class="row">
                                <div class="col-sm-4 col-xs-8"><p>Categories</p></div>
                                <div class="col-sm-8">
                                    <select name="catid" id="catid" class="form-control">
                                        <option>Select Category</option>
                                        <%
                                            cate_ad cDAO = new cate_ad();
                                            ResultSet rs = cDAO.getAll();
                                            while (rs.next()) {

                                        %>
                                        <option value="<%= rs.getInt("CateID")%>"><%= rs.getString("CateName")%></option>

                                        <%
                                            }
                                        %>
                                    </select>
                                </div>            
                            </div>
                            <div class ="row">
                                <div class="col-sm-4 col-xs-8"><p>Brand ID</p></div>
                                <div class="col-sm-8">
                                    <select name="brandid" id="brandid" class="form-control">
                                        <option>Select Brand</option>
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
                            <div class="row">
                                <br>
                                <div class="col-sm-4 col-xs-8"><p>Manufacture</p></div>
                                <div class="col-sm-8">
                                    <select name="manuid" id="manuid" class="form-control">
                                        <option>Select Manufacture</option>
                                        <%
                                            ManufacturerDAO manuDAO = new ManufacturerDAO();
                                            List<ManufacturerModel> rsManu = manuDAO.getAllManufacturer();
                                            for (int i = 0; i < rsManu.size(); i++) {

                                        %>
                                        <option value="<%= rsManu.get(i).getManuID()%>"><%= rsManu.get(i).getManuName()%></option>

                                        <%
                                            }
                                        %>
                                    </select>
                                </div>                       
                            </div>
                            <div class="row"><br/>
                                <div class="col-sm-4 col-xs-8"><p>Image</p></div>
                                <div class="col-sm-8"><input class="form-control" type="file" id="image" name="image" size="50"  /></div>                        
                            </div>
                        </div>
                        <div class="w-50 p-3">
                            <div class="row">
                                <br>
                                <div class="col-sm-4 col-xs-8"><p>Description</p></div>
                                <div class="col-sm-8"><input class="form-control" type="text" id="des" name="des"/></div>

                            </div>
                            <div class="row">
                                <br>
                                <div class="col-sm-4 col-xs-8"><p>Quantity</p></div>
                                <div class="col-sm-8"><input class="form-control" type="number" id="quantity" name="quantity" /></div>                       
                            </div>
                            <div class="row"><br/>
                                <div class="col-sm-4 col-xs-8"><p>Indication</p></div>
                                <div class="col-sm-8"><input class="form-control" id="indicaction" type="text" name="indication"/></div>                        
                            </div>
                            <div class="row"><br/>
                                <div class="col-sm-4 col-xs-8"><p>Contraindication</p></div>
                                <div class="col-sm-8"><input class="form-control" id="contraindication" type="text" name="contraindication"/></div>                        
                            </div>
                            <div class="row"><br/>
                                <div class="col-sm-4 col-xs-8"><p>Using</p></div>
                                <div class="col-sm-8"><input class="form-control" id="using" type="text" name="using"/></div>                        
                            </div> 
                            <div class="row"><br/>
                                <div class="col-sm-4 col-xs-8"><p>Element</p></div>
                                <div class="col-sm-8"><input class="form-control" id="element" type="text" name="element"/></div>                        
                            </div>
                            <div class="row"><br/>
                                <div class="col-sm-4 col-xs-8"><p>Made in</p></div>
                                <div class="col-sm-8"><input class="form-control" id="madein" type="text" name="madein"/></div>                        
                            </div>
                            <div class="col-sm-4 col-xs-8"></div>
                            <div class="col-sm-4 w-100 d-flex flex-row justify-content-end" id="lbtn">
                                <a href='/ProductController' class="btn btn-secondary mx-3" id="btl">Back to List</a>  
                                <input class="btn btn-primary me-3" id="submit" type="submit" name="btnAddNew" value="Add New"/>
                            </div>
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
                            var manuid = document.getElementById("manuid").value;
                            var image = document.getElementById("image").value;
                            var catid = document.getElementById("catid").value;
                            var brandid = document.getElementById("brandid").value;
                            var color = document.getElementById("color").value;
                            if (name === "" || des === "" || price === "" || quantity === "" || manuid === "" || catid === "" || brandid === "" || color === "") {
                                alert("Please enter full data!");
                                return false;
                            }

                            if (!image) {
                                alert("Please, choose a picture!");
                                return false;
                            }

                            // Code ki?m tra gi� tr? c?a c�c tr??ng s?
                            if (price <= 0 || quantity <= 0) {

                                alert("Accept only positive number greater than 0!");
                                return false;
                            }
                            if (isNaN(price) || isNaN(quantity) {
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