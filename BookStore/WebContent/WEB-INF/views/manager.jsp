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
		    <li><a href="#book-tabs-3">Edit Book</a></li>
		  </ul>
		  <div id="book-tabs-1">
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
		    <p>Morbi tincidunt, dui sit amet facilisis feugiat, odio metus gravida ante, ut pharetra massa metus id nunc. Duis scelerisque molestie turpis. Sed fringilla, massa eget luctus malesuada, metus eros molestie lectus, ut tempus eros massa ut dolor. Aenean aliquet fringilla sem. Suspendisse sed ligula in ligula suscipit aliquam. Praesent in eros vestibulum mi adipiscing adipiscing. Morbi facilisis. Curabitur ornare consequat nunc. Aenean vel metus. Ut posuere viverra nulla. Aliquam erat volutpat. Pellentesque convallis. Maecenas feugiat, tellus pellentesque pretium posuere, felis lorem euismod felis, eu ornare leo nisi vel felis. Mauris consectetur tortor et purus.</p>
		  </div>
		  <div id="book-tabs-3">
		    <p>Mauris eleifend est et turpis. Duis id erat. Suspendisse potenti. Aliquam vulputate, pede vel vehicula accumsan, mi neque rutrum erat, eu congue orci lorem eget lorem. Vestibulum non ante. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce sodales. Quisque eu urna vel enim commodo pellentesque. Praesent eu risus hendrerit ligula tempus pretium. Curabitur lorem enim, pretium nec, feugiat nec, luctus a, lacus.</p>
		    <p>Duis cursus. Maecenas ligula eros, blandit nec, pharetra at, semper at, magna. Nullam ac lacus. Nulla facilisi. Praesent viverra justo vitae neque. Praesent blandit adipiscing velit. Suspendisse potenti. Donec mattis, pede vel pharetra blandit, magna ligula faucibus eros, id euismod lacus dolor eget odio. Nam scelerisque. Donec non libero sed nulla mattis commodo. Ut sagittis. Donec nisi lectus, feugiat porttitor, tempor ac, tempor vitae, pede. Aenean vehicula velit eu tellus interdum rutrum. Maecenas commodo. Pellentesque nec elit. Fusce in lacus. Vivamus a libero vitae lectus hendrerit hendrerit.</p>
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