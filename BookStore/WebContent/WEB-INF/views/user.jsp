<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User</title>

<link rel="stylesheet" type="text/css" href="css/tabs.css" />
<script type="text/javascript" src="js/tabs.js" ></script>
<script type="text/javascript" src="js/jquery-3.3.1.min.js" ></script>
<script type="text/javascript">
	$(function() {
		$("#profileForm").submit(function(e) {
	
		    var url = "${pageContext.request.contextPath}/editUser"; // the script where you handle the form input.
		
		    $.ajax({
		           type: "POST",
		           url: url,
		           data: $("#profileForm").serialize(), // serializes the form's elements.
		           success: function(data)
		           {
		        	   if (data.code == 200) {
			               $("#profileResult").text(data.message);
			               $("#profileResult").css('color', 'green');
		        	   } else {
		        		   $("#profileResult").text(data.message);
			               $("#profileResult").css('color', 'red');
		        	   }
		           },
		           error:function(result)
	               {
		        	   $("#profileResult").text(result.message);
		               $("#profileResult").css('color', 'red');
	              	}
		         });
		
		    e.preventDefault(); // avoid to execute the actual submit of the form.
		});
		
		$("#passwordForm").submit(function(e) {
			
		    var url = "${pageContext.request.contextPath}/editUser"; // the script where you handle the form input.
		
		    $.ajax({
		           type: "POST",
		           url: url,
		           data: $("#passwordForm").serialize(), // serializes the form's elements.
		           success: function(data)
		           {
		        	   if (data.code == 200) {
			               $("#passwordResult").text(data.message);
			               $("#passwordResult").css('color', 'green');
		        	   } else {
		        		   $("#passwordResult").text(data.message);
			               $("#passwordResult").css('color', 'red');
		        	   }
		           },
		           error:function(result)
	               {
		        	   $("#passwordResult").text(result.message);
		               $("#passwordResult").css('color', 'red');
	              	}
		         });
		
		    e.preventDefault(); // avoid to execute the actual submit of the form.
		});
	});
</script>
</head>

<body>
<jsp:include page="_menu.jsp"></jsp:include>
     <!-- Tab links -->
	<div class="tab">
	  <button class="tablinks active" onclick="openTab(event, 'Search')">Search</button>
	  <button class="tablinks" onclick="openTab(event, 'Cart')">My Cart</button>
	  <button class="tablinks" onclick="openTab(event, 'EditProfile')">Edit Profile</button>
	  <button class="tablinks" onclick="openTab(event, 'EditPassword')">Change Password</button>
	</div>
	
	<!-- Tab content -->
	<div id="Search" class="tabcontent" style='display:block'>
	  <h3>Search For Books</h3>
	  <p>London is the capital city of England.</p>
	</div>
	
	<div id="Cart" class="tabcontent">
	  <h3>My Cart</h3>
	  <p>Paris is the capital of France.</p> 
	</div>
	
	<div id="EditProfile" class="tabcontent">
	  <h3>Edit Profile</h3>
	  <form method="POST" id = "profileForm">
         <table border="0">
         	<tr>
               <td>First Name</td>
               <td><input type="text" name="firstName" value= "${loginedUser.firstName}" /> </td>
            </tr>
            <tr>
               <td>Last Name</td>
               <td><input type="text" name="lastName" value= "${loginedUser.lastName}" /> </td>
            </tr>
            <tr>
               <td>Email</td>
               <td><input type="text" name="email" value= "${loginedUser.email}" /> </td>
            </tr>
            <tr>
               <td>Phone</td>
               <td><input type="text" name="phone" value= "${loginedUser.phone}" /> </td>
            </tr>
            <tr>
               <td>Shipping address</td>
               <td><input type="text" name="address" value= "${loginedUser.address}" /> </td>
            </tr>
            <tr>
               <td colspan ="2">
                  <input type="submit" value= "Submit" />
               </td>
            </tr>
         </table>
      </form>
      <p id="profileResult"></p>
	</div>
	<div id="EditPassword" class="tabcontent">
	  <h3>Change Password</h3>
	  <form method="POST" id="passwordForm">
         <table border="0">
         	<tr>
               <td>Previous Password</td>
               <td><input type="password" name="oldpassword" value= "" /> </td>
            </tr>
         	<tr>
               <td>New Password</td>
               <td><input type="password" name="password" value= "" /> </td>
            </tr>
          	<tr>
               <td>Confirm New Password</td>
               <td><input type="password" name="password2" value= "" /> </td>
            </tr>
            <tr>
               <td colspan ="2">
                  <input type="submit" value= "Submit" />
               </td>
            </tr>
         </table>
      </form>
      <p id="passwordResult"></p>
	</div>
</body>
</html>