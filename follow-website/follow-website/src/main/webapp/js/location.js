
//<editor-fold defaultstate="collapsed" desc="VARIABLE">
var param = function (name, value) {
    this.name = name;
    this.value = value;
};

var taskID;
var isAdmin = "false";
//</editor-fold>

var Location = function () {

    //<editor-fold defaultstate="collapsed" desc="INIT">
    return {
        init: function () {
            loadLocation();
            $('#btn_search').click(function () {
                $('#info').text('');
                var location = $('#canonical').val();
                var keyword = $('#keyword').val();
                if(location === "" || keyword === "") {
                    $('#info').text('These field are required');
                }
                else {
                    var option = $('#locations').html();
                    if(option.indexOf('<option>' + location + '</option>') === -1)
                        $('#info').text('this location is not valid');
                    else
                        searchGoogle(location, keyword);
                }
            });
        }
    };
    //</editor-fold>

}();

//<editor-fold defaultstate="collapsed" desc="LOAD LOCATION">
var loadLocation = function () {
    $.ajax({
        url: "getListLocation",
        type: "POST",
        success: function (response) {
            var option = "";
            response = $.parseJSON(response);
            for(var key in response) {
                option += '<option>' + response[key] + '</option>';
            }
            $('#locations').append(option);
        },
        error: function (e) {
            //showNotifications('Error load suggest avertisement crawling', "error");
        }
    });
};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="SEARCH GOOGLE">
var searchGoogle = function (location, keyword) {
    var filterParam = new Array();
    filterParam[filterParam.length] = new param("keyword", keyword);
    filterParam[filterParam.length] = new param("location", location);
    $.ajax({
        url: "searchGoogle",
        type: "POST",
        data: filterParam,
        success: function (response) {
            if(response === "error")
                alert('error search google');
            window.open("showCapture?image=" + response, '_blank'); 
        },
        error: function (e) {
            alert('error search google');
        }
    });
}
//</editor-fold>


