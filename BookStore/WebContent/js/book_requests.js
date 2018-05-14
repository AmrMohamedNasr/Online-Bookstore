var serverUrl = "http://localhost:8080/BookStore";

function edit_book_info(isbn) {
	 var url = serverUrl + "/bookmgr"; // the script where you handle the form input.	
    $.ajax({
       type: "GET",
       url: url,
       data: {'queries[isbn]':isbn, 'conauthor':true}, // serializes the form's elements.
       success: function(data)
       {
    	   if (data.code == 200 && data.records.length == 1) {
    		   var record = data.records[0];
               $('#etitle').val(record.title);
               $('#eoldisbn').val(record.isbn);
               $('#enewisbn').val(record.isbn);
               $('#ecategory').val(record.category);
               $('#epublisher').val(record.publisher);
               $('#eprice').val(record.price);
               $('#ecopies').val(record.numberOfCopies);
               $('#ethreshold').val(record.threshold);
               var date = new Date(record.publicationDate);
               var day = date.getDate();
               if (day < 10) {
            	   day = "0" + day;
               }
               var mon = date.getMonth() + 1;
               if (mon < 10) {
            	   mon = "0" + mon;
               }
               var newDate = mon + "/" + day + "/" + date.getFullYear();
               $('#epubdate').val(newDate);
               $('#eauthor').val(record.authors);
               $("#edit-book-link").trigger("click");
    	   }
       },
       error:function(result)
       {
   		}
     });
	
}

function add_book() {
	var url = serverUrl + "/bookmgr"; // the script where you handle the form input.
	var data = $("#bookAddForm").serialize();
    $.ajax({
       type: "POST",
       url: url,
       data: data, // serializes the form's elements.
       success: function(data)
       {
    	   if (data.code == 200) {
               $("#addBookResult").text(data.message);
               $("#addBookResult").css('color', 'green');
               $("#books-table").data('dynatable').process();
               $('#atitle').val("");
               $('#aisbn').val("");
               $('#acategory').val("");
               $('#apublisher').val("");
               $('#aprice').val("");
               $('#acopies').val("");
               $('#athreshold').val("");
    	   } else {
    		   $("#addBookResult").text(data.message);
               $("#addBookResult").css('color', 'red');
    	   }
       },
       error:function(result)
       {
    	   $("#addBookResult").text(result.message);
           $("#addBookResult").css('color', 'red');
   		}
     });
}

function edit_book() {
	var url = serverUrl + "/bookmgr"; // the script where you handle the form input.
	var data = $("#bookEditForm").serialize();
	data += "&edit=true";
    $.ajax({
       type: "POST",
       url: url,
       data: data, // serializes the form's elements.
       success: function(data)
       {
    	   if (data.code == 200) {
               $("#editBookResult").text(data.message);
               $("#editBookResult").css('color', 'green');
               $("#books-table").data('dynatable').process();
    	   } else {
    		   $("#editBookResult").text(data.message);
               $("#editBookResult").css('color', 'red');
    	   }
       },
       error:function(result)
       {
    	   $("#editBookResult").text(result.message);
           $("#editBookResult").css('color', 'red');
   		}
     });
}