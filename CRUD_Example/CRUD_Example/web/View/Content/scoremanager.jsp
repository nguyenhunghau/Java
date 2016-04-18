<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Score manager</title>
        <link href="Css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="Css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="Css/index.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery.js"></script>
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <link href="Css/main.css" rel="stylesheet" type="text/css"/>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/coursejs.js" type="text/javascript"></script>
        <script src="js/semesterjs.js" type="text/javascript"></script>
        <script src="js/scorejs.js" type="text/javascript"></script>
        <script src="js/classjs.js" type="text/javascript"></script>
        <script type = "text/javascript">
            function searchScore(studentId) {

                var SemesterId = document.getElementById('SemesterId').value;
                window.location = '/CRUD_Example/scoremanager.jsp?StudentId=' +
                        studentId + '&&SemesterId=' + SemesterId;
            }
        </script>
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
                                                <form id = "myform" method="post">
                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-3" >Course: </label>
                                                        <div class="col-md-9">
                                                            <select class = "form-control" name = "CourseId" id = "course" >
                                                            </select>
                                                        </div>
                                                    </div>

                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-3" >Semester </label>
                                                        <div class="col-md-9">
                                                            <select class = "form-control" name = "SemesterId" id = "semester">
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div >
                                                        <button type="submit"  class="btn div-submit-index" id = "btn_searchscore">Search</button>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class = "row" style="overflow: hidden;">
                                                <table class="tbl_border" cellspacing="0px" cellpadding="5px" rules="all" border="1"
                                                       style="border-collapse: collapse; text-align: center" width="100%" id="table1">
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
                                                </table>
                                            </div>
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

            var studentId = getParameter('ID');
            var scoreId = getParameter('scoreId');
            jQuery(document).ready(function () {
                if (scoreId !== "" && scoreId !== null) {
                    deleteScore(scoreId,studentId);
                    $(location).attr('href', '/CRUD_Example/scoremanager.jsp?ID=' + studentid);
                }
                getListCourse();
                var course = $('#course').val();
                getListSemester('1');
                getListScore(studentId);
            });

            $('#course').bind('change', function () {
                var course = $('#course').val();
                $('#semester').find('option').remove();
                getListSemester(course);
            });

            $("#myform").submit(function () {
                getListScore(studentId);
            });
        </script>
    </body>
</html