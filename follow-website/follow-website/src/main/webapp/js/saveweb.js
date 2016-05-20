var saveWebsite = function (urlPara, idHtml, idCapture) {
    $('#info').html('');
    $('#schedule').hide();
    $('#browseHistory').find('table').remove();
    var url = $('#url').val();
    if (urlPara !== '')
        url = urlPara;
    if (url === "") {
        $('#info').append("Please fill url");
        return false;
    }
    if ($(idHtml).is(":checked")) {
        if ($('#capture').is(":checked")) {
            url += "#*type=both";
        } else {
            url += "#*type=html";
        }
    } else {
        if ($(idCapture).is(":checked")) {
            url += "#*type=capture";
        } else {
            $('#info').append('choose at least one option');
            return false;
        }
    }

    if (/^(?:(?:(?:https?|ftp):)?\/\/)(?:\S+(?::\S*)?@)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})).?)(?::\d{2,5})?(?:[/?#]\S*)?$/i.test(url)) {
        showSave(true);
        $.ajax({
            url: 'getContentWebsite',
            type: 'get',
            data: {url: url},
            success: function (data) {
                createTable(data);
                showSave(false);
            }, error: function (jqXHR, textStatus, errorThrown) {
                alert('error');
                showSave(false);
            }
        });
    } else {
        $('#info').append('url is wrong format');
    }
    ;
};

var loadWeb = function () {
    
    var param = getParameter("url");
    var parts = param.split("/");
    var url = param;
    var type = "";
    if (param.lastIndexOf("&&type=") > 0) {
        url = param.substring(0, param.lastIndexOf("&&type="));
        type = param.substring(param.lastIndexOf("&&type=") + 7);
    }
    if (type == "checked") {
        
        loadFile(url);
    } else {
        debugger;
        showSave(true);
        //Check url exist or not 
        $.ajax({
            url: "checkUrl",
            type: "get",
            data: {url: url},
            success: function (data) {

                if (data !== "false") {
                    loadFile(data);
                } else {
                    dialog_save_website_show(url);
                }
                showSave(false);

            }, error: function (jqXHR, textStatus, errorThrown) {
                alert('error');

            }
        });
    }
    showSave(false);
};

var loadFile = function (link) {
    var parts = link.split("/");
    var head = parts[0] + "/" + parts[1] + "/" + parts[2];
    link = link.substring(link.indexOf(parts[2]) + 24);
    link = head + "/" + link.replace(/\//g, "**").replace(/\?/g, "++") + ".html";
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
    $('#urlHidden').val(url);
    if (url === "") {
        $('#info').append("Please fill url");
        return false;
    }
    if (/^(?:(?:(?:https?|ftp):)?\/\/)(?:\S+(?::\S*)?@)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})).?)(?::\d{2,5})?(?:[/?#]\S*)?$/i.test(url)) {
        showWait(true);
        $.ajax({
            url: 'viewHistory',
            type: 'get',
            data: {url: url},
            success: function (response) {
                var result = JSON.parse(response);
                var len = result.length;
                var content = "<table class='table table-bordered tbl_view' id = 'myTable'><thead>" +
                        "<tr><th>PC</th><th>Tablet</th><th>Mobile</th><th>Html</th>" +
                        "<th>Time save</th><th>Delete</th></tr></thead><tbody>";
                if (len > 0) {
                    $('#frequency').val(result[len - 1]);
                    for (var i = 0; i < len - 1; i++) {
                        content += createRow(result[i], i, true);
                    }
                    
                    content += "</tbody></table>";
                    //$('#info').append('Find all ' + len + " result");
                    $('#browseHistory').append(content);
                    $('#schedule').show();
                } else {
                    dialog_save_website_show(url);
                }
                showWait(false);
            }, error: function (jqXHR, textStatus, errorThrown) {
                alert('error');
                showWait(false);
            }
        });
    } else {
        $('#info').append('url is wrong format');
    }
    ;
};

var createTable = function (data) {
    var frequency = data.substring(data.lastIndexOf("&&frequency=") + 12);
    data = data.substring(0,data.lastIndexOf("&&frequency="));
    var head = "<table class='table table-bordered tbl_view' id = 'myTable'><thead>" +
            "<tr><th>PC</th><th>Tablet</th><th>Mobile</th><th>Html</th>" +
            "<th>Time save</th><th>Frequency</th><th>Delete</th></tr></thead><tbody>";
    var content = createRow(data, 1, false);
    $('#browseHistory').append(head + content + "</tbody></table>");
    addOptionForSelect(frequency);
};

