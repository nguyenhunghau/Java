<%-- 
    Document   : wait
    Created on : Apr 28, 2016, 11:51:07 PM
    Author     : Nguyen Hung Hau
--%>

<script type="text/javascript">
    var showSave = function (show) {
        if (show) {
            $("#saving-div-background ").show();
            $('.container').css('opacity', '0.4');

        } else {
            $("#saving-div-background ").hide();
            $('.container').css('opacity', '');
        }
    };
</script>

<div id="saving-div-background" class="watingbackground">
    <div id="loading-div1" >
        <img src="images/saving.gif" alt="Saving.."/>
    </div>
</div>
