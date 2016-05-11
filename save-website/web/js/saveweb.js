var saveWebsite = function (urlPara) {
    $('#info').html('');
    $('#browseHistory').find('table').remove();
    var url = $('#url').val();
    if (urlPara !== '')
        url = urlPara;

    if (url === "") {
        $('#info').append("Please fill url");
        return false;
    }

    if (/^(?:(?:(?:https?|ftp):)?\/\/)(?:\S+(?::\S*)?@)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})).?)(?::\d{2,5})?(?:[/?#]\S*)?$/i.test(url)) {
        showSave(true);
        $('.container').css('opacity', '0.4');
        $.ajax({
            url: '/save-website/getContentWebsite',
            type: 'get',
            data: {url: url},
            success: function (data) {
                data = data.replace("**", "/");
                $(location).attr('href', '/save-website/showcontent.htm?url=' + data + "&&type=check");
            }, error: function (jqXHR, textStatus, errorThrown) {
                alert('error');
                showSave(false);
                $('.container').css('opacity', '');
            }
        });
    } else {
        $('#info').append('url is wrong format');
    }
    ;
};

var loadWeb = function () {
    var param = getParameter("url");
    var url = param;
    var type = "";
    if(param.indexOf("&&type=") > 0){
        url = param.substring(0, param.lastIndexOf("&&type="));
        type = param.substring(param.lastIndexOf("&&type=") + 7);
    }
    if (type === "checked") {
        loadFile(url);
    } else {
        if (type === "check") {
            loadFile(url);
            $(location).attr('href', '/save-website/showcontent.htm?url=' + url + "&&type=checked");
            
        } else {
            //Check url exist or not 
            $.ajax({
                url: "/save-website/checkUrl",
                type: "get",
                data: {url: url},
                success: function (data) {
                    
                    if (data !== "false") {
                        
                        loadFile(data);
                    } else {
                       
                        saveWebsite(url.substring(29));
                        //dialog_save_website_show(url);
                    }

                }, error: function (jqXHR, textStatus, errorThrown) {
                    alert('error');
                }
            });
        }
    }
};

var loadFile = function (link) {
    var head = link.substring(0, 29);
    link = link.substring(29);
    link = head + link.replace(/\//g, "**").replace(/\?/g, "++") + ".html";
    $.get(link, function (data) {

        var attribute = data.split("</head>")[1];
        attribute = attribute.split(">")[0];
        if (attribute.toLowerCase().indexOf("class=\"") >= 0) {
            attribute = attribute.split("class=\"")[1].split("\"")[0];
            $('body').addClass(attribute);
        }
        $('body').append(data);
    });
    $('link[href="css/style.css"]').remove();
    $('link[href="css/bootstrap.min.css"]').remove();
    showSave(false);
    $('#popup_save_website_related').hide();
};

var initdialog = function () {
    var url = getParameter("url");
    dialog_save_website_show(url);
};

var getParameter = function (name) {
    var results = new RegExp('[\?&]' + name + '=([^#]*)').exec(window.location.href);
    if (results === null) {
        return null;
    } else {
        return results[1] || 0;
    }
};

var viewHistory = function () {
    $('#info').html('');
    $('#browseHistory').find('table').remove();

    var url = $('#url').val();
    if (url === "") {
        $('#info').append("Please fill url");
        return false;
    }

    if (/^(?:(?:(?:https?|ftp):)?\/\/)(?:\S+(?::\S*)?@)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})).?)(?::\d{2,5})?(?:[/?#]\S*)?$/i.test(url)) {
        showWait(true);
        $.ajax({
            url: '/save-website/browseHistoryWebsite',
            type: 'get',
            data: {url: url},
            success: function (data) {
                var result = JSON.parse(data);
                var len = result.length;
                var content = "<table class='table table-bordered tbl_view'><thead>" +
                        "<tr><th>No</th>" + "<th>Time</th>" +
                        "<th>Action</th></tr></thead><tbody>";

                if (len > 0) {
                    for (var i = 0; i < len; i++) {
                        content += "<tr><td>" + (i + 1) +
                                "</td><td>" + result[i].timeSave +
                                "</td><td><a href= \"showcontent.htm?url=save/" + result[i].linkUrl + 
                                "&&type=checked\"><img src=\"images/view.png\" class = \"img-edit\" title = 'View' />" +
                                "</a></td></tr>";
                    }

                    content += "</tbody></table>";
                    $('#info').append('Find all ' + len + " result");
                    $('#browseHistory').append(content);
                } else {
                    dialog_save_website_show(url);
                }
                showWait(false);
            }, error: function (jqXHR, textStatus, errorThrown) {
                alert('error');
                showWait(false);
            }
        });
        //showWait(false);
    } else {
        $('#info').append('url is wrong format');
    }
    ;
};