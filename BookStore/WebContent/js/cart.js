var serverUrl = "http://localhost:8080/BookStore"

function clear_cart() {
	var url = serverUrl  + "/cart";
	
    $.ajax({
       type: "DELETE",
       url: url,
       success: function(data)
       {
    	   
    	   if (data.code == 200) {
    		    
	           update_cart_table();
	           make_notification("Cleared cart");
	           $('#my-final-table').data('dynatable').process();
    	   } else {
    		   make_notification("Couldn't clear cart");
    	   }
       },
       error:function(result)
       {
    	   make_notification("Couldn't clear cart");
      	}
    });
}
function update_cart_table() {
	var url = serverUrl  + "/cart"; // the script where you handle the form input.
	
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
		            $("#totalcartprice").html("<b>Total price : </b>" + data.totalcartprice + " $");
		            $("#totalcartprice2").html("<b>Total price : </b>" + data.totalcartprice + " $");
		            for (i = 0; i < myRecords.length; i++) {
		            	myRecords[i].copies = "<div><input type='text' id='"+myRecords[i].isbn+"' class='enterTextbox' value='"+ myRecords[i].copies+ "' placeholder='Enter copies number' style='width:100%'/></div>"
		            }
		            var dynatable = $('#cartTable').dynatable({
	                    dataset: {
	                        records: myRecords
	                    }
	                }).data('dynatable');
		            dynatable.settings.dataset.originalRecords = myRecords;
	                dynatable.process();
        	   }
           },
           error:function(result)
           {
        	   alert("ERROR");
        	   console.log(result.responseText);
          	}
         });
    
}
function inner_add_to_cart(isbn, quantity) {
	var url = serverUrl  + "/cart";
	if (!isNaN(quantity) && quantity > 0) {
		var data = {isbn : isbn};
		$.ajax({
           type: "POST",
           url: url,
           data: data, // serializes the form's elements.
           success: function(data)
           {
        	   if (data.code == 200) {
        		   data = {isbn : isbn, amount:quantity};
        		   $.ajax({
    		           type: "POST",
    		           url: url,
    		           data: data, // serializes the form's elements.
    		           success: function(data)
    		           {
    		        	   if (data.code == 200) {
    		        		   make_notification("Added to Cart...");
    		        		   $('#my-final-table').data('dynatable').process();
    		        	   } else {
    		        		   make_notification("Couldn't add to cart..." + data.message);
    		        	   }
    		           },
    		           error:function(result)
        		        {
        		        	   make_notification("Couldn't add to cart... : " + data.message);
        		       	}
        		     });
        	   } else {
        		   make_notification("Couldn't add to cart..." + data.message);
        	   }
	           },
           error:function(result)
	        {
	        	   make_notification("Couldn't add to cart..." + data.message);
	       	}
         });
	} else {
		make_notification("Invalid Input !");
	}
}
function add_to_cart(isbn) {
	showDialog('Please Enter Your Requested Number Of Copies : ', 1, inner_add_to_cart, isbn);
};
function inner_modify_to_cart(isbn, quantity) {
	if (!isNaN(quantity)) {
		modify_cart(isbn, quantity);
	} else {
		make_notification("Invalid input");
	}
}
function modify_in_cart(isbn) {
	showDialog('Please Enter Your Requested Number Of Copies : ', 0, inner_modify_to_cart, isbn);
}
function modify_cart(isbn, amount) {
	var url = serverUrl  + "/cart";
	var data = {isbn : isbn,
			amount:amount};
	$.ajax({
           type: "POST",
           url: url,
           data: data, // serializes the form's elements.
           success: function(data)
           {
        	   if (data.code == 200) {
        		   make_notification("Modified Cart...");
        		   $('#my-final-table').data('dynatable').process();
        	   } else {
        		   make_notification("Couldn't modify cart..." + data.message);
        	   }
           },
           error:function(result)
        {
        	   make_notification("Couldn't modify cart..." + data.message);
       	}
     });
};
function make_notification(message) {
	if (("Notification" in window)) {
		if (Notification.permission === "granted") {
		    // If it's okay let's create a notification
		    var notification = new Notification(message);
		}  else if (Notification.permission !== "denied") {
		    Notification.requestPermission(function (permission) {
	  		      // If the user accepts, let's create a notification
		     	 if (permission === "granted") {
		        	var notification = new Notification(message);
		      	} else {
		      		alert(message);
		      	}
	   	 	});
		 } else {
			 alert(message);
		 }
	} else {
		alert(message);
	}
}