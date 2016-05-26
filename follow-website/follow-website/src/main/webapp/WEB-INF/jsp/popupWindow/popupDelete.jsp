<%-- 
    Document   : popupDelete
    Created on : Dec 26, 2013, 12:41:30 AM
    Author     : LeDinhTuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(document).ready(function () {
        $('#no').click(function () {
            dialog_delete_hide();
        });

        $('#yes').click(function () {
            var url = $('#paraUrl').val();
            var id = $('#paraId').val();
            var time = $('#paraTime').val();
            deleteUrl(url, id, time);
            multiDeleteAccount();
            dialog_delete_hide();
        });
    });
    var dialog_delete_show = function (url, id, time) {
        $('#paraUrl').val(url);
        $('#paraId').val(id);
        $('#paraTime').val(time);
        $("#popup_delete").stop().fadeTo(500, 1);
        $("#overlay").stop().fadeTo(500, 0.7);
    };
    var dialog_delete_hide = function () {
        $("#popup_delete").stop().fadeOut(500);
        $("#overlay").stop().fadeOut(1000);
    };
</script>
<div id="popup_delete">
    <p class="pop_01">Delete</p>
    <p class="pop_02">Do you want delete item selected?</p>
    <div >
        <a id="yes" class="get_ideads">Delete</a>
        <a id="no" class="get_ideads">Cancel</a>
    </div>
</div>
<div style="display:none">
    <p id="paraUrl"></p>
    <p id="paraId"></p>
    <p id="paraTime"></p>
</div>
<div id="overlay"></div>