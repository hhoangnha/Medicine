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

        <script src="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/alertify.min.js"></script>

        <!-- CSS -->
        <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/alertify.min.css"/>
        <!-- Default theme -->
        <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/default.min.css"/>
        <!-- Semantic UI theme -->
        <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/semantic.min.css"/>
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
        <%
            // L?y d? li?u t? session
            String msgSuccess = (String) session.getAttribute("msgSuccess");
            if (msgSuccess != null) {
        %>
        <script>
            // S? d?ng SweetAlert ?? hi?n th? thông báo
            alertify.success("Thành công");
        </script>
        <%
                // Xóa thông báo sau khi hi?n th?
                session.removeAttribute("message");
            }
        %>


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
                                            <th>StaffID</th>
                                            <th>CustomerID</th>
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
                                                if (rs.getInt("OrderStatus") == 1) {
                                                    t = "Ðã đặt";
                                                } else if (rs.getInt("OrderStatus") == 2) {
                                                    t = "Xác nhận";
                                                } else if (rs.getInt("OrderStatus") == 3) {
                                                    t = "Thành công";
                                                } else if (rs.getInt("OrderStatus") == 4) {
                                                    t = "Ðã huỷ";
                                                }
                                        %>
                                        <tr>
                                            <td><%= rs.getInt("OrderID")%></td>
                                            <td><%= rs.getInt("StaffID")%></td>
                                            <td><%= rs.getInt("CustomerID")%></td>
                                            <td><%= rs.getDate("OrderDate")%></td>
                                            <td><%= t%></td>
                                            <td><%= rs.getInt("Total")%></td>

                                            <%
                                                if (rs.getInt("OrderStatus") == 1) {
                                            %> 

                                            <td style="text-align: center">
                                                <a style="color:white" class="btn bg-primary btn-sm " href="/OrderController/confirmOrder/<%= rs.getInt("OrderID")%>" >Confirm</a>
                                                <a style="color:white" onclick="return confirm('Are you sure? Order can not restore');" class="btn btn-danger btn-sm" href="/OrderController/deleteOrder/<%= rs.getString("OrderID")%>" >Cancel</a>  
                                            </td>
                                            <%
                                            } else if (rs.getInt("OrderStatus") == 2) {
                                            %> 


                                            <td>
                                                <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAACXBIWXMAAAsTAAALEwEAmpwYAAAFZElEQVR4nO1Z208cVRifqC++eXny8md4+yO2SkuxpabMlKJogiaWRGFzjlNry2UOlyVA2hr7ZCOWSxe3beyqBbZGhQAa7VIFtsJSlu7CAPpU68sxv+Hi7JnZnZndKdWEk3zJZGfOd37f+e7fStLu2l27q+ilqupDcrD1JYU2B2WqDSiUxRXK1mSq/Q3Cs/EbYf0KYfVHKHsRe6QHvarUpmcVwhoVqi0qlHEvJBN2W6ZaQ0Ww9ZkdB364ruFJhWinFcLueQVuEYRqf8lE6z6qtj6xI+Blwg4qlOnFArdqRFupIOzV+wZcVdVHjFvPAaD6oxAPnb/Ir1wf4z/9muDJVJpn9HWD8PzjzVnjHb7Bt3mE6cJZvoIve7f1UZmyS3YHvtPUzS/FRvmdlVWur//pivAthKltPZtLGxGc6Qt43IYd+KMftvGeL4f50rJ74CJl9HXeF71u8LITwhdN2JnN243dfOLmTMHARZqYmuE1TV12QnT64bBZTOtC53hiYckWyHT6d94T7+f1Mcqrom/x0i8OGIRn/PZ5fIDPpOds984upAze4nlHCNtfEHiENUQGMzPckh342cwcbxkL8ZcH9/FAuCQv7Rncy098d4r/dueWrRA2mtARtos2narj7bZm89XsEC+NHHAELtL+yEH+dWLYwm98atriE55NaTPDZiUpOKx42Gc3+owb9QrerI2r09csfHujMUuyq1Sbn/Zw+ygPskOlGG1w88WABx0beo+n9GWLAGl9jR9rOSNq4ZQr8GVlvQ+LtQ3ivPmAmfR8QWZjptrhOp5atYLfosuxsWxfICzpqgDcqCqzM6yYpJp/aLsvN6+bCBp/40R7lhAVwaYXnM2HNgfNm5D6xVDpJtp4BX928pyhWfNvofMDohbed9bARj2/vQkp38y050a/7+DPTH5ivL8Qz76syyOjojP3udCA0Yxsb0JhZmYajNGcAKujNZ5tvmvi4+1vgjE16x0KQEGAX5w1QLRV86bkUiaLaeXValuA8IuVtT8MU/B684FNQsY2v59PpS0lt7MGhPiPkGZmahd9tsCb7dkr+EC4xOBt/iazsmbJB74LUH7lMF/UM7am4cZsAiYqi5QXL4CTCVVF33Rt3+lV3TX4QLiEv+6LCQlODEdy48QwFTtNOJlNwESiE08W5MQOYRQlcb4wmUsIJ/CBcAnvnQr7EEYJq8+XyFDP50tkdkK4Af/KYKklkbV/WkAiw9DJqZTQRtsdE9aWEG7AB8IlvGWsw7GUqAw2P+duykbY7XxmhAYG9Xw+QHDszvHTrsCXRcp5IpP0p5jb9IMG82ZMD9CAmw8YuvVtUTXRFu0Z3Mu/SYxYwmetUE4rlJ2U3C6M+xBzzQwwPRAdE3VRsQ1NT7zfwvdCdES8/bueGhpDC0TrNjNBm4fpgXgY2kInc8plNtcSMQu/8fg0rzoujFmI1iEV1NRTtiw29Wi8xUPnMou8Y7zbiCRubr3he2axeT1XU0+0zKG6xselQhZmlYIdGqMPOyE2Quy8URIHYx8YWRVlBwjPSFK9UxctoVJ3GKsohO2TilmYVYpMcUuYHuTLul5oPD7NaxptBltUC0m+jBaJFhGZwycwPRCLPS+U1tcMh7XY/Mb/B4PozyXfhrs2QoAwPUDM9jIjxbfYU2sNlf+C92u4K2ii0+5AELImeljULygAUUnihkF4xm+YbqA8EDOsIkQc327ebmFWiciQE0CBJFMtXbTDul0Ia9CGmOwKIsLu4tZltf0xaacXsiMmZjLVFgoAnkR58Fqw7SnpQS8UWRg6yVSrQ82OxsPo7NCeEnZv8/lnvENJrFD2/H/ib9bdtbuk///6B4+zY+wurfiOAAAAAElFTkSuQmCC">
                                            </td>
                                            <%
                                            } else if (rs.getInt("OrderStatus") == 4) {
                                            %> 

                                            <td>
                                                <svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="45" height="45" viewBox="0 0 48 48">
                                                <linearGradient id="wRKXFJsqHCxLE9yyOYHkza_fYgQxDaH069W_gr1" x1="9.858" x2="38.142" y1="9.858" y2="38.142" gradientUnits="userSpaceOnUse"><stop offset="0" stop-color="#f44f5a"></stop><stop offset=".443" stop-color="#ee3d4a"></stop><stop offset="1" stop-color="#e52030"></stop></linearGradient><path fill="url(#wRKXFJsqHCxLE9yyOYHkza_fYgQxDaH069W_gr1)" d="M44,24c0,11.045-8.955,20-20,20S4,35.045,4,24S12.955,4,24,4S44,12.955,44,24z"></path><path d="M33.192,28.95L28.243,24l4.95-4.95c0.781-0.781,0.781-2.047,0-2.828l-1.414-1.414	c-0.781-0.781-2.047-0.781-2.828,0L24,19.757l-4.95-4.95c-0.781-0.781-2.047-0.781-2.828,0l-1.414,1.414	c-0.781,0.781-0.781,2.047,0,2.828l4.95,4.95l-4.95,4.95c-0.781,0.781-0.781,2.047,0,2.828l1.414,1.414	c0.781,0.781,2.047,0.781,2.828,0l4.95-4.95l4.95,4.95c0.781,0.781,2.047,0.781,2.828,0l1.414-1.414	C33.973,30.997,33.973,29.731,33.192,28.95z" opacity=".05"></path><path d="M32.839,29.303L27.536,24l5.303-5.303c0.586-0.586,0.586-1.536,0-2.121l-1.414-1.414	c-0.586-0.586-1.536-0.586-2.121,0L24,20.464l-5.303-5.303c-0.586-0.586-1.536-0.586-2.121,0l-1.414,1.414	c-0.586,0.586-0.586,1.536,0,2.121L20.464,24l-5.303,5.303c-0.586,0.586-0.586,1.536,0,2.121l1.414,1.414	c0.586,0.586,1.536,0.586,2.121,0L24,27.536l5.303,5.303c0.586,0.586,1.536,0.586,2.121,0l1.414-1.414	C33.425,30.839,33.425,29.889,32.839,29.303z" opacity=".07"></path><path fill="#fff" d="M31.071,15.515l1.414,1.414c0.391,0.391,0.391,1.024,0,1.414L18.343,32.485	c-0.391,0.391-1.024,0.391-1.414,0l-1.414-1.414c-0.391-0.391-0.391-1.024,0-1.414l14.142-14.142	C30.047,15.124,30.681,15.124,31.071,15.515z"></path><path fill="#fff" d="M32.485,31.071l-1.414,1.414c-0.391,0.391-1.024,0.391-1.414,0L15.515,18.343	c-0.391-0.391-0.391-1.024,0-1.414l1.414-1.414c0.391-0.391,1.024-0.391,1.414,0l14.142,14.142	C32.876,30.047,32.876,30.681,32.485,31.071z"></path>
                                                </svg>
                                            </td>

                                            <%
                                            } else if (rs.getInt("OrderStatus") == 3) {
                                            %> 
                                            <td>
                                                <img style="width: 45; height: 45;" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAACXBIWXMAAAsTAAALEwEAmpwYAAAB30lEQVR4nO2WSytEYRjH368xk4QoWchCNnqNO9PIZVymYRKRy4wkFshSydLCTlE2YqVsJCUbFkou0ZzPcI7LYAbDzF9ORF5zPc5c8vzr2b1n+v3O85znHcYolOTHu8sQT7FUCQnoGS7JiFTyNIureBS/TQL8P3SA61iMBCTqAGiEtIRGSAo/QqZTBWXnSnpuIdOZgh6LHz1mP0wnSnoJmC5kdFufMWKAWr21fpQfK+khUPYO3/oFP/JR9oHHNBC4lNHleBLg+0tfUH6U6h1wy+jsexThS15RcXiV4t+AW4Z9UIQfKH5F5cHv8IkTcEc+YxvzifBFAVTtX4d9TncBdZu0PqNp4T7kmY4JEX6wMICqvfDwugt8wqtQRqB5XpRon/QK8EMFAVTvRIbnegs47D+2iRFo+SZhnX0Q4IfzA6jZjg6e6y1gWfHAlRkUINtmvLDOifDOvCDqtm5i+viZngKqxKoHzixRQnjzuUHUbcYGzxO1hczrt3DmhJZwZgfVM7HC80TeA/Ubt792wpUBmNfig+eJvsgaF+/gMn6DNwDtU9644XkybuKGJY+6jd4FbKM+TfA8WX8lLMse2Ma1vXmeTIG/LEYCEnUANEJaQiMk0QhpC42QRCNEoVBY6LwBWB5lLqkli2oAAAAASUVORK5CYII=">
                                            </td>

                                            <%
                                                }
                                            %>

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
