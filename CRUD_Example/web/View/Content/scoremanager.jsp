

<%@page import="DTO.User"%>
<%@page import="DAO.ScoreDao"%>
<%@page import="DTO.Course"%>
<%@page import="DAO.CourseDao"%>
<%-- 
    Document   : newstudent
    Created on : Apr 5, 2016, 7:04:01 PM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DTO.Score"%>
<%@page import="DTO.Semester"%>
<%@page import="java.util.List"%>
<%@page import="DTO.Student"%>
<%@page import="DAO.StudentDao"%>
<%@page import="DAO.SemesterDao"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Score manager</title>
        <link href="Css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="Css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="Css/index.css" rel="stylesheet" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="js/jquery.js"></script>
        <link href="Css/main.css" rel="stylesheet" type="text/css"/>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap.js"></script>
        
        <script type = "text/javascript">
            function searchScore(studentId){
                
                var SemesterId = document.getElementById('SemesterId').value;
                window.location = '/CRUD_Example/scoremanager.jsp?StudentId=' + 
                                   studentId + '&&SemesterId=' + SemesterId;
            }
        </script>
    </head>
    <body>
        <%
            SemesterDao semesterDao = new SemesterDao();
            CourseDao courseDao = new CourseDao();
            StudentDao studentDao = new StudentDao();
            ScoreDao scoreDao = new ScoreDao();
            HttpSession sessions = request.getSession();
            User user = (User)session.getAttribute("user");
            List<Score> listScore = null;
            //Get studentId
            String strStudentId = request.getParameter("StudentId");
             //Get course id
            String strCourseId = request.getParameter("courseid");
            if(strCourseId == null)
                listScore = scoreDao.getListScore(strStudentId, "");
            int courseId = 1;
            if(user == null){
                sessions.setAttribute("url", request.getContextPath() + "/scoremanager.jsp?StudentId=" + strStudentId);
                response.sendRedirect("/CRUD_Example/logins.jsp");
            }
            
            //Get SemesterId
            String strSemesterId = request.getParameter("SemesterId");
            if(strSemesterId != null){
                listScore = scoreDao.getListScore(strStudentId,strSemesterId);
                courseId = semesterDao.getSemester(strSemesterId).getCourseId();
            } else {
                strSemesterId = "";
            }
            //Get list Course
            List<Course> listCourse = courseDao.getListCourse();
           
            if(strCourseId != null)
                courseId = Integer.valueOf(strCourseId);
            
            String strAction = request.getParameter("act");
            //delete score
            if(strAction != null){
                if(strAction.equals("delete")){
                    String strId = request.getParameter("ID");
                    strStudentId = scoreDao.getScore(strId).getStudentId();
                    scoreDao.deleteScore(strId);
                    response.sendRedirect("/CRUD_Example/scoremanager.jsp?StudentId=" + strStudentId);
                }
            }
            
            List<Semester> listSemester = semesterDao.getListSemester(String.valueOf(courseId));
            String strMessage = "View score studentID: " + strStudentId;
        %>
        <jsp:include page = "Share/header.jsp"></jsp:include>
        <div class="container div-content">
            <div class="row">
                <div class="col-md-3 menu_left">
                    <jsp:include page = "Share/menu_left.jsp"></jsp:include>
                </div>
                <div class="col-md-9 ">
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
                                                <form name ="myname" onsubmit="searchScore(<%=strStudentId %>)">
                                                <div class="form-group col-md-12">
                                                    <label class="col-md-12" ></label>
                                                    <input type="hidden" name = "StudentId" value="<%= strStudentId %>"/>
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label class="col-md-3" >Course: </label>
                                                    <div class="col-md-9">

                                                        <select class = "form-control" name = "CourseId" id = "course" >
                                                           <%
                                                               for(int i = 0; i < listCourse.size(); i++){ 
                                                                    if(listCourse.get(i).getId() == courseId){
                                                            %>

                                                                        <option value = "<%=listCourse.get(i).getId() %>" selected="selected">
                                                                            <%= listCourse.get(i).getYearBegin() + " - " + listCourse.get(i).getYearEnd() %>
                                                                        </option>    

                                                            <% 
                                                                    } else {
                                                            %>              
                                                                        <option value = "<%=listCourse.get(i).getId() %>">
                                                                            <%= listCourse.get(i).getYearBegin() + " - " + listCourse.get(i).getYearEnd() %>
                                                                        </option> 

                                                            <%      
                                                                    }
                                                                }
                                                           %>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="form-group col-md-6">
                                                    <label class="col-md-3" >Semester </label>
                                                    <div class="col-md-9">
                                                        <select class = "form-control" name = "SemesterId">
                                                            <%
                                                               for(int i = 0; i < listSemester.size(); i++){
                                                                   if(strSemesterId.equals(String.valueOf(listSemester.get(i).getId()))){
                                                            %>
                                                                        <option value = "<%=listSemester.get(i).getId() %>" selected = "selected"><%=listSemester.get(i).getName()%> </option>
                                                            <%
                                                                   } else {
                                                            %> 
                                                                        <option value = "<%=listSemester.get(i).getId() %>"><%=listSemester.get(i).getName()%></option>
                                                            <%
                                                                   }
                                                                }
                                                            %>

                                                        </select>
                                                    </div>
                                                </div>
                                                <div >
                                                    <button type="submit"  class="btn div-submit-index" >Search</button>
                                                </div></form>
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
                                                            Semester
                                                        </td>
                                                        <td class="headertable">
                                                            Subject
                                                        </td>
                                                        <td class="headertable">
                                                            Score 1
                                                        </td>
                                                        <td class="headertable">
                                                            Score 2
                                                        </td>
                                                        <td class="headertable">
                                                            Score 3
                                                        </td>
                                                        <td class="headertable">
                                                            Average
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
                                                        if(listScore != null)
                                                        {
                                                            for(int i =0; i< listScore.size(); i++){
                                                                int no = i + 1;
                                                                String StrScore1 = String.valueOf(listScore.get(i).getScrore_1());
                                                                String StrScore2 = String.valueOf(listScore.get(i).getScrore_2());
                                                                String StrScore3 = String.valueOf(listScore.get(i).getScrore_3());
                                                                String strScoreAverage = String.valueOf(listScore.get(i).getAverage());
                                                                if(StrScore1.equals("-1.0"))
                                                                    StrScore1 = "-";
                                                                if(StrScore2.equals("-1.0"))
                                                                    StrScore2 = "-";
                                                                if(StrScore3.equals("-1.0"))
                                                                    StrScore3 = "-";
                                                                if(strScoreAverage.equals("-1.0"))
                                                                    strScoreAverage = "-";
                                                    %>
                                                            <tr>
                                                                <td align = "center">
                                                                    <%= no%>
                                                                </td>
                                                                <td align = "center">
                                                                    <%= listScore.get(i).getStrNameSemester()%>
                                                                </td>
                                                                <td>
                                                                    <%= listScore.get(i).getNameSubject()%>
                                                                </td>
                                                                <td align = "right">
                                                                    <%= StrScore1 %>
                                                                </td>
                                                               <td align = "right">
                                                                    <%= StrScore2 %>
                                                                </td>
                                                                <td align = "right">
                                                                    <%= StrScore3 %>
                                                                </td>
                                                                <td align = "right">
                                                                    <%= strScoreAverage %>
                                                                </td>
                                                                 <td align = "center">
                                                                     <a href="<%= "updatescore.jsp?ID=" + listScore.get(i).getId() %>" >
                                                                         <img src="img/images/Edit.png" class = "img-edit" alt=""/>
                                                                     </a>
                                                                </td>
                                                                <td align = "center">
                                                                     <a href="<%= "scoremanager.jsp?act=delete&&ID=" + listScore.get(i).getId()%>" >
                                                                         <img src="img/images/delete.png" class = "img-edit" alt="" onclick="return confirm('Are you sure?')"/>
                                                                     </a>
                                                                </td>
                                                            </tr>   
                                                    <%} }%>
                                                </table>
                                            </div>
                                                
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
            </div>
        </div><!--kt_content right-->
    </div>
    <script type="text/javascript">
        // bind change event to select
         $('#course').bind('change', function () {
            var url = this.value; // get selected value
            if (url!== '') { // require a URL
                window.location = 'scoremanager.jsp?StudentId=' + <%=strStudentId %> +
                                    '&&courseid=' + url; // redirect
            }
            return false;
        });
    </script>

    </body>
</html


