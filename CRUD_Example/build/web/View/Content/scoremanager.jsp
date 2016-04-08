

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
        <title>JSP Page</title>
    </head>
    <body>
        <%
            SemesterDao semesterDao = new SemesterDao();
            CourseDao courseDao = new CourseDao();
            StudentDao studentDao = new StudentDao();
            ScoreDao scoreDao = new ScoreDao();
            HttpSession sessions = request.getSession();
            List<Score> listScore = (List<Score>)sessions.getAttribute("listScore");
            //Get list Course
            List<Course> listCourse = courseDao.getListCourse();
            //Get course id
            String strCourseId = request.getParameter("courseid");
            //Get studentId
            String strStudentId = request.getParameter("StudentId");
            //Get SemesterId
            String strSemesterId = request.getParameter("SemesterId");
            String strAction = request.getParameter("act");
            //delete score
            if(strAction != null){
                if(strAction.equals("delete")){
                    String strId = request.getParameter("ID");
                    strStudentId = scoreDao.getScore(strId).getStudentId();
                    scoreDao.deleteScore(strId);
                }
            }
            //Create session with current student
            sessions.setAttribute("student", strStudentId);
            if(strCourseId == null){
                strCourseId = "1";
            }
            if(listScore == null)
                listScore = scoreDao.getListScore(strStudentId, "");
            List<Semester> listSemester = semesterDao.getListSemester(strCourseId);
            String strMessage = (String)sessions.getAttribute("message");
            if(strMessage == null){
                strMessage = "";
            }
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
                                                <form action = "../../getListScore " method="post">
                                                    <div class="form-group col-md-12">
                                                        <label class="col-md-12" ><%=strMessage %> </label>
                                                    </div>
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
                                                                        if(strCourseId.equals(String.valueOf(listCourse.get(i).getId()))){
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
                                                                %>
                                                                   
                                                                   <option value = "<%=listSemester.get(i).getId() %>"><%=listSemester.get(i).getName()%></option>
                                                                
                                                                <%   }
                                                               %>
                                                               
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div >
                                                        <button type="submit"  class="btn div-submit-index">Search</button>
                                                    </div>
                                                </form>
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
                                                                    <%= listScore.get(i).getScrore_1()%>
                                                                </td>
                                                                <td align = "right">
                                                                    <%= listScore.get(i).getScrore_2()%>
                                                                </td>
                                                                <td align = "right">
                                                                   <%= listScore.get(i).getScrore_3()%>
                                                                </td>
                                                                <td align = "right">
                                                                    <%= listScore.get(i).getAverage()%>
                                                                </td>
                                                                 <td align = "center">
                                                                     <a href="<%= "updatescore.jsp?ID=" + listScore.get(i).getId() %>" >
                                                                         <img src="../../img/images/Edit.png" class = "img-edit" alt=""/>
                                                                     </a>
                                                                </td>
                                                                <td align = "center">
                                                                     <a href="<%= "scoremanager.jsp?act=delete&&ID=" + listScore.get(i).getId()%>" >
                                                                         <img src="../../img/images/delete.png" class = "img-edit" alt="" onclick="return confirm('Are you sure?')"/>
                                                                     </a>
                                                                </td>
                                                            </tr>   
                                                    <%} }%>
                                                </table>
                                            </div>
                                                
                                                <div class="row" style="margin-top:10px;margin-left: 20px;">
                                                    <a href="<%="/CRUD_Example/faces/View/Content/newscore.jsp?SemesterId=" + strSemesterId %>" >Add new score</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
            </div>
        </div><!--kt_content right-->
            </div>
        </div>
        <script type="text/javascript">
            // bind change event to select
             $('#course').bind('change', function () {
                var url = this.value; // get selected value
                if (url!== '') { // require a URL
                    window.location = 'scoremanager.jsp?courseid=' + url; // redirect
                }
                return false;
            });
        </script>
    
    </body>
</html


