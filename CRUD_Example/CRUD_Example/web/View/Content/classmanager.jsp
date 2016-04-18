<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Class manager</title>
        <link href="Css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="Css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="Css/index.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery.js"></script>
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <link href="Css/main.css" rel="stylesheet" type="text/css"/>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/classjs.js" type="text/javascript"></script>

        <script>
            var Id = getParameter('ID');
            jQuery(document).ready(function () {
                if (Id !== "" && Id !== null) {
                    deleteClass(Id);
                    
                }
                getTableClass();
            });
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
                    <!--content_right-->
                    <div class="row content-right">
                        <div class="col-sm-12 col-md-12 col-lg-12">
                            <div class="row">
                                <div class="col-sm-12 col-md-12 col-lg-12">
                                    <div class="row">
                                        <div class="col-sm-12 col-md-12 col-lg-12 list-student">
                                            <div class="form-group col-md-12">
                                                <label class="col-md-12" ></label>
                                            </div>
                                            <div class="row">
                                                <h3 style="text-align: center;color: red">Class manager</h3>
                                            </div>
                                            <div class = "row" style="overflow: hidden;">
                                                <table class="tbl_border" cellspacing="0px" cellpadding="5px" rules="all" border="1"
                                                       style="border-collapse: collapse;text-align: center" width="100%" id="table1">
                                                    <thead>
                                                        <tr>
                                                            <td class="headertable" style="width: 10px;">
                                                                No
                                                            </td>
                                                            <td class="headertable">
                                                                Course Id
                                                            </td>
                                                            <td class="headertable">
                                                                Name
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

                                            <div class="row" style="margin-top:10px;margin-left: 20px;">
                                                <a href="/CRUD_Example/newclass.jsp">Create new class</a>
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
    </body>
</html>
