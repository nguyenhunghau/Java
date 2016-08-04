<%-- 
    Document   : login
    Created on : May 13, 2016, 10:51:43 AM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="css/zice.style.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="css/icon.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="css/tipsy.css" media="screen" />
        <style>
            #versionBar {
                background-color:#212121;
                position:fixed;
                width:100%;
                height:35px;
                bottom:0;
                left:0;
                text-align:center;
                line-height:35px;
            }
            .copyright{
                text-align:center; font-size:10px; color:#CCC;
            }
            .copyright a{
                color:#A31F1A; text-decoration:none
            }    
        </style>
        <link rel="shortcut icon" href="images/favicon.ico">
    </head>
    <body>
        <div id="alertMessage" class="error"></div>
        <div id="successLogin"></div>
        <label id="error_maintenance" style="color: red; font-weight: bold; font-size: x-large; display: block; margin-top: 5%; text-align: center;">メンテナンス中です</label>
        <div class="text_success"><img src="images/loader_green.gif"  alt="ziceAdmin" /><span>Please wait</span></div>

        <div id="login" >
            <div class="ribbon"></div>
            <div class="inner">
                <div  class="logo" ><img src="images/corpnew_img02.gif" style="height: 90px; margin-left: 30px;" alt="Self Design" /></div>
                <div class="userbox"></div>
                <div class="formLogin">
                    <div class="tip">
                        <input id="username" name="username" class="text" value="${remember_username}" autocomplete="off"/>
                    </div>
                    <div class="tip">
                        <input id="password" name="password" type="password" class="text" value="${remember_password}" autocomplete="off"/>
                    </div>
                    <div style="padding:20px 0px 0px 0px ;">

                        <div style="float:right;padding:2px 0px ;">
                            <div> 
                                <ul class="uibutton-group">
                                    <li><a class="uibutton normal" id="but_login" >Login</a></li>
                                    <li><a class="uibutton  normal" href="#" id="forgot_pw">forpassword</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="formForgot">
                    <div class="tip">
                        <input id="email" name="email" class="text" autocomplete="off"/>
                    </div>
                    <div style="padding:20px 0px 0px 0px ;">
                        <div style="float:right;padding:2px 0px ;">
                            <div> 
                                <ul class="uibutton-group">
                                    <li><a class="uibutton normal" id="but_forgot" >Forgot</a></li>
                                    <li><a class="uibutton normal back_login" href="#" ><< Back login</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="clear"></div>
            <div class="shadow"></div>
        </div>

        <!--Login div-->
        <div class="clear"></div>
        <div id="versionBar" >
            <div class="copyright" > &copy; Copyright 2013-2014  All Rights Reserved <span class="tip"><a href="http://www.fabercompany.co.jp/" title="Faber Company" target='_blank'>Faber Company</a> </span> </div>
            <!-- // copyright-->
        </div>
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script type='text/javascript' src='js/jquery-jrumble.js'></script>
        <script type='text/javascript' src='js/jquery.ui.min.js'></script>
        <script type='text/javascript' src='js/jquery.tipsy.js'></script>
        <script type='text/javascript' src='js/iphone.check.js'></script>
        <script type='text/javascript' src='js/login.js?ver=1.0'></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#error_maintenance').hide();
                $('#error').hide();
                $(".formForgot").hide();
                $("#username").focus();
                $("#forgot_pw").click(function () {
                    $(".formLogin").hide();
                    $(".formForgot").show();
                    $("#email").focus();
                    return false;
                });
                $(".back_login").click(function () {
                    $(".formLogin").show();
                    $(".formForgot").hide();
                    $("#username").focus();
                    return false;
                });
                $("#password").keyup(function (event) {
                    if (event.keyCode == 13) {
                        Login();
                    }
                });
                $("#email").keyup(function (event) {
                    if (event.keyCode == 13) {
                        Forgot();
                    }
                });
            });

            $('#but_login').on("click", function () {
                debugger;
                Login();
            });
            function Login() {
                if ($("#username").val() == "" || $("#password").val() == "") {
                    showError("Please Input Username / Password", 1500);
                    $('.inner').jrumble({x: 4, y: 0, rotation: 0});
                    $('.inner').trigger('startRumble');
                    setTimeout('$(".inner").trigger("stopRumble")', 500);
                    setTimeout('hideTop()', 5000);
                    return false;
                }
                hideTop();
                checkLogin();
            }
            var showErrorUsernamePassword = function () {
                showError("Wrong Username or password", 5000);
            };
            var showErrorUsernameMiss = function () {
                showError("Please fill Username and password", 5000);
            };
        </script>
    </body>
</html>
