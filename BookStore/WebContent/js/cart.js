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
	var url = serverUrl  + "/cart";
	var data = {isbn : isbn};
	$.ajax({
           type: "POST",
           url: url,
           data: data, // serializes the form's elements.
           success: function(data)
           {
        	   if (data.code == 200) {
        		   make_notification("Added to cart...");
        	   } else {
        		   make_notification("Couldn't add to cart...");
        	   }
           },
           error:function(result)
        {
        	   make_notification("Couldn't add to cart...");
       	}
         });
};

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
        	   } else {
        		   make_notification("Couldn't modify cart...");
        	   }
           },
           error:function(result)
        {
        	   make_notification("Couldn't modify cart...");
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