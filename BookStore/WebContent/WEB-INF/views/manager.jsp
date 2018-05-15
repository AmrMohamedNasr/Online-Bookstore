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
<script type="text/javascript" src="js/author_requests.js"></script>
<script type="text/javascript" src="js/category_requests.js"></script>
<script type="text/javascript" src="js/publisher_requests.js"></script>
<script type="text/javascript" src="js/order_requests.js"></script>
<script type="text/javascript" src="js/user_requests.js"></script>
<script type="text/javascript" src="js/report_requests.js"></script>
</head>
<body>
<jsp:include page="_menu.jsp"></jsp:include>
<div class="sidenav">
	<div id = "sidenavinner">
	<div id = "sidenavinnerinner">
	  <h2> Options</h2>
	  <a class="sideNavButton active" onclick="openTab(event, 'Books');update_book_table();">Books</a>
	  <a class="sideNavButton" onclick="openTab(event, 'Authors');update_author_table();">Authors</a>
	  <a class="sideNavButton" onclick="openTab(event, 'Publishers');update_publisher_table();">Publishers</a>
	  <a class="sideNavButton" onclick="openTab(event, 'Categories');update_category_table();">Categories</a>
	  <a class="sideNavButton" onclick="openTab(event, 'Orders');update_order_table();">Orders</a>
	  <a class="sideNavButton" onclick="openTab(event, 'Users');update_user_table();">Users</a>
	  <a class="sideNavButton" onclick="openTab(event, 'Reports');ask_for_report(0);">Reports</a>
	</div>
	</div>
