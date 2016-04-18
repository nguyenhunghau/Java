<%-- 
    Document   : newjsp
    Created on : Apr 12, 2016, 3:17:46 PM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
         <link href="Css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="Css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="Css/index.css" rel="stylesheet" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="js/jquery.js"></script>
        <link href="Css/main.css" rel="stylesheet" type="text/css"/>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap.js"></script>
        
        <script>
            function myFunction(){ 
                var username = document.getElementById('Username');
                var password = document.getElementById('Password');

                if (username.value == "" || password.value == "" ){  
                  alert("Please fill all information");  
                  return false;  
                }
            }
        </script>
    </head>
    <body>
          <jsp:include page = "/Share/header.jsp"></jsp:include>
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
                                        <form name =" myform" onsubmit="return myFunction();" action = "/CRUD_Example/LoginServlet" method="post">
                                            <div class="form-group col-md-12">
                                                <label class="col-md-12" ><%= strMessage %></label>
                                            </div>
                                            <div class="form-group col-md-12">
                                                <label class="col-md-3" >Username: </label>
                                                <div class="col-md-9">
                                                    <input type="text" class = "form-control" name = "Username" id = "Username"/>
                                                </div>
                                            </div>
                                            <div class="form-group col-md-12">
                                                <label class="col-md-3" >Password: </label>
                                                <div class="col-md-9">
                                                    <input type="password" class = "form-control" name = "Password" id = "Password"/>
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
