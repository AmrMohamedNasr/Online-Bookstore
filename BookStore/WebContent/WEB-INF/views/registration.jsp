<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration Page</title>
</head>
<body>
	<jsp:include page="_menu.jsp"></jsp:include>   
 
      <h3>Registration Page</h3>
 
      <p style="color: red;">${errorMessage}</p>
 
      <form method="POST" action="${pageContext.request.contextPath}/register">
         <table border="0">
         	<tr>
               <td>First Name</td>
               <td><input type="text" name="firstName" value= "${user.firstName}" /> </td>
            </tr>
            <tr>
               <td>Last Name</td>
               <td><input type="text" name="lastName" value= "${user.lastName}" /> </td>
            </tr>
            <tr>
               <td>User Name</td>
               <td><input type="text" name="username" value= "${user.username}" /> </td>
            </tr>
            <tr>
               <td>Password</td>
               <td><input type="password" name="password" value= "${user.password}" /> </td>
            </tr>
          <tr>
               <td>Confirm Password</td>
               <td><input type="password" name="password2" value= "${user.password2}" /> </td>
            </tr>
            <tr>
               <td>Email</td>
               <td><input type="text" name="email" value= "${user.email}" /> </td>
            </tr>
            <tr>
               <td>Phone</td>
               <td><input type="text" name="phone" value= "${user.phone}" /> </td>
            </tr>
            <tr>
               <td>Shipping address</td>
               <td><input type="text" name="address" value= "${user.address}" /> </td>
            </tr>
            <tr>
               <td colspan ="2">
                  <input type="submit" value= "Submit" />
               </td>
            </tr>
         </table>
      </form>
      
      <a href="${pageContext.request.contextPath}/login"> log in now</a>
</body>
</html>