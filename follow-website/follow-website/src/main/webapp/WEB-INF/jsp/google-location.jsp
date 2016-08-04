<%-- 
    Document   : google-location
    Created on : Aug 3, 2016, 8:52:41 PM
    Author     : Nguyen Hung Hau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Search google by location</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="js/location.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                Location.init();
            });
        </script>
    </head>
    <body>
        <%
            HttpSession sessions = request.getSession();
            if (session.getAttribute("account") == null) {
                response.sendRedirect("login.htm?url=google-location.htm");
            }
        %>
        <div class="container">

            <div class="form-horizontal" role="form" style="margin-top: 100px;">
                <div class="form-group">
                    <label class="control-label col-sm-2">Location:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="canonical" list="locations" placeholder="please choose location" autocomplete="off">
                        <datalist id="locations">
                        </datalist>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" >Keyword:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="keyword" placeholder="Enter keyword">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <div class="">
                            <p style="margin-left: 30%;font-weight: bold" id = "info"></p>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default" id = "btn_search">Search</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
