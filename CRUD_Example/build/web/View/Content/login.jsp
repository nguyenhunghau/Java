<%-- 
    Document   : login
    Created on : Apr 8, 2016, 4:12:00 PM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
          <jsp:include page = "../Share/header.jsp"></jsp:include>
        <% 
            HttpSession sessions = request.getSession();
            sessions.setAttribute("user", null);
            String strMessage = request.getParameter("error");
            if(strMessage != null)
                strMessage = "Username or password is incorrect";
            else
                strMessage = "";
        %>
        <div class="container div-content">
            <div class="row">
                <div class="col-md-12">
                    <!--content_right-->
                    <div class="row div-login">
                        <div class="col-sm-12 col-md-12 col-lg-12">
                            <div class="row">
                                <div class="col-sm-12 col-md-12 col-lg-12 ">
                                    <div class="row">
                                        <form action = "../../LoginServlet" method="post">
                                            <div class="form-group col-md-12">
                                                <label class="col-md-12" ><%= strMessage %></label>
                                            </div>
                                            <div class="form-group col-md-12">
                                                <label class="col-md-3" >Username: </label>
                                                <div class="col-md-9">
                                                    <input type="text" class = "form-control" name = "Username"/>
                                                </div>
                                            </div>
                                            <div class="form-group col-md-12">
                                                <label class="col-md-3" >Password: </label>
                                                <div class="col-md-9">
                                                    <input type="password" class = "form-control" name = "Password"/>
                                                </div>
                                            </div>
                                             <div>
                                                <button type="submit"  class="btn div-submit-index">Login</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
