<%-- 
    Document   : _Layout
    Created on : Apr 4, 2016, 2:13:28 PM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <link href="../../Css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="../../Css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="../../Css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
        <!--header-->
        <div class="row th_header">
            <div class="col-sm-12 col-md-12 col-lg-12">
                <div class="row">
                    <div class="col-sm-2 col-md-2 col-lg-2 col-xs-offset-1">
                        <!--logo-->
                        <a href="@Url.Action("Index","Project")"><img src="~/Content/img/logoDizo.jpg" style="width:140px;height:100px;margin-left:-30px;" /></a>
                    </div><!--kt logo-->
                    <div class="col-sm-9 col-md-9 col-lg-9">
                        <!--tim kiem va quan li-->
                        <div class="row ">
                            <!--quan li-->
                            <div class="col-sm-8 col-md-8 col-lg-8">
                                @*<form class="navbar-form navbar-right cong_cu_tk" role="search">
                                        <div class="form-group tao">
                                            <input type="text" class="form-control form_tim_kiem" style="height: 5px" placeholder="Nhập từ cần tìm">
                                        </div>
                                        <button type="submit" class="btn btn-default nut_tim_kiem"><i>Search</i></button>
                                    </form>*@
                            </div><!--tk tb mail-->
                            @{Html.RenderAction("Index", "Accounts");}
                        </div><!--kt quan li-->
                    </div><!-- kt tim kiem va quan li-->
                </div>
            </div>
        </div><!--kt_header-->
    </div>
    <div class="container">
        <div class="row">
            <!--menu-->
            <div class="col-sm-12 col-md-12 col-lg-12">
                <nav class="navbar navbar-default  thanh_dieu_huong" role="navigation">
                    <div class="container">
                        <!-- Brand and toggle get grouped for better mobile display -->
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                        </div>

                        <!-- Collect the nav links, forms, and other content for toggling -->
                        <div class="collapse navbar-collapse  thanh_dieu_duong" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav">
                                <li><img src="~/Content/img/icon_tr_chu_03.gif" /><a href="@Url.Action("Index", "Home", new { page = 1 })">Trang chủ</a></li>
                                <li><img src="~/Content/img/icon_canhan_03.gif" /><a href="@Url.Action("Index", "Profile", new { userID = AccountCode.GetCodeAccount() })">Trang cá nhân</a></li>
                                <li><img src="~/Content/img/icon_canhan_03.gif" /><a href="@Url.Action("PostProject", "Project",new {ID = -1 })">Tạo dự án</a></li>
                                <li><img src="~/Content/img/icon_canhan_03.gif" /><a href="@Url.Action("Index", "Project", new {ID = -1 })">Tìm dự án</a></li>
                            </ul>
                        </div><!-- /.navbar-collapse -->
                    </div><!-- /.container-fluid -->
                </nav>
            </div>
           

        </div>
    </div>

   <div class="container div_content">
    <div class="row">
        <div class="col-md-3 menu_left">
            <!--Menu left -->
        </div>
      
    </div>
</div><!--kt_content-->
            
    </body>
</html>
