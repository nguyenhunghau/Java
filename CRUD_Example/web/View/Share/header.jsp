<%-- 
    Document   : Header
    Created on : Apr 4, 2016, 2:26:11 PM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../../Css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="../../Css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="../../Css/index.css" rel="stylesheet" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="../../js/jquery.js"></script>
        <script src="../../js/bootstrap.min.js"></script>
        <script src="../../js/bootstrap.js"></script>
    </head>
    <body>
        <div class="container">
        <!--header-->
            <div class="row th-header">
                <div class="col-sm-12 col-md-12 col-lg-12">
                    <div class="row">
                        <div class="col-sm-2 col-md-2 col-lg-2 col-xs-offset-1">
                            <!--logo-->
                            <a href=""><img src="../../img/student.jpg" style="width:140px;height:100px;margin-left:-30px;" /></a>
                        </div><!--kt logo-->
                        <div class="col-sm-9 col-md-9 col-lg-9">
                            
                            <div class="row ">
                                <!--quan li-->
                                <div class="col-sm-8 col-md-8 col-lg-8">
                                    <form class="navbar-form navbar-right cong_cu_tk" role="search">
                                        <div class="form-group tao">
                                            <input type="text" class="form-control frm_search" placeholder="Search">
                                        </div>
                                        <button type="submit" class="btn btn-default btn_search"><i>Search</i></button>
                                    </form>
                                </div>
                                <div class="col-sm-6 col-md-6 col-lg-2 ">
                                    <div class="dropdown ten_ng_dung">
                                        <a href="">
                                            <img src="../../img/icon_login.gif" />Login
                                        </a>
                                    </div>
                                </div>
                                <div class="col-sm-3 col-md-3 col-lg-2">
                                    <div class="dropdown ten_ng_dung">
                                        <a href="">
                                            <img src="../../img/icon_login.gif" />Register
                                        </a>
                                    </div>
                                </div><!--kt nguoi dung-->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="container">
            <div class="row">
                <!--menu-->
                <div class="col-sm-12 col-md-12 col-lg-12">
                    <nav class="navbar navbar-default  thanh-dieu-duong" role="navigation">
                        <div class="container">
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                                    <span class="sr-only">Toggle navigation</span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
                            </div>

                            <!-- Collect the nav links, forms, and other content for toggling -->
                            <div class="collapse navbar-collapse  thanh-dieu-duong" id="bs-example-navbar-collapse-1">
                                <ul class="nav navbar-nav">
                                    <li><img src="../../img/icon_index.gif" /><a href="">Index</a></li>
                                    <li><img src="../../img/icon_profile.gif" /><a href="">About</a></li>
                                    <li><img src="../../img/icon_profile.gif" /><a href="">Contact</a></li>
                                </ul>
                            </div>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </body>
</html>
