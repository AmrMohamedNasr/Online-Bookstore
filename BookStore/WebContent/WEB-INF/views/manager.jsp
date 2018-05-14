<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery-3.3.1.min.js" ></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manager</title>
<link rel="stylesheet" type="text/css" href="css/side_nav.css" />
<link rel="stylesheet" type="text/css" href="css/nav_bar.css" />
<link rel="stylesheet" type="text/css" href="css/general.css" />
<script type="text/javascript" src="js/jquery-3.3.1.min.js" ></script>
<script type="text/javascript" src="js/side_nav.js" ></script>
<script type="text/javascript" src="js/jquery.dynatable.js" ></script>
<link rel="stylesheet" media="all" href="https://s3.amazonaws.com/dynatable-docs-assets/css/jquery.dynatable.css" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="js/dialog.js" ></script>
<script type="text/javascript" src="js/manager_page.js"></script>
<script type="text/javascript" src="js/book_requests.js"></script>
</head>
<body>
<jsp:include page="_menu.jsp"></jsp:include>
<div class="sidenav">
	<div id = "sidenavinner">
	<div id = "sidenavinnerinner">
	  <h2> Options</h2>
	  <a class="sideNavButton active" onclick="openTab(event, 'Books')">Books</a>
	  <a class="sideNavButton" onclick="openTab(event, 'Authors')">Authors</a>
	  <a class="sideNavButton" onclick="openTab(event, 'Publishers')">Publishers</a>
	  <a class="sideNavButton" onclick="openTab(event, 'Categories')">Categories</a>
	  <a class="sideNavButton" onclick="openTab(event, 'Orders')">Orders</a>
	  <a class="sideNavButton" onclick="openTab(event, 'Users')">Users</a>
	  <a class="sideNavButton" onclick="openTab(event, 'Reports')">Reports</a>
	</div>
	</div>
