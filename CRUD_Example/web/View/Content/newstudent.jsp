<%-- 
    Document   : Index
    Created on : Apr 4, 2016, 2:34:19 PM
    Author     : root
--%>
<%@page import="DTO.User"%>
<%@page import="DAO.CourseDao"%>
<%@page import="DTO.Course"%>
<%@page import="DTO.ClassStudy"%>
<%@page import="java.util.List"%>
<%@page import="DAO.ClassStudyDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
        
        <script>
            function myFunction(){ 
                var name=document.getElementById('Name');
                var birthday = document.getElementById('Birthday');
                var address = document.getElementById('Address');

                if (name.value == "" || birthday.value == "" || address.value == ""){  
                  alert("Please fill all textbox");  
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
        <% 
            HttpSession sesions = request.getSession();
            ClassStudyDao classDao = new ClassStudyDao();
            CourseDao courseDao = new CourseDao();
            String strCourseId = request.getParameter("courseid");
            
            if(strCourseId == null){
                strCourseId = "1";
            }
            User user = (User)session.getAttribute("user");
            if(user == null){
                sesions.setAttribute("url", request.getRequestURI());
                response.sendRedirect("/CRUD_Example/faces/View/Content/login.jsp");
            }
            List<Course> listCourse = courseDao.getListCourse();
            //Get list class
            List<ClassStudy> listClass = classDao.getListClass(strCourseId);
        %>
         <jsp:include page = "../Share/header.jsp"></jsp:include>
         <div class="container div-content">
            <div class="row">
                <div class="col-md-3 menu_left">
                    <jsp:include page = "../Share/menu_left.jsp"></jsp:include>
                </div>
                <div class="col-md-9 ">
                   <div class="row content-right">
                        <div class="col-sm-12 col-md-12 col-lg-12">
                            <div class="row">
                                <div class="col-sm-12 col-md-12 col-lg-12">
                                    <div class="row">
                                        <div class="col-sm-12 col-md-12 col-lg-12 list-student">
                                            <div class="row">
                                                <form name =" myform" onsubmit="return myFunction();" action = "../../addNewStudent" method="post"  />
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
                                                            <label class="col-md-3" >Birthday: </label>
                                                            <div class="col-md-9">
                                                                <input type="text" class = "form-control" placeholder ="2016-09-15" name = "Birthday" id="Birthday"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Gender </label>
                                                            <div class="col-md-9">
                                                                <select class = "form-control" name = "Gender" id="Gender">
                                                                   <option value = "Male">Male</option>
                                                                    <option value = "Female">Female</option>
                                                                </select>
                                                             </div>
                                                        </div>
                                                    </div> 
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Address: </label>
                                                            <div class="col-md-9">
                                                                <input type="text" class = "form-control" placeholder = "Address" name = "Address" id = "Address"/>
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
                                                                    %>

                                                                       <option value = "<%=listClass.get(i).getId() %>"><%=listClass.get(i).getName()%></option>

                                                                    <%   }
                                                                   %>

                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <button type="submit"  class="btn div-submit-index">Add new student</button>
                                                    </div>
                                                </form>
                                            </div>
                                            <h3 class="text-subframe"></h3>    
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
