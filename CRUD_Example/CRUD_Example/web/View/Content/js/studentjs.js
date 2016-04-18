
//<editor-fold defaultstate="collapsed" desc="get List student">
var getListStudent = function () {

    $.ajax({
        type: 'POST',
        url: '/CRUD_Example/getListStudent',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            createTable(data);
        },
        error: function () {
            alert('error');
        }
    });
};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="get  student">
var getStudent = function (studentid) {

    $.ajax({
        type: 'POST',
        url: '/CRUD_Example/getStudent',
        data: {Id: studentid},
        dataType: 'json',
        success: function (data) {
            $("#name").val(data.strName);
            $("#address").val(data.strAddress);
            var d = $.datepicker.parseDate("M dd, yy", data.birthday);
            var datestrInNewFormat = $.datepicker.formatDate("yy-mm-dd", d);
            $("#birthday").val(datestrInNewFormat);
            $("#gender").val(data.strGender);
            $("#class_study").val(data.classId);

        },
        error: function () {
            alert('Can not find this student');
        }
    });
};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Add student">
var addStudent = function () {

// Get input
    var student = new Object();
    student.strName = $("#name").val();
    student.birthday = $("#birthday").val();
    student.strGender = $("#gender").val();
    student.strAddress = $("#address").val();
    student.receiveDay = $.datepicker.formatDate("yy-mm-dd", new Date());
    student.classId = $("#class_study").val();

    if (student.name === "" || student.birthday === "" || student.address === "") {
        alert("Please fill all textbox");
        return false;
    }
    if (!isValidDate(student.birthday)) {
        alert("Birthday is incorrect with format datetime");
        return false;
    }

    $.ajax({
        url: '/CRUD_Example/addNewStudent',
        type: 'post',
        data: {json: JSON.stringify(student)},
        success: function (data) {
            $(location).attr('href', '/CRUD_Example/studentmanager.jsp');
        },
        error: function (xhr, status, error) {
            alert(xhr.status);
        }
    });
};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Search student">
var searchStudent = function () {

    var Id = $("#id").val();
    var Name = $("#name").val();

    $.ajax({
        url: '/CRUD_Example/getListStudent',
        data: {Id: Id, Name: Name},
        type: 'post',
        dataType: 'json',
        success: function (data) {
            createTable(data);
        },
        error: function () {
            alert('error');
        }
    });
};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Update student">
var updateStudent = function (id) {

    // Get input
    var student = new Object();
    student.strId = id;
    student.strName = $("#name").val();
    student.birthday = $("#birthday").val();
    student.strGender = $("#gender").val();
    student.strAddress = $("#address").val();
    student.receiveDay = $.datepicker.formatDate("yy-mm-dd", new Date());
    student.classId = $("#class_study").val();
    
    if (student.name === "" || student.birthday === "" || student.address === "") {
        alert("Please fill all textbox");
        return false;
    }
    if (!isValidDate(student.birthday)) {
        alert("Birthday is incorrect with format datetime");
        return false;
    }
    
    $.ajax({
        url: '/CRUD_Example/updateStudent',
        type: 'post',
        data: {json: JSON.stringify(student)},
        success: function (data)
        {
            $(location).attr('href', '/CRUD_Example/studentmanager.jsp');
        },
        error: function () {
            alert('error');
        }
    });
};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Create table student">
var createTable = function (data) {
    var content = "<tbody>";
    $('#table1').find('tbody').remove();
    var len = data.length;
    if (len > 0) {
        for (var i = 0; i < len; i++) {
            content += "<tr><td>" + (i + 1) +
                    "</td><td>" + data[i].strId +
                    "</td><td>" + data[i].strName +
                    "</td><td>" + data[i].birthday +
                    "</td><td>" + data[i].strGender +
                    "</td><td>" + data[i].strAddress +
                    "</td><td><a href= \"updatestudent.jsp?ID=" + data[i].strId +
                    "\"><img src=\"img/images/Edit.png\" class = \"img-edit\"/>" +
                    "</td><td><a href= \"studentmanager.jsp?ID=" + data[i].strId +
                    "\" onclick = \"return confirm('Do you want to delete?' ); \">" +
                    "<img src=\"img/images/delete.png\" class = \"img-edit\"/>" +
                    "</td><td><a href= \"scoremanager.jsp?ID=" + data[i].strId +
                    "\"><img src=\"img/score.png\" class = \"img-edit\"/>" +
                    "</td></tr>";
        }
        content += "</tbody>";
        if (content !== "") {
            $("#table1").append(content);
        }
    }
};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Delete student">
var checkDelete = function (studentId) {
   
    $.ajax({
        url: '/CRUD_Example/deleteStudent',
        data: {studentId: studentId},
        type: 'post',
        dataType: 'json',
        success: function (data) {
            $(location).attr('href', '/CRUD_Example/studentmanager.jsp');
        },
        error: function () {
            alert('error');
        }
    });
};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Validate form when add student">

var isValidDate = function (dateString) {
    // First check for the pattern
    var regex_date = /^\d{4}\-\d{1,2}\-\d{1,2}$/;

    if (!regex_date.test(dateString))
    {
        return false;
    }

    // Parse the date parts to integers
    var parts = dateString.split("-");
    var day = parseInt(parts[2], 10);
    var month = parseInt(parts[1], 10);
    var year = parseInt(parts[0], 10);

    // Check the ranges of month and year
    if (year < 1000 || year > 3000 || month === 0 || month > 12)
    {
        return false;
    }

    var monthLength = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

    // Adjust for leap years
    if (year % 400 === 0 || (year % 100 !== 0 && year % 4 === 0))
    {
        monthLength[1] = 29;
    }

    // Check the range of the day
    return day > 0 && day <= monthLength[month - 1];
};
//</editor-fold>

