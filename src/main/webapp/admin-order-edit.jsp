<%@page import="Daos.OrderDAO"%>
<%@page import="Daos.StaffDAO"%>
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

        <title>Detail</title>
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
                //  int oid = (int) session.getAttribute("orderid");
                int orderIdString = (int) session.getAttribute("OrderID");
                int oid = 1;
                if (orderIdString != 0) {
                    try {
                        oid = orderIdString;
                    } catch (NumberFormatException e) {

                    }
                }
                
                UserModel user = (UserModel) session.getAttribute("acc");
                StaffDAO callStaffFunc = new StaffDAO();
                int staffID = callStaffFunc.getStaffIDByUserID(user.getUserID());

                OrderID_DAOad od = new OrderID_DAOad();
                ResultSet userOrderInfor = od.getUserOrderInfor(oid);
                int orderStatus = 1;
                if (userOrderInfor.next()) {
                    orderStatus = userOrderInfor.getInt("OrderStatus");
                    String t = "";
                    if (orderStatus == 1) {
                        t += "Waiting";
                    } else if (orderStatus == 2) {
                        t += "Accepted";
                    } else if (orderStatus == 3) {
                        t += "Delivered";
                    } else if (orderStatus == 4) {
                        t += "Cancel";
                    }
            %>

            <div class="">
                <div class="card-body m-5">

                    <form  method="post" action='/OrderID_ad' onsubmit="return validateFormOrder()">
                        <input name='oid' value='<%=oid%>' hidden />
                        <input name='staffID' value='<%=staffID%>'hidden/>
                        <div class='row '>
                            <div class='col-md-4 card'>
                                <h5 class="card-title">Order Detail</h5>
                                <div class='card-body'>Order ID: <%=orderIdString%><b></b>
                                    <br>
                                    Date: <%=userOrderInfor.getString("OrderDate")%>

                                    <br>
                                    Account: <%=userOrderInfor.getString("Username")%>

                                    <br>
                                    Total: <b class="text-danger"><%=userOrderInfor.getString("Username")%></b>


                                    <br>
                                    Address: <i><%=userOrderInfor.getString("Address")%></i>

                                    <br>
                                    Phone: <%=userOrderInfor.getString("Phone")%>

                                    <br>
                                    <!--Note: <%--<%=userOrderInfor.getString("Username")%>--%>

                                    <br>-->
                                    <%
                                        }
                                    %>
                                </div>

                                <select id="mySelect" class='form-select mb-2' name="a" <% if (orderStatus == 4) {%> disabled="" <%}%>>

                                    <option  <% if (orderStatus == 1) {%>selected="" <%} %> value="1">Waiting</option><!-- comment -->
                                    <option  <% if (orderStatus == 2) {%>selected="" <%} %> value="2">Accepted</option><!-- comment -->
                                    <option  <% if (orderStatus == 3) {%>selected="" <%} %> value="3">Delivered</option><!-- comment -->
                                    <option  <% if (orderStatus == 4) {%>selected="" <%}%> value="4">Cancel</option><!-- comment -->

                                </select>
                            </div>
                            <div class='col-md-8'>

                                <div class="card">
                                    <div class="card-body">
                                        <h5 class="card-title">List Product </h5>

                                        <table class="table ">
                                            <thead>
                                                <tr>
                                                    <th>Product Name</th>
                                                    <th>Unit</th>
                                                    <th>Quantity</th>
                                                    <th>Total</th>



                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%     OrderID_DAOad ord = new OrderID_DAOad();
                                                    ResultSet rsDt = ord.getListOrderProduct(oid);
                                                    while (rsDt.next()) {%>
                                                <tr>
                                                    <td><%=rsDt.getString("ProName")%></td>
                                                    <td><%=rsDt.getString("UnitName")%></td>
                                                    <td><%=rsDt.getInt("Quantity")%></td>
                                                    <td><%=rsDt.getInt("Total")%></td>
                                                </tr>
                                                <%  }
                                                %>


                                            </tbody>
                                        </table>
                                        <!-- End Table with stripped rows -->

                                    </div>
                                </div>
                            </div>
                        </div>





                        <a class='btn btn-secondary' href="/OrderID_ad">back to list</a>
                        <input class='btn btn-info' type="submit" id='btn-up' style="display:none" value="Update" name ="Create" />

                        <%--OrderDAO o = new OrderDAO();
                UserModel user = (UserModel) session.getAttribute("acc");
                StaffDAO callStaffFunc = new StaffDAO();
                int staffID = callStaffFunc.getStaffIDByUserID(user.getUserID());
                o.updateStaffIDForOrder(oid, staffID);
                        --%>
                    </form>

                </div>
            </div></div>


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