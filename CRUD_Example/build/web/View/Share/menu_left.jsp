<%-- 
    Document   : menu_left
    Created on : Apr 4, 2016, 4:47:50 PM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../../Css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="left-sidebar">
    <h2>Menu</h2>
    <div class="panel-group category-products" id="accordian">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordian" href="#newstudent" class="collapsed">
                        <span class="badge pull-right"><i class="fa fa-plus"></i></span>
                        Student manager
                    </a>
                </h4>
            </div>
            <div id="newstudent" class="panel-collapse collapse" style="height: 0px;">
                <div class="panel-body">
                    <ul>
                        <li><a href="/CRUD_Example/faces/View/Content/studentmanager.jsp">List student</a></li>
                        <li><a href="/CRUD_Example/faces/View/Content/newstudent.jsp">Add new student</a></li>
                    </ul>
                </div>
            </div>
        </div>
        
         <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordian" href="#score" class="collapsed">
                        <span class="badge pull-right"><i class="fa fa-plus"></i></span>
                        Score manager
                    </a>
                </h4>
            </div>
            <div id="score" class="panel-collapse collapse" style="height: 0px;">
                <div class="panel-body">
                    <ul>
                        <li><a href="">Add score</a></li>
                        <li><a href="">Update score</a></li>
                        <li><a href="">Get final gradle</a></li>
                    </ul>
                </div>
            </div>
         </div> 
         <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordian" href="#subject" class="collapsed">
                        <span class="badge pull-right"><i class="fa fa-plus"></i></span>
                        Subject manager
                    </a>
                </h4>
            </div>
            <div id="subject" class="panel-collapse collapse" style="height: 0px;">
                <div class="panel-body">
                    <ul>
                        <li><a href="">Add subect</a></li>
                        <li><a href="">Update subject</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div><!--/category-products-->
</div>
    </body>
</html>