</div>
<div class='pagebody'>
	<div id="Books" class="main" style='display:block'>
		<h3>Books</h3><hr>
		<div id="bookTabs">
		  <ul>
		    <li><a href="#book-tabs-1">Search Books</a></li>
		    <li><a href="#book-tabs-2">Add Book</a></li>
		    <li><a id = "edit-book-link" href="#book-tabs-3">Edit Book</a></li>
		  </ul>
		  <div id="book-tabs-1">
		  	<form id = "bookSearchForm">
			  	<table width = 100%>
				  	<tr>
					  	<td style="width:20%"><label for="isbn"><b>ISBN : </b></label></td>
					  	<td style="width:30%"><input type="text" id = "iisbn" name="isbn" value= "" placeholder="Enter ISBN" style="width:70%"/></td>
					  	<td style="width:20%"><label for = "title"><b>Title : </b></label></td>
					  	<td style="width:30%"><input class = "autotitle" type="text" id = "ititle" name="title" value= "" placeholder = "Enter Title" style="width:70%"/></td>
				  	</tr>
				  	<tr>
					  	<td><label for = "price1"><b>Lowest Price : </b></label></td>
					  	<td><input type="text" id = "iprice1" name="price1" value= "" placeholder= "Enter Lowest Price" style="width:70%"/></td>
					  	<td><label for = "price2"><b>Highest Price : </b></label></td>
					  	<td><input type="text" id = "iprice2" name="price2" value= "" placeholder= "Enter Highest Price" style="width:70%"/></td>
				  	</tr>
				  	<tr>
					  	<td><label for="category"><b>Category : </b></label></td>
					  	<td><input type="text" class = "autocategory"id = "icategory" name="category" value= "" placeholder = "Enter Category" style="width:70%"/></td>
					  	<td><label for="publisher"><b>Publisher : </b></label></td>
					  	<td><input type="text" class = "autopublisher"id = "ipublisher" name="publisher" value= "" placeholder="Enter publisher name" style="width:70%"/> </td>
				  	</tr>
				  	<tr>
					  	<td><label for = "date"><b>Publication Date : </b></label></td>
					  	<td><input type="text" class ="mydatepickers" id="ipubdate" name="date" value= "" placeholder = "Enter Publication Date" style="width:70%"/></td>
					  	<td><label for="authors"><b>Authors : </b></label></td>
					  	<td><textarea class = "autoauthor" rows="3" cols="50" id="iauthor"name="authors" form="bookSearchForm" placeholder="Enter authors names seperated by ;"  style="width:70%;resize: none;"></textarea></td>
				  	</tr>
				  	<tr>
					  	<td><label for = "threshold1"><b>Min Threshold : </b></label></td>
					  	<td><input type="text" id = "ithreshold1" name="threshold1" value= "" placeholder= "Enter Min Threshold" style="width:70%"/></td>
					  	<td><label for = "threshold2"><b>Max Threshold : </b></label></td>
					  	<td><input type="text" id = "ithreshold2" name="threshold2" value= "" placeholder= "Enter Max Threshold" style="width:70%"/></td>
				  	</tr>
				  	<tr>
					  	<td><label for = "copies1"><b>Min Copies Number : </b></label></td>
					  	<td><input type="text" id = "icopies1" name="copies1" value= "" placeholder= "Enter Min Number" style="width:70%"/></td>
					  	<td><label for = "copies2"><b>Max Copies Number : </b></label></td>
					  	<td><input type="text" id = "icopies2" name="copies2" value= "" placeholder= "Enter Max Number" style="width:70%"/></td>
				  	</tr>
			  	</table>
	      	</form>
		  	<table id="books-table">
			  <thead>
			  	<tr>
				    <th>ISBN</th>
				    <th>Title</th>
				    <th>Category</th>
				    <th>Publication Date</th>
				    <th>Price</th>
				    <th>Publisher</th>
				    <th>Number Of Copies</th>
				    <th>Threshold</th>
				    <th>Edit Book</th>
			    </tr>
			  </thead>
			  <tbody>
			  </tbody>
			</table>
		  </div>
		  <div id="book-tabs-2">
		  		<form id = "bookAddForm">
			  	<table width = 100%>
				  	<tr>
					  	<td style="width:20%"><label for="isbn"><b>ISBN : </b></label></td>
					  	<td style="width:30%"><input type="text" id = "aisbn" name="isbn" value= "" placeholder="Enter ISBN" style="width:70%"/></td>
					  	<td style="width:20%"><label for = "title"><b>Title : </b></label></td>
					  	<td style="width:30%"><input class = "autotitle" type="text" id = "atitle" name="title" value= "" placeholder = "Enter Title" style="width:70%"/></td>
				  	</tr>
				  	<tr>
					  	<td><label for = "price"><b>Price : </b></label></td>
					  	<td><input type="text" id = "aprice" name="price" value= "" placeholder= "Enter Price" style="width:70%"/></td>
					  	<td><label for = "threshold"><b>Threshold : </b></label></td>
					  	<td><input type="text" id = "athreshold" name="threshold" value= "" placeholder= "Enter Threshold" style="width:70%"/></td>
				  	</tr>
				  	<tr>
					  	<td><label for="category"><b>Category : </b></label></td>
					  	<td><input type="text" class = "autocategory"id = "acategory" name="category" value= "" placeholder = "Enter Category" style="width:70%"/></td>
					  	<td><label for="publisher"><b>Publisher : </b></label></td>
					  	<td><input type="text" class = "autopublisher"id = "apublisher" name="publisher" value= "" placeholder="Enter publisher name" style="width:70%"/> </td>
				  	</tr>
				  	<tr>
					  	<td><label for = "date"><b>Publication Date : </b></label></td>
					  	<td><input type="text" class = "mydatepickers" id="apubdate" name="date" value= "" placeholder = "Enter Publication Date" style="width:70%"/></td>
					  	<td><label for="authors"><b>Authors : </b></label></td>
					  	<td><textarea class = "autoauthor" rows="3" cols="50" id="aauthor"name="authors" form="bookAddForm" placeholder="Enter authors names seperated by ;"  style="width:70%;resize: none;"></textarea></td>
				  	</tr>
				  	<tr>
					  	<td><label for = "copies"><b>Available Copies : </b></label></td>
					  	<td><input type="text" id = "acopies" name="copies" value= "" placeholder= "Enter Copies Number" style="width:70%"/></td>
				  	</tr>
			  	</table>
			  	<input type="submit" value="Add Book"/>
	      	</form>
	      	<p id="addBookResult"></p>
		  </div>
		  <div id="book-tabs-3">
	      	<form id = "bookEditForm">
			  	<table width = 100%>
				  	<tr>
					  	<td style="width:20%"><label for="oldisbn"><b>Old ISBN : </b></label></td>
					  	<td style="width:30%"><input type="text" id = "eoldisbn" name="oldisbn" value= "" placeholder="Enter Old ISBN" style="width:70%"/></td>
				  		<td style="width:20%"><label for="isbn"><b>ISBN : </b></label></td>
					  	<td style="width:30%"><input type="text" id = "enewisbn" name="isbn" value= "" placeholder="Enter New ISBN" style="width:70%"/></td>
				  	</tr>
				  	<tr>
					  	<td style="width:20%"><label for = "title"><b>Title : </b></label></td>
					  	<td style="width:30%"><input class = "autotitle" type="text" id = "etitle" name="title" value= "" placeholder = "Enter Title" style="width:70%"/></td>
					  	<td><label for = "price"><b>Price : </b></label></td>
					  	<td><input type="text" id = "eprice" name="price" value= "" placeholder= "Enter Price" style="width:70%"/></td>
				  	</tr>
				  	<tr>
					  	<td><label for="category"><b>Category : </b></label></td>
					  	<td><input type="text" class = "autocategory"id = "ecategory" name="category" value= "" placeholder = "Enter Category" style="width:70%"/></td>
					  	<td><label for="publisher"><b>Publisher : </b></label></td>
					  	<td><input type="text" class = "autopublisher"id = "epublisher" name="publisher" value= "" placeholder="Enter publisher name" style="width:70%"/> </td>
				  	</tr>
				  	<tr>
					  	<td><label for = "date"><b>Publication Date : </b></label></td>
					  	<td><input type="text" class = "mydatepickers" id="epubdate" name="date" value= "" placeholder = "Enter Publication Date" style="width:70%"/></td>
					  	<td><label for="authors"><b>Authors : </b></label></td>
					  	<td><textarea class = "autoauthor" rows="3" cols="50" id="eauthor"name="authors" form="bookEditForm" placeholder="Enter authors names seperated by ;"  style="width:70%;resize: none;"></textarea></td>
				  	</tr>
				  	<tr>
				  		<td><label for = "threshold"><b>Threshold : </b></label></td>
					  	<td><input type="text" id = "ethreshold" name="threshold" value= "" placeholder= "Enter Threshold" style="width:70%"/></td>
					  	<td><label for = "copies"><b>Available Copies : </b></label></td>
					  	<td><input type="text" id = "ecopies" name="copies" value= "" placeholder= "Enter Copies Number" style="width:70%"/></td>
				  	</tr>
			  	</table>
			  	<input type="submit" value="Edit Book"/>
	      	</form>
	      	<p id="editBookResult"></p>
	      </div>
		</div>
	</div>
	<div id="Authors" class="main" style='display:none'>
		<h3>Authors</h3><hr>
		<div id="authorTabs">
		  <ul>
		    <li><a href="#author-tabs-1">Search Authors</a></li>
		    <li><a href="#author-tabs-2">Add Author</a></li>
		    <li><a href="#author-tabs-3">Edit Author</a></li>
		  </ul>
		  <div id="author-tabs-1">
		  	<table id="author-table">
			  <thead>
			  	<tr>
				    <th>AID</th>
				    <th>Name</th>
				    <th>Edit Author</th>
			    </tr>
			  </thead>
			  <tbody>
			  </tbody>
			</table>
		  </div>
		  <div id="author-tabs-2">
		    <p>Morbi tincidunt, dui sit amet facilisis feugiat, odio metus gravida ante, ut pharetra massa metus id nunc. Duis scelerisque molestie turpis. Sed fringilla, massa eget luctus malesuada, metus eros molestie lectus, ut tempus eros massa ut dolor. Aenean aliquet fringilla sem. Suspendisse sed ligula in ligula suscipit aliquam. Praesent in eros vestibulum mi adipiscing adipiscing. Morbi facilisis. Curabitur ornare consequat nunc. Aenean vel metus. Ut posuere viverra nulla. Aliquam erat volutpat. Pellentesque convallis. Maecenas feugiat, tellus pellentesque pretium posuere, felis lorem euismod felis, eu ornare leo nisi vel felis. Mauris consectetur tortor et purus.</p>
		  </div>
		  <div id="author-tabs-3">
		    <p>Mauris eleifend est et turpis. Duis id erat. Suspendisse potenti. Aliquam vulputate, pede vel vehicula accumsan, mi neque rutrum erat, eu congue orci lorem eget lorem. Vestibulum non ante. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce sodales. Quisque eu urna vel enim commodo pellentesque. Praesent eu risus hendrerit ligula tempus pretium. Curabitur lorem enim, pretium nec, feugiat nec, luctus a, lacus.</p>
		    <p>Duis cursus. Maecenas ligula eros, blandit nec, pharetra at, semper at, magna. Nullam ac lacus. Nulla facilisi. Praesent viverra justo vitae neque. Praesent blandit adipiscing velit. Suspendisse potenti. Donec mattis, pede vel pharetra blandit, magna ligula faucibus eros, id euismod lacus dolor eget odio. Nam scelerisque. Donec non libero sed nulla mattis commodo. Ut sagittis. Donec nisi lectus, feugiat porttitor, tempor ac, tempor vitae, pede. Aenean vehicula velit eu tellus interdum rutrum. Maecenas commodo. Pellentesque nec elit. Fusce in lacus. Vivamus a libero vitae lectus hendrerit hendrerit.</p>
		  </div>
		</div>
	</div>
	<div id="Publishers" class="main" style='display:none'>
		<h3>Publishers</h3><hr>
		<div id="publisherTabs">
		  <ul>
		    <li><a href="#publisher-tabs-1">Search Publishers</a></li>
		    <li><a href="#publisher-tabs-2">Add Publisher</a></li>
		    <li><a href="#publisher-tabs-3">Edit Publisher</a></li>
		  </ul>
		  <div id="publisher-tabs-1">
		  	<table id="publisher-table">
			  <thead>
			  	<tr>
				    <th>PID</th>
				    <th>Name</th>
				    <th>Address</th>
				    <th>Phone</th>
				    <th>Edit Publisher</th>
			    </tr>
			  </thead>
			  <tbody>
			  </tbody>
			</table>  
		  </div>
		  <div id="publisher-tabs-2">
		    <p>Morbi tincidunt, dui sit amet facilisis feugiat, odio metus gravida ante, ut pharetra massa metus id nunc. Duis scelerisque molestie turpis. Sed fringilla, massa eget luctus malesuada, metus eros molestie lectus, ut tempus eros massa ut dolor. Aenean aliquet fringilla sem. Suspendisse sed ligula in ligula suscipit aliquam. Praesent in eros vestibulum mi adipiscing adipiscing. Morbi facilisis. Curabitur ornare consequat nunc. Aenean vel metus. Ut posuere viverra nulla. Aliquam erat volutpat. Pellentesque convallis. Maecenas feugiat, tellus pellentesque pretium posuere, felis lorem euismod felis, eu ornare leo nisi vel felis. Mauris consectetur tortor et purus.</p>
		  </div>
		  <div id="publisher-tabs-3">
		    <p>Mauris eleifend est et turpis. Duis id erat. Suspendisse potenti. Aliquam vulputate, pede vel vehicula accumsan, mi neque rutrum erat, eu congue orci lorem eget lorem. Vestibulum non ante. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce sodales. Quisque eu urna vel enim commodo pellentesque. Praesent eu risus hendrerit ligula tempus pretium. Curabitur lorem enim, pretium nec, feugiat nec, luctus a, lacus.</p>
		    <p>Duis cursus. Maecenas ligula eros, blandit nec, pharetra at, semper at, magna. Nullam ac lacus. Nulla facilisi. Praesent viverra justo vitae neque. Praesent blandit adipiscing velit. Suspendisse potenti. Donec mattis, pede vel pharetra blandit, magna ligula faucibus eros, id euismod lacus dolor eget odio. Nam scelerisque. Donec non libero sed nulla mattis commodo. Ut sagittis. Donec nisi lectus, feugiat porttitor, tempor ac, tempor vitae, pede. Aenean vehicula velit eu tellus interdum rutrum. Maecenas commodo. Pellentesque nec elit. Fusce in lacus. Vivamus a libero vitae lectus hendrerit hendrerit.</p>
		  </div>
		</div>
	</div>
	<div id="Categories" class="main" style='display:none'>
		<h3>Categories</h3><hr>
		<div id="categoryTabs">
		  <ul>
		    <li><a href="#category-tabs-1">Search Categories</a></li>
		    <li><a href="#category-tabs-2">Add Category</a></li>
		    <li><a href="#category-tabs-3">Edit Category</a></li>
		  </ul>
		  <div id="category-tabs-1">
		  	<table id="category-table">
			  <thead>
			  	<tr>
				    <th>CID</th>
				    <th>Name</th>
				    <th>Address</th>
				    <th>Edit Category</th>
			    </tr>
			  </thead>
			  <tbody>
			  </tbody>
			</table>  
		  </div>
		  <div id="category-tabs-2">
		    <p>Morbi tincidunt, dui sit amet facilisis feugiat, odio metus gravida ante, ut pharetra massa metus id nunc. Duis scelerisque molestie turpis. Sed fringilla, massa eget luctus malesuada, metus eros molestie lectus, ut tempus eros massa ut dolor. Aenean aliquet fringilla sem. Suspendisse sed ligula in ligula suscipit aliquam. Praesent in eros vestibulum mi adipiscing adipiscing. Morbi facilisis. Curabitur ornare consequat nunc. Aenean vel metus. Ut posuere viverra nulla. Aliquam erat volutpat. Pellentesque convallis. Maecenas feugiat, tellus pellentesque pretium posuere, felis lorem euismod felis, eu ornare leo nisi vel felis. Mauris consectetur tortor et purus.</p>
		  </div>
		  <div id="category-tabs-3">
		    <p>Mauris eleifend est et turpis. Duis id erat. Suspendisse potenti. Aliquam vulputate, pede vel vehicula accumsan, mi neque rutrum erat, eu congue orci lorem eget lorem. Vestibulum non ante. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce sodales. Quisque eu urna vel enim commodo pellentesque. Praesent eu risus hendrerit ligula tempus pretium. Curabitur lorem enim, pretium nec, feugiat nec, luctus a, lacus.</p>
		    <p>Duis cursus. Maecenas ligula eros, blandit nec, pharetra at, semper at, magna. Nullam ac lacus. Nulla facilisi. Praesent viverra justo vitae neque. Praesent blandit adipiscing velit. Suspendisse potenti. Donec mattis, pede vel pharetra blandit, magna ligula faucibus eros, id euismod lacus dolor eget odio. Nam scelerisque. Donec non libero sed nulla mattis commodo. Ut sagittis. Donec nisi lectus, feugiat porttitor, tempor ac, tempor vitae, pede. Aenean vehicula velit eu tellus interdum rutrum. Maecenas commodo. Pellentesque nec elit. Fusce in lacus. Vivamus a libero vitae lectus hendrerit hendrerit.</p>
		  </div>
		</div>
	</div>
	<div id="Orders" class="main" style='display:none'>
		<h3>Orders</h3><hr>
		<div id="orderTabs">
		  <ul>
		    <li><a href="#order-tabs-1">Search Orders</a></li>
		    <li><a href="#order-tabs-2">Add Order</a></li>
		  </ul>
		  <div id="order-tabs-1">
		 	<table id="order-table">
			  <thead>
			  	<tr>
				    <th>ISBN</th>
				    <th>Time Requested</th>
				    <th>Quantity</th>
				    <th>Confirm Order</th>
			    </tr>
			  </thead>
			  <tbody>
			  </tbody>
			</table>  
		  </div>
		  <div id="order-tabs-2">
		    <p>Morbi tincidunt, dui sit amet facilisis feugiat, odio metus gravida ante, ut pharetra massa metus id nunc. Duis scelerisque molestie turpis. Sed fringilla, massa eget luctus malesuada, metus eros molestie lectus, ut tempus eros massa ut dolor. Aenean aliquet fringilla sem. Suspendisse sed ligula in ligula suscipit aliquam. Praesent in eros vestibulum mi adipiscing adipiscing. Morbi facilisis. Curabitur ornare consequat nunc. Aenean vel metus. Ut posuere viverra nulla. Aliquam erat volutpat. Pellentesque convallis. Maecenas feugiat, tellus pellentesque pretium posuere, felis lorem euismod felis, eu ornare leo nisi vel felis. Mauris consectetur tortor et purus.</p>
		  </div>
		</div>
	</div>
	<div id="Users" class="main" style='display:none'>
		<h3>Upgrade Users</h3><hr>
		<table id="publisher-table">
		  <thead>
		  	<tr>
			    <th>Username</th>
			    <th>First Name</th>
			    <th>Last Name</th>
			    <th>Email</th>
			    <th>Phone</th>
			    <th>Address</th>
			    <th>Upgrade To Manager</th>
		    </tr>
		  </thead>
		  <tbody>
		  </tbody>
		</table>  
	</div>
	<div id="Reports" class="main" style='display:none'>
		<h3>Reports</h3><hr>
	</div>
</div>
</body>
</html>