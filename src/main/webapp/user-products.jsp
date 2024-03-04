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
            .form {
                z-index: 15;
                position: relative;
                background: #FFFFFF;
                width: 600px;
                border-radius: 4px;
                box-shadow: 0 0 30px rgba(0, 0, 0, 0.1);
                box-sizing: border-box;
                margin: 100px auto 10px;
                overflow: hidden;
            }
            .form-toggle {
                z-index: 10;
                position: absolute;
                top: 60px;
                right: 60px;
                background: #FFFFFF;
                width: 60px;
                height: 60px;
                border-radius: 100%;
                -webkit-transform-origin: center;
                transform-origin: center;
                -webkit-transform: translate(0, -25%) scale(0);
                transform: translate(0, -25%) scale(0);
                opacity: 0;
                cursor: pointer;
                -webkit-transition: all 0.3s ease;
                transition: all 0.3s ease;
            }
            .form-toggle:before, .form-toggle:after {
                content: '';
                display: block;
                position: absolute;
                top: 50%;
                left: 50%;
                width: 30px;
                height: 4px;
                background: #4285F4;
                -webkit-transform: translate(-50%, -50%);
                transform: translate(-50%, -50%);
            }
            .form-toggle:before {
                -webkit-transform: translate(-50%, -50%) rotate(45deg);
                transform: translate(-50%, -50%) rotate(45deg);
            }
            .form-toggle:after {
                -webkit-transform: translate(-50%, -50%) rotate(-45deg);
                transform: translate(-50%, -50%) rotate(-45deg);
            }
            .form-toggle.visible {
                -webkit-transform: translate(0, -25%) scale(1);
                transform: translate(0, -25%) scale(1);
                opacity: 1;
            }
            .form-group {
                display: -webkit-box;
                display: flex;
                flex-wrap: wrap;
                -webkit-box-pack: justify;
                justify-content: space-between;
                margin: 0 0 20px;
            }
            .form-group:last-child {
                margin: 0;
            }
            .form-group label {
                display: block;
                margin: 0 0 10px;
                color: rgba(0, 0, 0, 0.6);
                font-size: 12px;
                font-weight: 500;
                line-height: 1;
                text-transform: uppercase;
                letter-spacing: .2em;
            }
            .two .form-group label {
                color: #FFFFFF;
            }
            .form-group input {
                outline: none;
                display: block;
                background: rgba(0, 0, 0, 0.1);
                width: 100%;
                border: 0;
                border-radius: 4px;
                box-sizing: border-box;
                padding: 12px 20px;
                color: rgba(0, 0, 0, 0.6);
                font-family: inherit;
                font-size: inherit;
                font-weight: 500;
                line-height: inherit;
                -webkit-transition: 0.3s ease;
                transition: 0.3s ease;
            }
            .form-group input:focus {
                color: rgba(0, 0, 0, 0.8);
            }
            .two .form-group input {
                color: #FFFFFF;
            }
            .two .form-group input:focus {
                color: #FFFFFF;
            }
            .form-group button {
                outline: none;
                background: #4285F4;
                width: 100%;
                border: 0;
                border-radius: 4px;
                padding: 12px 20px;
                color: #FFFFFF;
                font-family: inherit;
                font-size: inherit;
                font-weight: 500;
                line-height: inherit;
                text-transform: uppercase;
                cursor: pointer;
            }
            .two .form-group button {
                background: #FFFFFF;
                color: #4285F4;
            }
            .form-group .form-remember {
                font-size: 12px;
                font-weight: 400;
                letter-spacing: 0;
                text-transform: none;
            }
            .form-group .form-remember input[type='checkbox'] {
                display: inline-block;
                width: auto;
                margin: 0 10px 0 0;
            }
            .form-group .form-recovery {
                color: #4285F4;
                font-size: 12px;
                text-decoration: none;
            }
            .form-panel {
                padding: 60px calc(5% + 60px) 60px 60px;
                box-sizing: border-box;
            }
            .form-panel.one:before {
                content: '';
                display: block;
                opacity: 0;
                visibility: hidden;
                -webkit-transition: 0.3s ease;
                transition: 0.3s ease;
            }
            .form-panel.one.hidden:before {
                display: block;
                opacity: 1;
                visibility: visible;
            }
            .form-panel.two {
                z-index: 5;
                position: absolute;
                top: 0;
                left: 95%;
                background: #4285F4;
                width: 100%;
                min-height: 100%;
                padding: 60px calc(10% + 60px) 60px 60px;
                -webkit-transition: 0.3s ease;
                transition: 0.3s ease;
                cursor: pointer;
            }
            .form-panel.two:before, .form-panel.two:after {
                content: '';
                display: block;
                position: absolute;
                top: 60px;
                left: 1.5%;
                background: rgba(255, 255, 255, 0.2);
                height: 30px;
                width: 2px;
                -webkit-transition: 0.3s ease;
                transition: 0.3s ease;
            }
            .form-panel.two:after {
                left: 3%;
            }
            .form-panel.two:hover {
                left: 93%;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            }
            .form-panel.two:hover:before, .form-panel.two:hover:after {
                opacity: 0;
            }
            .form-panel.two.active {
                left: 10%;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
                cursor: default;
            }
            .form-panel.two.active:before, .form-panel.two.active:after {
                opacity: 0;
            }
            .form-header {
                margin: 0 0 40px;
            }
            .form-header h1 {
                padding: 4px 0;
                color: #4285F4;
                font-size: 24px;
                font-weight: 700;
                text-transform: uppercase;
            }
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
            <div class="container">
                <form method="get">
                    <div class="row container">
                        <div >
                            <div class="form-group">
                                <label for="username">Từ khoá</label>
                                <input name="q" type="text" value="${param.q}" />
                            </div>

                        </div><div >
                            <div class="form-group ml-2">
                                <label for="username">&nbsp;</label>
                                <input  type="submit" value="Search" />
                            </div>

                        </div>

                    </div>
                </form>
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
                                        <li><a onclick='addToCart(<%=rs.getInt("ProID")%>)'><i class="fa fa-shopping-cart"></i></a></li>
                                    </ul>
                                </div>
                                <img src='/resources/images/<%=rs.getString("ProImage")%>' alt="">
                            </div>
                            <div class="down-content">
                                <h4><%= rs.getString("ProName")%></h4>
                                <div class="row d-flex justify-content-between">
                                    <div class="col-md-6"><span class="text-danger">123</span></div>
                                    <div class="col-md-6"><p > <%= rs.getString("BrandName")%></span></div>
                                </div>

                                <small>Chỉ còn <%= rs.getInt("Quantity")%> sản phẩm</small>
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
                                    <a href="/UserHomeController/Products?q=${param.q}&page=<%= pageNumber%>"><%= pageNumber+1%></a>
                                </li>
                                <% }%>

                            </ul>
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
