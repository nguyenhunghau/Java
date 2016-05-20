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
        $(document).ready(function () {
            $('#schedule').hide();
            $('#btn_save').click(function () {
                saveWebsite('','#html','#capture');
            });

            $('#btn_view').click(function () {
                viewHistory();
            });

            $('#btn_change').click(function () {
                changeSchedule('');
            });
        });
    </script>

    <body>
        <%
            HttpSession sessions = request.getSession();
            if(session.getAttribute("account") == null)
                response.sendRedirect("login.htm");
        %>
        <jsp:include page="popupWindow/load.jsp"/>
        <jsp:include page="popupWindow/save.jsp"/>
        <jsp:include page="popupWindow/savewindow.jsp"/>

        <div class="container">
            <div class="row" style="margin-top: 50px;padding: 20px;">
                <div class="row">
                    <div class = "col-md-2 col-sm-2">
                        <a href="/index.htm"><img src="images/logo.gif" alt="" class="img_logo"/></a>
                    </div>
                    <div class = "col-md-4  col-sm-4">
                        <input type="text" class="form-control" id="url">
                    </div>
                    <div class = "col-md-2  col-sm-2" style="margin-top: 5px">
                        <label class="checkbox-inline"><input type="checkbox" id="html" checked>html</label>
                        <label class="checkbox-inline"><input type="checkbox" id="capture" checked>capture</label>
                    </div>
                    <div class = "col-md-2  col-sm-2">
                        <button class="btn btn-main" id = "btn_save" style="width: 100%;margin-bottom: 10px">Save page</button>
                    </div>
                    <div class = "col-md-2  col-sm-2">
                        <button class="btn btn-main" id = "btn_view" style="width: 100%">Browse History</button>
                    </div>

                </div>
                <div class = "row" style="color: red">
                    <p style="margin-left: 30%;font-weight: bold" id = "info"></p>
                </div>
                <div class = "row" id = "schedule">
                    <div class="col-md-2 col-sm-2">
                        <p style="text-align: center"><strong>Frequency</strong></p>
                    </div>
                    <div class="col-md-2 col-sm-2">
                        <input type = "hidden" id="urlHidden"/>
                        <select id = "frequency" class = "form-control">
                            <option value="1">1 day</option>
                            <option value="2">2 days</option>
                            <option value="3">3 days</option>
                            <option value="7">1 week</option>
                            <option value="10">10 days</option>
                            <option value="15">15 days</option>
                            <option value="30">1 month</option>
                        </select>
                    </div>
                    <div class = "col-md-2 col-sm-2">
                        <button class="btn btn-primary btn-sm" id = "btn_change" style="width: 100%;">Save change</button>
                    </div>
                </div>
                <div class="row" style="margin-left: 10px;font-size: 9pt;color: green" id = "browseHistory">

                </div>
            </div>
        </div>
    </body>
</html>