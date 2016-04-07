<%-- 
    Document   : updatestudent
    Created on : Apr 5, 2016, 11:02:49 PM
    Author     : 12121_000
--%>

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
            String idStudent = (String)request.getParameter("ID");
            StudentDao studentDao = new StudentDao();
            Student student = studentDao.getStudent(idStudent);
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
                                                            <label></label>
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
        </div><!--kt_content right-->
       
    </body>
</html>

</script>