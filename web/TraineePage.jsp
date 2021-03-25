<%-- 
    Document   : TraineePage
    Created on : Jun 5, 2018, 2:36:01 PM
    Author     : 1396582
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="Assets/CSS/materialize.min.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/material-design-lite/1.1.0/material.min.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.datatables.net/1.10.16/css/dataTables.material.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%      response.setHeader("Cache-control", "no-store.no-cache,must-revalidate");
            response.addHeader("Cache-control", "post-check=0,precheck=0");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
//            if (session.getAttribute("regId") == null || !session.getAttribute("role").toString().equals("2")) {
//                response.sendRedirect("index.jsp");
             %>
        <nav>
            <div class="nav-wrapper">
                <a class="brand-logo right" href="#">Assignment Tracker</a>
                <ul class="nav navbar-nav">
                    <li><h5><%=session.getAttribute("user")%></h5></li>
                    <li  style="margin-left:10px;margin-top:23px"><a href="MainServlet" id="logout">Logout</a></li>
                </ul>
            </div>
        </nav>
        <table id="traineeAssignment" class="mdl-data-table striped" style="width:100%">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Deadline</th>
                    <th>Issued By</th>
                    <th>Upload</th>
                </tr>
            </thead>
        </table>
    </body>
    <script src="Assets/JS/jquery-3.1.0.min.js" type="text/javascript"></script>
    <script src="Assets/JS/traineepage.js" type="text/javascript"></script>
    <script src="Assets/JS/materialize.min.js" type="text/javascript"></script>
    <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js" type="text/javascript"></script>
    <script src="https://cdn.datatables.net/1.10.16/js/dataTables.material.min.js" type="text/javascript"></script>
</html>
