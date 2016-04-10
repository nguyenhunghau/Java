<%-- 
    Document   : loginsuccess
    Created on : Apr 10, 2016, 12:14:19 PM
    Author     : 12121_000
--%>

<%@page import="DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <%
            HttpSession sessions = request.getSession();
            User user  = (User)sessions.getAttribute("user");
        %>
        <div class="col-sm-3 col-md-3 col-lg-3">
        <!-- nguoi dung-->
            <ul class="nav nav-pills">
                <li class="dropdown">
                    <a href="#" class="">
                        <img src="../../img/icon_login.gif" />
                        <%=user.getStrUsername()%>
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu" >
                        <li>
                            <a href="/CRUD_Example/View/Content/login.jsp">
                                <img src="../../img/images/icon_logout.gif" style="margin:0px 10px 0px -10px" />
                                Logout
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </body>
</html>
