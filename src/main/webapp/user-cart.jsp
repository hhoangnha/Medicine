<%-- 
    Document   : user-home
    Created on : Oct 28, 2023, 8:24:21 PM
    Author     : Nguyen Hoang Nha - CE170092
--%>

<%@page import="Daos.CartDAO"%>
<%@page import="Model.UserModel"%>
<%@page import="Model.CartItem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Model.ProductModel"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Daos.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">

        <title>Cart</title>
        <link rel="stylesheet" type="text/css" href="/resources/UserAssets/css/bootstrap.min.css">

        <link rel="stylesheet" type="text/css" href="/resources/UserAssets/css/font-awesome.css">

        <link rel="stylesheet" href="/resources/UserAssets/css/templatemo-hexashop.css">

        <link rel="stylesheet" href="/resources/UserAssets/css/owl-carousel.css">

        <link rel="stylesheet" href="/resources/UserAssets/css/lightbox.css">
        <!--/resources/jq.js-->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <style>
            .summary{
                background-color: #ddd;
                border-top-right-radius: 1rem;
                border-bottom-right-radius: 1rem;
                padding: 4vh;
                color: rgb(65, 65, 65);
            }
            @media(max-width:767px){
                .summary{
                    border-top-right-radius: unset;
                    border-bottom-left-radius: 1rem;
                }
            }
            .summary .col-2{
                padding: 0;
            }
            .summary .col-10
            {
                padding: 0;
            }
            .row{
                margin: 0;
            }
            .title b{
                font-size: 1.5rem;
            }
            .main{
                margin: 0;
                padding: 2vh 0;
                width: 100%;
            }
            .col-2, .col{
                padding: 0 1vh;
            }
            a{
                padding: 0 1vh;
            }
            .close{
                margin-left: auto;
                font-size: 0.7rem;
            }
            img{
                width: 3.5rem;
            }
            .back-to-shop{
                margin-top: 4.5rem;
            }
            h5{
                margin-top: 4vh;
            }
            hr{
                margin-top: 1.25rem;
            }
            form{
                padding: 2vh 0;
            }
            select{
                border: 1px solid rgba(0, 0, 0, 0.137);
                padding: 1.5vh 1vh;
                margin-bottom: 4vh;
                outline: none;
                width: 100%;
                background-color: rgb(247, 247, 247);
            }
            input,textarea{
                border: 1px solid rgba(0, 0, 0, 0.137);
                padding: 1vh;
                margin-bottom: 4vh;
                outline: none;
                width: 100%;
                background-color: rgb(247, 247, 247);
            }
            input:focus::-webkit-input-placeholder,textarea:focus::-webkit-input-placeholder
            {
                color:transparent;
            }

            .btn{
                background-color: #000;
                border-color: #000;
                color: white;
                width: 100%;
                font-size: 0.7rem;
                margin-top: 4vh;
                padding: 1vh;
                border-radius: 0;
            }
            .btn:focus{
                box-shadow: none;
                outline: none;
                box-shadow: none;
                color: white;
                -webkit-box-shadow: none;
                -webkit-user-select: none;
                transition: none;
            }
            .btn:hover{
                color: white;
            }
            a{
                color: black;
            }
            a:hover{
                color: black;
                text-decoration: none;
            }
            #code{
                background-image: linear-gradient(to left, rgba(255, 255, 255, 0.253) , rgba(255, 255, 255, 0.185)), url("https://img.icons8.com/small/16/000000/long-arrow-right.png");
                background-repeat: no-repeat;
                background-position-x: 95%;
                background-position-y: center;
            }
        </style>
    </head>
    <body>

       
        <%
            UserModel uM = (UserModel) session.getAttribute("acc");
            CartDAO cd = new CartDAO();
            List<CartItem> cartItems = null;
            if(uM != null){
            cartItems = cd.getCartItems(uM.getUserID());
            }
            
        %>


        <%--<jsp:include page="user-header.jsp" />--%>
        
        <section class="section" id="product">
            <div class="container">
                <%    if (session.getAttribute("errorCartMsg") != null) {
                %>
                <span class="text-danger m-2"><%=session.getAttribute("errorCartMsg")%></span>
                <%}%>
                <div class="card">
                    <div class="row">
                        <div class="col-md-8 cart">
                            <div class="title">
                                <div class="row">
                                    <div class="col"><h4><b>Cart</b></h4></div>
                                    <!--<div class="col align-self-center text-right text-muted">3 items</div>-->
                                </div>
                            </div>    

                            <%
                                ProductDAO pd = new ProductDAO();
                                if (cartItems == null) {

                                } else {
                                    
                                    for (CartItem item : cartItems) {
                                        ProductModel pdm = pd.getProduct(item.getProId());
                                           System.out.println(pdm);
                                    
                            %>




                            <div class="row border-top border-bottom">
                                <div class="row main align-items-center">
                                    <div class="col">
                                        <div class="row text-muted"><%=pdm.getProName()%></div>
                                        <!--<div class="row"><%=pdm.getProName()%></div>-->
                                        <small>chỉ còn: <%=pdm.getQuantity()%> sản phẩm</small>
                                    </div>
                                    <div class="col">
                                        <a  onclick='decreaseQuantity(<%=pdm.getProID()%>)'>-</a><a href="#" id="val<%=pdm.getProID()%>" class="border"><%=item.getQuantity()%></a><a onclick='increaseQuantity(<%=pdm.getProID()%>)'>+</a>
                                    </div>
                                    <div class="col"> <%=pdm.getProName()%> <span class="close" onclick="confirmRemove(<%=pdm.getProID()%>)">&#10005;</span></div>
                                </div>
                            </div>
                            <% }
                                }%>

                        </div>

                        <%
                            UserModel udd = (UserModel) session.getAttribute("acc");

                            if (udd != null) {%>
                        <div class="col-md-4 summary">
                            <div><h5><b>Total</b></h5></div>
                            <hr>
                            <div class="row">
                                <div class="col" style="padding-left:0;"></div>
                            </div>
                            <form method="post"  onsubmit="return validateForm()"  name="form" action='/UserCartController/CreateOrder'>
                                <p>Fullname</p>
                                <input type="text" name="name" value="${sessionScope.fullname}" placeholder="">
                                <p>Phone number</p>
                                <input type="text" value="<%=udd.getPhone()%>" name="phone" >
                                <p>Address</p>
                                <textarea name='address'><%=udd.getAddress()%></textarea>
                                <p>Note</p>
                                <textarea name='note'></textarea>

                                <div class="row" style="border-top: 1px solid rgba(0,0,0,.1); padding: 2vh 0;">
                                    <div class="col">Total order</div>
                                </div>
                                <button class="btn" type="submit">Confirm order</button>
                            </form>

                        </div> <%
                        } else { %>

                        <div class="mt-3">
                            <a href="/loginController"> Bạn chưa đăng nhập <small  class="text-primary">Đăng nhập ngay</small></a>
                        </div>
                        <%}

                        %>




                    </div>



                </div>  
            </div>

        </section>

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

        <!-- Global Init -->
        <script src="/resources/UserAssets/js/custom.js"></script>
        <script>
                                function validateForm() {
                                    var name = document.forms["form"]["name"].value;
                                    if (name == "") {
                                        alert("Name not empty.");
                                        return false;
                                    }
                                    var phone = document.forms["form"]["phone"].value;
                                    if (phone == "" || isNaN(phone) || phone.length() != 10) {
                                        alert("Phone is invalid. Phone must be 10 number.");
                                        return false;
                                    }
                                    var address = document.forms["form"]["address"].value;
                                    if (address == "") {
                                        alert("Please enter address.");
                                        return false;
                                    }




                                }

                                function confirmRemove(productId) {
                                    Swal.fire({
                                        title: 'Xác nhận xoá khỏi giỏ hàng!',
                                        icon: 'warning',
                                        showCancelButton: true,
                                        confirmButtonText: 'Đồng ý',
                                        cancelButtonText: "Đóng",
                                    }).then((result) => {
                                        if (result.isConfirmed) {
                                            $.ajax({
                                                type: "GET",
                                                url: "UserCartController/RemoveFromCart/" + productId,
                                                success: function (response) {
                                                    location.reload();
                                                },
                                                error: function () {
                                                    console.error('Lỗi khi gửi yêu cầu AJAX');
                                                }
                                            });
                                        }
                                    });
                                }
                                function decreaseQuantity(productId) {


                                    if ($('#val' + productId).text() == 1) {
                                        Swal.fire({
                                            title: 'Nếu bạn chọn đồng ý, sản phẩm sẽ bị xoá khỏi giỏ hàng!',
                                            icon: 'warning',
                                            showCancelButton: true,
                                            confirmButtonText: 'Đồng ý',
                                            cancelButtonText: "Đóng",
                                        }).then((result) => {
                                            if (result.isConfirmed) {
                                                $.ajax({
                                                    type: "GET",
                                                    url: "UserCartController/DecreaseQuantity/" + productId + "?quan=-1",
                                                    success: function (response) {
                                                        location.reload();
                                                    },
                                                    error: function () {
                                                        console.error('Lỗi khi gửi yêu cầu AJAX');
                                                    }
                                                });
                                            } else {
                                                return false;
                                            }
                                        });

                                    } else {
                                        $.ajax({
                                            type: "GET",
                                            url: "UserCartController/DecreaseQuantity/" + productId + "?quan=-1",
                                            success: function (response) {
                                                location.reload();
                                            },
                                            error: function () {
                                                console.error('Lỗi khi gửi yêu cầu AJAX');
                                            }
                                        });
                                    }


                                }
                                function increaseQuantity(productId) {
                                    $.ajax({
                                        type: "GET",
                                        url: "UserCartController/IncreaseQuantity/" + productId + "?quan=1",
                                        success: function (response) {
                                            location.reload();
                                        },
                                        error: function () {
                                            console.error('Lỗi khi gửi yêu cầu AJAX');
                                        }
                                    });
                                }
        </script>

    </body>
</html>
