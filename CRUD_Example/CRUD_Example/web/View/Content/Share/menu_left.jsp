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
                                <li><a href="/CRUD_Example/studentmanager.jsp">List student</a></li>
                                <li><a href="/CRUD_Example/newstudent.jsp">Add new student</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                 <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordian" href="#class" class="collapsed">
                                <span class="badge pull-right"><i class="fa fa-plus"></i></span>
                                Class manager
                            </a>
                        </h4>
                    </div>
                    <div id="class" class="panel-collapse collapse" style="height: 0px;">
                        <div class="panel-body">
                            <ul>
                                <li><a href="/CRUD_Example/classmanager.jsp">Get list class</a></li>
                                <li><a href="/CRUD_Example/newclass.jsp">Add class</a></li>
                            </ul>
                        </div>
                    </div>
                 </div> 
                
            </div>
        </div>
    </body>
</html>
