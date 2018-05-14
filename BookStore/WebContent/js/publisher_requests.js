var serverUrl = "http://localhost:8080/BookStore";

function edit_publisher_info(pid) {
	var url = serverUrl + "/publishermgr";
    // the script where you handle the form input.	
   $.ajax({
      type: "GET",
      url: url,
      data: {'queries[pid]':pid}, // serializes the form's elements.
      success: function(data)
      {
   	   if (data.code == 200 && data.records.length == 1) {
   		   var record = data.records[0];
              $('#epublisherid').val(record.pid);
              $('#epublishername').val(record.name);
              $('#epublisheraddress').val(record.address);
              $('#epublisherphone').val(record.phone);
              $("#edit-publisher-link").trigger("click");
   	   }
      },
      error:function(result)
      {
  		}
    });
	
}

function add_publisher() {
	var url = serverUrl + "/publishermgr";
	var data = $("#publisherAddForm").serialize();
   $.ajax({
      type: "POST",
      url: url,
      data: data, // serializes the form's elements.
      success: function(data)
      {
   	   if (data.code == 200) {
              $("#addPublisherResult").text(data.message);
              $("#addPublisherResult").css('color', 'green');
              update_publisher_table();
              $('#apublishername').val("");
              $('#apublisheraddress').val("");
              $('#apublisherphone').val("");
   	   } else {
   		   $("#addPublisherResult").text(data.message);
   		   $("#addPublisherResult").css('color', 'red');
   	   }
      },
      error:function(result)
      {
   	   	$("#addPublisherResult").text(result.message);
   	   	$("#addPublisherResult").css('color', 'red');
      }
    });
}

function edit_publisher() {
	var url = serverUrl + "/publishermgr";
	var data = $("#publisherEditForm").serialize();
	data += "&edit=true";
   $.ajax({
      type: "POST",
      url: url,
      data: data, // serializes the form's elements.
      success: function(data)
      {
   	   if (data.code == 200) {
              $("#editPublisherResult").text(data.message);
              $("#editPublisherResult").css('color', 'green');
              update_publisher_table();
   	   } else {
   		   $("#editPublisherResult").text(data.message);
   		   $("#editPublisherResult").css('color', 'red');
   	   }
      },
      error:function(result)
      {
   	   $("#editPublisherResult").text(result.message);
   	   $("#editPublisherResult").css('color', 'red');
      }
    });
}