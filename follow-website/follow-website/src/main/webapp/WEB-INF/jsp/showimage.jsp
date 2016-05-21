<%-- 
    Document   : checkurl
    Created on : May 5, 2016, 9:43:46 PM
    Author     : Nguyen Hung Hau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <script src="js/saveweb.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>

    <script>
        $(document).ready(function () {
            var image = getParameter("url");
            $('#img_capture').attr("src",image);
        });
    </script>
    <body>
        <%
            HttpSession sessions = request.getSession();
            if(session.getAttribute("account") == null)
                response.sendRedirect("login.htm");
        %>
        <div class = "container">
            <div class="row">
                <img id = "img_capture" style="width: 100%;height: 100%;margin-top: 10px;margin-bottom: 10px;"/>
            </div>
        </div>
    </body>
</html>
