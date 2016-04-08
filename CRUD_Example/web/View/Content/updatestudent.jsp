<%-- 
    Document   : updatestudent
    Created on : Apr 5, 2016, 11:02:49 PM
    Author     : 12121_000
--%>

<%@page import="DTO.Course"%>
<%@page import="DTO.ClassStudy"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="DAO.CourseDao"%>
<%@page import="DAO.ClassStudyDao"%>
<%@page import="DTO.Student"%>
<%@page import="DAO.StudentDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add new student</title>
       
    </head>
    <body>
        <jsp:include page = "../Share/header.jsp"></jsp:include>
        <%
            ClassStudyDao classDao = new ClassStudyDao();
            CourseDao courseDao = new CourseDao();
            String idStudent = request.getParameter("ID");
            StudentDao studentDao = new StudentDao();
            Student student = studentDao.getStudent(idStudent);
           
            String strCourseId = request.getParameter("courseid");
            
            if(strCourseId == null){
                strCourseId = "1";
            }
            List<Course> listCourse = courseDao.getListCourse();
            //Get list class
            List<ClassStudy> listClass = classDao.getListClass(strCourseId);
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
                                                <form action = "../../updateStudent" method="post" />
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <input type="hidden" class = "form-control" name = "ID"  value = "<%= idStudent %>" />
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Name: </label>
                                                            <div class="col-md-9">
                                                                <input type="text" class = "form-control" name = "Name" placeholder ="Name" value = "<%=student.getName() %>" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Birthday: </label>
                                                            <div class="col-md-9">
                                                                <input type="text" id ="birthday" class = "form-control datepicker" placeholder ="2016-09-15" name = "Birthday"  value = "<%=student.getBirthday()%>" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Gender </label>
                                                            <div class="col-md-9">
                                                                <select class = "form-control" name = "Gender">
                                                                    <% if(student.getGender().equals("Male")) {%>
                                                                        <option value = "Male" selected = "selected">Male</option>
                                                                        <option value = "Female">Female</option>
                                                                    <%} else { %>
                                                                        <option value = "Male">Male</option>
                                                                        <option value = "Female"  selected = "selected">Female</option>
                                                                    <% }%>
                                                                    
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div> 
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Address: </label>
                                                            <div class="col-md-9">
                                                                <input type="text" class = "form-control" name = "Address" placeholder ="Address" value = "<%=student.getAddress()%>" />
                                                            </div>
                                                        </div>
                                                    </div> 
                                                            <div class="row">
                                                        <div class="form-group col-md-12">
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
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Class: </label>
                                                            <div class="col-md-9">
                                                                <select class = "form-control" name = "ClassId">
                                                                    <%
                                                                       for(int i = 0; i < listClass.size(); i++){ 
                                                                            if(listClass.get(i).equals(student.getClassId())){
                                                                    %>

                                                                       <option value = "<%=listClass.get(i).getId() %>" selected = "selected"><%=listClass.get(i).getName()%></option>
                                                                    <%
                                                                       } else {
                                                                            
                                                                     %>
                                                                        <option value = "<%=listClass.get(i).getId() %>"><%=listClass.get(i).getName()%></option>
                                                                     <%  
                                                                        }
                                                                    }
                                                                    %>

                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <button type="submit"  class="btn div-submit-index">Update student</button>
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
        </div><!--kt_content right-->
       <script type="text/javascript">
            // bind change event to select
             $('#course').bind('change', function () {
                var url = this.value; // get selected value
                if (url!== '') { // require a URL
                    window.location = 'newstudent.jsp?courseid=' + url; // redirect
                }
                return false;
            });
        </script>
    </body>
</html>
