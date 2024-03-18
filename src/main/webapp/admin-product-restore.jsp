<%@page import="Daos.ProductDAO"%>
<!DOCTYPE html>
<html lang="en">


    <%@page import="Daos.userad_ad"%>
    <%@page import="java.sql.ResultSet"%>
    <%@page import="Model.UserModel"%>
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Product</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

       
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
        <%
            // L?y d? li?u t? session
            String msgSuccess = (String) session.getAttribute("msgSuccess");
            if (msgSuccess != null) {
        %>
        <script>
            // S? d?ng SweetAlert ?? hi?n th? th�ng b�o
            alertify.success("Th�nh c�ng");
        </script>
        <%
                // X�a th�ng b�o sau khi hi?n th?
                session.removeAttribute("message");
            }
        %>
        <jsp:include page="admin-header.jsp" />

        <jsp:include page="admin-aside.jsp" />

        <main id="main" class="main">

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

                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Product</h5>
<!--                                <a href="/ProductController/Create" class="btn btn-primary ">Add new</a>-->

                                <!-- Table with stripped rows -->
                                <table class="table datatable">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Quantity</th>
                                            <th >Name</th>  
                          
                                            <th>Pro Code</th>
                                            <th>Category</th>
                                            <th>Brand</th>
                                            <th class="w-50">Manufacturer</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ProductDAO cDAO = new ProductDAO();
                                            ResultSet rs = cDAO.getAllDeletedList();
                                            while (rs.next()) {

                                        %>
                                        <tr>
                                            <td><%= rs.getInt("ProID")%></td>
                                             <td>
                                                 <%=rs.getString("ProCode")%>
                                                
                                            </td>
                                            <td><%= rs.getString("ProName")%></td>
                                            <td><%=  rs.getInt("Quantity")%></td>
                                            
                                            <td><%=  rs.getString("CateName")%></td>
                                            <td><%=  rs.getString("BrandName")%></td>
                                            <td><%=  rs.getString("ManuName")%></td>
                                            <td>
                                                <a href="/ProductController/Restore/<%=rs.getString("ProID")%>"><button class="btn btn-sm btn-info">Restore</button> </a>
                                            </td>
                                        </tr>

                                        <%
                                            }
                                        %>

                                    </tbody>
                                </table>
                                <!--            <div class="col-sm-8" id="lbtn">
                                                <a href='/ProductController' class="btn btn-secondary" id="btl"><button>Back to List</button></a>  
                                            </div>-->
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
