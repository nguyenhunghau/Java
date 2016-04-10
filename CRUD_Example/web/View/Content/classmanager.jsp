<%-- 
    Document   : classmanager
    Created on : Apr 8, 2016, 10:53:58 PM
    Author     : root
--%>

<%@page import="DTO.User"%>
<%@page import="DTO.ClassStudy"%>
<%@page import="java.util.List"%>
<%@page import="DAO.ClassStudyDao"%>
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
            ClassStudyDao classStudyDao = new ClassStudyDao();
            HttpSession sessions = request.getSession();
            User user = (User)session.getAttribute("user");
            if(user == null){
                sessions.setAttribute("url", request.getRequestURI());
                response.sendRedirect("/CRUD_Example/faces/View/Content/login.jsp");
            }
            //Check ID to delete
            String strId = request.getParameter("ID");
            if(strId != null){
                classStudyDao.deleteClass(Integer.valueOf(strId));
            }
            List<ClassStudy> listClass = classStudyDao.getListClass("1");
            String strMessage = (String)sessions.getAttribute("message");
            if(strMessage == null){
                strMessage = "";
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
                                            <div class="form-group col-md-12">
                                                        <label class="col-md-12" ><%=strMessage %> </label>
                                            </div>
                                            <div class="row">
                                                <h3 style="text-align: center;color: red">Class manager</h3>
                                            </div>
                                            <div class = "row" style="overflow: hidden;">
                                                <table class="tbl_border" cellspacing="0px" cellpadding="5px" rules="all" border="1"
                                                    style="border-collapse: collapse;" width="100%" id="tblshow">
                                                    <thead>
                                                          <tr>
                                                        <td class="headertable" style="width: 10px;">
                                                            No
                                                        </td>
                                                        <td class="headertable">
                                                            Course Id
                                                        </td>
                                                        <td class="headertable">
                                                            Name
                                                        </td>
                                                        <td class="headertable">
                                                           Edit
                                                        </td >
                                                        <td class="headertable">
                                                            Delete
                                                        </td>
                                                    </tr>
                                                    </thead>
                                                  
                                                    <% 
                                                        
                                                            for(int i =0; i< listClass.size(); i++){
                                                                int no = i + 1;
                                                    %>
                                                            <tr>
                                                                <td align = "center">
                                                                    <%= no%>
                                                                </td>
                                                                <td align = "center">
                                                                    <%= listClass.get(i).getCourseId()%>
                                                                </td>
                                                                <td  align = "center">
                                                                    <%= listClass.get(i).getName()%>
                                                                </td>
                                                               
                                                                 <td align = "center">
                                                                     <a href= "<%= "updateclass.jsp?ID=" + listClass.get(i).getId() %>" ><img src="../../img/images/Edit.png" class = "img-edit" title="Edit" alt="" /></a>
                                                                </td>
                                                                <td align = "center">
                                                                    <a href= "<%= "classmanager.jsp?ID=" + listClass.get(i).getId() %>"><img src="../../img/images/delete.png" class = "img-edit" title ="Delete" onclick="return confirm('Are you sure?')"/></a>
                                                                </td>
                                                               
                                                            </tr>   
                                                    <%} %>
                                                </table>
                                            </div>
                                                
                                                <div class="row" style="margin-top:10px;margin-left: 20px;">
                                                <a href="/CRUD_Example/faces/View/Content/newstudent.jsp">Create new class</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div><!--kt_content right-->
       
    </body>
</html>
