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
          var iFrame = document.getElementById( 'ipdf' + (i+1) );
          var pdiv = document.getElementById('pdf'+(i+1));
          resizeIFrameToFitContent(pdiv, iFrame );
      },
      error:function(result)
      {
    	  make_notification("Not Found");
  		}
    });
	
}
