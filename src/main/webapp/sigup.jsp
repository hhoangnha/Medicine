<%-- 
    Document   : sigup
    Created on : Oct 31, 2023, 1:23:19 AM
    Author     : Ngo Phuc Vinh - CE170573
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib  prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Register</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900|Material+Icons'><link rel="stylesheet" href="./style.css">
        <style>
            html {
                width: 100%;
                height: 100%;
            }
            body {
                background: linear-gradient(45deg, rgba(66, 183, 245, 0.8) 0%, rgba(66, 245, 189, 0.4) 100%);
                color: rgba(0, 0, 0, 0.6);
                font-family: "Roboto", sans-serif;
                font-size: 14px;
                line-height: 1.6em;
                -webkit-font-smoothing: antialiased;
                -moz-osx-font-smoothing: grayscale;
            }
            .overlay, .form-panel.one:before {
                position: absolute;
                top: 0;
                left: 0;
                display: none;
                background: rgba(0, 0, 0, 0.8);
                width: 100%;
                height: 100%;
            }
            .form {
                z-index: 15;
                position: relative;
                background: #FFFFFF;
                width: 600px;
                border-radius: 4px;
                box-shadow: 0 0 30px rgba(0, 0, 0, 0.1);
                box-sizing: border-box;
                margin: 100px auto 10px;
                overflow: hidden;
            }
            .form-toggle {
                z-index: 10;
                position: absolute;
                top: 60px;
                right: 60px;
                background: #FFFFFF;
                width: 60px;
                height: 60px;
                border-radius: 100%;
                -webkit-transform-origin: center;
                transform-origin: center;
                -webkit-transform: translate(0, -25%) scale(0);
                transform: translate(0, -25%) scale(0);
                opacity: 0;
                cursor: pointer;
                -webkit-transition: all 0.3s ease;
                transition: all 0.3s ease;
            }
            .form-toggle:before, .form-toggle:after {
                content: '';
                display: block;
                position: absolute;
                top: 50%;
                left: 50%;
                width: 30px;
                height: 4px;
                background: #4285F4;
                -webkit-transform: translate(-50%, -50%);
                transform: translate(-50%, -50%);
            }
            .form-toggle:before {
                -webkit-transform: translate(-50%, -50%) rotate(45deg);
                transform: translate(-50%, -50%) rotate(45deg);
            }
            .form-toggle:after {
                -webkit-transform: translate(-50%, -50%) rotate(-45deg);
                transform: translate(-50%, -50%) rotate(-45deg);
            }
            .form-toggle.visible {
                -webkit-transform: translate(0, -25%) scale(1);
                transform: translate(0, -25%) scale(1);
                opacity: 1;
            }
            .form-group {
                display: -webkit-box;
                display: flex;
                flex-wrap: wrap;
                -webkit-box-pack: justify;
                justify-content: space-between;
                margin: 0 0 20px;
            }
            .form-group:last-child {
                margin: 0;
            }
            .form-group label {
                display: block;
                margin: 0 0 10px;
                color: rgba(0, 0, 0, 0.6);
                font-size: 12px;
                font-weight: 500;
                line-height: 1;
                text-transform: uppercase;
                letter-spacing: .2em;
            }
            .two .form-group label {
                color: #FFFFFF;
            }
            .form-group input {
                outline: none;
                display: block;
                background: rgba(0, 0, 0, 0.1);
                width: 100%;
                border: 0;
                border-radius: 4px;
                box-sizing: border-box;
                padding: 12px 20px;
                color: rgba(0, 0, 0, 0.6);
                font-family: inherit;
                font-size: inherit;
                font-weight: 500;
                line-height: inherit;
                -webkit-transition: 0.3s ease;
                transition: 0.3s ease;
            }
            .form-group input:focus {
                color: rgba(0, 0, 0, 0.8);
            }
            .two .form-group input {
                color: #FFFFFF;
            }
            .two .form-group input:focus {
                color: #FFFFFF;
            }
            .form-group button {
                outline: none;
                background: #4285F4;
                width: 100%;
                border: 0;
                border-radius: 4px;
                padding: 12px 20px;
                color: #FFFFFF;
                font-family: inherit;
                font-size: inherit;
                font-weight: 500;
                line-height: inherit;
                text-transform: uppercase;
                cursor: pointer;
            }
            .two .form-group button {
                background: #FFFFFF;
                color: #4285F4;
            }
            .form-group .form-remember {
                font-size: 12px;
                font-weight: 400;
                letter-spacing: 0;
                text-transform: none;
            }
            .form-group .form-remember input[type='checkbox'] {
                display: inline-block;
                width: auto;
                margin: 0 10px 0 0;
            }
            .form-group .form-recovery {
                color: #4285F4;
                font-size: 12px;
                text-decoration: none;
            }
            .form-panel {
                padding: 60px calc(5% + 60px) 60px 60px;
                box-sizing: border-box;
            }
            .form-panel.one:before {
                content: '';
                display: block;
                opacity: 0;
                visibility: hidden;
                -webkit-transition: 0.3s ease;
                transition: 0.3s ease;
            }
            .form-panel.one.hidden:before {
                display: block;
                opacity: 1;
                visibility: visible;
            }
            .form-panel.two {
                z-index: 5;
                position: absolute;
                top: 0;
                left: 95%;
                background: #4285F4;
                width: 100%;
                min-height: 100%;
                padding: 60px calc(10% + 60px) 60px 60px;
                -webkit-transition: 0.3s ease;
                transition: 0.3s ease;
                cursor: pointer;
            }
            .form-panel.two:before, .form-panel.two:after {
                content: '';
                display: block;
                position: absolute;
                top: 60px;
                left: 1.5%;
                background: rgba(255, 255, 255, 0.2);
                height: 30px;
                width: 2px;
                -webkit-transition: 0.3s ease;
                transition: 0.3s ease;
            }
            .form-panel.two:after {
                left: 3%;
            }
            .form-panel.two:hover {
                left: 93%;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            }
            .form-panel.two:hover:before, .form-panel.two:hover:after {
                opacity: 0;
            }
            .form-panel.two.active {
                left: 10%;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
                cursor: default;
            }
            .form-panel.two.active:before, .form-panel.two.active:after {
                opacity: 0;
            }
            .form-header {
                margin: 0 0 40px;
            }
            .form-header h1 {
                padding: 4px 0;
                color: #4285F4;
                font-size: 24px;
                font-weight: 700;
                text-transform: uppercase;
            }
            .two .form-header h1 {
                position: relative;
                z-index: 40;
                color: #FFFFFF;
            }
            .pen-footer {
                display: -webkit-box;
                display: flex;
                -webkit-box-orient: horizontal;
                -webkit-box-direction: normal;
                flex-direction: row;
                -webkit-box-pack: justify;
                justify-content: space-between;
                width: 600px;
                margin: 20px auto 100px;
            }
            .pen-footer a {
                color: #FFFFFF;
                font-size: 12px;
                text-decoration: none;
                text-shadow: 1px 2px 0 rgba(0, 0, 0, 0.1);
            }
            .pen-footer a .material-icons {
                width: 12px;
                margin: 0 5px;
                vertical-align: middle;
                font-size: 12px;
            }
            .cp-fab {
                background: #FFFFFF !important;
                color: #4285F4 !important;
            }
            .error {
                color: red;
            }
        </style>
        <script type="text/javascript">


            var patt_phone = /^(0|84)[2-9]\d{8}$/;
            var patt_password = /^(?=.*[A-Z])(?=.*[!@#$%^&*])(?=.*\d)[A-Za-z\d!@#$%^&*]{8,}$/;
            var patt_email = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

            function check() {
                phone = document.getElementById("txtphone").value;
                pass = document.getElementById("user-pass").value;
                email = document.getElementById("txtemail").value;
                repass = document.getElementById("user-repass").value;

                if (patt_password.test(pass) == false) {
                    document.getElementById("txtPassMessage").innerHTML = "Password includes 8 characters (Including uppercase letters, lowercase letters, numbers and characters)";
                    return false;
                } else {
                    document.getElementById("txtPassMessage").innerHTML = "";
                }
                if (patt_email.test(email) == false) {
                    document.getElementById("txtEmailMessage").innerHTML = "Email is invalid, Please Enter Email Again";
                    return false;
                } else {
                    document.getElementById("txtEmailMessage").innerHTML = "";
                }
                if (patt_phone.test(phone) == false) {
                    document.getElementById("txtPhoneMessage").innerHTML = "Phone Number is invalid, Please Enter Phone Number Again";
                    return false;
                } else {
                    document.getElementById("txtPhoneMessage").innerHTML = "";
                }
                if (pass != repass) {
                    document.getElementById("txtRepass").innerHTML = "Confim pass incorrect";
                    return false;
                } else {
                    document.getElementById("txtRepass").innerHTML = "";
                }
                return true;
            }
        </script>

    </head>
    <body>
        <!-- partial:index.partial.html -->
        <!-- Form-->
        <div class="form">
            <div class="form-toggle"></div>
            <div class="form-panel one">
                <div class="form-header">
                    <h1>Register Account</h1>
                </div>
                <form action="/SigUpController" method="post" class="form-signup" onsubmit="return check()">
                    <div class="text-danger">${trung}</div>
                    <div class="form-group">
                        <label for="fullname">FullName</label>
                        <input name="fullname" type="text" id="user-fullname" required="required"/>
                    </div>
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input name="user" type="text" id="user-name" required="required"/>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input name="pass" type="password" id="user-pass"  required="required"/>
                        <!--<div class="error" id="txtPassMessage"></div>-->
                    </div>
                    <div class="form-group">
                        <label for="cpassword">Confirm Password</label>
                        <input name="repass" type="password" id="user-repass" required="required"/>
                        <div class="error" id="txtRepass"></div>
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input name="email" type="text" id="txtemail"  required/>
                        <div class="error" id="txtEmailMessage"></div>
                    </div>
                                        <div class="form-group">
                        <label for="phone">Phone</label>
                        <input name="phone" type="text" id="txtphone"  required/>
                        <div class="error" id="txtPhoneMessage"></div>
                    </div>
                    <div class="form-group">
                        <label for="address">Address</label>
                        <input name="address" type="text" id="txtphone"  required/>
                        <!--<div class="error" id="txtPhoneMessage"></div>-->
                    </div>
                    <div class="form-group">
                        <label for="birthday">Birthday</label>
                        <input name="birthday" type="date" id="txtphone"  required/>
                        <!--<div class="error" id="txtPhoneMessage"></div>-->
                    </div>
                    <div class="form-group">
                        <button type="submit" name="register">Register</button>
                    </div>
                </form>
                <h6 class="text-success" style="padding-top: 24px;text-align: center;">${mess}</h6>
            </div>
            <!--            <div class="form-panel two">-->
            <!--                <div class="form-header">
                                <h1>Register Account</h1>
                            </div>-->
            <!--</div>-->
        </div>
    </div>
    <div class="pen-footer"><a href="/loginController"><i class="material-icons">arrow_backward</i>Back Login</a><a href="#" target="_blank">Contact ?<i class="material-icons">arrow_forward</i></a></div>
    <!-- partial -->

    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
    <script src='https://codepen.io/andytran/pen/vLmRVp.js'></script><script src="./script.js"></script>
</body>
</html>
