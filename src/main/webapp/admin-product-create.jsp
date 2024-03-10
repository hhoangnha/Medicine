<%@page import="Model.BrandModel"%>
<%@page import="Model.UnitModel"%>
<%@page import="Daos.UnitDAO"%>
<%@page import="Model.UnitProductModel"%>
<%@page import="Daos.UnitProductDAO"%>
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

        <title>Add new Product</title>
        <meta content="" name="description">
        <meta content="" name="keywords"> 

        <!-- Favicons -->
        <link href="/resources/AdminAssets/img/favicon.png" rel="icon">
        <link href="/resources/AdminAssets/img/apple-touch-icon.png" rel="apple-touch-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.gstatic.com" rel="preconnect">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet" />


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
                    <h5 class="card-title">Add new Product</h5>
                    <form class="d-flex flex-row justify-content-center" method="post" onsubmit="return validateForm()" enctype="multipart/form-data" action="/ProductController" >
                        <br>
                        <div class="w-50 p-3">
                            <div class="row">     
                                <br>
                                <div class="col-sm-4 col-xs-8"> <p>Product Code</p></div>
                                <div class="col-sm-8"><input class="form-control" type="text" id="code" name="code"  /></div>

                            </div>
                            <div class="row">     
                                <br>
                                <div class="col-sm-4 col-xs-8"> <p>Product Name</p></div>
                                <div class="col-sm-8"><input class="form-control" type="text" id="name" name="name"  /></div>

                            </div>
                            <div class="row">
                                <br>
                                <div class="col-sm-4 col-xs-8"><p>Quantity</p></div>
                                <div class="col-sm-8"><input class="form-control" type="number" id="quantity" name="quantity" /></div>                       
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
                                            List<BrandModel> r = cDAO2.getAll();
                                            for (BrandModel item : r) {%>
                                        <option value="<%= item.getBrandID()%>"><%= item.getBrandName()%></option>

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
                            <div class="row">
                                <br>

                                <div class="col-sm-4 col-xs-8"><p>Units</p></div>
                                <div class="col-sm-8">
                                    <select name="unitid" id="unitid" multiple class="form-control">
                                        <option>Select Unit</option>
                                        <%
                                            UnitDAO unitDAO = new UnitDAO();
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
                                    <textarea class="form-control" type="text" id="des" name="des"></textarea>
                                </div>                        
                            </div>

                            <div class="row"><br/>
                                <div class="col-sm-4 col-xs-8"><p>Indication</p></div>
                                <div class="col-sm-8">
                                    <textarea class="form-control" id="indicaction" type="text" name="indication"></textarea>
                                </div>                        
                            </div>
                            <div class="row"><br/>
                                <div class="col-sm-4 col-xs-8"><p>Contraindication</p></div>
                                <div class="col-sm-8">
                                    <textarea class="form-control" id="contraindication" type="text" name="contraindication"></textarea>
                                </div>                        
                            </div>
                            <div class="row"><br/>
                                <div class="col-sm-4 col-xs-8"><p>Using</p></div>
                                <div class="col-sm-8">
                                    <textarea  class="form-control" id="using" type="text" name="using"></textarea></div>                        
                            </div> 
                            <div class="row"><br/>
                                <div class="col-sm-4 col-xs-8"><p>Element</p></div>
                                <div class="col-sm-8"><input class="form-control" id="element" type="text" name="element"/></div>                        
                            </div>
                            <div class="row"><br/>
                                <div class="col-sm-4 col-xs-8"><p>Made in</p></div>
                                <div class="col-sm-8"><input class="form-control" id="madein" type="text" name="madein"/></div>                        
                            </div>
                            <div class="row">
                                <div class="col-sm-4 col-xs-8"></div>
                                <div class="col-sm-8 d-flex flex-row justify-content-center" id="lbtn">
                                    <a href='/ProductController' class="btn btn-secondary mx-3" id="btl">Back to List</a>  
                                    <input class="btn btn-primary me-3" id="submit" type="submit" name="btnAddNew" value="Add New"/>
                                </div>
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
                            var selectedUnits = $('#unitid').val(); // Mảng các đơn vị đã chọn
                            var prices = $('.unitPrice').map(function () {
                                return {unitID: $(this).data('unitid'), price: $(this).val()};
                            }).get(); // Mảng các đơn vị và giá tương ứng


                            var name = document.getElementById("name").value;
                            var des = document.getElementById("des").value;
                            var quantity = document.getElementById("quantity").value;
                            var manuid = document.getElementById("manuid").value;
                            var image = document.getElementById("image").value;
                            var catid = document.getElementById("catid").value;
                            var brandid = document.getElementById("brandid").value;
                            if (name == "" || des == "" || quantity == "" || manuid == "" || catid == "" || brandid == "") {
                                alert("Please enter full data!");
                                return false;
                            }


                            if (!image) {
                                alert("Please, choose a picture!");
                                return false;
                            }

                            // Code ki?m tra gi� tr? c?a c�c tr??ng s?
//                            if (price <= 0 || quantity <= 0) {
//
//                                alert("Accept only positive number greater than 0!");
//                                return false;
//                            }
//                            if (isNaN(price) || isNaN(quantity) {
//                                alert("Input must be number.");
//                                return false;
//                            }

                        }


        </script>
        <!-- Template Main JS File -->
        <script src="/resources/AdminAssets/js/main.js"></script>

    </body>

</html>