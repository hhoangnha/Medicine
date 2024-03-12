<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Import Results</title>
        <link href="/resources/AdminAssets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="/resources/AdminAssets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="/resources/AdminAssets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="/resources/AdminAssets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="/resources/AdminAssets/vendor/quill/quill.snow.css" rel="stylesheet">
        <link href="/resources/AdminAssets/vendor/quill/quill.bubble.css" rel="stylesheet">
        <link href="/resources/AdminAssets/vendor/remixicon/remixicon.css" rel="stylesheet">
        <link href="/resources/AdminAssets/vendor/simple-datatables/style.css" rel="stylesheet">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container">
                <a class="navbar-brand" href="/ProductController"> <i class="bi bi-arrow-left"></i> Back to product</a>
            </div>
        </nav>

        <div class="container">
            <h2>Add new Products:</h2>
            <ul>
                <%
                    List<String> successfulProducts = (List<String>) session.getAttribute("successfulProducts");
                    if (successfulProducts != null) {
                        for (String product : successfulProducts) {
                %>
                <li><%= product%></li>
                    <%
                            }
                        }
                    %>
            </ul>

            <h2>Modify Products:</h2>
            <ul>
                <%
                    List<String> updateProducts = (List<String>) session.getAttribute("updateProducts");
                    if (updateProducts != null) {
                        for (String product : updateProducts) {
                %>
                <li><%= product%></li>
                    <%
                            }
                        }
                    %>
            </ul>

            <h2>Failed Products:</h2>
            <ul>
                <%
                    List<String> failedProducts = (List<String>) session.getAttribute("failedProducts");
                    if (failedProducts != null) {
                        for (String product : failedProducts) {
                %>
                <li><%= product%></li>
                    <%
                            }
                        }
                    %>
            </ul>

            <%
                session.removeAttribute("failedProducts");
                session.removeAttribute("updateProducts");
                session.removeAttribute("successfulProducts");

            %>


        </div>
    </body>
</html>
