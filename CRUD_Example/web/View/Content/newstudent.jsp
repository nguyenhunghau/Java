<%-- 
    Document   : newstudent
    Created on : Apr 5, 2016, 7:04:01 PM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add new student</title>
        
    </head>
    <body>
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
                                                <form action = "../../AddStudentServlet" method="post" />
                                                    
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Name: </label>
                                                            <div class="col-md-9">
                                                                <input type="text" class = "form-control" placeholder ="Name" name = "Name"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Birthday: </label>
                                                            <div class="col-md-9">
                                                                <input type="text" class = "form-control" placeholder ="2016-09-15" name = "Birthday"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Gender </label>
                                                            <div class="col-md-9">
                                                                <select class = "form-control" name = "Gender">
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
                                                                <input type="text" class = "form-control" placeholder = "Address" name = "Address"/>
                                                            </div>
                                                        </div>
                                                    </div> 
                                                    <div>
                                                        <button type="submit"  class="btn div-submit-index">Add new student</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div><!--tk dan thoai-->
                            </div>
                        </div>
            </div>
        </div><!--kt_content right-->
       
    </body>
</html>
