var serverUrl = "http://localhost:8080/BookStore";

function edit_category_info(cid) {
	var url = serverUrl + "/categorymgr";
    // the script where you handle the form input.	
   $.ajax({
      type: "GET",
      url: url,
      data: {'queries[cid]':cid}, // serializes the form's elements.
      success: function(data)
      {
   	   if (data.code == 200 && data.records.length == 1) {
   		   var record = data.records[0];
              $('#ecategoryid').val(record.cid);
              $('#ecategoryname').val(record.name);
              $("#edit-category-link").trigger("click");
   	   }
      },
      error:function(result)
      {
  		}
    });
	
}

function add_category() {
	var url = serverUrl + "/categorymgr";
	var data = $("#categoryAddForm").serialize();
   $.ajax({
      type: "POST",
      url: url,
      data: data, // serializes the form's elements.
      success: function(data)
      {
   	   if (data.code == 200) {
              $("#addCategoryResult").text(data.message);
              $("#addCategoryResult").css('color', 'green');
              update_category_table();
              $('#acategoryname').val("");
   	   } else {
   		   $("#addCategoryResult").text(data.message);
          $("#addCategoryResult").css('color', 'red');
   	   }
      },
      error:function(result)
      {
   	   	$("#addCategoryResult").text(result.message);
   	   	$("#addCategoryResult").css('color', 'red');
      }
    });
}

function edit_category() {
	var url = serverUrl + "/categorymgr";
	var data = $("#categoryEditForm").serialize();
	data += "&edit=true";
   $.ajax({
      type: "POST",
      url: url,
      data: data, // serializes the form's elements.
      success: function(data)
      {
   	   if (data.code == 200) {
              $("#editCategoryResult").text(data.message);
              $("#editCategoryResult").css('color', 'green');
              update_category_table();
   	   } else {
   		   $("#editCategoryResult").text(data.message);
   		   $("#editCategoryResult").css('color', 'red');
   	   }
      },
      error:function(result)
      {
   	   $("#editCategoryResult").text(result.message);
   	   $("#editCategoryResult").css('color', 'red');
      }
    });
}