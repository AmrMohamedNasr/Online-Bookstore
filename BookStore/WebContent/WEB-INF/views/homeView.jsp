<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <link rel="stylesheet" type="text/css" href="css/nav_bar.css" />
      <link rel="stylesheet" type="text/css" href="css/general.css" />
      <script type="text/javascript" src="js/jquery-3.3.1.min.js" ></script>
      <title>Home Page</title>
      <script type="text/javascript">
			$(function() {
				$("#homeNav").attr('class','active');
			});
		</script>
   </head>
   <body>
    
      <jsp:include page="_menu.jsp"></jsp:include>
    <div class='pagebody'>
      <h3>Books4Fun Home Page</h3><hr>
             <p> Welcome to books4fun online store, where we offer the latest books and you can buy online any book you want
             and we will ship it to you for free !</p>
   </div>
   </body>
</html>