<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/nav_bar.css" />
<script type="text/javascript" src="js/jquery-3.3.1.min.js" ></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manager</title>
<script type="text/javascript">
			$(function() {
				$("#managerNav").attr('class','active');
			});
</script>
</head>
<body>
<jsp:include page="_menu.jsp"></jsp:include>
<div class='pagebody'>
      <h3>Hello: ${loginedUser.username}</h3>
 
      User Name: <b>${loginedUser.username}</b>
      <br />
      Phone: ${loginedUser.phone } <br />
</div>
</body>
</html>