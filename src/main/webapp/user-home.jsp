<%-- 
    Document   : user-home
    Created on : Oct 28, 2023, 8:24:21 PM
    Author     : Nguyen Hoang Nha - CE170092
--%>

<%@page import="Daos.cate_ad"%>
<%@page import="Model.CartItem"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Daos.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">

        <title>Group 3 - Medicine</title>
        <link rel="stylesheet" type="text/css" href="/resources/UserAssets/css/bootstrap.min.css">

        <link rel="stylesheet" type="text/css" href="/resources/UserAssets/css/font-awesome.css">

        <link rel="stylesheet" href="/resources/UserAssets/css/templatemo-hexashop.css">

        <link rel="stylesheet" href="/resources/UserAssets/css/owl-carousel.css">

        <link rel="stylesheet" href="/resources/UserAssets/css/lightbox.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

        <script src="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/alertify.min.js"></script>

        <!-- CSS -->
        <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/alertify.min.css"/>
        <!-- Default theme -->
        <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/default.min.css"/>
        <!-- Semantic UI theme -->
        <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/semantic.min.css"/>
        <!--/resources/jq.js-->
    </head>
    <body>

        <%
            ProductDAO pDAO = new ProductDAO();
            List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
            int totalItems = 0;

            if (cart != null) {
                for (CartItem item : cart) {
//                    totalItems += item.getQuantity();
                }
            }
        %>

        <!-- ***** Preloader Start ***** -->
        <div id="preloader">
            <div class="jumper">
                <div></div>
                <div></div>
                <div></div>
            </div>
        </div>  
        <!-- ***** Preloader End ***** -->


        <!-- ***** Header Area Start ***** -->
        <jsp:include page="user-header.jsp" />
        <!-- ***** Header Area End ***** -->

        <!-- ***** Main Banner Area Start ***** -->
        <div class="main-banner" id="top">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="left-content">
                            <div class="thumb">
                                <div class="inner-content">
                                    <!--<h4>Shoe show</h4>-->
                                    <!--<span>Chào mừng bạn đến với trang web</span>-->
                                    <div class="main-border-button">
                                        <a href="#">Explore now!</a>
                                    </div>
                                </div>
                                <img src="https://img.freepik.com/premium-vector/vector-pill-poster-medical-healthcare_8071-22128.jpg" alt="">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="right-content">
                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="right-first-image">
                                        <div class="thumb">

                                            <div class="hover-content">
                                                <div class="inner">
                                                    <h4>Medicine</h4>
                                                    <!--<p>Lorem ipsum dolor sit amet, conservisii ctetur adipiscing elit incid.</p>-->
                                                    <div class="main-border-button">
                                                        <a href="#">Explore now</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <img src="https://c8.alamy.com/comp/JM10B2/colorful-poster-in-white-background-with-medicine-bottle-in-closeup-JM10B2.jpg">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="right-first-image">
                                        <div class="thumb">

                                            <div class="hover-content">
                                                <div class="inner">
                                                    <h4>Vitamin</h4>
                                                    <!--<p>Lorem ipsum dolor sit amet, conservisii ctetur adipiscing elit incid.</p>-->
                                                    <div class="main-border-button">
                                                        <a href="#">Explore now</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <img src="https://static.vecteezy.com/system/resources/previews/006/410/200/original/vitamin-a-banner-template-hand-drawn-style-with-elements-of-products-sources-of-vitamin-a-illustration-suitable-for-brochure-flyer-or-poster-design-vector.jpg">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="right-first-image">
                                        <div class="thumb">

                                            <div class="hover-content">
                                                <div class="inner">
                                                    <h4>Phụ kiện</h4>
                                                    <!--<p>Lorem ipsum dolor sit amet, conservisii ctetur adipiscing elit incid.</p>-->
                                                    <div class="main-border-button">
                                                        <a href="#">Khám phá ngay</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <img src="https://venusglobal.com.vn/wp-content/uploads/2022/06/z2173898985839_60fa23433e2673c34d6c158a9241bb84-1.jpg">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="right-first-image">
                                        <div class="thumb">

                                            <div class="hover-content">
                                                <div class="inner">
                                                    <h4>Other</h4>
                                                    <!--<p>Lorem ipsum dolor sit amet, conservisii ctetur adipiscing elit incid.</p>-->
                                                    <div class="main-border-button">
                                                        <a href="#">Khám phá ngay</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <img src="https://avisure.vn/uploaded/Dangmangthai/bo-3-avisure-sat-canxi-dha(1).jpg">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ***** Main Banner Area End ***** -->

        <!-- ***** Men Area Starts ***** -->

        <%
            cate_ad cateP = new cate_ad();
            ResultSet cate = cateP.getAll();
            while (cate.next()) {
        %>
        <section class="section" id='men'>
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="section-heading">
                            <h2><%=cate.getString("CateName")%></h2>
                            <span><%=cate.getString("CateDescription")%></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="men-item-carousel">
                            <div class="owl-men-item owl-carousel">

                                <%  ResultSet rs = pDAO.getByCategory(cate.getInt("CateID"));
                                    while (rs.next()) {
                                %>

                                <div class="item" onclick="redirectToDetail(<%=rs.getInt("ProID")%>)">
                                    <div class="thumb">
                                        <div class="hover-content">
                                            <ul>
                                                <li><a href='/UserHomeController/Detail/<%=rs.getInt("ProID")%>'><i class="fa fa-eye"></i></a></li>
                                                <!--<li><a onclick='addToCart(<%=rs.getInt("ProID")%>)'><i class="fa fa-shopping-cart"></i></a></li>-->
                                            </ul>
                                        </div>
                                        <img style="max-height:300px" src="/resources/images/<%= rs.getString("ProImage")%>" alt="">
                                    </div>
                                    <div class="down-content">
                                        <h4><%= rs.getString("ProName")%></h4>
                                        <div class="row d-flex justify-content-between">
                                            <div class="col-md-6"><span class="text-danger"><%= rs.getString("Origin")%></span></div>
                                            <div class="col-md-6"><p > <%= rs.getString("BrandName")%></span></div>
                                        </div>

                                        <small> <%= rs.getInt("Quantity")%> product</small>
                                    </div>
                                </div>
                                <%}

                                %>



                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <%            }
        %>



        <!-- ***** Explore Area Starts ***** -->
        <!--        <section class="section" id="explore">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="left-content">
                                    <h2>Explore Our Products</h2>
                                    <span>You are allowed to use this HexaShop HTML CSS template. You can feel free to modify or edit this layout. You can convert this template as any kind of ecommerce CMS theme as you wish.</span>
                                    <div class="quote">
                                        <i class="fa fa-quote-left"></i><p>You are not allowed to redistribute this template ZIP file on any other website.</p>
                                    </div>
                                    <p>There are 5 pages included in this HexaShop Template and we are providing it to you for absolutely free of charge at our TemplateMo website. There are web development costs for us.</p>
                                    <p>If this template is beneficial for your website or business, please kindly <a rel="nofollow" href="https://paypal.me/templatemo" target="_blank">support us</a> a little via PayPal. Please also tell your friends about our great website. Thank you.</p>
                                    <div class="main-border-button">
                                        <a href="products.html">Discover More</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="right-content">
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="leather">
                                                <h4>Leather Bags</h4>
                                                <span>Latest Collection</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="first-image">
                                                <img src="/resources/UserAssets/images/explore-image-01.jpg" alt="">
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="second-image">
                                                <img src="/resources/UserAssets/images/explore-image-02.jpg" alt="">
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="types">
                                                <h4>Different Types</h4>
                                                <span>Over 304 Products</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>-->
        <!-- ***** Explore Area Ends ***** -->

        <!-- ***** Social Area Starts ***** -->
        <!--        <section class="section" id="social">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="section-heading">
                                    <h2>Social Media</h2>
                                    <span>Details to details is what makes Hexashop different from the other themes.</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="container">
                        <div class="row images">
                            <div class="col-2">
                                <div class="thumb">
                                    <div class="icon">
                                        <a href="http://instagram.com">
                                            <h6>Fashion</h6>
                                            <i class="fa fa-instagram"></i>
                                        </a>
                                    </div>
                                    <img src="/resources/UserAssets/images/instagram-01.jpg" alt="">
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="thumb">
                                    <div class="icon">
                                        <a href="http://instagram.com">
                                            <h6>New</h6>
                                            <i class="fa fa-instagram"></i>
                                        </a>
                                    </div>
                                    <img src="/resources/UserAssets/images/instagram-02.jpg" alt="">
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="thumb">
                                    <div class="icon">
                                        <a href="http://instagram.com">
                                            <h6>Brand</h6>
                                            <i class="fa fa-instagram"></i>
                                        </a>
                                    </div>
                                    <img src="/resources/UserAssets/images/instagram-03.jpg" alt="">
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="thumb">
                                    <div class="icon">
                                        <a href="http://instagram.com">
                                            <h6>Makeup</h6>
                                            <i class="fa fa-instagram"></i>
                                        </a>
                                    </div>
                                    <img src="/resources/UserAssets/images/instagram-04.jpg" alt="">
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="thumb">
                                    <div class="icon">
                                        <a href="http://instagram.com">
                                            <h6>Leather</h6>
                                            <i class="fa fa-instagram"></i>
                                        </a>
                                    </div>
                                    <img src="/resources/UserAssets/images/instagram-05.jpg" alt="">
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="thumb">
                                    <div class="icon">
                                        <a href="http://instagram.com">
                                            <h6>Bag</h6>
                                            <i class="fa fa-instagram"></i>
                                        </a>
                                    </div>
                                    <img src="/resources/UserAssets/images/instagram-06.jpg" alt="">
                                </div>
                            </div>
                        </div>
                    </div>
                </section>-->
        <!-- ***** Social Area Ends ***** -->

        <!-- ***** Subscribe Area Starts ***** -->
        <!--        <div class="subscribe">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-8">
                                <div class="section-heading">
                                    <h2>By Subscribing To Our Newsletter You Can Get 30% Off</h2>
                                    <span>Details to details is what makes Hexashop different from the other themes.</span>
                                </div>
                                <form id="subscribe" action="" method="get">
                                    <div class="row">
                                        <div class="col-lg-5">
                                            <fieldset>
                                                <input name="name" type="text" id="name" placeholder="Your Name" required="">
                                            </fieldset>
                                        </div>
                                        <div class="col-lg-5">
                                            <fieldset>
                                                <input name="email" type="text" id="email" pattern="[^ @]*@[^ @]*" placeholder="Your Email Address" required="">
                                            </fieldset>
                                        </div>
                                        <div class="col-lg-2">
                                            <fieldset>
                                                <button type="submit" id="form-submit" class="main-dark-button"><i class="fa fa-paper-plane"></i></button>
                                            </fieldset>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="col-lg-4">
                                <div class="row">
                                    <div class="col-6">
                                        <ul>
                                            <li>Store Location:<br><span>Sunny Isles Beach, FL 33160, United States</span></li>
                                            <li>Phone:<br><span>010-020-0340</span></li>
                                            <li>Office Location:<br><span>North Miami Beach</span></li>
                                        </ul>
                                    </div>
                                    <div class="col-6">
                                        <ul>
                                            <li>Work Hours:<br><span>07:30 AM - 9:30 PM Daily</span></li>
                                            <li>Email:<br><span>info@company.com</span></li>
                                            <li>Social Media:<br><span><a href="#">Facebook</a>, <a href="#">Instagram</a>, <a href="#">Behance</a>, <a href="#">Linkedin</a></span></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>-->
        <!-- ***** Subscribe Area Ends ***** -->

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
        <script src="/resources/UserAssets/js/quantity.js"></script>
        <%
            // Lấy dữ liệu từ session
            String msgSuccess = (String) session.getAttribute("UpdateSuccess");
            if (msgSuccess != null) {
        %>

        <script>
                                                    // Sử dụng SweetAlert để hiển thị thông báo
                                                    alertify.success("Update information successfully");
        </script>
        <%
                // Xóa thông báo sau khi hiển thị
                session.removeAttribute("UpdateSuccess");
            }
        %>

        <!-- Global Init -->
        <script src="/resources/UserAssets/js/custom.js"></script>

        <script>
                                                    function redirectToDetail(proID) {
                                                        window.location.href = '/UserHomeController/Detail/' + proID;
                                                    }
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
