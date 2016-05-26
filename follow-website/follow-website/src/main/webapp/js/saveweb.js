var param = function (name, value) {
    this.name = name;
    this.value = value;
};

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
    var filterParam = new Array();
    var type;
    if ($(idHtml).is(":checked")) {
        if ($('#capture').is(":checked")) {
            type = "both";
        } else {
            type = "html";
        }
    } else {
        if ($(idCapture).is(":checked")) {
            type = "capture";
        } else {
            $('#info').append('choose at least one option');
            return false;
        }
    }
    filterParam[filterParam.length] = new param("url", url);
    filterParam[filterParam.length] = new param("type", type);
    
    if (/^(?:(?:(?:https?|ftp):)?\/\/)(?:\S+(?::\S*)?@)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})).?)(?::\d{2,5})?(?:[/?#]\S*)?$/i.test(url)) {

        showSave(true);
        $.ajax({
            url: 'getContentWebsite',
            type: 'get',
            data: {para: filterParam},
            success: function (data) {
                var result = JSON.parse(data);
                if (idHtml === '#htmlWindow') {
                    if (typeof result.html !== "undefined") {
                        window.location.replace("showcontent.htm?url=save/" + result.html + "&&type=checked");
                    } else {
                        window.location.replace("showcontent.htm?url=save/" + result.pc);
                    }
                } else {
                    createTable(result);
                }

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
                    $('#frequency').val(result[0].frequent);
                    for (var i = 0; i < len; i++) {
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

    var head = "<table class='table table-bordered tbl_view' id = 'myTable'><thead>" +
            "<tr><th>PC</th><th>Tablet</th><th>Mobile</th><th>Html</th>" +
            "<th>Time save</th><th>Frequency</th><th>Delete</th></tr></thead><tbody>";
    var content = createRow(data, 1, false);

    $('#browseHistory').append(head + content + "</tbody></table>");
    addOptionForSelect(data.frequent);
};

var createRow = function (data, id, history) {
    id = "row" + id;
    var html = "Emply";
    var urlPC = "Emply";
    var urlTablet = "Emply";
    var urlMobile = "Emply";
    var d = $.datepicker.parseDate("M dd, yy", data.timeSave);
    var time = $.datepicker.formatDate("yy-mm-dd", d);
    var select = "";

    if (typeof data.html !== "undefined")
        html = "<img src='images/view.png' class = 'img-edit'" +
                "onclick = 'showFile(\"" + data.html + "\")'/>";
    if (typeof data.pc !== "undefined") {
        urlPC = "<img src='images/view.png' class = 'img-edit'" +
                "onclick = 'showFile(\"" + data.pc + "\")'/>";
    }
    if (typeof data.tablet !== "undefined")
        urlTablet = "<img src='images/view.png' class = 'img-edit'" +
                "onclick = 'showFile(\"" + data.tablet + "\")'/>";
    if (typeof data.mobile !== "undefined")
        urlMobile = "<img src='images/view.png' class = 'img-edit'" +
                "onclick = 'showFile(\"" + data.mobile + "\")'/>";

    if (!history)
        select = "<td><select onchange='changeSchedule(\"" + data.linkUrl + "\")' " +
                "id = 'timetable' class = 'form-control'></select></td>";

    var content = "<tr id = '" + id + "'><td>" + urlPC + "</td><td>" + urlTablet +
            "</td><td>" + urlMobile + "</td><td>" + html + "</td><td>" + time +
            "</td>" + select + "<td>" + "<img src='images/delete.png' class = 'img-edit'" +
            "onclick = 'showDialogDelete(\"" + data.linkUrl + "\",\"#" + id + "\",\"" + time + "\")' /></td></tr>";

    return content;
};

var showFile = function (link) {
    var url = "loadFile?url=" + link;
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

var changeSchedule = function (url) {
    var time = $('#timetable').val();
    var filterParam = new Array();
    if (url === '') {
        url = $('#urlHidden').val();
        time = $('#frequency').val();
    }
    filterParam[filterParam.length] = new param("url", url);
    filterParam[filterParam.length] = new param("time", time);
    
    showSave(true);
    $.ajax({
        url: 'changeSchedule',
        type: 'get',
        data: {param: filterParam},
        success: function (data) {
            showSave(false);
        }, error: function (jqXHR, textStatus, errorThrown) {
            alert('error');
            showSave(false);
            $('.container').css('opacity', '');
        }
    });
};

var showDialogDelete = function (url, id, time) {
    dialog_delete_show(url, id, time);
};

var deleteUrl = function (url, id, time) {
    showSave(true);
    var filterParam = new Array();
    filterParam[filterParam.length] = new param("url", url);
    filterParam[filterParam.length] = new param("time", time);
    $.ajax({
        url: 'deleteUrl',
        type: 'get',
        data: {param: filterParam},
        success: function (data) {
            if (data === "true") {
                $(id).remove();
                var rowCount = $('#myTable >tbody >tr').length;
                if (rowCount === 0) {
                    $('#myTable').remove();
                    $('#schedule').hide();
                }
                dialog_delete_hide();
            }
            showSave(false);
        }, error: function (jqXHR, textStatus, errorThrown) {
            alert('error');
            showSave(false);
            dialog_delete_hide();
            $('.container').css('opacity', '');
        }
    });
};