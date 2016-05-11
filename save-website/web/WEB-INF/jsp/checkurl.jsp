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
            initdialog();
        });
    </script>
    <body>
        <jsp:include page="popupWindow/save.jsp"/>
        <div id="popup_save_website_related" class="container" style=" left: 20%; min-height: 150px;">
            <div class="row">
                <a id = "link"><p class="info" id = "p_link"  style="margin-left: 10px" ></p></a>
            </div>
            <div class="row">
                <p class="info" id = "notsave">This url is not yet saved. Do you want to save this url?</p>
            </div>

            <div class="row">
                <div class="col-md-3 col-sm-3"></div>
                <div class="col-md-3 col-sm-3">
                    <div class="button">
                        <a id="save" class="btn btn-main">Save</a>
                    </div>
                </div>
                <div class="col-md-3 col-sm-3">
                    <div class="button">
                        <a id="cancel" class="btn btn-main">Cancel</a>
                    </div>
                </div>
                <div class="col-md-3"></div>
            </div>
        </div>
        <div id="overlay"></div>
    </body>
</html>
