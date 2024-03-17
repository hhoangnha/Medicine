<%@page import="Model.ManufacturerModel"%>
<%@page import="Model.BrandModel"%>
<%@page import="Model.CategorieModel"%>
<%@page import="java.util.List"%>
<%@page import="Daos.BrandDAO"%>
<%@page import="Daos.cate_ad"%> 
<%@page import="Daos.ManufacturerDAO"%>
<%@page import="Daos.ProductDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="UTF-8"> 
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">

        <title>Product</title>

        <!-- Additional CSS Files -->
        <link rel="stylesheet" type="text/css" href="/resources/UserAssets/css/bootstrap.min.css">

        <link rel="stylesheet" type="text/css" href="/resources/UserAssets/css/font-awesome.css">

        <link rel="stylesheet" href="/resources/UserAssets/css/templatemo-hexashop.css">

        <link rel="stylesheet" href="/resources/UserAssets/css/owl-carousel.css">

        <link rel="stylesheet" href="/resources/UserAssets/css/lightbox.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <style>

        </style>
    </head>

    <body>



        <jsp:include page="user-header.jsp" />
        <!-- ***** Main Banner Area Start ***** -->
        <div class="page-heading" id="top"> 
        </div>
        <!-- ***** Main Banner Area End ***** -->


        <!-- ***** Products Area Starts ***** -->
        <section class="section" id="products">
            <%

                String pageParam = request.getParameter("page");
                int pageQ = 0; // Giá trị mặc định
                String queryParam = request.getParameter("q");
                String qQ = "";

                if (pageParam != null && !pageParam.isEmpty()) {
                    // Nếu tham số page được truyền, sử dụng giá trị được truyền
                    pageQ = Integer.parseInt(pageParam);
                }
                if (queryParam != null && !queryParam.isEmpty()) {
                    // Nếu tham số page được truyền, sử dụng giá trị được truyền
                    qQ = queryParam;
                }%>



            <%
                ManufacturerDAO mDao = new ManufacturerDAO();
                cate_ad cDao = new cate_ad();
                BrandDAO bDao = new BrandDAO();
            %>

            <div class="container">


                <div class="row">
                    <div class="col-md-3">
                        <form method="get">
                            <div class="row container">
                                <div >
                                    <div class="form-group">
                                        <label for="username">Key to search</label>
                                        <input name="q" type="text" value="${param.q}" />
                                    </div>

                                </div><div >
                                    <div class="form-group ml-2">
                                        <label for="username">&nbsp;</label>
                                        <input  type="submit" value="Search" />
                                    </div>

                                </div>

                            </div>

                            <h5>Category</h5>
                            <div class="form-check">
                                <%
                                    List<CategorieModel> cateData = cDao.getListCateModel();

                                    for (CategorieModel citem : cateData) {%>

                                <input class="form-check-input" name="cate[]" type="checkbox" value="<%= citem.getCateID()%>" id="catecheck<%= citem.getCateID()%>">
                                <label class="form-check-label" for="catecheck<%= citem.getCateID()%>">
                                    <%= citem.getCateName()%>
                                </label>
                                <br/>
                                <%}
                                %>

                            </div>

                            <hr/>
                            <h5>Brand</h5>
                            <div class="form-check">
                                <%
                                    List<BrandModel> brandData = bDao.getAll();

                                    for (BrandModel bitem : brandData) {%>

                                <input class="form-check-input" name="brand[]" type="checkbox" value="<%= bitem.getBrandID()%>" id="manucheck<%= bitem.getBrandID()%>">
                                <label class="form-check-label" for="manucheck<%= bitem.getBrandID()%>">
                                    <%= bitem.getBrandName()%>
                                </label>
                                <br/>
                                <%}
                                %>

                            </div>
                            <hr/>
                            <h5>Manufacturer</h5>
                            <div class="form-check">
                                <%
                                    List<ManufacturerModel> manuData = mDao.getAllManufacturer();

                                    for (ManufacturerModel mitem : manuData) {%>

                                <input class="form-check-input" name="manu[]" type="checkbox" value="<%= mitem.getManuID()%>" id="manucheck<%= mitem.getManuID()%>">
                                <label class="form-check-label" for="manucheck<%= mitem.getManuID()%>">
                                    <%= mitem.getManuName()%>
                                </label>
                                <br/>
                                <%}
                                %>

                            </div>
                            <hr/>
                        </form>
                    </div>
                    <div class="col-md-9">
                        <div class="row mt-4">
                            <%
                                ProductDAO pDAO = new ProductDAO();
                                ResultSet rs = pDAO.searchProduct(qQ, 1);
                                if (pageQ > 0) {
                                    rs = pDAO.searchProduct(qQ, pageQ);
                                }

                                while (rs.next()) {
                            %>
                            <div class="col-lg-4">
                                <div class="item">
                                    <div class="thumb">
                                        <div class="hover-content">
                                            <ul>
                                                <li><a href='/UserHomeController/Detail/<%=rs.getInt("ProID")%>'><i class="fa fa-eye"></i></a></li>
                                                <!--<li><a onclick='addToCart(<%=rs.getInt("ProID")%>)'><i class="fa fa-shopping-cart"></i></a></li>-->
                                            </ul>
                                        </div>
                                        <img style="max-height:300px" src='/resources/images/<%=rs.getString("ProImage")%>' alt="">
                                    </div>
                                    <div class="down-content">
                                        <h5><%= rs.getString("ProName")%></h5>
                                        <div class="row d-flex justify-content-between">
                                            <div class="col-md-6"><small class="text-danger"><%= rs.getString("Origin")%></small></div>
                                            <div class="col-md-6"><p > <%= rs.getString("BrandName")%></span></div>
                                        </div>

                                        <small>Only <%= rs.getInt("Quantity")%> products</small>
                                    </div>
                                </div>
                            </div>
                            <% }%>




                            <div class="col-lg-12">
                                <div class="pagination">
                                    <ul>
                                        <% for (int pageNumber = 0; pageNumber <= 5; pageNumber++) { %>
                                        <%
                                            String isActive = "";
                                            if (pageNumber == pageQ) {
                                                isActive = "active";
                                            }

                                        %>
                                        <li class="<%= isActive%>">
                                            <a href="/UserHomeController/Products?q=${param.q}&page=<%= pageNumber%>"><%= pageNumber + 1%></a>
                                        </li>
                                        <% }%>

                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </section>
        <!-- ***** Products Area Ends ***** -->

        <!-- ***** Footer Start ***** -->
        <jsp:include page="user-footer.jsp" />


        <!-- jQuery -->
        <script src="/resources/UserAssets/js/jquery-2.1.0.min.js"></script>

        <!-- Bootstrap -->
        <script src="/resources/UserAssets/js/popper.js"></script>
        <script src="/resources/UserAssets/js/bootstrap.min.js"></script>

        <!-- Plugins -->
        <script src="/resources/UserAssets/js/owl-carousel.js"></script>
        <script src="/resources/UserAssets/js/accordions.js"></script>
        <script src="/resources/UserAssets/js/datepicker.js"></script>
        <script src="/resources/UserAssets/js/scrollreveal.min.js"></script>
        <script src="/resources/UserAssets/js/waypoints.min.js"></script>
        <script src="/resources/UserAssets/js/jquery.counterup.min.js"></script>
        <script src="/resources/UserAssets/js/imgfix.min.js"></script> 
        <script src="/resources/UserAssets/js/slick.js"></script> 
        <script src="/resources/UserAssets/js/lightbox.js"></script> 
        <script src="/resources/UserAssets/js/isotope.js"></script> 

        <!-- Global Init -->
        <script src="/resources/UserAssets/js/custom.js"></script>


        <script>
            function addToCart(id) {
                let quan = 1;
                $.ajax({
                    url: '/UserCartController/AddToCart/' + id + "?quan=" + quan,
                    method: 'GET',
                    success: function (response) {
                        if (response.success) {
                            Swal.fire({
                                title: 'Thêm vào giỏ hàng thành công!',
                                icon: 'success',
                                showCancelButton: false,
                                confirmButtonText: 'Đồng ý'
                            }).then((result) => {
                                if (result.isConfirmed) {
                                    location.reload();
                                }
                            });
                        }
                    }
                });
            }


        </script>

    </body>

</html>
