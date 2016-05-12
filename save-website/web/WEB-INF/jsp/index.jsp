<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
    
        <title>Save website</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="js/saveweb.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script>
            $(document).ready( function (){
                $('#btn_save').click( function () {
                   //saveWebsite(''); 
                   setInterval(saveWebsite(''), 1000*60*60*24);
                });
                
                $('#btn_view').click( function () {
                    viewHistory();
                });
            });
        </script>
    
    <body>
        <jsp:include page="popupWindow/load.jsp"/>
        <jsp:include page="popupWindow/save.jsp"/>
        <jsp:include page="popupWindow/savewindow.jsp"/>
        
        <div class="container">
            <div class="row" style="margin-top: 50px;">
                <div class="col-md-1"></div>
                <div class="col-md-10 ">
                    <div class="row" style="height: 50px">
                        <div class = "col-md-2 col-sm-2">
                            <a href="/index.htm"><img src="images/logo.gif" alt="" class="img_logo"/></a>
                        </div>
                        <div class = "col-md-6  col-sm-6">
                            <input type="text" class="form-control" id="url">
                        </div>
                        <div class = "col-md-2  col-sm-2">
                            <button class="btn btn-main" id = "btn_save" style="width: 100%">Save page</button>
                        </div>
                        <div class = "col-md-2  col-sm-2">
                            <button class="btn btn-main" id = "btn_view" style="width: 100%">Browse History</button>
                        </div>

                    </div>
                    <div class = "row" style="color: red">
                        <p style="margin-left: 30%;font-weight: bold" id = "info"></p>
                    </div>

                    <div class="row" style="margin-left: 10px;font-size: 9pt;color: green" id = "browseHistory">
                       
                    </div>
                </div>
                <div class="col-md-1">

                </div>
            </div>
        </div>

    </body>
</html>