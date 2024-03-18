<%@page import="Daos.OrderDAO"%>
<!DOCTYPE html>
<html lang="en">
    <%@page import="java.sql.ResultSet"%>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Order</title>
        <meta content="" name="description">
        <meta content="" name="keywords">
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

        <script src="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/alertify.min.js"></script>

        <!-- CSS -->
        <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/alertify.min.css"/>
        <!-- Default theme -->
        <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/default.min.css"/>
        <!-- Semantic UI theme -->
        <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/semantic.min.css"/>
        <!-- Template Main CSS File -->
        <link href="/resources/AdminAssets/css/style.css" rel="stylesheet">

    </head>

    <body>
    


        <jsp:include page="admin-header.jsp" />
        <jsp:include page="admin-aside.jsp" />
        <!--                <main id="main" class="main">
                
                            <div class="pagetitle">
                                <h1></h1>
                                <nav>
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                                        <li class="breadcrumb-item">Tables</li>
                                        <li class="breadcrumb-item active">Data</li>
                                    </ol>
                                </nav>
                            </div> End Page Title 
                
                            <section class="section">
                                <div class="row">
                                    <div class="col-lg-12">
                
                                        <div class="card">
                                            <div class="card-body">
                                                <h5 class="card-title">Orders</h5>
                
                                                 Table with stripped rows 
                                                <table class="table datatable">
                                                    <thead>
                                                        <tr>
                                        <th>OrderID</th>
                                        <th>Username</th>
                                        <th>OrderTotal</th>
                                        <th>OrderStatus</th>
                                        <th>Actions</th>
                                    </tr>
                                                    </thead>
                                                    <tbody>
                
                
        
        
                
                //String t = "";
                    //if (rs.getInt("OrderStatus") == 0) {
        //                t += "Đã hủy";
        //            } else if (rs.getInt("OrderStatus") == 1) {
        //                t += "Đã đặt hàng";
        //            } else if (rs.getInt("OrderStatus") == 2) {
        //                t += "Đã đóng gói";
        //            } else if (rs.getInt("OrderStatus") == 3) {
        //                t += "Đang vân chuyển";
        //            } else if (rs.getInt("OrderStatus") == 4) {
        //                t += "Đã giao";
        //            }
        
                
        
        
        
         
        
            </tbody>
        </table>
         End Table with stripped rows 
        
        </div>
        </div>
        
        </div>
        </div>
        </section>
        
        </main> End #main -->
        <main id="main" class="main">

            <div class="pagetitle">
                <h1></h1>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="index.html">Home</a></li>

                        <li class="breadcrumb-item active">Order</li>

                    </ol>
                </nav>
            </div><!-- End Page Title -->

            <section class="section">
                <div class="row">
                    <div class="col-lg-12">

                        <div class="card">
                            <div class="card-body">

                                <h5 class="card-title">Order</h5>
                                <br/>

                                <table class="table datatable">
                                    <thead>
                                        <tr>
                                            <th>OrderID</th>
                                            <th>Staff</th>
                                            <th>Customer</th>
                                            <th>Date</th>
                                            <th>Status</th>
                                            <th>Total</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% OrderDAO o = new OrderDAO();
                                            ResultSet rs = o.getAll();

                                            while (rs.next()) {
                                                String t = "";
                                                if (rs.getInt("OrderStatus") == 1){
                                                    t = "Waiting for confirm";
                                                } else if (rs.getInt("OrderStatus") == 2){
                                                    t = "Order Accepted";
                                                } else if (rs.getInt("OrderStatus") == 3){
                                                    t = "Delivered";
                                                } else if (rs.getInt("OrderStatus") == 4){
                                                    t = "Cancel Order";
                                                }
                                        %>
                                        <tr>
                                            <td><%= rs.getInt("OrderID")%></td>
                                            <td>
                                                <%
                                                    if (rs.getInt("StaffID") == 0) {
                                                %>
                                                No staff
                                                <%} else {%>
                                                <%= rs.getInt("StaffID")%>
                                                <%}
                                                %>

                                            </td>
                                            <td><%= rs.getString("Username")%></td>
                                            <td><%= rs.getDate("OrderDate")%></td>
                                            <td><%= t%></td>
                                            <td><%= rs.getInt("Total")%></td>
                                            <td style="text-align: center">
<!--                                                <a style="color:white" class="btn bg-primary btn-sm " href="/OrderController/confirmOrder/<%= rs.getInt("OrderID")%>" >Confirm</a>
                                                <a style="color:white" onclick="return confirm('Are you sure? Order can not restore');" class="btn btn-danger btn-sm" href="/OrderController/deleteOrder/<%= rs.getString("OrderID")%>" >Cancel</a>  -->
                                                <a style="color:white" class="btn bg-primary btn-sm " href="/OrderController/editOrderStatus/<%= rs.getString("OrderID")%>" >Edit Status</a>
                                            </td>
                                        </tr>
                                        <% }%>

                                    </tbody>
                                </table>
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

    </body>

</html>
