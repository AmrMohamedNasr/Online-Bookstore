<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User</title>

<link rel="stylesheet" type="text/css" href="css/side_nav.css" />
<link rel="stylesheet" type="text/css" href="css/nav_bar.css" />
<link rel="stylesheet" type="text/css" href="css/general.css" />
<script type="text/javascript" src="js/side_nav.js" ></script>
<script type="text/javascript" src="js/cart.js" ></script>
<script type="text/javascript" src="js/user.js" ></script>
<script type="text/javascript" src="js/book.js" ></script>
<script type="text/javascript" src="js/jquery-3.3.1.min.js" ></script>
<script type="text/javascript" src="js/jquery.dynatable.js" ></script>
<link rel="stylesheet" media="all" href="https://s3.amazonaws.com/dynatable-docs-assets/css/jquery.dynatable.css" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
 <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  
<script type="text/javascript">
	$(function() {
		$("#userNav").attr('class','active');
		$("#profileForm").submit(function(e) {
			edit_user();
		 	e.preventDefault()
		});
		
		$("#passwordForm").submit(function(e) {
			edit_password();
		 	e.preventDefault()
		});
		
		$( "#datepicker" ).datepicker();
		$('#my-final-table').dynatable();
		$('#cartTable').dynatable();
		
		$("#bookSearchForm").submit(function(e) {
			search_books($('#my-final-table'), $("#searchResult"), $('#searchDiv'), $("#bookSearchForm"));
		    e.preventDefault();
		});
	});
	
	function toggle_credit_display() {
	    var x = document.getElementById("creditDiv");
	    if (x.style.display === "none") {
	        x.style.display = "block";
	        $('#showCheckout').text("Hide Checkout");
	    } else {
	        x.style.display = "none";
	        $('#showCheckout').text("Show Checkout");
	    }
	}
</script>
</head>

<body>
<jsp:include page="_menu.jsp"></jsp:include>
<div class="sidenav">
	<div id = "sidenavinner">
	<div id = "sidenavinnerinner">
	  <h2> Options</h2>
	  <a class="sideNavButton active" onclick="openTab(event, 'Search')">Search</a>
	  <a class="sideNavButton" onclick="openTab(event, 'Cart');update_cart_table($('#cartDiv1'), $('#cartDiv2'), $('#cartTable'))">My Cart</a>
	  <a class="sideNavButton" onclick="openTab(event, 'EditProfile')">Edit Profile</a>
	  <a class="sideNavButton" onclick="openTab(event, 'EditPassword')">Change Password</a>
	</div>
	</div>
	</div>
<div class='pagebody'>
     <!-- Tab links -->
	
	<!-- Tab content -->
	<div id="Search" class="main" style='display:block'>
	  <h3>Search For Books</h3><hr>
	  <form method="POST" id = "bookSearchForm">
         <table border="0">
         	<tr>
               <td>ISBN</td>
               <td><input type="text" name="isbn" value= "" /> </td>
            </tr>
            <tr>
               <td>Title</td>
               <td><input type="text" name="title" value= "" /> </td>
            </tr>
            <tr>
               <td>Publication Date</td>
               <td><input type="text" id="datepicker" name="date" value= "" /> </td>
            </tr>
            <tr>
               <td>Price</td>
               <td><input type="text" name="price" value= "" /> </td>
            </tr>
            <tr>
               <td>Category</td>
               <td><input type="text" name="category" value= "" /> </td>
            </tr>
            <tr>
               <td>Publisher</td>
               <td><input type="text" name="publisher" value= "" /> </td>
            </tr>
            <tr>
               <td>Authors</td>
               <td><textarea rows="4" cols="50" name="authors" form="bookSearchForm"></textarea> </td>
            </tr>
            <tr>
               <td colspan ="2">
                  <input type="submit" value= "Search" />
               </td>
            </tr>
         </table>
      </form>
    
      <div id="searchDiv" style="display:none">
      <table id="my-final-table">
		  <thead>
		  <tr>
		    <th>ISBN</th>
		    <th>Title</th>
		    <th>Category</th>
		    <th>Publication Date</th>
		    <th>Price</th>
		    <th>Publisher</th>
		    <th>Add To Cart</th>
		    </tr>
		  </thead>
		  <tbody>
		  </tbody>
	</table>
	</div>
       <p id="searchResult"></p>
	</div>
	<div id="Cart" class="main" style="display:none">
	  <h3>My Cart</h3><hr>
		  <div id="cartDiv1" style="display:none">
		      <table id="cartTable">
				  <thead>
				  <tr>
				    <th>ISBN</th>
				    <th>Title</th>
				    <th>Price</th>
				    <th>Copies</th>
				    <th>Total Price</th>
				    <th>Change Amount</th>
				    </tr>
				  </thead>
				  <tbody>
				  </tbody>
			</table>
			<button onClick='clear_cart()'>Clear Cart</button>
			<button id="showCheckout" onClick='toggle_credit_display()'>Show Checkout</button>
			<div id="creditDiv" style="display:none">
					<p>:p</p>
			</div>
		</div>
		<div id="cartDiv2" style="display:block">
			<p>Cart is empty</p>
		</div>
	</div>
	
	<div id="EditProfile" class="main" style="display:none">
	  <h3>Edit Profile</h3><hr>
	  <form method="POST" id = "profileForm">
         	<label for="username"><b>Username</b></label><br>
               <input type="text" name="username" value= "${loginedUser.username}" placeholder = "Enter Username" required/> <br>
         	<label for="firstName"><b>First Name</b></label><br>
               <input type="text" name="firstName" value= "${loginedUser.firstName}" placeholder = "Enter First Name" required/> <br>

               <label for="lastName"><b>Last Name</b></label><br>
               <input type="text" name="lastName" value= "${loginedUser.lastName}" placeholder = "Enter Last Name" required/> <br>

               <label for="email"><b>Email</b></label><br>
               <input type="text" name="email" value= "${loginedUser.email}" placeholder = "Enter Email" required/><br>
               
               <label for="phone"><b>Phone</b></label><br>
               <input type="text" name="phone" value= "${loginedUser.phone}" placeholder="Enter Phone Number" required/><br>
               <label for="address"><b>Shipping Address</b></label><br>
               <input type="text" name="address" value= "${loginedUser.address}" placeholder="Enter Shipping Address" required/>
<br><br>
                  <input type="submit" value= "Edit Profile" />
      </form>
      <p id="profileResult"></p>
	</div>
	<div id="EditPassword" class="main" style="display:none">
	  <h3>Change Password</h3>
	  <hr>
	  <form method="POST" id="passwordForm">
        	<label for="oldpassword"><b>Old Password</b></label><br>
               <input type="password" name="oldpassword" value= "" placeholder="Enter Old Password" style="width:18%" required/> <br>
    
               <label for="password"><b>New Password</b></label><br>
               <input type="password" name="password" value= "" placeholder="Enter New Password" style="width:18%" required/> <br>
				<label for="password2"><b>Repeat New Password</b></label><br>
               <input type="password" name="password2" value= "" placeholder="Repeat New Password" style="width:18%" required/>
				<br><br>
                <input type="submit" value= "Change Password" />
      </form>
      <p id="passwordResult"></p>
	</div>
</div>
</body>
</html>