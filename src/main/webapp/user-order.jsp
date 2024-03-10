<%@page import="Model.OrderModel"%>
<%@page import="java.util.List"%>
<%@page import="Daos.OrderID_DAOad"%>
<%@page import="Daos.OrderDAO"%>
<!DOCTYPE html>
<html lang="en">


    <%@page import="Daos.userad_ad"%>
    <%@page import="java.sql.ResultSet"%>
    <%@page import="Model.UserModel"%>
    <%@page import="Daos.cate_ad"%>
    <%@page import="Daos.userad_ad"%>
    <%@page import="java.sql.ResultSet"%>
    <%@page import="Model.CategorieModel"%>
    <%@page import="Daos.userad_ad"%>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    </head>

    <body>

        <%--<jsp:include page="admin-header.jsp" />--%>
        <%--<jsp:include page="admin-aside.jsp" />--%>
        <jsp:include page="user-header.jsp" />

        <main class='mt-5'>

            <div class="pagetitle">
                <h1></h1>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                        <li class="breadcrumb-item">Tables</li>
                        <li class="breadcrumb-item active">Data</li>
                    </ol>
                </nav>
            </div><!-- End Page Title -->

            <section class="section">
                <div class="row">
                    <div class="col-lg-12">

                        <div class="card container">
                            <div class="card-body">
                                <h5 class="card-title">Order</h5>

                                <table class="table ">
                                    <thead>
                                        <tr>
                                            <th>Order date</th>
                                            <th>Total order</th>
                                            <th>Status order</th>
                                            <th></th>
                                            <!--<th></th>-->

                                        </tr>
                                    </thead>
                                    <tbody>

                                        <% //CusDAO cd = new CusDAO();
                                            UserModel user = (UserModel) session.getAttribute("acc");
                                            OrderDAO d = new OrderDAO();
                                            ResultSet rs = d.getAllOrderByUserID(user.getUserID());
                                            while (rs.next()) {
                                        %>

                                        <tr>
                                            <td><%= rs.getString("OrderDate")%></td>
                                            <td><%= rs.getString("Total")%> </td>
                                            <td>
                                                <%--
                                                <%if (userListOrders.get(i).getOrderStatus()==1")) {%><p>New</p><%}%>
                                                <%if (userListOrders.get(i).getOrderStatus()==2")) {%><p>Waiting</p><%}%>
                                                <%if (userListOrders.get(i).getOrderStatus()==3")) {%><p>Shipping</p><%}%>
                                                <%if (userListOrders.get(i).getOrderStatus()==4")) {%><p>Done</p><%}%>
                                                <%if (userListOrders.get(i).getOrderStatus()==0")) {%><p class='text-danger'>Cancel</p><%}%>
                                                --%>
                                                <%if (rs.getInt("OrderStatus") == 1) {%><p>Waiting to Accept Order</p><%}%>
                                                <%if (rs.getInt("OrderStatus") == 2) {%><p>Order Accepted</p><%}%>
                                                <%if (rs.getInt("OrderStatus") == 3) {%><p>Delivered</p><%}%>
                                                <%if (rs.getInt("OrderStatus") == 4) {%><p class='text-danger'>Cancel</p><%}%>
                                            </td>
                                            <td>
                                                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#basicModal<%=rs.getInt("OrderID")%>">
                                                    View detail
                                                </button>
                                            </td>

                                        </tr>
                                        <%--Code work well here--%>
                                    <%--display detail order by orderID--%>
                                    <div class="modal fade" id="basicModal<%=rs.getInt("OrderID")%>" tabindex="-1">
                                        <div class="modal-dialog modal-xl">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title"><%=  rs.getString("OrderDate")%></h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">x</button>
                                                </div>
                                                <div class="modal-body">
                                                    <%
                                                        OrderID_DAOad dod = new OrderID_DAOad();
                                                        ResultSet rsc = dod.getProductOrder(rs.getInt("OrderID"));
                                                        while (rsc.next()) {%>
                                                    <div class="row">
                                                        <div class="col-md-2">ID: <%=rsc.getInt("OdID")%></div> 
                                                        <div class="col-md-4">Name <%=rsc.getString("ProName")%></div>
                                                        <div class="col-md-2">Price <%=rsc.getString("total_price")%></div>
                                                        <div class="col-md-2">Quantity: <%=rsc.getString("Quantity")%></div>
                                                    </div>
                                                    <%
                                                        }
                                                    %>
                                                </div>
                                                <div class="modal-footer">
                                                    <%
                                                        if (rs.getInt("OrderStatus") == 1) {%>
                                                    <a onclick="confirmCancelOrder(<%=user.getUserID()%>)" class="btn btn-danger">Cancel order</a>
                                                    <%
                                                        }
                                                    %>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                    <%}%>



                                    </tbody>
                                </table><!--
                                <!-- End Table with stripped rows -->

                            </div>
                        </div>

                    </div>
                </div>
            </section>

        </main><!-- End #main -->

        <!-- Vendor JS Files -->
        <script src="/resources/AdminAssets/vendor/apexcharts/apexcharts.min.js"></script>
        <script src="/resources/AdminAssets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="/resources/AdminAssets/vendor/chart.js/chart.umd.js"></script>
        <script src="/resources/AdminAssets/vendor/echarts/echarts.min.js"></script>
        <script src="/resources/AdminAssets/vendor/quill/quill.min.js"></script>
        <script src="/resources/AdminAssets/vendor/simple-datatables/simple-datatables.js"></script>
        <script src="/resources/AdminAssets/vendor/tinymce/tinymce.min.js"></script>
        <script src="/resources/AdminAssets/vendor/php-email-form/validate.js"></script>

        <!-- Template Main JS File -->
        <script src="/resources/AdminAssets/js/main.js"></script>

        <script src="/resources/UserAssets/js/jquery-2.1.0.min.js"></script>
        <script>
                                                        function confirmCancelOrder(productId) {
                                                            Swal.fire({
                                                                title: 'Xác nhận huỷ đơn hàng!',
                                                                icon: 'warning',
                                                                showCancelButton: true,
                                                                confirmButtonText: 'Đồng ý',
                                                                cancelButtonText: "Đóng",
                                                            }).then((result) => {
                                                                if (result.isConfirmed) {
                                                                    $.ajax({
                                                                        type: "GET",
                                                                        url: "/UserCartController/CancelOrder/" + productId,
                                                                        success: function (response) {
                                                                            location.reload();
                                                                        },
                                                                        error: function () {
                                                                            console.error('Lỗi khi gửi yêu cầu AJAX');
                                                                        }
                                                                    });
                                                                }
                                                            });
//                                                        let quan = 1;
//                                                        $.ajax({
//                                                            url: '/UserCartController/AddToCart/' + id + "?quan=" + quan,
//                                                            method: 'GET',
//                                                            success: function (response) {
//                                                                if (response.success) {
//                                                                    Swal.fire({
//                                                                        title: 'Thêm vào giỏ hàng thành công!',
//                                                                        icon: 'success',
//                                                                        showCancelButton: false,
//                                                                        confirmButtonText: 'Đồng ý'
//                                                                    }).then((result) => {
//                                                                        if (result.isConfirmed) {
//                                                                            location.reload();
//                                                                        }
//                                                                    });
//                                                                }
//                                                            }
//                                                        });
                                                        }
        </script>
    </body>

</html>