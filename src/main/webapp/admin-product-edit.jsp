<%@page import="Model.ManufacturerModel"%>
<%@page import="java.util.List"%>
<%@page import="Daos.ManufacturerDAO"%>
<%@page import="Daos.cate_ad"%>
<%@page import="Daos.BrandDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Model.ProductModel"%>
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
                    <h5 class="card-title">Edit Product</h5>
                    <%
                        ProductModel sp = (ProductModel) session.getAttribute("thongtinsanpham");
                    %>
                    <form class="d-flex flex-row justify-content-center" method="post" onsubmit="return validateForm()" enctype="multipart/form-data" action="/ProductController" >
                        <br>
                        <div class="w-50 p-3">
                            <div class="row">
                                <div class="col-sm-4 "><p>Product ID</p></div>
                                <div class="col-sm-8"><input class="form-control" type="number" id="id" name="id" value="<%= sp.getProID()%>" readonly="" /></div>
                            </div>
                            <div class="row">                       
                                <div class="col-sm-4 "> <p>Product Name</p></div>
                                <div class="col-sm-8"><input class="form-control" type="text" id="name" name="name" value="<%= sp.getProName()%>" /></div>
                            </div>
                            <div class="row">
                                <br>
                                <div class="col-sm-4 col-xs-8"><p>Manufacture Date</p></div>
                                <div class="col-sm-8"><input class="form-control" type="date" id="manufacturedate" name="manufacturedate" value="<%= sp.getManufactureDate()%>"/></div>                       
                            </div>
                            <div class="row">
                                <br>
                                <div class="col-sm-4 col-xs-8"><p>Expiration Date</p></div>
                                <div class="col-sm-8"><input class="form-control" type="date" id="expirationdate" name="expirationdate" value="<%= sp.getExpirationDate()%>"/></div>                       
                            </div>
                            <div class="row"><br/>
                                <div class="col-sm-4 "><p>Categories ID</p></div>
                                <div class="col-sm-8">
                                    <select name="catid" id="catid">
                                        <%
                                            cate_ad cDAO = new cate_ad();
                                            ResultSet rs = cDAO.getAll();

                                            while (rs.next()) {
                                                String brandID = rs.getInt("CateID") + "";
                                                String selectedID = sp.getCateID() + "";
                                                //                                    String brandName = r.getString("BrandName");
                                                // Kiểm tra xem BrandID có phải là giá trị cũ không
                                                //                                    boolean isSelected = (selectedBrandID == brandID);
%>
                                        <option value="<%= rs.getInt("CateID")%>"<%= brandID.equals(selectedID) ? "selected" : ""%>><%= rs.getString("CateName")%></option>
                                        <%
                                            }
                                        %>
                                    </select>

                                </div>
                            </div>    
                            <div class="row"><br/>
                                <div class="col-sm-4 "><p>Brand ID</p></div>
                                <div class="col-sm-8">
                                    <select name="brandid" id="brandid" value="<%= sp.getBrandID()%>">
                                        <%
                                            BrandDAO cDAO2 = new BrandDAO();
                                            ResultSet r = cDAO2.getAll();

                                            request.setAttribute("selected", sp.getBrandID());
                                            while (r.next()) {
                                                String brandID = r.getInt("BrandID") + "";
                                                String selectedID = sp.getBrandID() + "";
                                                //                                    String brandName = r.getString("BrandName");
                                                // Kiểm tra xem BrandID có phải là giá trị cũ không
                                                //                                    boolean isSelected = (selectedBrandID == brandID);
                                        %>
                                        <option value="<%= r.getInt("BrandID")%>"<%= brandID.equals(selectedID) ? "selected" : ""%>><%= r.getString("BrandName")%></option>
                                        <%
                                            }
                                        %>
                                    </select>                    
                                </div>
                            </div>
                            <div class="row"><br/>
                                <div class="col-sm-4 "><p>Manu ID</p></div>
                                <div class="col-sm-8">
                                    <select name="manuid" id="manuid" value="<%= sp.getManuID()%>">
                                        <%
                                            ManufacturerDAO manuDAO = new ManufacturerDAO();
                                            List<ManufacturerModel> rsManu = manuDAO.getAllManufacturer();
                                            for (int i = 0; i < rsManu.size(); i++) {
                                                String ManuID = rsManu.get(i).getManuID() + "";
                                                String selectedID = rsManu.get(i).getManuName() + "";
                                                //                                    String brandName = r.getString("BrandName");
                                                // Kiểm tra xem BrandID có phải là giá trị cũ không
                                                //                                    boolean isSelected = (selectedBrandID == brandID);
                                        %>
                                        <option value="<%= rsManu.get(i).getManuID()%>"<%= ManuID.equals(selectedID) ? "selected" : ""%>><%= rsManu.get(i).getManuName()%></option>
                                        <%
                                            }
                                        %>
                                    </select>                    
                                </div>
                            </div>
                            <div class="d-flex flex-row ">
                                <div class="w-50">
                                    <div class="col-sm-12 "><p>Old Image:</p></div>
                                    <div class="col-sm-12">
                                        <img src="/resources/images/<%= sp.getProImage()%>" alt="Product Image" width="100px" height="100px">
                                        <input id="oldImage" name="oldImage" value="<%= sp.getProImage()%>" hidden="">
                                    </div>
                                </div>
                                    <div class="w-50">
                                    <div class="col-sm-12"><p>Image Options:</p></div>
                                    <br>
                                    <div class="col-sm-12">
                                        <!--                                <input  id="updateImage" name="updateImage" >
                                                                        <label for="updateImage">Cập nhật ảnh</label>
                                                                        <br>-->
                                        <input type="file" id="newImage" name="newImage" size="50" >
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="w-50 p-3">
                            <div class="row">
                                <br>
                                <div class="col-sm-4 col-xs-8"><p>Description</p></div>
                                <div class="col-sm-8"><input class="form-control" type="text" id="des" name="des" value="<%= sp.getProDescription()%>"/></div>

                            </div>
                            <div class="row">
                                <br>
                                <div class="col-sm-4 col-xs-8"><p>Quantity</p></div>
                                <div class="col-sm-8"><input class="form-control" type="number" id="quantity" name="quantity" value="<%= sp.getQuantity()%>"/></div>                       
                            </div>
                            <div class="row"><br/>
                                <div class="col-sm-4 col-xs-8"><p>Indication</p></div>
                                <div class="col-sm-8"><input class="form-control" id="indicaction" type="text" name="indication" value="<%= sp.getIndication()%>"/></div>                        
                            </div>
                            <div class="row"><br/>
                                <div class="col-sm-4 col-xs-8"><p>Contraindication</p></div>
                                <div class="col-sm-8"><input class="form-control" id="contraindication" type="text" name="contraindication" value="<%= sp.getContraindication()%>"/></div>                        
                            </div>
                            <div class="row"><br/>
                                <div class="col-sm-4 col-xs-8"><p>Using</p></div>
                                <div class="col-sm-8"><input class="form-control" id="using" type="text" name="using" value="<%= sp.getUsing()%>"/></div>                        
                            </div> 
                            <div class="row"><br/>
                                <div class="col-sm-4 col-xs-8"><p>Element</p></div>
                                <div class="col-sm-8"><input class="form-control" id="element" type="text" name="element" value="<%= sp.getElement()%>"/></div>                        
                            </div>
                            <div class="row"><br/>
                                <div class="col-sm-4 col-xs-8"><p>Made in</p></div>
                                <div class="col-sm-8"><input class="form-control" id="madein" type="text" name="madein"value="<%= sp.getMadeIn()%>"/></div>                        
                            </div>
                            <div class="col-sm-4 col-xs-8"></div>
                            <div class="col-sm-4 w-100 p-4 d-flex flex-row justify-content-center" id="lbtn">
                                <a href='/ProductController' style="margin: 0 0 0 30%" class="btn btn-secondary ms-5" id="btl">Back to List</a>  
                                <input class="btn btn-primary ms-3" id="submit" type="submit" name="btnAddNew" value="Add New"/>
                            </div>
                        </div>





                        <!--                        <div class="col-sm-4 "></div>
                                                <div class="col-sm-8" id="lbtn">
                                                    <input class="btn btn-primary" id="submit" type="submit" name="btnUpdate" value="Update"/>
                                                    <a href='/ProductController' class="btn btn-secondary" id="btl">Back to List</a>  
                                                </div>-->

                    </form>

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
        <script >
                        function validateForm() {

                            var name = document.getElementById("name2").value;
                            var des = document.getElementById("des2").value;
                            var price = document.getElementById("price2").value;
                            var quantity = document.getElementById("quantity2").value;
                            var size = document.getElementById("size2").value;
                            var oldImage = document.getElementById("oldImage").value;
                            var newImage = document.getElementById("newImage").value;
                            var catid = document.getElementById("catid").value;
                            var brandid = document.getElementById("brandid").value;
                            var color = document.getElementById("color2").value;
                            if (name === "" || des === "" || price === "" || quantity === "" || size === "" || catid === "" || brandid === "" || color === "") {
                                alert("Please enter full data!");
                                return false;
                            }

                            if (!newImage && oldImage === "") {
                                alert("Please, choose a picture!");
                                return false;
                            }

                            // Code ki?m tra giá tr? c?a các tr??ng s?
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