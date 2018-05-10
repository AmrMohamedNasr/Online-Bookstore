var serverUrl = "http://localhost:8080/BookStore"

function edit_user() {
	 var url = serverUrl + "/editUser"; // the script where you handle the form input.
		
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
}

function edit_password() {
	var url = serverUrl + "/editUser";
	
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
}