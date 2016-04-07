<%-- 
    Document   : newscore
    Created on : Apr 7, 2016, 7:58:02 PM
    Author     : root
--%>

<%@page import="java.util.List"%>
<%@page import="DAO.SubjectDao"%>
<%@page import="DTO.Subject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            SubjectDao subjectDao = new SubjectDao();
            HttpSession sessions = request.getSession();
            String strStudentId = (String)sessions.getAttribute("student");
            String strSemesterId = request.getParameter("SemesterId");
            if(strSemesterId == null)
                strSemesterId = "1";
            //Get list subject
            List<Subject> listSubject = subjectDao.getListSubject();
        %>
        <jsp:include page = "../Share/header.jsp"></jsp:include>
        
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
                                                <form action = "../../addNewScore" method="post" />
                                                    
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Subject </label>
                                                            <div class="col-md-9">
                                                                <select class = "form-control" name = "Subject">
                                                                    <%
                                                                        for(int i = 0; i< listSubject.size(); i++){
                                                                    %>
                                                                        <option value = "<%= listSubject.get(i).getId()%>">
                                                                            <%= listSubject.get(i).getName() %>
                                                                        </option>
                                                                    <%        
                                                                        }
                                                                    %>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Score 1: </label>
                                                            <div class="col-md-9">
                                                                <input type="text" class = "form-control" placeholder ="Score 1" name = "Score1"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Score 2: </label>
                                                            <div class="col-md-9">
                                                                <input type="text" class = "form-control" placeholder = "Score 2" name = "Score2"/>
                                                             </div>
                                                        </div>
                                                    </div> 
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Score 3 </label>
                                                            <div class="col-md-9">
                                                                <input type="text" class = "form-control" placeholder = "Score 3" name = "Score3"/>
                                                            </div>
                                                        </div>
                                                    </div> 
                                                    <div>
                                                        <button type="submit"  class="btn div-submit-index">Add new score</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div><!--tk dan thoai-->
                            </div>
                        </div>
            </div>
    </body>
</html>
