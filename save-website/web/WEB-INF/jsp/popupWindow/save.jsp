<%-- 
    Document   : wait
    Created on : Apr 28, 2016, 11:51:07 PM
    Author     : Nguyen Hung Hau
--%>

<script type="text/javascript">
    var showSave = function(show) {
        if (show) {
            $("#saving-div-background ").show();
        } else {
            $("#saving-div-background ").hide();
        }
    };
</script>

<div id="saving-div-background" class="watingbackground">
    <div id="loading-div" >
        <img src="images/saving.gif" alt="Saving.."/>
    </div>
</div>
