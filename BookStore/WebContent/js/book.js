var serverUrl = "http://localhost:8080/BookStore"

function search_books(e, t, d, f) {
	var url = serverUrl  + "/book";
	
    $.ajax({
           type: "GET",
           url: url,
           data: f.serialize(), // serializes the form's elements.
           dataType: "json",
           success: function(data)
           {
        	   
        	   if (data.code == 200) {
        		   
	               t.text("");
	           	   t.css('color', 'green');
	           	   
		            var myRecords = data.values;
		            if (myRecords.length > 0) {
		            	d.css('display','block');
		            } else {
		            	d.css('display','none');
		            }
		            var i = 0;
		            for (i = 0; i < myRecords.length; i++) {
		            	myRecords[i].addToCart = "<button onclick='add_to_cart("+myRecords[i].isbn +")' >Add to Cart</button>";
		            }
		            var dynatable = e.dynatable({
	                    dataset: {
	                        records: myRecords
	                    }
	                }).data('dynatable');
		            dynatable.settings.dataset.originalRecords = myRecords;
	                dynatable.process();
	                
        	   } else {
        		  	t.text(data.message);
            		t.css('color', 'red');
        	   }
           },
           error:function(result)
           {
        	   t.text(result.message);
               t.css('color', 'red');
          	}
         });
}