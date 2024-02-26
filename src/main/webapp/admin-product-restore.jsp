<%-- 
    Document   : DeletedProductList
    Created on : Oct 31, 2023, 12:29:12 AM
    Author     : C15TQK
--%>

<%@page import="Daos.ProductDAO"%>
<%@page import="java.sql.ResultSet"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Restore</title>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css"/>
        <script src="https://code.jquery.com/jquery-3.7.0.js"></script>
        <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
        <link rel="stylesheet" href="resources/bootstrap.css"/>
        <link rel="stylesheet" href="/resources/bootstrap.min.css"/>
        <link rel="stylesheet" href="/resources/list.css"/>
        <script>
            $(document).ready(function () {
                $('#example').DataTable();
            });
        </script>

    </head>
    <style>
        
    </style>
    <body>
        <div class="container">

            <h1>Product Restore</h1>

            <table id="example" class="display" style="width:100%" border="1 cellpadding=12 cellspacing=12">
                <thead>
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Product Description</th>
                <th>Product Price</th>
                <th>Product Quantity</th>
                <th>Product Size</th>
                <th>Product Picture</th>
                <th>Categories ID</th>
                <th>Brand ID</th>
                <th>Color</th>
                <th></th>
                </thead>
                <tbody>
                    <%
                        ProductDAO cDAO = new ProductDAO();
                        ResultSet rs = cDAO.getAllDeletedList();
                        while (rs.next()) {

                    %>
                    <tr>
                        <td><%= rs.getInt("ProID")%></td>
                        <td><%= rs.getString("ProName")%></td>
                        <td><%= rs.getString("Description")%></td>
                        <td><%=  rs.getInt("Price")%></td>
                        <td><%= rs.getInt("Quantity")%></td>
                        <td><%= rs.getInt("Size")%></td>
                        <td>
                            <!-- Hiển thị ảnh -->
                            <img src="/resources/images/<%=rs.getString("Image")%>" alt="Product Image" width="100px" height="100px">
                        </td>
                        <td><%= rs.getString("CateName")%></td>
                        <td><%= rs.getString("BrandName")%></td>
                        <td><%= rs.getString("Color")%></td>
                        <td>
                            <a href="/ProductController/Restore/<%=rs.getString("ProID")%>"><button>Restore</button> </a>
                        </td>
                    </tr>

                    <%

                        }
                    %>

                </tbody>
            </table>
            <div class="col-sm-8" id="lbtn">
                <a href='/ProductController' class="btn btn-secondary" id="btl"><button>Back to List</button></a>  
            </div>
        </div>
    </body>
    <script>new DataTable('#example');</script>
</html>
