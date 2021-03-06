<%-- 
    Document   : AdminHomepage
    Created on : Apr 23, 2018, 6:51:00 PM
    Author     : 1396582
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="Assets/CSS/table.css" rel="stylesheet" type="text/css"/>
        <link href="Assets/CSS/materialize.min.css" rel="stylesheet" type="text/css"/>
        <!--<link href="Assets/CSS/datatable.css" rel="stylesheet" type="text/css"/>-->
        <!--<link href="https://cdnjs.cloudflare.com/ajax/libs/material-design-lite/1.1.0/material.min.css" rel="stylesheet" type="text/css"/>-->
        <link href="https://cdn.datatables.net/1.10.16/css/dataTables.material.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%      response.setHeader("Cache-control", "no-store.no-cache,must-revalidate");
            response.addHeader("Cache-control", "post-check=0,precheck=0");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
//            if (session.getAttribute("regId") == null || !session.getAttribute("role").toString().equals("1")) {
//                response.sendRedirect("index.jsp");
            %>
        <nav>
            <div class="nav-wrapper">
                <a class="brand-logo right" href="#">Assignment Tracker</a>
                <ul class="nav navbar-nav">
                    <li class="active" id="assg"><a href="#">Upload Assignments</a></li>
                    <li id="view"><a href="#">View Assignments</a></li>
                    <li id="sub"><a href="#">Submissions</a></li>
                    <li  style="margin-left:10px;"><a href="MainServlet" id="logout">Logout</a></li>
                </ul>
            </div>
        </nav>
        <div class="container-fluid" id="add">
            <div class="row">
                <div class="col s4"></div>
                <div class="col s4 m4">
                    <h3 style="margin-left:40px;">Upload Assignment</h3>
                    <div class="card">
                        <div class="card-content">
                            <div class="input-field">
                                <input type="text" id="aname">
                                <label>Assignment Name</label>
                            </div>
                            <div class="input-field">
                                <input type="date" id="deadline">
                                <label>Deadline</label>
                            </div>
                            <div >
                                <label>Description</label>
                                <textarea id="desc" class="input-field"></textarea>
                            </div>
                            <div class="file-field input-field">
                                <div class = "btn">
                                    <span>Browse</span>
                                    <input type = "file" id="filePath"/>
                                </div>
                                <div class = "file-path-wrapper">
                                    <input class = "file-path validate" type = "text"
                                           placeholder = "Upload file" />
                                </div>

                            </div>
                            <div class="input-field">
                                <input type="text" id="issuer">
                                <label>Issued By</label>
                            </div>
                            <div class="input-field">
                                <a class="waves-effect waves-light btn" id="btnUpload">Upload</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

       

        <div class="container-fluid" id="assignments">
            <br>
            <table id="assgTable" cellspacing="0" width="100%" class="table table-striped table-bordered wow fadeInUp">
                <thead>
                    <tr>
                        <th>Assignment Name</th>
                        <th>Description</th>
                        <th>Deadline</th>
                        <th>Issued By</th>
                        <th>Assignment File</th>
                    </tr>
                </thead>
            </table>
        </div>
        
         <div class="container-fluid" id="submissions">
            
             <table id="assgSubTable" class="mdl-data-table striped" style="width:100%">
                <thead>
                    <tr>
                        <!--<th>S.No.</th>-->
                        <th>Assignment Name</th>
                        <th>Description</th>
                        <th>Deadline</th>
                        <th>Issued By</th>
                        <th>View</th>
                    </tr>
                </thead>
            </table>
            
        </div>

        
        <div class="container-fluid" id="assgSubmissions">
            
             <table id="assgSubmissionTable" class="mdl-data-table striped" style="width:100%">
                <thead>
                    <tr>
                        <!--<th>S.No.</th>-->
                        <th>Assignment Name</th>
                        <th>Submitted by</th>
                        <th>Download</th>
                    </tr>
                </thead>
            </table>
            
        </div>
        

    </body>
    <script src="Assets/JS/jquery-3.1.0.min.js" type="text/javascript"></script>
    <script src="Assets/JS/adminpage.js" type="text/javascript"></script>
    <script src="Assets/JS/materialize.min.js" type="text/javascript"></script>
    <script src="Assets/JS/table.js" type="text/javascript"></script>
    <script src="Assets/JS/datatable.js" type="text/javascript"></script>
    <!--<script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js" type="text/javascript"></script>-->
    <script src="https://cdn.datatables.net/1.10.16/js/dataTables.material.min.js" type="text/javascript"></script>
</html>
