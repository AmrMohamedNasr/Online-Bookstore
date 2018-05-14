var serverUrl = "http://localhost:8080/BookStore";

function edit_author_info(aid) {
	var url = serverUrl + "/authormgr";
    // the script where you handle the form input.	
   $.ajax({
      type: "GET",
      url: url,
      data: {'queries[aid]':aid}, // serializes the form's elements.
      success: function(data)
      {
   	   if (data.code == 200 && data.records.length == 1) {
   		   var record = data.records[0];
              $('#eauthorid').val(record.aid);
              $('#eauthorname').val(record.name);
              $("#edit-author-link").trigger("click");
   	   }
      },
      error:function(result)
      {
  		}
    });
	
}

function add_author() {
	var url = serverUrl + "/authormgr";
	var data = $("#authorAddForm").serialize();
   $.ajax({
      type: "POST",
      url: url,
      data: data, // serializes the form's elements.
      success: function(data)
      {
   	   if (data.code == 200) {
              $("#addAuthorResult").text(data.message);
              $("#addAuthorResult").css('color', 'green');
              update_author_table();
              $('#aauthorname').val("");
   	   } else {
   		   $("#addAuthorResult").text(data.message);
          $("#addAuthorResult").css('color', 'red');
   	   }
      },
      error:function(result)
      {
   	   	$("#addAuthorResult").text(result.message);
   	   	$("#addAuthorResult").css('color', 'red');
      }
    });
}

function edit_author() {
	var url = serverUrl + "/authormgr";
	var data = $("#authorEditForm").serialize();
	data += "&edit=true";
   $.ajax({
      type: "POST",
      url: url,
      data: data, // serializes the form's elements.
      success: function(data)
      {
   	   if (data.code == 200) {
              $("#editAuthorResult").text(data.message);
              $("#editAuthorResult").css('color', 'green');
              update_author_table();
   	   } else {
   		   $("#editAuthorResult").text(data.message);
   		   $("#editAuthorResult").css('color', 'red');
   	   }
      },
      error:function(result)
      {
   	   $("#editAuthorResult").text(result.message);
   	   $("#editAuthorResult").css('color', 'red');
      }
    });
}