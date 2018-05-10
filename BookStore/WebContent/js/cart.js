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
	var url = serverUrl  + "/cart";
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