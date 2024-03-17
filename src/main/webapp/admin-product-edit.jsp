<%@page import="Daos.UnitProductDAO"%>
<%@page import="Model.UnitProductModel"%>
<%@page import="Model.BrandModel"%>
<%@page import="Model.UnitModel"%>
<%@page import="Daos.UnitDAO"%>
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

        <title>Product</title>
        <meta content="" name="description">
        <meta content="" name="keywords"> 
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet" />

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
                            <input class="form-control" type="text" hidden id="id" name="id" value="<%=sp.getProID()%>"  />
                            <div class="row">     
                                <br>
                                <div class="col-sm-4 col-xs-8"> <p>Product Code</p></div>
                                <div class="col-sm-8"><input class="form-control" type="text" disabled id="code" name="code" value="<%=sp.getProCode()%>"  /></div>

                            </div>
                            <div class="row">     
                                <br>
                                <div class="col-sm-4 col-xs-8"> <p>Product Name</p></div>
                                <div class="col-sm-8"><input class="form-control" type="text" id="name" name="name"  value="<%=sp.getProName()%>" /></div>

                            </div>
                            <div class="row">
                                <br>
                                <div class="col-sm-4 col-xs-8"><p>Quantity</p></div>
                                <div class="col-sm-8"><input class="form-control" type="number" id="quantity" name="quantity" value="<%=sp.getQuantity()%>" /></div>                       
                            </div>
                            <div class="row">
                                <br>
                                <div class="col-sm-4 col-xs-8"><p>Manufacture Date</p></div>
                                <div class="col-sm-8"><input class="form-control" type="date" id="manufacturedate" name="manufacturedate" value="<%=sp.getManufactureDate()%>" /></div>                       
                            </div>
                            <div class="row">
                                <br>
                                <div class="col-sm-4 col-xs-8"><p>Expiration Date</p></div>
                                <div class="col-sm-8"><input class="form-control" type="date" id="expirationdate" name="expirationdate" value="<%=sp.getExpirationDate()%>" /></div>                       
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
                                        <option 
                                            <%if (sp.getCateID() == rs.getInt("CateID")) {  %>
                                            selected
                                            <%}
                                            %>
                                            value="<%= rs.getInt("CateID")%>"><%= rs.getString("CateName")%></option>

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
                                            List<BrandModel> r = cDAO2.getAll();
                                            for (BrandModel item : r) {%>
                                        <option 
                                            <%if (sp.getBrandID() == item.getBrandID()) {  %>
                                            selected
                                            <%}%>
                                            value="<%= item.getBrandID()%>"><%= item.getBrandName()%></option>
                                        <% }

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
                                        <%                                            ManufacturerDAO manuDAO = new ManufacturerDAO();
                                            List<ManufacturerModel> rsManu = manuDAO.getAllManufacturer();
                                            for (int i = 0; i < rsManu.size(); i++) {

                                        %>
                                        <option
                                            <%if (sp.getManuID() == rsManu.get(i).getManuID()) {  %>
                                            selected
                                            <%}%>
                                            value="<%= rsManu.get(i).getManuID()%>"><%= rsManu.get(i).getManuName()%></option>

                                        <%
                                            }
                                        %>
                                    </select>
                                </div>                       
                            </div>
                            <div class="row"><br/>
                                <div class="col-sm-4 col-xs-8"><p>Image</p></div>
                                <div class="col-sm-8"><input class="form-control" type="file" id="image" name="image" size="50"  /></div>   
                                <img style="max-height:200px" src="/resources/images/<%= sp.getProImage()%>" alt="">
                                <input name="currentImage" value="<%= sp.getProImage()%>"  />
                            </div>
                            <div class="row">
                                <br>

                                <div class="col-sm-4 col-xs-8"><p>Units</p></div>
                                <small>To change the currency, please delete the old data and select a new unit</small>
                                <div class="alert alert-secondary mt-3" role="alert">

                                    <%
                                        UnitDAO unitDAO = new UnitDAO(); 
                                        UnitProductDAO uPD = new UnitProductDAO();
                                        List<UnitProductModel> uProData = uPD.getProductUnits(sp.getProID());

                                        for (UnitProductModel uPitem : uProData) { 
                                        UnitModel unitItem = unitDAO.getUnit(uPitem.getUnitID());
                                    %>
                                        
                                    <div class="d-flex justify-content-between">
                                        <span><%= unitItem.getUnitName() %>: <span class="text-danger"><%= uPitem.getPrice() %></span> </span>
                                        <button type="button" class="btn " >
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <%    }

                                    %>





                                </div>
                                <div class="col-sm-8 mb-2">
                                    <select name="unitid" id="unitid" multiple class="form-control">
                                        <option>Select Unit</option>
                                        <%                                            
                                            List<UnitModel> rsUnit = unitDAO.getAllUnit();
                                            for (UnitModel item : rsUnit) {
                                        %>
                                        <option value="<%= item.getUnitID()%>"><%= item.getUnitName()%></option>

                                        <%}
                                        %>
                                    </select>
                                </div>   



                            </div>
                            <div id="unitDetails"></div>
                        </div>
                        <div class="w-50 p-3">

                            <div class="row"><br/>
                                <div class="col-sm-4 col-xs-8"><p>Description</p></div>
                                <div class="col-sm-8">
                                    <textarea class="form-control" type="text" id="des" name="des"><%= sp.getProDescription()%></textarea>
                                </div>                        
                            </div>

                            <div class="row"><br/>
                                <div class="col-sm-4 col-xs-8"><p>Indication</p></div>
                                <div class="col-sm-8">
                                    <textarea class="form-control" id="indicaction" type="text" name="indication"><%= sp.getIndication()%></textarea>
                                </div>                        
                            </div>
                            <div class="row"><br/>
                                <div class="col-sm-4 col-xs-8"><p>Contraindication</p></div>
                                <div class="col-sm-8">
                                    <textarea class="form-control" id="contraindication" type="text" name="contraindication"><%= sp.getContraindication()%></textarea>
                                </div>                        
                            </div>
                            <div class="row"><br/>
                                <div class="col-sm-4 col-xs-8"><p>Using</p></div>
                                <div class="col-sm-8">
                                    <textarea  class="form-control" id="using" type="text" name="using"><%= sp.getUsing()%></textarea></div>                        
                            </div> 
                            <div class="row"><br/>
                                <div class="col-sm-4 col-xs-8"><p>Element</p></div>
                                <div class="col-sm-8"><input class="form-control" id="element" type="text" value="<%= sp.getElement()%>" name="element"/></div>                        
                            </div>
                            <div class="row"><br/>
                                <div class="col-sm-4 col-xs-8"><p>Made in</p></div>
                                <div class="col-sm-8"><input class="form-control" id="madein" type="text" <%= sp.getMadeIn()%> name="madein"/></div>                        
                            </div>
                            <div class="row">
                                <div class="col-sm-4 col-xs-8"></div>
                                <div class="col-sm-8 d-flex flex-row justify-content-center" id="lbtn">
                                    <a href='/ProductController' class="btn btn-secondary mx-3" id="btl">Back to List</a>  
                                    <input class="btn btn-primary me-3" id="submit" type="submit" name="btnUpdate" value="Update"/>
                                </div>
                            </div>
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
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
        <script >
                        $(document).ready(function () {
                            $('#unitid').select2();
                            $('#unitid').change(function () {
                                $('#unitDetails').empty(); // Xóa bỏ các input hiện tại trước khi thêm mới

                                $(this).find('option:selected').each(function () {
                                    var selectedUnitID = $(this).val();
                                    var selectedUnitName = $(this).text();
                                    var unitInputHTML = '<div class="form-group">' +
                                            '<label for="unitPrice">Price for ' + selectedUnitName + '</label>' +
                                            '<input name="prices[]" type="number" class="form-control unitPrice" data-unitid="' + selectedUnitID + '" placeholder="Enter price">' +
                                            '</div>';
                                    $('#unitDetails').append(unitInputHTML);
                                });
                            });

                            // Xóa input khi bỏ chọn một đơn vị
                            $('#unitid').on('change', function () {
                                $(this).find('option:not(:selected)').each(function () {
                                    var unselectedUnitID = $(this).val();
                                    $('.unitPrice[data-unitid="' + unselectedUnitID + '"]').parent().remove();
                                });
                            });
                        });
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