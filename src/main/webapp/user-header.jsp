<%-- 
    Document   : user-header
    Created on : Oct 31, 2023, 1:34:41 AM
    Author     : Nguyen Hoang Nha - CE170092
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="Daos.cate_ad"%>
<%@page import="java.util.List"%>
<%@page import="Model.CartItem"%>
<%@page import="Daos.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib  prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            cate_ad cateP = new cate_ad();
            ResultSet cate = cateP.getAll();
            ProductDAO pDAO = new ProductDAO();
            List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
            int totalItems = 0;

            if (cart != null) {
                for (CartItem item : cart) {
//                    totalItems += item.getQuantity();
                }
            }
        %>        
        <header class="header-area header-sticky">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <nav class="main-nav">
                            <!-- ***** Logo Start ***** -->
                            <a href="/UserHomeController" class="logo">
                                <img src="/resources/UserAssets/images/white-logo.png">
                            </a>
                            <!-- ***** Logo End ***** -->
                            <!-- ***** Menu Start ***** -->
                            <ul class="nav">
                                <li class="scroll-to-section"><a href="/UserHomeController/Products">Products</a></li>
                         
                               
                                
                                <li class="scroll-to-section"><a href="/UserCartController">Cart (<span class='text-danger'><%=totalItems%></span>)</a></li>
                                <c:if test="${sessionScope.acc != null}" >
                                    <li class="submenu">
                                        <a href="javascript:;">${sessionScope.fullname}</a>
                                        <ul>
                                            <li><a class='text-info' href="/UserHomeController/Profile/${sessionScope.user}">User Profile</a></li>
                                            <li><a href="/UserHomeController/Order">Order</a></li>
                                            <li><a class='text-info' href="/loginController/Logout" >Logout</a></li>
                                            <!--<li><a href="#">Log Out</a></li>-->
                                            <!--<li><a rel="nofollow" href="ttps://templatemo.com/page/4" target="_blank">Template Page 4</a></li>-->
                                        </ul>
                                    </li>
                                </c:if>
                                    <c:if test="${sessionScope.acc == null}">
                                    <li><a class='text-info' href="/loginController" class="btn btn-primary btn-block">Sign In</a></li></c:if>
                            </ul>
                            <a class='menu-trigger'>
                                <span>Menu</span>
                            </a>
                            <!-- ***** Menu End ***** -->
                        </nav>
                    </div>
                </div>
            </div>
        </header>
    </body>
</html>
