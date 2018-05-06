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
<script type="text/javascript" src="js/jquery.dynatable.js" ></script>
<link rel="stylesheet" media="all" href="https://s3.amazonaws.com/dynatable-docs-assets/css/jquery.dynatable.css" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script src="js/jquery.creditCardValidator.js"></script>
  
<script type="text/javascript">
	function clear_cart() {
		var url = "${pageContext.request.contextPath}/cart"; // the script where you handle the form input.
		
	    $.ajax({
	           type: "DELETE",
	           url: url,
	           success: function(data)
	           {
	        	   
	        	   if (data.code == 200) {
	        		    
                       update_cart_table();
                       if (("Notification" in window)) {
	              			if (Notification.permission === "granted") {
	              		    	// If it's okay let's create a notification
	              		   	 var notification = new Notification("Cleared Cart...");
	              		  	}
	              		  	// Otherwise, we need to ask the user for permission
	              		  	else if (Notification.permission !== "denied") {
	              		    	Notification.requestPermission(function (permission) {
	              		      	// If the user accepts, let's create a notification
             		     	 		if (permission === "granted") {
             		        			var notification = new Notification("Cleared Cart...");
             		      			}
             		   	 		});
           	  			}
	              		}
	        	   } else {
	        		   if (("Notification" in window)) {
	              			if (Notification.permission === "granted") {
	              		    	// If it's okay let's create a notification
	              		   	 var notification = new Notification("Couldn't clear Cart...");
	              		  	}
	              		  	// Otherwise, we need to ask the user for permission
	              		  	else if (Notification.permission !== "denied") {
	              		    	Notification.requestPermission(function (permission) {
	              		      	// If the user accepts, let's create a notification
             		     	 		if (permission === "granted") {
             		        			var notification = new Notification("Couldn't clear Cart...");
             		      			}
             		   	 		});
           	  			}
	              		}
	        	   }
	           },
	           error:function(result)
               {
	        	   alert("ERROR");
	        	   console.log(result.responseText);
	        	   if (("Notification" in window)) {
             			if (Notification.permission === "granted") {
             		    	// If it's okay let's create a notification
             		   	 var notification = new Notification("Couldn't clear Cart...");
             		  	}
             		  	// Otherwise, we need to ask the user for permission
             		  	else if (Notification.permission !== "denied") {
             		    	Notification.requestPermission(function (permission) {
             		      	// If the user accepts, let's create a notification
        		     	 		if (permission === "granted") {
        		        			var notification = new Notification("Couldn't clear Cart...");
        		      			}
        		   	 		});
      	  			}
             		}
              	}
	         });
	}
	function update_cart_table() {
		var url = "${pageContext.request.contextPath}/cart"; // the script where you handle the form input.
		
	    $.ajax({
	           type: "GET",
	           url: url,
	           success: function(data)
	           {
	        	   
	        	   if (data.code == 200) {
	        		    var myRecords = data.values;
			            if (myRecords.length > 0) {
			            	$('#cartDiv1').css('display','block');
			            	$('#cartDiv2').css('display','none');
			            } else {
			            	$('#cartDiv1').css('display','none');
			            	$('#cartDiv2').css('display','block');
			            }
			            var i = 0;
			            for (i = 0; i < myRecords.length; i++) {
			            	myRecords[i].changeAmount = "<div><input type='text' id='"+myRecords[i].isbn+"' class='enterTextbox' value='"+ myRecords[i].copies+ "'/></div>"
			            }
			            var dynatable = $('#cartTable').dynatable({
		                    dataset: {
		                        records: myRecords
		                    }
		                }).data('dynatable');
			            dynatable.settings.dataset.originalRecords = myRecords;
		                dynatable.process();
		                $( ".enterTextbox" ).keypress(function(e) {
		                	if (e.which == 13) {
		                        modify_cart($(this).attr('id'), $(this).val());
		                        update_cart_table();
		                    }
		              	});
	        	   }
	           },
	           error:function(result)
               {
	        	   alert("ERROR");
	        	   console.log(result.responseText);
              	}
	         });
	    
	}
	function add_to_cart(isbn) {
		var url = "${pageContext.request.contextPath}/cart";
		var data = {isbn : isbn};
		$.ajax({
	           type: "POST",
	           url: url,
	           data: data, // serializes the form's elements.
	           success: function(data)
	           {
	        	   if (data.code == 200) {
	        		   if (("Notification" in window)) {
	              			if (Notification.permission === "granted") {
	              		    	// If it's okay let's create a notification
	              		   	 var notification = new Notification("Added To Cart...");
	              		  	}
	              		  	// Otherwise, we need to ask the user for permission
	              		  	else if (Notification.permission !== "denied") {
	              		    	Notification.requestPermission(function (permission) {
	              		      	// If the user accepts, let's create a notification
              		     	 		if (permission === "granted") {
              		        			var notification = new Notification("Added To Cart...");
              		      			}
              		   	 		});
            	  			}
	              		}
	        	   } else {
	        		   if (("Notification" in window)) {
		              		if (Notification.permission === "granted") {
		              		    // If it's okay let's create a notification
		              		    var notification = new Notification("Couldn't add to cart...");
		              		  }
		              		  // Otherwise, we need to ask the user for permission
		              		  else if (Notification.permission !== "denied") {
		              		    Notification.requestPermission(function (permission) {
			              		      // If the user accepts, let's create a notification
		              		     	 if (permission === "granted") {
		              		        	var notification = new Notification("Couldn't add to cart...");
		              		      	}
	              		   	 	});
		              		 }
	        	   		}
	        	   }
	           },
	           error:function(result)
            {
	        	   if (("Notification" in window)) {
	              		if (Notification.permission === "granted") {
	              		    // If it's okay let's create a notification
	              		    var notification = new Notification("Couldn't add to cart...");
	              		  }
	              		  // Otherwise, we need to ask the user for permission
	              		  else if (Notification.permission !== "denied") {
	              		    Notification.requestPermission(function (permission) {
		              		      // If the user accepts, let's create a notification
	              		     	 if (permission === "granted") {
	              		        	var notification = new Notification("Couldn't add to cart...");
	              		      	}
             		   	 	});
	              		 }
       	   		}
           	}
	         });
	};
	
	function modify_cart(isbn, amount) {
		var url = "${pageContext.request.contextPath}/cart";
		var data = {isbn : isbn,
				amount:amount};
		$.ajax({
	           type: "POST",
	           url: url,
	           data: data, // serializes the form's elements.
	           success: function(data)
	           {
	        	   if (data.code == 200) {
	        		   if (("Notification" in window)) {
	              			if (Notification.permission === "granted") {
	              		    	// If it's okay let's create a notification
	              		   	 var notification = new Notification("Modified in Cart...");
	              		  	}
	              		  	// Otherwise, we need to ask the user for permission
	              		  	else if (Notification.permission !== "denied") {
	              		    	Notification.requestPermission(function (permission) {
	              		      	// If the user accepts, let's create a notification
              		     	 		if (permission === "granted") {
              		        			var notification = new Notification("Modified in Cart...");
              		      			}
              		   	 		});
            	  			}
	              		}
	        	   } else {
	        		   if (("Notification" in window)) {
		              		if (Notification.permission === "granted") {
		              		    // If it's okay let's create a notification
		              		    var notification = new Notification("Couldn't modify cart...");
		              		  }
		              		  // Otherwise, we need to ask the user for permission
		              		  else if (Notification.permission !== "denied") {
		              		    Notification.requestPermission(function (permission) {
			              		      // If the user accepts, let's create a notification
		              		     	 if (permission === "granted") {
		              		        	var notification = new Notification("Couldn't modify cart...");
		              		      	}
	              		   	 	});
		              		 }
	        	   		}
	        	   }
	           },
	           error:function(result)
            {
	        	   if (("Notification" in window)) {
	              		if (Notification.permission === "granted") {
	              		    // If it's okay let's create a notification
	              		    var notification = new Notification("Couldn't modify cart...");
	              		  }
	              		  // Otherwise, we need to ask the user for permission
	              		  else if (Notification.permission !== "denied") {
	              		    Notification.requestPermission(function (permission) {
		              		      // If the user accepts, let's create a notification
	              		     	 if (permission === "granted") {
	              		        	var notification = new Notification("Couldn't modify cart...");
	              		      	}
             		   	 	});
	              		 }
       	   		}
           	}
	         });
	};
	
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
		
		$( "#datepicker" ).datepicker();
		$('#my-final-table').dynatable();
		$('#cartTable').dynatable();
		
		$("#bookSearchForm").submit(function(e) {
			
		    var url = "${pageContext.request.contextPath}/book"; // the script where you handle the form input.
		
		    $.ajax({
		           type: "GET",
		           url: url,
		           data: $("#bookSearchForm").serialize(), // serializes the form's elements.
		           dataType: "json",
		           success: function(data)
		           {
		        	   
		        	   if (data.code == 200) {
		        		   
			               $("#searchResult").text("");
			           	   $("#searchResult").css('color', 'green');
			           	   
				            var myRecords = data.values;
				            if (myRecords.length > 0) {
				            	$('#searchDiv').css('display','block');
				            } else {
				            	$('#searchDiv').css('display','none');
				            }
				            var i = 0;
				            for (i = 0; i < myRecords.length; i++) {
				            	myRecords[i].addToCart = "<button onclick='add_to_cart("+myRecords[i].isbn +")' >Add to Cart</button>";
				            }
				            var dynatable = $('#my-final-table').dynatable({
			                    dataset: {
			                        records: myRecords
			                    }
			                }).data('dynatable');
				            dynatable.settings.dataset.originalRecords = myRecords;
			                dynatable.process();
			                
		        	   } else {
		        		  	$("#searchResult").text(data.message);
		            		$("#searchResult").css('color', 'red');
		        	   }
		           },
		           error:function(result)
	               {
		        	   alert("ERROR");
		        	   console.log(result.responseText);
		        	   $("#searchResult").text(result.message);
		               $("#searchResult").css('color', 'red');
	              	}
		         });
		
		    e.preventDefault(); // avoid to execute the actual submit of the form.
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
     <!-- Tab links -->
	<div class="tab">
	  <button class="tablinks active" onclick="openTab(event, 'Search')">Search</button>
	  <button class="tablinks" onclick="openTab(event, 'Cart');update_cart_table()">My Cart</button>
	  <button class="tablinks" onclick="openTab(event, 'EditProfile')">Edit Profile</button>
	  <button class="tablinks" onclick="openTab(event, 'EditPassword')">Change Password</button>
	</div>
	
	<!-- Tab content -->
	<div id="Search" class="tabcontent" style='display:block'>
	  <h3>Search For Books</h3>
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
	
	<div id="Cart" class="tabcontent">
	  <h3>My Cart</h3>
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