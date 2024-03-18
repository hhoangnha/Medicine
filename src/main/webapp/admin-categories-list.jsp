<%@page import="java.util.List"%>
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
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>User</title>
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
        <style>
            .CateNameColumn{
                width: 15%;
            }
        </style>
        <%
            // Lấy dữ liệu từ session
            String msgSuccess = (String) session.getAttribute("msgSuccess");
            if (msgSuccess != null) {
        %>
        <script>
            // Sử dụng SweetAlert để hiển thị thông báo
//            alertify.success("Thành công");
        </script>
        <%
                // Xóa thông báo sau khi hiển thị
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
                        <li class="breadcrumb-item active">Category</li>
                    </ol>
                </nav>
            </div><!-- End Page Title -->

            <section class="section">
                <div class="row">
                    <div class="col-lg-12">

                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Category</h5>
                                <!--<span>All data will show in User page</span>-->
                                <br/>
                                <a href="/AdminCateController/Create" class="btn btn-primary ">Add new</a>
                                <!-- Table with stripped rows -->
                                <table class="table datatable">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th class="CateNameColumn">Name</th>
                                            <th>Description</th>

                                            <th></th>
                                            <th></th>

                                        </tr>
                                    </thead>
                                    <tbody>

                                        <% //CusDAO cd = new CusDAO();
                                            cate_ad d = new cate_ad();
                                            List<CategorieModel> listCategory = d.getListCateModel();

                                            // list<user> lu = d.getAdminUser("user");
                                            for(int i = 0; i < listCategory.size(); i++) {
                                        %>

                                        <tr>
                                            <td><%=listCategory.get(i).getCateID()%></td>
                                            <td class="CateNameColumn"><%=listCategory.get(i).getCateName()%> </td>

                                            <td><%=listCategory.get(i).getCateDescription()%></td>


                                            <td style="width: 20%; text-align: center">
                                                <a style="color:white" class="btn bg-primary btn-sm " href="/AdminCateController/Edit/<%=listCategory.get(i).getCateID()%>" >Edit</a>
                                                <a style="color:white" onclick="return confirm('Are you sure? Category can not restore');" class="btn bg-danger btn-sm " href="/AdminCateController/Delete/<%=listCategory.get(i).getCateID()%>" >Delete</a> <br>  



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