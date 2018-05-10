<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-3.3.1.min.js" ></script>
<link rel="stylesheet" type="text/css" href="css/nav_bar.css" />
<link rel="stylesheet" type="text/css" href="css/general.css" />
<title>Registration Page</title>
<script type="text/javascript">
	$(function() {
		$("#registerNav").attr('class','active');
	});
</script>
</head>
<body>
<jsp:include page="_menu.jsp"></jsp:include>   
<div class='pagebody'>
      <h3>Registration Page</h3>
 		<hr>
      <p style="color: red;">${errorMessage}</p>
 
      <form method="POST" action="${pageContext.request.contextPath}/register">
               <label for="firstName"><b>First Name</b></label><br>
               <input type="text" name="firstName" value= "${firstName}" placeholder = "Enter First Name" required/> <br>

               <label for="lastName"><b>Last Name</b></label><br>
               <input type="text" name="lastName" value= "${lastName}" placeholder = "Enter Last Name" required/> <br>

               <label for="username"><b>Username</b></label><br>
               <input type="text" name="username" value= "${username}" placeholder = "Enter Username" required/> <br>

               <label for="password"><b>Password</b></label><br>
               <input type="password" name="password" value= "${password}" placeholder = "Enter Password" required/> <br>

               <label for="password2"><b>Repeat Password</b></label><br>
               <input type="password" name="password2" value= "${password2}" placeholder = "Repeat Password" required/> <br>

               <label for="email"><b>Email</b></label><br>
               <input type="text" name="email" value= "${email}" placeholder = "Enter Email" required/><br>
               
               <label for="phone"><b>Phone</b></label><br>
               <input type="text" name="phone" value= "${phone}" placeholder="Enter Phone Number" required/><br>
            
            <label for="address"><b>Shipping Address</b></label><br>
            <input type="text" name="address" value= "${address}" placeholder="Enter Shipping Address" required/>
			<br><br>
            
                  <input type="submit" value= "Sign Up" />
      </form>
</div>
</body>
</html>