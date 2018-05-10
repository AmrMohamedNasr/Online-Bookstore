<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <link rel="stylesheet" type="text/css" href="css/nav_bar.css" />
      <script type="text/javascript" src="js/jquery-3.3.1.min.js" ></script>
      <title>Login</title>
      <script type="text/javascript">
			$(function() {
				$("#loginNav").attr('class','active');
			});
		</script>
		<link rel="stylesheet" type="text/css" href="css/general.css" />
   </head>
   <body>
 
      <jsp:include page="_menu.jsp"></jsp:include>   
 <div class='pagebody'>
      <h3>Login Page</h3>
 		<hr>
      <p style="color: red;">${errorMessage}</p>
 
      <form method="POST" action="${pageContext.request.contextPath}/login">
         <input type="hidden" name="redirectId" value="${param.redirectId}" />
 		<label for="userName"><b>Username</b></label><br>
        <input type="text" name="userName" value= "${user.userName}" placeholder="Enter username" required>
        <br><label for="userName"><b>Password</b></label><br>
       	<input type="password" name="password" value= "${user.password}" placeholder="Enter password" required/>
       <br><br>
        <input type="submit" value= "Log In" />
      </form>
      
  	
 </div>
   </body>
</html>