var createRow = function (data, id, history) {
    id = "row" + id;
    var parts = data.split('/');
    var html = "", content = "", select = "";
    var urlReal = data.substring(data.indexOf(parts[1]) + 24);
    var urlPC = "save/" + parts[0] + "/" + parts[1] + "/capture/capturepc.jpg";
    var linkHtml = "save/" + parts[0] + "/" + parts[1] + "/" +
            urlReal.replace(/\//g, "**").replace(/\?/g, "++") + ".html";
    if (!history)
        select = "<td><select onchange='changeSchedule(\"" + urlReal + "\")' " +
                "id = 'timetable' class = 'form-control'></select></td>";
    if (existUrl(linkHtml)) {
        html = "<img src=\"images/view.png\" class = \"img-edit\" " +
                "onclick = 'showhtml(\"save/" + data + "\")'/>";
    } else {
        html = "N/A";
    }

    if (existUrl(urlPC)) {
        var urlTablet = "save/" + parts[0] + "/" + parts[1] + "/capture/capturetablet.jpg";
        var urlMobile = "save/" + parts[0] + "/" + parts[1] + "/capture/capturemobile.jpg";
        var time = parts[1].split("_")[0];
        content += "<tr id = '" + id + "'><td><img src='images/view.png' class = 'img-edit' onclick = 'showimage(\"" +
                urlPC + "\")'/>" + "</td><td><img src='images/view.png' class = 'img-edit'" +
                "onclick = 'showimage(\"" + urlTablet + "\")'/>" + "</td><td>" +
                "<img src='images/view.png' class = 'img-edit'" + "onclick = 'showimage(\"" +
                urlMobile + "\")'/>" + "</td><td>" + html + "</td><td>" + time +
                "</td>" + select + "<td>" + "<img src='images/delete.png'" +
                "class = 'img-edit' onclick = 'deleteUrl(\"" + urlReal + "\",\"#" + id + "\",\"" + time + "\")' /></td></tr>";

    } else {
        content += "<tr><td>N/A</td><td>N/A</td><td>N/A</td><td>" +
                +html + "</td><td>" + time + "</td>" + select + "<td><img src='images/delete.png'" +
                " class = 'img-edit' onclick = 'deleteUrl(\"" + urlReal + "\",\"" + time + "\")' /></td></tr>";
    }
    return content;
};

var showimage = function (link) {
    var url = "showimage.htm?url=" + link;
    var win = window.open(url, '_blank');
    if (win)
        focus();
};

var showhtml = function (link) {
    var url = "showcontent.htm?url=" + link + "&&type=checked";
    var win = window.open(url, '_blank');
    if (win)
        focus();
};

var addOptionForSelect = function (frequency) {
    var option = "<option value = '1'>1 day</option>" +
            "<option value = '2'>2 days</option>" +
            "<option value = '3'>3 days</option>" +
            "<option value = '7'>1 week</option>" +
            "<option value = '10'>10 days</option>" +
            "<option value = '15'>15 days</option>" +
            "<option value = '30'>1 month</option>";
    $('#timetable').append(option);
    $('#timetable').val(frequency);
};

var existUrl = function (url) {
    var result = true;
    $.ajax({
        url: url,
        type: "HEAD",
        error: function () {
            result = false;
        }
    });
    return result;
};

var changeSchedule = function (url) {
    var time = $('#timetable').val();
    if (url == '') {
        url = $('#urlHidden').val();
        time = $('#frequency').val();
    }
    showSave(true);
    $.ajax({
        url: 'changeSchedule',
        type: 'get',
        data: {url: url + "#*time=" + time},
        success: function (data) {
            showSave(false);
        }, error: function (jqXHR, textStatus, errorThrown) {
            alert('error');
            showSave(false);
            $('.container').css('opacity', '');
        }
    });
};

var deleteUrl = function (url, id, time) {
    showSave(true);
    $.ajax({
        url: 'deleteUrl',
        type: 'get',
        data: {url: url + "#*time=" + time},
        success: function (data) {
            if (data === "true") {
                $(id).remove();
                var rowCount = $('#myTable >tbody >tr').length;
                if (rowCount == 0) {
                    $('#myTable').remove();
                    $('#schedule').hide();
                }
            }
            showSave(false);
        }, error: function (jqXHR, textStatus, errorThrown) {
            alert('error');
            showSave(false);
            $('.container').css('opacity', '');
        }
    });
};