</div>
<div class='pagebody'>
	<div id="Books" class="main" style='display:block'>
		<h3>Books</h3><hr>
		<div id="bookTabs">
		  <ul>
		    <li><a href="#book-tabs-1" onclick="update_book_table()">Search Books</a></li>
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
		  	<table id="books-table" width=100%>
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
				    <th style="width:15%">Edit Book</th>
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
					  	<td style="width:30%"><input type="text" id = "aisbn" name="isbn" value= "" placeholder="Enter ISBN" style="width:70%" required/></td>
					  	<td style="width:20%"><label for = "title"><b>Title : </b></label></td>
					  	<td style="width:30%"><input class = "autotitle" type="text" id = "atitle" name="title" value= "" placeholder = "Enter Title" style="width:70%" required/></td>
				  	</tr>
				  	<tr>
					  	<td><label for = "price"><b>Price : </b></label></td>
					  	<td><input type="text" id = "aprice" name="price" value= "" placeholder= "Enter Price" style="width:70%" required/></td>
					  	<td><label for = "threshold"><b>Threshold : </b></label></td>
					  	<td><input type="text" id = "athreshold" name="threshold" value= "" placeholder= "Enter Threshold" style="width:70%" required/></td>
				  	</tr>
				  	<tr>
					  	<td><label for="category"><b>Category : </b></label></td>
					  	<td><input type="text" class = "autocategory"id = "acategory" name="category" value= "" placeholder = "Enter Category" style="width:70%" required/></td>
					  	<td><label for="publisher"><b>Publisher : </b></label></td>
					  	<td><input type="text" class = "autopublisher"id = "apublisher" name="publisher" value= "" placeholder="Enter publisher name" style="width:70%" required/> </td>
				  	</tr>
				  	<tr>
					  	<td><label for = "date"><b>Publication Date : </b></label></td>
					  	<td><input type="text" class = "mydatepickers" id="apubdate" name="date" value= "" placeholder = "Enter Publication Date" style="width:70%" required/></td>
					  	<td><label for="authors"><b>Authors : </b></label></td>
					  	<td><textarea class = "autoauthor" rows="3" cols="50" id="aauthor"name="authors" form="bookAddForm" placeholder="Enter authors names seperated by ;"  style="width:70%;resize: none;"></textarea></td>
				  	</tr>
				  	<tr>
					  	<td><label for = "copies"><b>Available Copies : </b></label></td>
					  	<td><input type="text" id = "acopies" name="copies" value= "" placeholder= "Enter Copies Number" style="width:70%" required/></td>
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
		    <li><a href="#author-tabs-1" onclick="update_author_table()">Search Authors</a></li>
		    <li><a href="#author-tabs-2">Add Author</a></li>
		    <li><a id = "edit-author-link" href="#author-tabs-3">Edit Author</a></li>
		  </ul>
		  <div id="author-tabs-1">
		  <form id="authorSearchForm">
			  	<table>
			  		<tr>
			  			<td style="width:10%"><label for = "aid"><b>AID : </b></label></td>
					  	<td style="width:30%"><input type="text" id = "iauthorid" name="aid" value= "" placeholder = "Enter AID" style="width:70%"/></td>
			  			<td style="width:20%"><label for = "name"><b>Author Name : </b></label></td>
					  	<td style="width:30%"><input class="autosauthor"type="text" id = "iauthorname" name="name" value= "" placeholder = "Enter Author Name" style="width:100%"/></td>
			  		</tr>
			  	</table>
		  	</form>
		  	<table id="author-table" width=100%>
			  <thead>
			  	<tr>
				    <th style="width:15%">AID</th>
				    <th>Name</th>
				    <th style="width:15%">Edit Author</th>
			    </tr>
			  </thead>
			  <tbody>
			  </tbody>
			</table>
		  </div>
		  <div id="author-tabs-2">
		 	 <form id="authorAddForm">
			  	<table>
			  		<tr>
			  			<td style="width:20%"><label for = "name"><b>Author Name : </b></label></td>
					  	<td style="width:30%"><input type="text" id = "aauthorname" name="name" value= "" placeholder = "Enter Author Name" style="width:100%" required/></td>
					  	<td><input type="submit" value="Add Author" style ="width:70%;float:right"/></td>
			  		</tr>
			  	</table>
		  	</form>
		  	<p id="addAuthorResult"></p>
		  </div>
		  <div id="author-tabs-3">
		  	<form id="authorEditForm">
			  	<table>
			  		<tr>
			  			<td style="width:10%"><label for = "aid"><b>AID : </b></label></td>
					  	<td style="width:30%"><input type="text" id = "eauthorid" name="aid" value= "" placeholder = "Enter AID" style="width:70%" required/></td>
			  			<td style="width:20%"><label for = "name"><b>New Author Name : </b></label></td>
					  	<td style="width:30%"><input type="text" id = "eauthorname" name="name" value= "" placeholder = "Enter New Author Name" style="width:100%" required/></td>
			  		</tr>
			  	</table>
			  	<input type="submit" value="Edit Author"/>
		  	</form>
		  	<p id="editAuthorResult"></p>
		  </div>
		</div>
	</div>
	<div id="Publishers" class="main" style='display:none'>
		<h3>Publishers</h3><hr>
		<div id="publisherTabs">
		  <ul>
		    <li><a href="#publisher-tabs-1" onclick="update_publisher_table()">Search Publishers</a></li>
		    <li><a href="#publisher-tabs-2">Add Publisher</a></li>
		    <li><a id = "edit-publisher-link" href="#publisher-tabs-3">Edit Publisher</a></li>
		  </ul>
		  <div id="publisher-tabs-1">
		  <form id="publisherSearchForm">
			  	<table>
			  		<tr>
			  			<td style="width:10%"><label for = "pid"><b>PID : </b></label></td>
					  	<td style="width:30%"><input type="text" id = "ipublisherid" name="pid" value= "" placeholder = "Enter PID" style="width:70%"/></td>
			  			<td style="width:20%"><label for = "name"><b>Publisher Name : </b></label></td>
					  	<td style="width:30%"><input class="autopublisher"type="text" id = "ipublishername" name="name" value= "" placeholder = "Enter Publisher Name" style="width:100%"/></td>
			  		</tr>
			  		<tr>
			  			<td style="width:10%"><label for = "phone"><b>Phone : </b></label></td>
					  	<td style="width:30%"><input type="text" id = "ipublisherphone" name="phone" value= "" placeholder = "Enter Phone" style="width:70%"/></td>
			  			<td style="width:20%"><label for = "address"><b>Address : </b></label></td>
					  	<td style="width:30%"><input class="autopublisher"type="text" id = "ipublisheraddress" name="address" value= "" placeholder = "Enter Address" style="width:100%"/></td>
			  		</tr>
			  	</table>
		  	</form>
		  	<table id="publisher-table" width = 100%>
			  <thead>
			  	<tr>
				    <th>PID</th>
				    <th>Name</th>
				    <th>Address</th>
				    <th>Phone</th>
				    <th style="width:15%">Edit Publisher</th>
			    </tr>
			  </thead>
			  <tbody>
			  </tbody>
			</table>  
		  </div>
		  <div id="publisher-tabs-2">
		  	<form id="publisherAddForm">
			  	<table>
			  		<tr>
			  			<td style="width:20%"><label for = "name"><b>Publisher Name : </b></label></td>
					  	<td style="width:35%"><input type="text" id = "apublishername" name="name" value= "" placeholder = "Enter Publisher Name" style="width:90%" required/></td>
					  	<td style="width:15%"><label for = "address"><b>Address : </b></label></td>
					  	<td style="width:30%"><input type="text" id = "apublisheraddress" name="address" value= "" placeholder = "Enter Address" style="width:100%" required/></td>
			  		</tr>
			  		<tr>
			  			<td style="width:20%"><label for = "phone"><b>Phone : </b></label></td>
					  	<td style="width:30%"><input type="text" id = "apublisherphone" name="phone" value= "" placeholder = "Enter Phone" style="width:90%" required/></td>
			  			<td></td>
			  			<td><input type="submit" value="Add Publisher" style ="width:70%;float:right"/></td>
			  		</tr>
			  	</table>
		  	</form>
		  	<p id="addPublisherResult"></p>
		  </div>
		  <div id="publisher-tabs-3">
		 		<form id="publisherEditForm">
			  	<table>
			  		<tr>
			  			<td style="width:20%"><label for = "pid"><b>PID : </b></label></td>
					  	<td style="width:35%"><input type="text" id = "epublisherid" name="pid" value= "" placeholder = "Enter PID" style="width:90%" required/></td>
			  			<td style="width:20%"><label for = "name"><b>Publisher Name : </b></label></td>
					  	<td style="width:35%"><input type="text" id = "epublishername" name="name" value= "" placeholder = "Enter Publisher Name" style="width:90%"/></td>	
  			  		</tr>
			  		<tr>
			  			<td style="width:15%"><label for = "address"><b>Address : </b></label></td>
					  	<td style="width:30%"><input type="text" id = "epublisheraddress" name="address" value= "" placeholder = "Enter Address" style="width:90%"/></td>
			  			<td style="width:20%"><label for = "phone"><b>Phone : </b></label></td>
					  	<td style="width:30%"><input type="text" id = "epublisherphone" name="phone" value= "" placeholder = "Enter Phone" style="width:90%"/></td>
			  			
			  		</tr>
			  	</table>
			  	<input type="submit" value="Edit Publisher"/>
		  	</form>
		  	<p id="editPublisherResult"></p>
		  </div>
		</div>
	</div>
	<div id="Categories" class="main" style='display:none'>
		<h3>Categories</h3><hr>
		<div id="categoryTabs">
		  <ul>
		    <li><a href="#category-tabs-1" onclick="update_category_table()">Search Categories</a></li>
		    <li><a href="#category-tabs-2">Add Category</a></li>
		    <li><a id = "edit-category-link" href="#category-tabs-3">Edit Category</a></li>
		  </ul>
		  <div id="category-tabs-1">
		  	<form id="categorySearchForm">
			  	<table>
			  		<tr>
			  			<td style="width:10%"><label for = "cid"><b>CID : </b></label></td>
					  	<td style="width:30%"><input type="text" id = "icategoryid" name="cid" value= "" placeholder = "Enter CID" style="width:70%"/></td>
			  			<td style="width:20%"><label for = "name"><b>Category Name : </b></label></td>
					  	<td style="width:30%"><input class="autocategory"type="text" id = "icategoryname" name="name" value= "" placeholder = "Enter Category Name" style="width:100%"/></td>
			  		</tr>
			  	</table>
		  	</form>
		  	<table id="category-table" width=100%>
			  <thead>
			  	<tr>
				    <th style="width:15%">CID</th>
				    <th>Name</th>
				    <th style="width:15%">Edit Category</th>
			    </tr>
			  </thead>
			  <tbody>
			  </tbody>
			</table>  
		  </div>
		  <div id="category-tabs-2">
		  	<form id="categoryAddForm">
			  	<table>
			  		<tr>
			  			<td style="width:20%"><label for = "name"><b>Category Name : </b></label></td>
					  	<td style="width:30%"><input type="text" id = "acategoryname" name="name" value= "" placeholder = "Enter Category Name" style="width:100%" required/></td>
					  	<td><input type="submit" value="Add Category" style ="width:70%;float:right"/></td>
			  		</tr>
			  	</table>
		  	</form>
		  	<p id="addCategoryResult"></p>
		  </div>
		  <div id="category-tabs-3">
		  	<form id="categoryEditForm">
			  	<table>
			  		<tr>
			  			<td style="width:10%"><label for = "cid"><b>CID : </b></label></td>
					  	<td style="width:30%"><input type="text" id = "ecategoryid" name="cid" value= "" placeholder = "Enter CID" style="width:70%" required/></td>
			  			<td style="width:20%"><label for = "name"><b>New Category Name : </b></label></td>
					  	<td style="width:30%"><input type="text" id = "ecategoryname" name="name" value= "" placeholder = "Enter New Category Name" style="width:100%" required/></td>
			  		</tr>
			  	</table>
			  	<input type="submit" value="Edit Category"/>
		  	</form>
		  	<p id="editCategoryResult"></p>
		  </div>
		</div>
	</div>
	<div id="Orders" class="main" style='display:none'>
		<h3>Orders</h3><hr>
		<div id="orderTabs">
		  <ul>
		    <li><a href="#order-tabs-1" onclick="update_order_table()">Search Orders</a></li>
		    <li><a href="#order-tabs-2">Add Order</a></li>
		  </ul>
		  <div id="order-tabs-1">
		  	<form id="orderSearchForm">
			  	<table width=100%>
			  		<tr>
			  			<td style="width:20%"><label for = "isbn"><b>ISBN : </b></label></td>
					  	<td style="width:35%"><input type="text" id = "iorderisbn" name="isbn" value= "" placeholder = "Enter ISBN" style="width:70%"/></td>
			  			<td style="width:20%"></td>
					  	<td style="width:35%"></td>
			  		</tr>
			  		<tr>
			  			<td style="width:20%"><label for = "lq"><b>Min Quantity : </b></label></td>
					  	<td style="width:35%"><input type="text" id = "iorderlq" name="lq" value= "" placeholder = "Enter Min Quantity" style="width:70%"/></td>
			  			<td style="width:20%"><label for = "hq"><b>Max Quantity : </b></label></td>
					  	<td style="width:35%"><input type="text" id = "iorderhq" name="hq" value= "" placeholder = "Enter Max Quantity" style="width:70%"/></td>
			  		</tr>
			  		<tr>
			  			<td style="width:20%"><label for = "ldate"><b>From : </b></label></td>
					  	<td style="width:35%"><input class ="mydatepickers"type="text" id = "iorderldate" name="ldate" value= "" placeholder = "Enter Date" style="width:70%"/></td>
			  			<td style="width:20%"><label for = "hdate"><b>To : </b></label></td>
					  	<td style="width:35%"><input class ="mydatepickers"type="text" id = "iorderhdate" name="hdate" value= "" placeholder = "Enter Date" style="width:70%"/></td>
			  		</tr>
			  	</table>
		  	</form>
		 	<table id="order-table" width=100%>
			  <thead>
			  	<tr>
				    <th>ISBN</th>
				    <th>Time Requested</th>
				    <th>Quantity</th>
				    <th style="width:15%">Confirm Order</th>
			    </tr>
			  </thead>
			  <tbody>
			  </tbody>
			</table>  
		  </div>
		  <div id="order-tabs-2">
		  		<form id="orderAddForm">
			  	<table>
			  		<tr>
			  			<td style="width:20%"><label for = "isbn"><b>ISBN: </b></label></td>
					  	<td style="width:35%"><input type="text" id = "aorderisbn" name="isbn" value= "" placeholder = "Enter ISBN" style="width:70%" required/></td>
					  	<td style="width:20%"><label for = "quantity"><b>Quantity: </b></label></td>
					  	<td style="width:35%"><input type="text" id = "aorderquantity" name="quantity" value= "" placeholder = "Enter Quantity" style="width:70%" required/></td>
					  	
			  		</tr>
			  	</table>
			  	<input type="submit" value="Add Order"/>
		  	</form>
		  	<p id="addOrderResult"></p>
		  </div>
		</div>
	</div>
	<div id="Users" class="main" style='display:none'>
		<h3>Upgrade Users</h3><hr>
		<form id="userSearchForm">
			  	<table width=100%>
			  		<tr>
			  			<td style="width:20%"><label for = "username"><b>Username : </b></label></td>
					  	<td style="width:35%"><input type="text" id = "iuserusername" name="username" value= "" placeholder = "Enter Username" style="width:70%"/></td>
			  			<td style="width:20%"><label for = "email"><b>Email : </b></label></td>
					  	<td style="width:35%"><input type="text" id = "iuseremail" name="email" value= "" placeholder = "Enter Email" style="width:70%"/></td>
			  		</tr>
			  		<tr>
			  			<td style="width:20%"><label for = "firstName"><b>First Name : </b></label></td>
					  	<td style="width:35%"><input type="text" id = "iuserfirstname" name="firstName" value= "" placeholder = "Enter First Name" style="width:70%"/></td>
			  			<td style="width:20%"><label for = "lastName"><b>Last Name : </b></label></td>
					  	<td style="width:35%"><input type="text" id = "iuserlastname" name="lastName" value= "" placeholder = "Enter Last Name" style="width:70%"/></td>
			  		</tr>
			  		<tr>
			  			<td style="width:20%"><label for = "phone"><b>Phone : </b></label></td>
					  	<td style="width:35%"><input type="text" id = "iuserphone" name="phone" value= "" placeholder = "Enter Phone" style="width:70%"/></td>
			  			<td style="width:20%"><label for = "address"><b>Address : </b></label></td>
					  	<td style="width:35%"><input type="text" id = "iuseraddress" name="address" value= "" placeholder = "Enter Address" style="width:70%"/></td>
			  		</tr>
			  		<tr>
			  			<td><label for="usertype"><b>User Type : </b></label></td>
			  			<td><select id="iusertype" name="usertype">
							  <option >All</option>
							  <option >Managers</option>
							  <option >Only Users</option>
							</select></td>
			  			<td></td>
			  			<td></td>
			  		</tr>
			  	</table>
		  	</form>
		  	<br>
		<table id="user-table" width=100%>
		  <thead>
		  	<tr>
			    <th>Username</th>
			    <th>First Name</th>
			    <th>Last Name</th>
			    <th>Email</th>
			    <th>Phone</th>
			    <th>Address</th>
			    <th style="width:15%">Upgrade To Manager</th>
		    </tr>
		  </thead>
		  <tbody>
		  </tbody>
		</table>  
	</div>
	<div id="Reports" class="main" style='display:none'>
		<h3>Reports</h3><hr>
		<div id="reportTabs">
		 <ul>
		    <li><a href="#pdf1" onclick="ask_for_report(0)">Monthly Total Book Sales</a></li>
		    <li><a href="#pdf2" onclick="ask_for_report(1)">Monthly Individual Books Sales</a></li>
		    <li><a href="#pdf3" onclick="ask_for_report(2)">Top 5 Users(Money Spend) last 3 months</a></li>
		    <li><a href="#pdf4" onclick="ask_for_report(3)">Top 5 Users(Books Bought) last 3 months</a></li>
		    <li><a href="#pdf5" onclick="ask_for_report(4)">Top 10 Books(Money Gain) last 3 months</a></li>
		    <li><a href="#pdf6" onclick="ask_for_report(5)">Top 10 Books(Amount Sold) last 3 months</a></li>
		  </ul>
		  <div class="pdfframes" id = "pdf1">
		  <iframe class="pdfframes" id = "ipdf1" width=100% >
		  </iframe>
		  </div>
		  <div class="pdfframes" id = "pdf2">
		  <iframe class="pdfframes" id = "ipdf2" width=100% >
		  </iframe>
		  </div>
		  <div class="pdfframes" id = "pdf3">
		  <iframe  class="pdfframes"id = "ipdf3" width=100% >
		  </iframe>
		  </div>
		  <div class="pdfframes" id = "pdf4">
		  <iframe class="pdfframes" id = "ipdf4" width=100% >
		  </iframe>
		  </div>
		  <div class="pdfframes" id = "pdf5">
		  <iframe class="pdfframes" id = "ipdf5" width=100% >
		  </iframe>
		  </div>
		  <div class="pdfframes" id = "pdf6">
		  <iframe class="pdfframes" id = "ipdf6" width=100% >
		  </iframe>
		  </div>
	  </div>
	</div>
</div>
</body>
</html>