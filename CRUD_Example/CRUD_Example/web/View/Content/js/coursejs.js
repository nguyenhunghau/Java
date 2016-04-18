
//<editor-fold defaultstate="collapsed" desc="Get list course">
var getListCourse = function () {
    $.ajax({
        type: 'POST',
        url: '/CRUD_Example/CourseServlet',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            var len = data.length;
            if (len > 0) {
                for (var i = 0; i < len; i++) {

                    $('#course').append($('<option>').text(data[i].strYearBegin + " - " + data[i].strYearEnd)
                            .attr('value', data[i].id));
                }
                $('#course').val(data[0].id);
            }
        }
    });
};
//</editor-fold>
