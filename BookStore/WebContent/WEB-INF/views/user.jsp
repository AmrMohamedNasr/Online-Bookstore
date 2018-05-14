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
<script type="text/javascript" src="js/jquery-3.3.1.min.js" ></script>
<script type="text/javascript" src="js/side_nav.js" ></script>
<script type="text/javascript" src="js/cart.js" ></script>
<script type="text/javascript" src="js/user.js" ></script>
<script type="text/javascript" src="js/checkout.js" ></script>
<script type="text/javascript" src="js/jquery.payform.min.js" ></script>
<script type="text/javascript" src="js/jquery.dynatable.js" ></script>
<link rel="stylesheet" media="all" href="https://s3.amazonaws.com/dynatable-docs-assets/css/jquery.dynatable.css" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="js/dialog.js" ></script>
<script type="text/javascript" src="js/user_page.js"></script>
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
	  <form id = "bookSearchForm">
	  	<table width = 100%>
	  	<tr>
	  	<td style="width:20%"><label for="isbn"><b>ISBN : </b></label></td>
	  	<td style="width:30%"><input type="text" id = "iisbn" name="isbn" value= "" placeholder="Enter ISBN" style="width:70%"/></td>
	  	<td style="width:20%"><label for = "title"><b>Title : </b></label></td>
	  	<td style="width:30%"><input type="text" id = "ititle" name="title" value= "" placeholder = "Enter Title" style="width:70%"/></td>
	  	</tr>
	  	<tr>
	  	<td><label for = "price1"><b>Lowest Price : </b></label></td>
	  	<td><input type="text" id = "iprice1" name="price1" value= "" placeholder= "Enter Lowest Price" style="width:70%"/></td>
	  	<td><label for = "price2"><b>Highest Price : </b></label></td>
	  	<td><input type="text" id = "iprice2" name="price2" value= "" placeholder= "Enter Highest Price" style="width:70%"/></td>
	  	</tr>
	  	<tr>
	  	<td><label for="category"><b>Category : </b></label></td>
	  	<td><input type="text" id = "icategory" name="category" value= "" placeholder = "Enter Category" style="width:70%"/></td>
	  	<td><label for="publisher"><b>Publisher : </b></label></td>
	  	<td><input type="text" id = "ipublisher" name="publisher" value= "" placeholder="Enter publisher name" style="width:70%"/> </td>
	  	</tr>
	  	<tr>
	  	<td><label for = "date"><b>Publication Date : </b></label></td>
	  	<td><input type="text" id="datepicker" name="date" value= "" placeholder = "Enter Publication Date" style="width:70%"/></td>
	  	<td><label for="authors"><b>Authors : </b></label></td>
	  	<td><textarea rows="3" cols="50" id="iauthor"name="authors" form="bookSearchForm" placeholder="Enter authors names seperated by ;"  style="width:70%;resize: none;"></textarea></td>
	  	</tr>
	  	</table>
	  	<input type="checkbox" id="hasStock"name="hasStock" value="yes" ><span><b>In Stock</b></span><br>
      </form>
    <br>
      <div id="searchDiv" style="display:block">
      <table id="my-final-table" width=100%>
		  <thead>
		  <tr>
		    <th>ISBN</th>
		    <th>Title</th>
		    <th>Category</th>
		    <th>Publication Date</th>
		    <th>Price</th>
		    <th>Publisher</th>
		    <th>In Stock</th>
		    <th style="width:15%">Add To Cart</th>
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
		  <div id="fullcartdiv1">
		  <table width=100%>
		  <tr >
		  <td style="width:50%">
		  <button id="showCheckout" class="hoverButtonright"onClick="update_cart_table($('#cartDiv1'), $('#cartDiv2'), $('#cartTable'));toggle_credit_display()" style="width:50%"><span>To Checkout</span></button>
		  	</td>
		  	<td>
		  	<button onClick='clear_cart()' style="width:30%;float:right">Clear Cart</button>
		  	</td>
		  	</tr>
		  	</table>
		  	<p id="totalcartprice"></p>
		      <table id="cartTable" width=100%>
				  <thead>
				  <tr>
				    <th>ISBN</th>
				    <th>Title</th>
				    <th>Price</th>
				    <th>In Stock</th>
				    <th>Copies</th>
				    <th>Total Price</th>
				    </tr>
				  </thead>
				  <tbody>
				  </tbody>
			</table>
		</div>
		<div id="fullcartdiv2" style="display:none">
		<table width=100%>
		  <tr >
		  <td style="width:50%">
		  <button class="hoverButtonleft"onClick="update_cart_table($('#cartDiv1'), $('#cartDiv2'), $('#cartTable'));toggle_credit_display()" style="width:50%"><span> To Cart</span></button>
			</td>
		  	<td>
		  	<button style="width:30%;float:right" id="confirm-purchase">Confirm Purchase</button>
		  	</td>
		  	</tr>
		  	</table>
			<p id="totalcartprice2"></p>
			<div class="creditCardForm">
			    <div class="payment">
			        <table width=100%>
			        	<tr>
			            	<td style = "width:20%"><label for="owner">Owner : </label></td>
			                <td style = "width:30%"><input type="text" class="form-control" id="owner" placeholder = "Enter Owner Name" style = "width:70%"></td>
			            	<td style = "width:10%"><label for="cvv">CVV : </label></td>
			                <td style = "width:30%"><input type="text" class="form-control" id="cvv" placeholder = "Enter ccv" style = "width:70%;float:right"></td>
			            </tr>
			            <tr>
			            	<td><label for="cardNumber">Card Number : </label></td>
			                <td><input type="text" class="form-control" id="cardNumber" placeholder = "Enter card number" style = "width:70%"></td>
			            	<td></td>
			            	<td></td>
		            	</tr>
	            		<tr>
		            		<td><label>Expiration Date : </label></td>
			                <td>
				                <select id="cmonth">
				                    <option value="01">January</option>
				                    <option value="02">February </option>
				                    <option value="03">March</option>
				                    <option value="04">April</option>
				                    <option value="05">May</option>
				                    <option value="06">June</option>
				                    <option value="07">July</option>
				                    <option value="08">August</option>
				                    <option value="09">September</option>
				                    <option value="10">October</option>
				                    <option value="11">November</option>
				                    <option value="12">December</option>
				                </select>
				                <select id="cyear"> 
				                    <option value="18"> 2018</option>
				                    <option value="19"> 2019</option>
				                    <option value="20"> 2020</option>
				                    <option value="21"> 2021</option>
				                    <option value="22"> 2022</option>
				                    <option value="23"> 2023</option>
				                </select>
   			           		</td>
   			           		<td></td>
			            <td style = "width:50%">
			            <div class="form-group" id="credit_cards" style="float:right">
			                <img src="images/visa.jpg" id="visa">
			                <img src="images/mastercard.jpg" id="mastercard">
			                <img src="images/amex.jpg" id="amex">
			            </div>
			            </td>
			            </tr>
			        </table>
			    </div>
			</div>
			<hr>
			<p class="examples-note">Here are some dummy credit card numbers and CVV codes so you can test out the form:</p>

        <div class="examples">
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Type</th>
                            <th>Card Number</th>
                            <th>Security Code</th>
                            <th>Account funds</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Visa</td>
                            <td>4716108999716531</td>
                            <td>257</td>
                            <td>Infinity</td>
                        </tr>
                        <tr>
                            <td>Master Card</td>
                            <td>5281037048916168</td>
                            <td>043</td>
                            <td>Constant 10000</td>
                        </tr>
                        <tr>
                            <td>American Express</td>
                            <td>342498818630298</td>
                            <td>3156</td>
                            <td>${account3}</td>
                        </tr>
                        <tr>
                        	<td>American Express</td>
                        	<td>378282246310005</td>
                        	<td>233</td>
                        	<td>0</td>
                        </tr>
                        <tr>
                        	<td>American Express</td>
                        	<td>371449635398431</td>
                        	<td>233</td>
                        	<td>Declined</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
		</div>
		</div>
		<div id="cartDiv2" style="display:block">
			<p>Cart is empty</p>
		</div>
	</div>
	
	<div id="EditProfile" class="main" style="display:none">
	  <h3>Edit Profile</h3><hr>
	  <form method="POST" id = "profileForm">
	  <table width=100%>
	  	<tr>
	  		<td style = "width:30%"><label for="username"><b>Username</b></label></td>
	  		<td style = "width:70%"><label for="email"><b>Email</b></label></td>
	  	</tr>
	  	<tr>
	  		<td><input type="text" name="username" value= "${loginedUser.username}" placeholder = "Enter Username" required style = "width:70%"/> </td>
	  		<td><input type="text" name="email" value= "${loginedUser.email}" placeholder = "Enter Email" required style = "width:40%"/></td>
	  	</tr>
	  	<tr>
	  		<td><label for="firstName"><b>First Name</b></label></td>
	  		<td><label for="phone"><b>Phone</b></label></td>
	  	</tr>
	  	<tr>
	  		<td><input type="text" name="firstName" value= "${loginedUser.firstName}" placeholder = "Enter First Name" required style = "width:70%"/></td>
	  		<td><input type="text" name="phone" value= "${loginedUser.phone}" placeholder="Enter Phone Number" required style = "width:40%"/></td>
	  	</tr>
	  	<tr>
	  		<td><label for="lastName"><b>Last Name</b></label></td>
	  		<td><label for="address"><b>Shipping Address</b></label><br></td>
	  	</tr>
        <tr>
        	<td><input type="text" name="lastName" value= "${loginedUser.lastName}" placeholder = "Enter Last Name" required style = "width:70%"/></td>
        	<td><input type="text" name="address" value= "${loginedUser.address}" placeholder="Enter Shipping Address" required style = "width:40%"/></td>
        </tr>    
	</table>
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
<div id = "promptDialog" style="dispaly:none">
	<form id = "promptForm">
        <label for="promptDialogNum" id = "promptDialogLabel">Number of required Copies</label>
        <input type="number" name="promptDialogNum" id="promptDialogNum" class="text ui-widget-content ui-corner-all" required/>
	</form>
</div>
</body>
</html>