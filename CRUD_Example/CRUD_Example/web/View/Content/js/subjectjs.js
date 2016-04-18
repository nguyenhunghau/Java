
//<editor-fold defaultstate="collapsed" desc="Get list subject">
var getListSubject = function () {
    $.ajax({
        url: '/CRUD_Example/getListSubject',
        type: 'post',
        cache: false,
        success: function (data) {
            var result = JSON.parse(data);
            var len = result.length;
            if (len > 0) {
                for (var i = 0; i < len; i++) {
                    $('#subject').append($('<option>').text(result[i].strName).attr('value', result[i].id));
                }
            }
        },
        error: function () {
            alert('error');
        }
    });
};
//</editor-fold>
