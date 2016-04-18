<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add new class</title>
        <link href="Css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="Css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="Css/index.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery.js"></script>
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <link href="Css/main.css" rel="stylesheet" type="text/css"/>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap.js"></script>

        <script src="js/coursejs.js" type="text/javascript"></script>

       
    </head>
    <body>

            <form>
                <div class="col-md-9" onsubmit="abc()">
                            <input type="text" class = "form-control"  id="name"/>
                        </div>
                <div>
                    <button type="submit"  class="btn div-submit-index" id = "btn_addClass">Add new class</button>
                </div>
            </form>
         <script>
           function abc(){
                alert('dsf');
           }
          $( "form" ).submit(function() {
              alert('dsf');
    return;
  });

        </script>
    </body>
</html>