
//<editor-fold defaultstate="collapsed" desc="Update class">
var updateClass = function (id) {

    // Get input
    var classStudy = new Object();
    classStudy.strName = $("#name").val();
    if (classStudy.strName === "") {
        alert("Please fill name of class");
        return false;
    }

    classStudy.id = id;
    classStudy.courseId = $("#course").val();
    $.ajax({
        url: '/CRUD_Example/updateClass',
        data: {json: JSON.stringify(classStudy)},
        type: 'post',
        success: function (data)
        {
           $(location).attr('href', '/CRUD_Example/classmanager.jsp');
        },
        error: function () {
            $(location).attr('href', '/CRUD_Example/classmanager.jsp');
        }
    });
};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Get list class">
var getListClass = function (course) {
    $.ajax({
        url: '/CRUD_Example/getListClass',
        data: {course: course},
        type: 'post',
        cache: false,
        success: function (data) {
            var result = JSON.parse(data);
            var len = result.length;
            if (len > 0) {
                for (var i = 0; i < len; i++) {
                    $('#class_study').append($('<option>').text(result[i].strName).attr('value', result[i].id));
                }
            }
        },
        error: function () {
            alert('error');
        }
    });
};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Get table class">
var getTableClass = function () {
    $.ajax({
        url: '/CRUD_Example/getListClass',
        data: {course: ""},
        type: 'post',
        cache: false,
        success: function (data) {

            var result = JSON.parse(data);
            var len = result.length;
            if (len > 0) {
                for (var i = 0; i < len; i++) {
                    content += "<tr><td>" + (i + 1) +
                            "</td><td>" + result[i].courseId +
                            "</td><td>" + result[i].strName +
                            "</td><td><a href= \"updateclass.jsp?ID=" + result[i].id +
                            "\"><img src=\"img/images/Edit.png\" class = \"img-edit\"/>" +
                            "</td><td><a href= \"classmanager.jsp?ID=" + result[i].id +
                            "\" onclick = \"return confirm('Do you want to delete?' );\" >" +
                            "<img src=\"img/images/delete.png\" class = \"img-edit\"/>" +
                            "</td></tr>";
                }

                if (content !== "") {
                    $("#table1").append(content);
                }
            }
        },
        error: function () {
            alert('error');
        }
    });
};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Validate form create class">
/**var validateClass = function () {
 
 $("#myform").validate({
 rules: {
 name: {
 required: true,
 }
 },
 messages: {
 name: {
 required: "Please enter a name"
 }
 }
 });
 /**var name = $("#name").val();
 
 if (name.value === "") {
 alert("Please fill name of class");
 return false;
 }*/

//};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Add class">
var addClass = function () {

    // Get input
    var classStudy = new Object();
    classStudy.strName = $("#name").val();
    if (classStudy.strName === "") {
        alert("Please fill name of class");
        return false;
    }

    classStudy.courseId = $("#course").val();

    $.ajax({
        url: '/CRUD_Example/addNewClass',
        data: {json: JSON.stringify(classStudy)},
        type: 'post',
        success: function (data) {
            $(location).attr('href', '/CRUD_Example/classmanager.jsp');
        },
        error: function (xhr, status, error) {
             $(location).attr('href', '/CRUD_Example/classmanager.jsp');
        }
    });
};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Get parameter">
var getParameter = function (name) {
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results === null) {
        return null;
    } else {
        return results[1] || 0;
    }
};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Get class">
var getClass = function (id) {

    $.ajax({
        url: '/CRUD_Example/getClass',
        data: {id: id},
        type: 'post',
        cache: false,
        success: function (data) {
            var result = JSON.parse(data);

            $("#course").val(result.courseId);
            $("#name").val(result.strName);

        },
        error: function () {
            alert('error');
        }
    });
};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Delete class">
var deleteClass = function (Id) {
    $.ajax({
        url: '/CRUD_Example/deleteClass',
        data: {Id: Id},
        type: 'post',
        dataType: 'json',
        success: function (data) {
            $(location).attr('href', '/CRUD_Example/classmanager.jsp');
        },
        error: function () {
            $(location).attr('href', '/CRUD_Example/classmanager.jsp');
        }
    });
};
//</editor-fold>
