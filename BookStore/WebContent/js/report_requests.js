var serverUrl = "http://localhost:8080/BookStore";

function ask_for_report(i) {
	var url = serverUrl + "/reportmgr";
	$.ajax({
      type: "GET",
      url: url,
      contentType: "application/pdf; charset=utf-8",
      data: {'reportIndex':i}, // serializes the form's elements.
      success: function(data)
      {
    	  var ifm = '#ipdf'+(i+1);
          $(ifm).attr('src',"data:application/pdf;charset=utf-8;base64,"+data.message);
      },
      error:function(result)
      {
    	  make_notification("Not Found");
  		}
    });
	
}
