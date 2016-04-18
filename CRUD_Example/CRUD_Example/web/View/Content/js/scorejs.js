
//<editor-fold defaultstate="collapsed" desc="Get list score">
var getListScore = function (StudentId) {

    var SemesterId = $("#semester").val();
    $.ajax({
        url: '/CRUD_Example/getListScore',
        data: {StudentId: StudentId, SemesterId: SemesterId},
        type: 'post',
        dataType: 'json',
        
        success: function (data) {
            var content = "<tbody>";
            $('#table1').find('tbody').remove();
            var len = data.length;
            if (len > 0) {
                for (var i = 0; i < len; i++) {
                    content += "<tr><td>" + (i + 1) +
                                "</td><td>" + data[i].strNameSemester +
                                "</td><td>" + data[i].strNameSubject +
                                "</td><td>" + data[i].score_1 +
                                "</td><td>" + data[i].score_2 +
                                "</td><td>" + data[i].score_3 +
                                "</td><td>" + data[i].average +
                                "</td><td><a href= \"updatescore.jsp?ID=" + data[i].Id + "&&StudentId=" + StudentId +
                                "\"><img src=\"img/images/Edit.png\" class = \"img-edit\"/>" +
                                "</td><td><a href= \"scoremanager.jsp?scoreId=" + data[i].Id + "&&ID=" + StudentId +
                                "\" onclick = \"return confirm('Do you want to delete?' ); \">" +
                                "<img src=\"img/images/delete.png\" class = \"img-edit\"/>" +
                                "</td></tr>";
                }
                content += "</tbody>";
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

//<editor-fold defaultstate="collapsed" desc="Update score">
var updateScore = function (id, studentid) {
debugger
    // Get input
    var score = new Object();
    score.Id = id;
    score.subjectId = $("#subject").val();
    score.score_1 = $("#score_1").val();
    score.score_2 = $("#score_2").val();
    score.score_3 = $("#score_3").val();
    $.ajax({
        url: '/CRUD_Example/updateScore',
        data: {json: JSON.stringify(score)},
        type: 'post',
        success: function (data)
        {
             $(location).attr('href', '/CRUD_Example/scoremanager.jsp?ID=' + studentid);
        },
        error: function () {
            alert('error');
        }
    });
};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Get score">
var getScore = function (id) {
    $.ajax({
        url: '/CRUD_Example/getScore',
        data: {id: id},
        type: 'post',
        cache: false,
        success: function (data) {
            var result = JSON.parse(data);

            $("#subject").val(result.subjectId);
            $("#score_1").val(result.score_1);
            $("#score_2").val(result.score_2);
            $("#score_3").val(result.score_3);

        },
        error: function () {
            alert('error');
        }
    });
};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Delete score">
var deleteScore = function (Id,studentid) {
    $.ajax({
        url: '/CRUD_Example/deleteScore',
        data: {Id: Id},
        type: 'post',
        dataType: 'json',
        success: function (data) {
            $(location).attr('href', '/CRUD_Example/scoremanager.jsp?ID=' + studentid);
        },
        error: function () {
            $(location).attr('href', '/CRUD_Example/scoremanager.jsp?ID=' + studentid);
        }
    });
};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Validate form update score">
var validate = function (evt) {
                var theEvent = evt || window.event;
                var key = theEvent.keyCode || theEvent.which;
                key = String.fromCharCode( key );
                var regex = /[0-9]|\./;
                if( !regex.test(key) ) {
                  theEvent.returnValue = false;
                  if(theEvent.preventDefault) theEvent.preventDefault();
                }
              };
//</editor-fold>
