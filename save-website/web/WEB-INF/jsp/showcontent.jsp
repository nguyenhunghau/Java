<%-- 
    Document   : showcontent
    Created on : Apr 27, 2016, 5:05:35 PM
    Author     : Nguyen Hung Hau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="js/saveweb.js" type="text/javascript"></script>
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script>
        $(document).ready(function () {
            loadWeb();
        });
    </script>
    </head>
    
    <body>
        <jsp:include page="popupWindow/save.jsp"/>
    </body>
</html>
