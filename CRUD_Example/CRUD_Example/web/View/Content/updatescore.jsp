<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update score</title>
        <link href="Css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="Css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="Css/index.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery.js"></script>
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <link href="Css/main.css" rel="stylesheet" type="text/css"/>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/scorejs.js" type="text/javascript"></script>
        <script src="js/subjectjs.js" type="text/javascript"></script>
        <script src="js/classjs.js" type="text/javascript"></script>
    </head>
    <body>
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
                                                <form id ="myform" method="post" >
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Subject </label>
                                                            <div class="col-md-9">
                                                                <select class = "form-control" name = "Subject" id = "subject">
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Score 1: </label>
                                                            <div class="col-md-9">
                                                                <input type="text" class = "form-control" placeholder ="Score 1" name = "Score1" id = "score_1" onkeypress='validate(event)' />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Score 2: </label>
                                                            <div class="col-md-9">
                                                                <input type="text" class = "form-control" placeholder = "Score 2" name = "Score2" onkeypress='validate(event)' id = "score_2"/>
                                                            </div>
                                                        </div>
                                                    </div> 
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="col-md-3" >Score 3 </label>
                                                            <div class="col-md-9">
                                                                <input type="text" class = "form-control" placeholder = "Score 3" name = "Score3" onkeypress='validate(event)' id = "score_3"/>
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
                </div>
            </div>
        </div>
        <script>

            var scoreid = getParameter('ID');
            var studentid = getParameter('StudentId');
            jQuery(document).ready(function () {
                getListSubject();
                getScore(scoreid);
            });

            $("#myform").submit(function () {
                
                updateScore(scoreid, studentid);
                alert('finish update');
                $(location).attr('href', '/CRUD_Example/scoremanager.jsp?ID=' + studentid);
                 
            });
        </script>
    </body>
</html>
