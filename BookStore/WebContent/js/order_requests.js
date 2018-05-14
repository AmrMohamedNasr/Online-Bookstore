var serverUrl = "http://localhost:8080/BookStore";

function add_order() {
	var url = serverUrl + "/ordermgr";
	var data = $("#orderAddForm").serialize();
   $.ajax({
      type: "POST",
      url: url,
      data: data, // serializes the form's elements.
      success: function(data)
      {
   	   if (data.code == 200) {
              $("#addOrderResult").text(data.message);
              $("#addOrderResult").css('color', 'green');
              update_order_table();
              $('#aorderisbn').val("");
              $('#aorderquantity').val("");
   	   } else {
   		   $("#addOrderResult").text(data.message);
          $("#addOrderResult").css('color', 'red');
   	   }
      },
      error:function(result)
      {
   	   	$("#addOrderResult").text(result.message);
   	   	$("#addOrderResult").css('color', 'red');
      }
    });
}

function confirm_order(isbn, timestamp) {
	var url = serverUrl + "/ordermgr";
	var data ={'confirm':true, 'isbn':isbn,'timestamp':timestamp};
   $.ajax({
      type: "POST",
      url: url,
      data: data, // serializes the form's elements.
      success: function(data)
      {
   	   if (data.code == 200) {
              make_notification("Confirmed Order..");
              update_order_table();
   	   } else {
   		   	make_notification("Failed to confirm : " + data.message);
   	   }
      },
      error:function(result)
      {
    	  make_notification("Failed to confirm..");
      }
    });
}
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