<%-- 
    Document   : Index
    Created on : Apr 4, 2016, 2:10:30 PM
    Author     : root
--%>
<%@page import="DAO.StudentDao"%>
<%@page import="java.util.AbstractList"%>
<%@page import="java.util.List"%>
<%@page import="DTO.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student manager</title>
    </head>
    <body>
        <jsp:include page = "../Share/header.jsp"></jsp:include>
        <%
            List<Student> listStudent = null;
            HttpSession sessions = request.getSession();
            StudentDao studenDao = new StudentDao();
            // Get id of student we want to delete
            String id = request.getParameter("ID");
            // Get type of action such as update, add new student...
            String type = request.getParameter("type");
            
            if(id != null){
                studenDao.deleteStudent(id);
            }
             
            // If the first pageload or then add new student or then update, get all student
            if(type == null || type.equals("update") || type.equals("addnew")){
                listStudent = studenDao.getListStudent("", "");
            } else {
                listStudent = (List<Student>)sessions.getAttribute("listStudent");
            }
           
            String message = (String)sessions.getAttribute("message");
            if(message == null)
                message = "";
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
                                                <form action = "../../getListStudent" method="post">
                                                    <div class="form-group col-md-12">
                                                        <label class="col-md-12" ><%=message %> </label>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-3" >ID: </label>
                                                        <div class="col-md-9">
                                                            <input type="text" class = "form-control" name = "ID"/>
                                                        </div>
                                                    </div>

                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-3" >Name: </label>
                                                        <div class="col-md-9">
                                                            <input type="text" class = "form-control" name = "name"/>
                                                        </div>
                                                    </div>
                                                    <div >
                                                        <button type="submit"  class="btn div-submit-index">Search</button>
                                                    </div>
                                                </form>
                                            </div>
                                            <% 
                                                    if(listStudent != null){
                                            %>
                                            
                                            <div class = "row" style="overflow: hidden;">
                                                
                                                <table class="tbl_border" cellspacing="0px" cellpadding="5px" rules="all" border="1"
                                                    style="border-collapse: collapse;" width="100%" id="tblshow">
                                                    <thead>
                                                          <tr>
                                                        <td class="headertable" style="width: 10px;">
                                                            No
                                                        </td>
                                                        <td class="headertable">
                                                            ID
                                                        </td>
                                                        <td class="headertable">
                                                            Name
                                                        </td>
                                                        <td class="headertable">
                                                            Birthday
                                                        </td>
                                                        <td class="headertable">
                                                            Gender
                                                        </td>
                                                        <td class="headertable">
                                                            Address
                                                        </td>
                                                        <td class="headertable">
                                                           Update
                                                        </td >
                                                        <td class="headertable">
                                                            Delete
                                                        </td>
                                                        <td class="headertable">
                                                            View score
                                                        </td>
                                                    </tr>
                                                    </thead>
                                                  
                                                    <% 
                                                        
                                                            for(int i =0; i< listStudent.size(); i++){
                                                                int no = i + 1;
                                                    %>
                                                            <tr>
                                                                <td>
                                                                    <%= no%>
                                                                </td>
                                                                <td>
                                                                    <%= listStudent.get(i).getId() %>
                                                                </td>
                                                                <td>
                                                                    <%= listStudent.get(i).getName()%>
                                                                </td>
                                                                <td>
                                                                    <%= listStudent.get(i).getBirthday()%>
                                                                </td>
                                                                <td>
                                                                    <%= listStudent.get(i).getGender()%>
                                                                </td>
                                                                <td>
                                                                    <%= listStudent.get(i).getAddress()%>
                                                                </td>
                                                                 <td>
                                                                     <a href=<%= "updatestudent.jsp?ID=" + listStudent.get(i).getId() %>><img src="../../img/images/Edit.png" class = "img-edit" title="Edit" alt=""/></a>
                                                                </td>
                                                                <td>
                                                                    <a href=<%= "scoremanager.jsp?ID=" + listStudent.get(i).getId() %>><img src="../../img/images/delete.png" class = "img-edit" title ="Delete"/></a>
                                                                </td>
                                                                <td>
                                                                    <a href= <%= "scoremanager.jsp?StudentId=" + listStudent.get(i).getId() %> ><img src="../../img/score.png" class = "img-edit" title="View score" alt=""/></a>
                                                                
                                                                </td>
                                                            </tr>   
                                                    <%} }%>
                                                </table>
                                            </div>
                                                
                                                <div class="row" style="margin-top:10px;margin-left: 20px;">
                                                <a href="/CRUD_Example/faces/View/Content/newstudent.jsp">Create new student</a>
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
