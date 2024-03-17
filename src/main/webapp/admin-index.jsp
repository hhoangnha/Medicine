
<%@page import="java.sql.ResultSet"%>
<%@page import="Daos.AdminDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Dashboard</title>
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

        <jsp:include page="admin-header.jsp" />
        <jsp:include page="admin-aside.jsp" />


        <%
            int orderN = (int) request.getAttribute("orderN");
            int cusN = (int) request.getAttribute("cusN");
            int orderNew = (int) request.getAttribute("orderNew");
             int orderSuccess = (int) request.getAttribute("orderSuccess");
                 int orderCancel = (int) request.getAttribute("orderCancel");
             double revenueTotal = (double) request.getAttribute("revenueTotal");
            int totalOrder = 0;
            int totalCustomer = 0;
            int totalRe = 0;
            int month = 0;

            int on = 0;
            int os = 0;
            int oc = 0;

            try {
                month = Integer.parseInt(request.getParameter("month"));
            } catch (Exception e) {
                month = 0;
            }

            AdminDao ac = new AdminDao();
//            ResultSet coutO = ac.countOrder(month);
//            ResultSet coutC = ac.countCustomer();
//            ResultSet coutR = ac.countTotalOrder(month);
//            ResultSet topPro = ac.topProduct(month);


        %>

        <main id="main" class="main">

            <div class="pagetitle">
                <h1>Dashboard</h1>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                        <li class="breadcrumb-item active">Dashboard</li>
                    </ol>

                </nav>

            </div><!-- End Page Title -->
            <!-- Recent Activity -->
            <div class="card">


                <div class="card-body">
                    <h5 class="card-title">Filter<span></span></h5>

                    <div class="activity">

                        <select class="form-select" value="<%=month%>" onchange="redirect(this)">
                            <option <% if (month == 0) {
                                    out.print("selected");
                                } %> value="0">This month</option>
                            <option <% if (month == 3) {
                                    out.print("selected");
                                } %> value="3">3 month</option>
                            <option <% if (month == 6) {
                                    out.print("selected");
                                } %> value="6">6 month</option>
                            <option <% if (month == 12)
                                    out.print("selected");%> value="12">12 month</option>
                        </select>

                    </div>

                </div>
            </div><!-- End Recent Activity -->

            <section class="section dashboard">
                <div class="row">

                    <!-- Left side columns -->
                    <div class="col-lg-12">
                        <div class="row">

                            <!-- Sales Card -->
                            <div class="col-xxl-4 col-md-6">
                                <div class="card info-card sales-card">


                                    <div class="card-body">
                                        <h5 class="card-title">Order <span></span></h5>

                                        <div class="d-flex align-items-center">
                                            <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                                <i class="bi bi-cart"></i>
                                            </div>
                                            <div class="ps-3">
                                                <h6><%=orderN%></h6>
                                                <span class="text-info small pt-1 fw-bold"><%=orderNew %></span> <span class="text-muted small pt-2 ps-1">New</span><br/>
                                                <span class="text-success small pt-1 fw-bold"><%=orderSuccess%></span><span class="text-muted small pt-2 ps-1">Success</span> <span class="text-danger small pt-1 fw-bold"><%=orderCancel%></span><span class="text-muted small pt-2 ps-1">Đã huỷ</span>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div><!-- End Sales Card -->

                            <!-- Revenue Card -->
                            <div class="col-xxl-4 col-md-6">
                                <div class="card info-card revenue-card">


                                    <div class="card-body">
                                        <h5 class="card-title">Revenue <span></span></h5>

                                        <div class="d-flex align-items-center">
                                            <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                                <i class="bi bi-currency-dollar"></i>
                                            </div>
                                            <div class="ps-3">
                                                <h6><%=revenueTotal%></h6>
                                                <!--<span class="text-success small pt-1 fw-bold">8%</span> <span class="text-muted small pt-2 ps-1">increase</span>-->

                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div><!-- End Revenue Card -->

                            <!-- Customers Card -->
                            <div class="col-xxl-4 col-xl-6">

                                <div class="card info-card customers-card">



                                    <div class="card-body">
                                        <h5 class="card-title">Customer <span></span></h5>

                                        <div class="d-flex align-items-center">
                                            <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                                <i class="bi bi-people"></i>
                                            </div>
                                            <div class="ps-3">
                                                <h6><%=cusN%></h6>
                                                <!--<span class="text-danger small pt-1 fw-bold">12%</span> <span class="text-muted small pt-2 ps-1">decrease</span>-->

                                            </div>
                                        </div>

                                    </div>
                                </div>

                            </div><!-- End Customers Card -->


                            <!-- Recent Sales -->
                            <div class="col-12">
                                <div class="card recent-sales overflow-auto">



                                    <div class="card-body">
                                        <h5 class="card-title">Top Products <span></span></h5>

                                        <table class="table table-borderless datatable">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Product ID</th>
                                                    <th scope="col">Type</th>
                                                    <th scope="col">Name</th>
                                                    <th scope="col">Quantity</th>
                                                    <th scope="col"></th>
                                                </tr>
                                            </thead>
                                            <tbody>



                                            </tbody>
                                        </table>

                                    </div>

                                </div>
                            </div><!-- End Recent Sales -->

                            <!-- Top Selling -->


                        </div>
                    </div><!-- End Left side columns -->

                    <!-- Right side columns -->

                </div>
            </section>

        </main><!-- End #main -->



        <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

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

        <script>

                            function redirect(selectElement) {
                                var selectedValue = selectElement.value;
                                if (selectedValue === "0") {
                                    window.location.href = '/AdminController';
                                } else if (selectedValue === "3") {
                                    window.location.href = '/AdminController?month=3';
                                } else if (selectedValue === "6") {
                                    window.location.href = '/AdminController?month=6';
                                } else if (selectedValue === "12") {
                                    window.location.href = '/AdminController?month=12';
                                }
                            }


        </script>

    </body>

</html>