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
                    <form   method="post" enctype="multipart/form-data" onsubmit="return validateForm();" action="/ProductController">
                        <br>
                        <div class="row">
                            <div class="col-sm-2"><p>Product ID</p></div>
                            <div class="col-sm-8"><input class="form-control" type="number" id="id2" name="id" value="<%= sp.getProID()%>" readonly="" /></div>
                        </div>
                        <br>
                        <div class="row">                       
                            <div class="col-sm-2"> <p>Product Name</p></div>
                            <div class="col-sm-8"><input class="form-control" type="text" id="name2" name="name" value="<%= sp.getProName()%>" /></div>

                        </div>
                        <div class="row">
                            <br>
                            <div class="col-sm-2"><p>Description</p></div>
                            <div class="col-sm-8"><input class="form-control" type="text" id="des2" name="des" value="<%= sp.getDescription()%>" /></div>

                        </div>
                        <div class="row">
                            <br>
                            <div class="col-sm-2"><p>Price</p></div>
                            <div class="col-sm-8"><input class="form-control" type="number" id="price2" name="price" value="<%= sp.getPrice()%>" /></div>                       
                        </div>
                        <div class="row">
                            <br>
                            <div class="col-sm-2"><p>Quantity</p></div>
                            <div class="col-sm-8"><input class="form-control" type="number" id="quantity2" name="quantity" value="<%= sp.getQuantity()%>" /></div>                       
                        </div>
                        <div class="row">
                            <br>
                            <div class="col-sm-2"><p>Size</p></div>
                            <div class="col-sm-8"><input class="form-control" type="number" id="size2" name="size" value="<%= sp.getSize()%>"/></div>                       
                        </div>
                        <!--                        <div class="row"><br/>
                                                    <div class="col-sm-2"><p>Image</p></div>
                                                    <div class="col-sm-8"><input class="form-control" type="file" name="image" size="50" value="" required=""/></div>                        
                                                </div>-->

                        <div class="row"><br/>
                            <div class="col-sm-2"><p>Old Image:</p></div>
                            <div class="col-sm-4">
                                <img src="/resources/images/<%= sp.getImage()%>" alt="Product Image" width="100px" height="100px">
                                <input id="oldImage" name="oldImage" value="<%= sp.getImage()%>" hidden="">

                            </div>
                            <br>
                            <div class="col-sm-2"><p>Image Options:</p></div>
                            <div class="col-sm-4">
                                <!--                                <input  id="updateImage" name="updateImage" >
                                                                <label for="updateImage">Cập nhật ảnh</label>
                                                                <br>-->
                                <input type="file"  id="newImage" name="newImage" size="50" >
                            </div>

                        </div>
                        <div class="row"><br/>
                            <div class="col-sm-2"><p>Categories ID</p></div>
                            <div class="col-sm-8">
                                <select name="catid" id="catid">
                                    <%
                                        cate_ad cDAO = new cate_ad();
                                        ResultSet rs = cDAO.getAll(1);

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
                            <div class="col-sm-2"><p>Brand ID</p></div>
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
                            <div class="col-sm-2"><p>Color</p></div>
                            <div class="col-sm-8"><input class="form-control" type="text" id="color2" name="color" value="<%= sp.getColor()%>"/></div>                        
                        </div>

                        <div class="col-sm-2"></div>
                        <div class="col-sm-8" id="lbtn">
                            <input class="btn btn-primary" id="submit" type="submit" name="btnUpdate" value="Update"/>
                            <a href='/ProductController' class="btn btn-secondary" id="btl">Back to List</a>  
                        </div>

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

                            if (!newImage&&oldImage==="") {
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