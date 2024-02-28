<%-- 
    Document   : addnewUnit
    Created on : 24-Feb-2024, 21:46:07
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900|Material+Icons'><link rel="stylesheet" href="./style.css">
    </head>
    <body>
        <div id="formProduct">
            <form id="formCreateUnit" method="post" action="UnitController">
                <div class="input-control">
                    <label>UnitID</label>
                    <input type="number" id="UnitID" name="UnitID" required=""/>
                </div>
                <div class="input-control">
                    <label>UnitName</label>
                    <input type="text" id="UnitName" name="UnitName" required=""/>
                </div>
                <input type="submit" name="btnAddUnit" value="Create"/>
            </form>
        </div>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script src='https://codepen.io/andytran/pen/vLmRVp.js'></script><script src="./script.js"></script>
    </body>
</html>
