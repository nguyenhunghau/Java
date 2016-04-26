<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <title>Get ranking url</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js" ></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script>

            $(document).ready(function () {
                $('#btn_search').click(function () {
                    getRanking();
                });

                $(document).keypress(function (e) {
                    if (e.which == 13) {
                        getRanking();
                    }
                });

                var getRanking = function () {
                    $('#info_keyword').html('');
                    $('#info_url').html('');
                    $('#info_ranking').html('');
                    $('#error').html('');

                    var ranking = new Object();
                    ranking.keyword = $('#keyword').val();
                    ranking.url = $('#url').val();
                    ranking.type = $('#type').val();

                    if (ranking.keyword === "" || ranking.url === "") {
                        $('#error').append("Please fill keyword and url");
                        return false;
                    }

                    if (/^(?:(?:(?:https?|ftp):)?\/\/)(?:\S+(?::\S*)?@)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})).?)(?::\d{2,5})?(?:[/?#]\S*)?$/i.test(ranking.url)) {
                        var json = JSON.stringify(ranking);
                        $.ajax({
                            url: '/ranking-tool/getRankingUrl',
                            type: 'get',
                            data: {json: json},
                            success: function (data) {
                                $('#info_keyword').append("<strong>keyword:</strong> " + ranking.keyword);
                                $('#info_url').append("<strong>url:</strong> " + ranking.url);
                                $('#info_ranking').append("<strong>ranking:</strong> " + data);
                            }, error: function (jqXHR, textStatus, errorThrown) {
                                alert('error');
                            }
                        });
                    } else {
                        $('#error').append('url is wrong format');
                    }
                };
            });
        </script>

    </head>
    <body>
        <div class="container">
            <div class="row" style="margin-top: 50px;">
                <div class="col-md-2"></div>
                <div class="col-md-8" style="background:#DCDCDC; padding: 30px;">
                    <div class="row">
                        <h2 >Ranking - tool</h2>
                        <div class="form-group">
                            <label for="keyword">Keyword:</label>
                            <input type="text" class="form-control" id="keyword">
                        </div>
                        <div class="form-group">
                            <label for="url">Url:</label>
                            <input type="text" class="form-control" id="url" >
                        </div>
                        <div class="form-group">
                            <label for="type">Type:</label>
                            <select class="form-control" id="type">
                                <option value="1" selected = "selected">Seach ranking by domain</option>
                                <option value="2">Seach ranking by Url</option>
                            </select>
                        </div>
                        <div style="color: red">
                            <label id = "error"></label>
                        </div>
                        <button class="btn btn-default" id = "btn_search">Search</button>
                    </div>
                    <div class="row" style="margin-left: 10px;font-size: 9pt;color: green">
                        <div class="row">
                            <h3 id = "info_keyword"></h3>
                        </div>
                        <div class="row">
                            <h3 id = "info_url"></h3>
                        </div>
                        <div class="row">
                            <h3 id = "info_ranking"></h3>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">

                </div>
            </div>
        </div>

    </body>
</html>

