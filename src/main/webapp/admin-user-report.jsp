<%@page import="Daos.OrderDAO"%>
<%@page import="Model.UserModel"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Daos.OrderID_DAOad"%>
<%@page import="Model.OrderModel"%>
<%@page import="Model.BrandModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>User Report</title>
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
            <%
                UserModel kh = (UserModel) session.getAttribute("thongtinkh");

            %>
            <div class="">
                <div class="card-body m-5">
                    <form  method="post" >
                        <input name='oid'  hidden />
                        <div class='row '>
                            <div class='col-md-4 card'>
                                <h5 class="card-title">Customer Information</h5>
                                <div class='card-body'></b>
                                    <br>
                                    Username: <%= kh.getUsername()%>
                                    <br>
                                    Full Name: <b class="text-danger"><%= kh.getFullname()%></b>
                                    <br>
                                    Address: <i><%= kh.getAddress()%></i>
                                    <br>
                                    Phone: <%= kh.getPhone()%>
                                    <br>
                                </div>
                            </div>

                            <div class='col-md-8'>
                                <div class="card">
                                    <div class="card-body">
                                        <h5 class="card-title">User Order History</h5>
                                        <table class="table ">
                                            <thead>
                                                <tr>
                                                    <th>Order Date</th>
                                                    <th>Total</th>
                                                    <th>Order Status</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>

                                                <% //CusDAO cd = new CusDAO();
                                                    OrderDAO d = new OrderDAO();
                                                    ResultSet rs = d.getAllOrderByUserID(kh.getUserID());
                                                    // list<user> lu = d.getAdminUser("user");
                                                    while (rs.next()) {
                                                %>

                                                <tr>
                                                    <td><%=  rs.getString("OrderDate")%></td>
                                                    <td><%= rs.getString("Total")%> </td>
                                                    <td>
                                                        <%if (rs.getString("OrderStatus").equals("1")) {%><p>Waiting For Confirm Order</p><%}%>
                                                        <%if (rs.getString("OrderStatus").equals("2")) {%><p>Accepted Order</p><%}%>
                                                        <%if (rs.getString("OrderStatus").equals("3")) {%><p>Order Delivered</p><%}%>
                                                        <%if (rs.getString("OrderStatus").equals("4")) {%><p class='text-danger'>Canceled</p><%}%>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#basicModal<%=rs.getInt("OrderID")%>">
                                                            View Detail
                                                        </button>
                                                    </td>
                                                </tr>
                                            <div class="modal fade" id="basicModal<%=rs.getInt("OrderID")%>" tabindex="-1">
                                                <div class="modal-dialog modal-xl">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title"><%=  rs.getString("OrderDate")%>p</h5>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">

                                                            <%
                                                                OrderID_DAOad dod = new OrderID_DAOad();
                                                                ResultSet rsc = dod.getProductOrder(rs.getInt("OrderID"));
                                                                while (rsc.next()) {
                                                            %>

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
                                                    </div>
                                                </div>
                                            </div>

                                            <%}%>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <a class='btn btn-secondary' href="/AdminController/Users">Back to list</a>
                    </form>
                </div>
            </div>
        </div>


        <script src="/resources/UserAssets/js/jquery-2.1.0.min.js"></script>
        <!-- Vendor JS Files -->
        <script src="/resources/AdminAssets/vendor/apexcharts/apexcharts.min.js"></script>
        <script src="/resources/AdminAssets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="/resources/AdminAssets/vendor/chart.js/chart.umd.js"></script>
        <script src="/resources/AdminAssets/vendor/echarts/echarts.min.js"></script>
        <script src="/resources/AdminAssets/vendor/quill/quill.min.js"></script>
        <script src="/resources/AdminAssets/vendor/simple-datatables/simple-datatables.js"></script>
        <script src="/resources/AdminAssets/vendor/tinymce/tinymce.min.js"></script>
        <script src="/resources/AdminAssets/vendor/php-email-form/validate.js"></script>
        <script>
            function validateForm() {
                var brandName = document.getElementById("brandName").value;
                if (brandName === "") {
                    alert("Please enter all files");
                    return false;
                }
                return true;
            }
        </script>
        <!-- Template Main JS File -->
        <script src="/resources/AdminAssets/js/main.js"></script>
        <script>
            $(document).ready(function () {
                $('#mySelect').change(function () {
                    //        alert("Giá trị đã thay đổi thành: " + $(this).val());
                    $("#btn-up").show();
                });
            });
        </script>
    </body>

</html>