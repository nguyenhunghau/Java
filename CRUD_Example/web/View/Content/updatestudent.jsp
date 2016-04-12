<%-- 
    Document   : updatestudent
    Created on : Apr 5, 2016, 11:02:49 PM
    Author     : 12121_000
--%>

<%@page import="DTO.User"%>
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
        <title>Update student</title>
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
                var name=document.getElementById('Name');
                var birthday = document.getElementById('Birthday');
                  
                var address = document.getElementById('Address');
                var classId = document.getElementById('ClassId');
                if (name.value == "" || birthday.value == "" || address.value == "" || classId.value == ""){  
                  alert("Please fill all information");  
                  return false;  
                }
                if(!isValidDate(birthday.value)){
                    alert("Birthday is incorrect with format datetime");
                    return false;  
                }
            }
            
            function isValidDate(dateString)
            {
                // First check for the pattern
                var regex_date = /^\d{4}\-\d{1,2}\-\d{1,2}$/;

                if(!regex_date.test(dateString))
                {
                    return false;
                }

                // Parse the date parts to integers
                var parts   = dateString.split("-");
                var day     = parseInt(parts[2], 10);
                var month   = parseInt(parts[1], 10);
                var year    = parseInt(parts[0], 10);

                // Check the ranges of month and year
                if(year < 1000 || year > 3000 || month == 0 || month > 12)
                {
                    return false;
                }

                var monthLength = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];

                // Adjust for leap years
                if(year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
                {
                    monthLength[1] = 29;
                }

                // Check the range of the day
                return day > 0 && day <= monthLength[month - 1];
            }
        </script>
    </head>
    <body>
        <jsp:include page = "Share/header.jsp"></jsp:include>
        <%
            ClassStudyDao classDao = new ClassStudyDao();
            HttpSession sessions = request.getSession();
            CourseDao courseDao = new CourseDao();
            String strIdStudent = request.getParameter("ID");
            String strCourseId = request.getParameter("courseid");
            StudentDao studentDao = new StudentDao();
            Student student = studentDao.getStudent(strIdStudent);
            ClassStudy classStudy = classDao.getClass(String.valueOf(student.getClassId()));
            if(strCourseId == null)
                strCourseId = String.valueOf(classStudy.getCourseId());
            User user = (User)session.getAttribute("user");
            if(user == null){
                sessions.setAttribute("url", request.getContextPath() + "/updatestudent.jsp?ID=" + strIdStudent);
                response.sendRedirect("/CRUD_Example/logins.jsp");
            }
            
            List<Course> listCourse = courseDao.getListCourse();
            //Get list class
            List<ClassStudy> listClass = classDao.getListClass(strCourseId);
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
                                                <form name =" myform" onsubmit="return myFunction();" action = "/CRUD_Example/updateStudent" method="post" />
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <input type="hidden" class = "form-control" name = "ID"  value = "<%= strIdStudent %>" id = "ID"/>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Name: </label>
                                                            <div class="col-md-9">
                                                                <input type="text" class = "form-control" name = "Name" placeholder ="Name" value = "<%=student.getName() %>" id ="Name"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Birthday: </label>
                                                            <div class="col-md-9">
                                                                <input type="text" class = "form-control datepicker" placeholder ="2016-09-15" name = "Birthday"  value = "<%=student.getBirthday()%>" id = "Birthday"/>
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
                                                                <input type="text" class = "form-control" name = "Address" placeholder ="Address" value = "<%=student.getAddress()%>" id = "Address"/>
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
                                                                            if(listCourse.get(i).getId() == Integer.valueOf(strCourseId)){
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
                                                                <select class = "form-control" name = "ClassId" id = "ClassId">
                                                                    <%
                                                                       for(int i = 0; i < listClass.size(); i++){ 
                                                                            if(listClass.get(i).getId() == student.getClassId()){
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
                var id = document.getElementById('ID').value;
                var url = this.value; // get selected value
                if (url!== '') { // require a URL
                    window.location = 'updatestudent.jsp?ID=' + id + '&&courseid=' + url; // redirect
                }
                return false;
            });
        </script>
    </body>
</html>
