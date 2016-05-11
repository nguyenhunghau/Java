<%-- 
    Document   : wait
    Created on : Apr 28, 2016, 11:51:07 PM
    Author     : Nguyen Hung Hau
--%>

<script type="text/javascript">
    var showWait = function(show) {
        if (show) {
            $("#loading-div-background").show();
        } else {
            $("#loading-div-background").hide();
        }
    };
</script>
<div id="alertMessage" class="error"></div>
<div id="loading-div-background" class="watingbackground">
    <div id="loading-div" >
        <img src="images/loading.gif" alt="Loading.."/>
    </div>
</div>
