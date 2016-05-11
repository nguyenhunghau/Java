<%-- 
    Document   : savewindow
    Created on : Apr 29, 2016, 12:40:58 AM
    Author     : Nguyen Hung Hau
--%>

<script type="text/javascript">
    $(document).ready(function () {
        $('#cancel').click(function () {
            parent.history.back();
        });

        $('#save').click(function () {
            dialog_save_website_hide();
            var url = $("#p_link").text();
            saveWebsite(url);
        });
    });
    var dialog_save_website_show = function (link) {
        
        $("#p_link").append(link);
        $("#link").attr("href", "showcontent.htm?url=" + link.substring(29));
        $("#popup_save_website_related").stop().fadeTo(500, 1);
        $("#overlay").stop().fadeTo(500, 0.7);
        $(".FixedHeader_Header").attr("id", "content").css("z-index", "1001").css("min-width", "584px");
    };
    var dialog_save_website_hide = function () {
        $("#popup_save_website_related").stop().fadeOut(100);
        $("#overlay").stop().fadeOut(1000);
    };
</script>
<div id="popup_save_website_related" class="container" style=" left: 20%; min-height: 150px;">
    <div class="row">
        <a id = "link"><p class="info" id = "p_link"  style="margin-left: 10px" ></p></a>
    </div>
    <div class="row">
        <p class="info" id = "notsave">This url is not yet saved. Do you want to save this url?</p>
    </div>

    <div class="row">
        <div class="col-md-3 col-sm-3"></div>
        <div class="col-md-3 col-sm-3">
            <div class="button">
                <a id="save" class="btn btn-main">Save</a>
            </div>
        </div>
        <div class="col-md-3 col-sm-3">
            <div class="button">
                <a id="cancel" class="btn btn-main">Cancel</a>
            </div>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>
<div id="overlay"></div>

