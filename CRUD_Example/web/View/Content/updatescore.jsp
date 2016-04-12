<%-- 
    Document   : updatescore
    Created on : Apr 7, 2016, 10:29:10 PM
    Author     : root
--%>

<%@page import="DTO.User"%>
<%@page import="DTO.Score"%>
<%@page import="DAO.ScoreDao"%>
<%@page import="DTO.Subject"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="DAO.SubjectDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update score</title>
         <link href="Css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="Css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="Css/index.css" rel="stylesheet" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="js/jquery.js"></script>
        <link href="Css/main.css" rel="stylesheet" type="text/css"/>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap.js"></script>
        
        <script>
            function validate(evt) {
                var theEvent = evt || window.event;
                var key = theEvent.keyCode || theEvent.which;
                key = String.fromCharCode( key );
                var regex = /[0-9]|\./;
                if( !regex.test(key) ) {
                  theEvent.returnValue = false;
                  if(theEvent.preventDefault) theEvent.preventDefault();
                }
              }
        </script>
    </head>
       <body>
        <%
            SubjectDao subjectDao = new SubjectDao();
            ScoreDao scoreDao = new ScoreDao();
            HttpSession sessions = request.getSession();
            User user = (User)session.getAttribute("user");
            String strId = request.getParameter("ID");
            if(user == null){
                sessions.setAttribute("url", request.getContextPath() + "/updatescore.jsp?ID=" + strId);
                response.sendRedirect("/CRUD_Example/logins.jsp");
            }
           
            Score score = scoreDao.getScore(strId);
            //Get list subject
            List<Subject> listSubject = subjectDao.getListSubject();
            
        %>
        <jsp:include page = "Share/header.jsp"></jsp:include>
        
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
                                                <form action = "/CRUD_Example/updateScore" method="post" />
                                                    
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <input type="hidden" class = "form-control" name = "ID" value="<%= strId %>"/>
                                                            
                                                            <label class="col-md-3" >Subject </label>
                                                            <div class="col-md-9">
                                                                <select class = "form-control" name = "Subject">
                                                                    <%
                                                                        for(int i = 0; i< listSubject.size(); i++){
                                                                            if(listSubject.get(i).getId() == Integer.valueOf(score.getSubjectId())){
                                                                    %>
                                                                        <option value = "<%= listSubject.get(i).getId()%>" selected = "selected">
                                                                            <%= listSubject.get(i).getName() %>
                                                                        </option>
                                                                    <%  
                                                                        } else {
                                                                    %>
                                                                        <option value = "<%= listSubject.get(i).getId()%>">
                                                                            <%= listSubject.get(i).getName() %>
                                                                        </option>
                                                                    <%
                                                                            }
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
                                                                <input type="text" class = "form-control" placeholder ="Score 1" name = "Score1" value="<%= score.getScrore_1()%>" onkeypress='validate(event)'/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Score 2: </label>
                                                            <div class="col-md-9">
                                                                <input type="text" class = "form-control" placeholder = "Score 2" name = "Score2" value="<%= score.getScrore_2()%>" onkeypress='validate(event)'/>
                                                             </div>
                                                        </div>
                                                    </div> 
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Score 3 </label>
                                                            <div class="col-md-9">
                                                                <input type="text" class = "form-control" placeholder = "Score 3" name = "Score3" value="<%= score.getScrore_3()%>" onkeypress='validate(event)'/>
                                                            </div>
                                                        </div>
                                                    </div> 
                                                    <div>
                                                        <button type="submit"  class="btn div-submit-index">Update score</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
            </div>
    </body>
</html>
