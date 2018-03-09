<%--
  Created by IntelliJ IDEA.
  User: Artem
  Date: 18.02.2018
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <%@include file="header.jsp"%>
    <title>$Title$</title>
</head>
<body>
<%@include file="top_menu.jsp"%>


<div class="container-fluid">
    <div class="row">
        <%@include file="left_menu.jsp"%>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
                <h1 class="h2">Upload Pump types DB</h1>
            </div>

            Select a file to upload: <br />
            <form action = "UploadPumpDbServlet" method = "post"
                  enctype = "multipart/form-data"
                  target="pump_db_loader"
            >
                <input type = "file" name = "file" size = "50" />
                <br />
                <input type = "submit" value = "Upload File" />
            </form>

            <iframe id="pump_db_loader" name="pump_db_loader"></iframe>

        </main>
    </div>
</div>

</body>
</html>
