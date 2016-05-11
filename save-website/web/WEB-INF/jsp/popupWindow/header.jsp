<%-- 
    Document   : header
    Created on : May 3, 2016, 11:12:04 PM
    Author     : Nguyen Hung Hau
--%>

<script type="text/javascript">
    var showHeader = function (show) {
        if (show) {
            $("#header").show();
        } else {
            $("#header").hide();
        }
    };
     $("#close").click(function () {
         $("#header").hide();
     });
    
</script>
<div id="header" class="row">
    <div class = "col-md-2 col-sm-2">
        <a href="/index.htm"><img src="images/logo.gif" alt="" class="img_logo"/></a>
    </div>
    <div class = "col-md-8 col-sm-8">
        <p style="text-align: center;" id = "current_url"></p>
    </div>
    <div class = "col-md-2 col-sm-2">
         <a id = "close"><img src="images/logo.gif" alt="" class="img_logo"/></a>
    </div>
</div>
