<%-- 
    Document   : index
    Created on : May 25, 2018, 2:46:15 PM
    Author     : 1396582
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="Assets/CSS/materialize.min.css" rel="stylesheet" type="text/css"/>
        <link href="Assets/CSS/index.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body onbeforeunload="noBack()">
        <nav>
            <div class="nav-wrapper">
                <a href="#" class="brand-logo right">Assignment Tracker</a>
                <ul class="left hide-on-med-and-down">
                    <li id="reg"><a href="#" class="active">Register</a></li>
                    <li id="log"><a href="#">Login</a></li>
                </ul>
            </div>
        </nav>

        <div class="row" id="register">
            <div class="col s4"></div>
            <div class="col s4 m4">
                <h3>Register</h3>
                <div class="card card1">
                    <div class="card-content">
                        <div class="input-field">
                            <input type="text" id="username">
                            <label>Username</label>
                        </div>
                        <div class="input-field">
                            <input type="email" id="email">
                            <label>Email</label>
                        </div>
                        <div class="input-field">
                            <input type="password" id="password">
                            <label>Password</label>
                        </div>
                        <div class="input-field">
                            <input type="password" id="cnpw">
                            <label>Confirm Password</label>
                        </div>
                        <div class="input-field">
                            <a class="waves-effect waves-light btn" id="registerButton">Register</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row" id="login">
            <div class="col s4"></div>
            <div class="col s4 m4">
                <h3>Login</h3>
                <div class="card card1">
                    <div class="card-content">
                        <div class="input-field s3">
                            <input type="text" id="loginUsername">
                            <label>Username</label>
                        </div>

                        <div class="input-field">
                            <input type="password" id="loginPassword">
                            <label>Password</label>
                        </div>
                        <div class="input-field">
                            <a class="waves-effect waves-light btn" id="loginButton">Login</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>


    </body>
    <script src="Assets/JS/jquery-3.1.0.min.js" type="text/javascript"></script>
    <script src="Assets/JS/materialize.min.js" type="text/javascript"></script>
    <script src="Assets/JS/index.js" type="text/javascript"></script>
    <script>


    </script>
</html>
