<%-- 
    Document   : newclass
    Created on : Apr 8, 2016, 11:09:58 PM
    Author     : root
--%>

<%@page import="DTO.User"%>
<%@page import="DTO.Course"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="DAO.CourseDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            function myFunction(){ 
            var name=document.getElementById('Name');
            
            if (name.value == ""){  
              alert("Please fill name of class");  
              return false;  
            }
            }
        </script>
    </head>
    <body>
        <jsp:include page = "../Share/header.jsp"></jsp:include>
        <% 
            CourseDao courseDao = new CourseDao();
            List<Course> listCourse = courseDao.getListCourse();
            HttpSession sessions = request.getSession();
            User user = (User)session.getAttribute("user");
            if(user == null){
                sessions.setAttribute("url", request.getRequestURI());
                response.sendRedirect("/CRUD_Example/faces/View/Content/login.jsp");
            }
        %>
        <div class="container div-content">
            <div class="row">
                <div class="col-md-3 menu_left">
                    <jsp:include page = "../Share/menu_left.jsp"></jsp:include>
                </div>
                <div class="col-md-9 ">
                    <!--content_right-->
                    <div class="row content-right">
                        <div class="col-sm-12 col-md-12 col-lg-12">
                            <div class="row">
                                <div class="col-sm-12 col-md-12 col-lg-12">
                                    <div class="row">
                                        <div class="col-sm-12 col-md-12 col-lg-12 list-student">
                                            <div class="row">
                                                <form name =" myform" onsubmit="return myFunction();" action = "../../addClass" method="post"  />
                                                    
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Name: </label>
                                                            <div class="col-md-9">
                                                                <input type="text" class = "form-control" placeholder ="Name" name = "Name" id="Name"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Course: </label>
                                                            <div class="col-md-9">
                                                                <select class = "form-control" name = "CourseId">
                                                                    <%
                                                                       for(int i = 0; i < listCourse.size(); i++){ 
                                                                    %>

                                                                       <option value = "<%=listCourse.get(i).getId() %>"><%=listCourse.get(i).getYearBegin() + " - " + listCourse.get(i).getYearEnd() %></option>

                                                                    <%   }
                                                                   %>

                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <button type="submit"  class="btn div-submit-index">Add new class</button>
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
            </div>
        </div>  
    </body>
</html>
