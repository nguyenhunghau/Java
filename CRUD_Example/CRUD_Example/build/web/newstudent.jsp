<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>add new student</title>
        <link href="Css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="Css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="Css/index.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery.js"></script>
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <link href="Css/main.css" rel="stylesheet" type="text/css"/>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/studentjs.js" type="text/javascript"></script>
        <script src="js/classjs.js" type="text/javascript"></script>
        <script src="js/coursejs.js" type="text/javascript"></script>
        <link href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">        
        <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    </head>
    <body>
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
                                                <form id ="myform"  method="post">
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Name: </label>
                                                            <div class="col-md-9">
                                                                <input type="text" class = "form-control" placeholder ="Name" name = "Name" id="name"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Birthday: </label>
                                                            <div class="col-md-9">
                                                                <input type="text" class = "form-control" placeholder ="2016-09-15" name = "Birthday" id="birthday"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Gender </label>
                                                            <div class="col-md-9">
                                                                <select class = "form-control" name = "Gender" id="gender">
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
                                                                <input type="text" class = "form-control" placeholder = "Address" name = "Address" id = "address"/>
                                                            </div>
                                                        </div>
                                                    </div> 
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Course: </label>
                                                            <div class="col-md-9">
                                                                <select class = "form-control" name = "CourseId" id = "course" >
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Class:  </label>
                                                            <div class="col-md-9">
                                                                <select class = "form-control" name = "classId" id = "class_study" >
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div>
                                                        <button type="submit"  class="btn div-submit-index" id = "btn_add">Add new student</button>
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
        <script>
            jQuery(document).ready(function () {
                getListCourse();
                getListClass('1');
            });

            $('#course').bind('change', function () {
                var course = $('#course').val();
                $('#class_study').find('option').remove();
                getListClass(course);
            });

            $("#myform").submit(function () {
                addStudent();
                alert('');
                $(location).attr('href', '/CRUD_Example/studentmanager.jsp');
            });
        </script>
    </body>
</html>
