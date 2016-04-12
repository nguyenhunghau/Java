<%-- 
    Document   : Index
    Created on : Apr 4, 2016, 2:10:30 PM
    Author     : root
--%>
<%@page import="DAO.MessageDao"%>
<%@page import="DTO.Score"%>
<%@page import="DAO.ScoreDao"%>
<%@page import="DTO.User"%>
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
        <link href="Css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="Css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="Css/index.css" rel="stylesheet" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="js/jquery.js"></script>
        <link href="Css/main.css" rel="stylesheet" type="text/css"/>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap.js"></script>
        
        <script type = "text/javascript">
            function searchStudent(){
                var id = document.getElementById('ID').value;
                var name = document.getElementById('Name').value;
                
                window.location = '/CRUD_Example/studentmanager.jsp?type=search&&ID=' +
                                    id + '&&Name=' + name;
            }
        </script>
    </head>
    <body>
        <jsp:include page = "Share/header.jsp"></jsp:include>
        <%
            List<Student> listStudent = null;
            HttpSession sessions = request.getSession();
            StudentDao studenDao = new StudentDao();
            ScoreDao scoreDao = new ScoreDao();
            User user = (User)session.getAttribute("user");
            if(user == null){
                sessions.setAttribute("url", request.getContextPath() + "/studentmanager.jsp");
                response.sendRedirect("/CRUD_Example/logins.jsp");
            }
            // Get id of student we want to delete
            String strId = request.getParameter("ID");
            String strName = "";
            if(strId == null)
                strId = "";
            // Get type of action such as update, add new student...
            String strType = request.getParameter("type");
            
            // If the first pageload or then add new student or then update, get all student
            if(strType == null){
                listStudent = studenDao.getListStudent("", "");
            } else {
                if(strType.equals("search")){
                    strName = request.getParameter("Name");
                     if(strName == null)
                        strName = "";
                    listStudent = studenDao.getListStudent(strId,strName);
                } else {
                    //Delete all score of this student
                    List<Score> listscore = scoreDao.getListScore(strId, "");
                    for(Score score:listscore){
                        scoreDao.deleteScore(String.valueOf(score.getId()));
                    }
                    //Delete student
                    studenDao.deleteStudent(strId);
                    response.sendRedirect("/CRUD_Example/studentmanager.jsp");
                }
                
            }
           
            String strMessage = request.getParameter("message");
            if(strMessage == null)
                strMessage = "";
            else
                strMessage = MessageDao.getMessage(strMessage);
        %>
        <div class="container div-content">
            <div class="row">
                <div class="col-md-3 menu_left">
                    <jsp:include page = "Share/menu_left.jsp"></jsp:include>
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
                                                <div class="form-group col-md-12">
                                                    <label class="col-md-12" ><%=strMessage %> </label>
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label class="col-md-3" >ID: </label>
                                                    <div class="col-md-9">
                                                        <input type="text" class = "form-control" value="<%=strId %>" name="ID" id = "ID"/>
                                                    </div>
                                                </div>

                                                <div class="form-group col-md-6">
                                                    <label class="col-md-3" >Name: </label>
                                                    <div class="col-md-9">
                                                        <input type="text" class = "form-control" name="Name" value="<%=strName %>" id = "Name"/>
                                                    </div>
                                                </div>
                                                <div >
                                                    <button type="submit"  class="btn div-submit-index" onclick="searchStudent()">Search</button>
                                                </div>
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
                                                           Edit
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
                                                                <td align = "center">
                                                                    <%= no%>
                                                                </td>
                                                                <td align = "center">
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
                                                                 <td align = "center">
                                                                     <a href= "<%= "updatestudent.jsp?ID=" + listStudent.get(i).getId() %>" ><img src="img/images/Edit.png" class = "img-edit" title="Edit" alt=""/></a>
                                                                </td>
                                                                <td align = "center">
                                                                    <a href= "<%= "studentmanager.jsp?type=delete&&ID=" + listStudent.get(i).getId() %>"><img src="img/images/delete.png" class = "img-edit" title ="Delete" onclick="return confirm('Are you sure?')"/></a>
                                                                </td>
                                                                <td align = "center">
                                                                    <a href= "<%= "scoremanager.jsp?StudentId=" + listStudent.get(i).getId() %>" ><img src="img/score.png" class = "img-edit" title="View score" alt=""/></a>
                                                                
                                                                </td>
                                                            </tr>   
                                                    <%} }%>
                                                </table>
                                            </div>
                                                
                                                <div class="row" style="margin-top:10px;margin-left: 20px;">
                                                <a href="/CRUD_Example/newstudent.jsp">Create new student</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div                                        
    </body>
</html>
