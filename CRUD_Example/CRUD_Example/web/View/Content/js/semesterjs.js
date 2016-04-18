//<editor-fold defaultstate="collapsed" desc="Get list semester">
var getListSemester = function (course) {
    $.ajax({
        url: '/CRUD_Example/getListSemester',
        data: {course: course},
        type: 'post',
        cache: false,
        success: function (data) {
            var result = JSON.parse(data);
            var len = result.length;
            if (len > 0) {
                for (var i = 0; i < len; i++) {
                    $('#semester').append($('<option>').text(result[i].strName).attr('value', result[i].id));
                }
            }
        },
        error: function () {
            alert('error');
        }
    });
};
//</editor-fold>
