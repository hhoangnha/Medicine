<!DOCTYPE html>
<html lang="en">

    
<%@page import="Daos.userad_ad"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Model.UserModel"%>
<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>User</title>
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

</head>

<body>
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
              <h5 class="card-title">Restore user</h5>
             
              <!-- Table with stripped rows -->
              <table class="table datatable">
                <thead>
                      <tr>
                                            <th>Username</th>
                                            <th>Fullname</th>
                                            <th>Phone</th>
                                            <th>Address</th>
                                            <th>Birthday</th>
                                            <th></th>

                                        </tr>
                </thead>
                <tbody>
                    
                    <% //CusDAO cd = new CusDAO();
                        userad_ad d = new userad_ad();
                        ResultSet rs = d.getAll("customer",0);

                        while (rs.next()) {
                    %>
                    
                    
                        <tr>
                           <tr>
                                            <td><%=  rs.getString("Username")%></td>
                                            <td><%= rs.getString("Fullname")%> </td>

                                            <td><%=  rs.getString("Phone")%></td>
                                            <!-- comment -->
                                            <td><%=  rs.getString("Address")%></td>
                                            <!-- comment -->
                                            <td><%=  rs.getDate("Birthday")%></td>
                            
                                            <td><a style="color:white" class="btn bg-primary btn-sm "  onclick="return confirm('Are you sure? User will restore');" href="/AdminController/Users/Restore/<%= rs.getString("username")%>" >Restore</a> <br></td><!-- comment -->
                              


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