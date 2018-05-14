var serverUrl = "http://localhost:8080/BookStore";
$(function() {
	$("#promptDialog").dialog({
        autoOpen: false,
        modal: true
	});
	$("#userNav").attr('class','active');
	$("#profileForm").submit(function(e) {
		edit_user();
	 	e.preventDefault()
	});
	$('#hasStock').change(function() {
        if($(this).is(":checked")) {
        	$('#my-final-table').data('dynatable').queries.add('hasStock', 'yes');
        	$('#my-final-table').data('dynatable').process();
        } else {
        	$('#my-final-table').data('dynatable').queries.add('hasStock', 'no');
        	$('#my-final-table').data('dynatable').process();
        }       
    });
	$("#passwordForm").submit(function(e) {
		edit_password();
	 	e.preventDefault()
	});
	$( "#datepicker" ).datepicker();
	$('#my-final-table').dynatable(
		{
		  features: {
		    paginate: true,
		    sort: true,
		    pushState: false,
		    search: false,
		    recordCount: true,
		    perPageSelect: true
		  },
		  inputs: {
		    queries: $("#iisbn,#ititle,#datepicker,#iprice1,#iprice2,#icategory,#ipublisher,#iauthor") ,
		    sorts: null,
		    multisort: null,
		    page: null,
		    queryEvent: 'blur change',
		    recordCountTarget: null,
		    recordCountPlacement: 'after',
		    paginationLinkTarget: null,
		    paginationLinkPlacement: 'after',
		    paginationPrev: 'Previous',
		    paginationNext: 'Next',
		    paginationGap: [1,2,2,1],
		    searchTarget: null,
		    searchPlacement: 'before',
		    perPageTarget: null,
		    perPagePlacement: 'before',
		    perPageText: 'Show: ',
		    recordCountText: 'Showing ',
		    processingText: 'Processing...'
		  },
		  dataset: {
		    ajax: true,
		    ajaxUrl: serverUrl + "/book",
		    ajaxCache:true,
		    ajaxOnLoad: true,
		    ajaxMethod: 'GET',
		    ajaxDataType: 'json',
		    records:[],
		    perPageDefault: 5,
		    perPageOptions: [5, 10, 25, 50, 100]
		  },
		  params: {
		    dynatable: 'dynatable',
		    queries: 'queries',
		    sorts: 'sorts',
		    page: 'page',
		    perPage: 'perPage',
		    offset: 'offset',
		    records: 'records',
		    record: null,
		    queryRecordCount: 'queryRecordCount',
		    totalRecordCount: 'totalRecordCount'
		  }
		}
	);
	$('#cartTable').dynatable({
		features:{
			pushState:false,
			search:false
		},
		dataset:{
			perPageDefault: 5,
		    perPageOptions: [5, 10, 25, 50, 100]
		}
	});
	$('#my-final-table').bind('dynatable:ajax:success', function (e, data) {
		if (data.code == 200) {
			$("#searchResult").text("");
			$("#searchResult").css('color', 'green');
		} else {
			$("#searchResult").text(data.message);
			$("#searchResult").css('color', 'red');
		}
	});
	$('#cartTable').bind('dynatable:afterProcess', function(e, data) {
		$( ".enterTextbox:not(.bound)" ).addClass('bound').keypress(function(e) {
        	if (e.which == 13) {
                modify_cart($(this).attr('id'), $(this).val());
                update_cart_table();
            }
      	});
	});
	$('#icategory').autocomplete({
		source:function(request, response) {
			$.ajax({
                url: "auto",
                type: "GET",
                data: {
                    term: request.term,
                    type: "category"
                },
                dataType: "json",
                success: function(data) {
                    response(data);
                }
            });
		}
	});
	$('#ipublisher').autocomplete({
		source:function(request, response) {
			$.ajax({
                url: "auto",
                type: "GET",
                data: {
                    term: request.term,
                    type: "publisher"
                },
                dataType: "json",
                success: function(data) {
                    response(data);
                }
            });
		}
	});
	$('#ititle').autocomplete({
		source:function(request, response) {
			$.ajax({
                url: "auto",
                type: "GET",
                data: {
                    term: request.term,
                    type: "title"
                },
                dataType: "json",
                success: function(data) {
                    response(data);
                }
            });
		}
	});
	$('#iauthor').autocomplete({
		source:function(request, response) {
			var tok = request.term.substring(request.term.lastIndexOf(";") + 1);
				if(tok.length != 0) {
				$.ajax({
                    url: "auto",
                    type: "GET",
                    data: {
                        term: tok,
                        type: "author"
                    },
                    dataType: "json",
                    success: function(data) {
                        response(data);
                    }
                });
			}
		},
		select:function(event, data) {
			event.preventDefault();
			var tok = $('#iauthor').val();
			tok = tok.substring(0, tok.lastIndexOf(";") + 1);
			var s = "";
            if (typeof data == "undefined") {
              tok = $('#iauthor').val();
            }else {
              tok += data.item.value;
            }
            $('#iauthor').val(tok);
		}
	});
	
});
function toggle_credit_display() {
    var x = document.getElementById("fullcartdiv2");
    var y = document.getElementById("fullcartdiv1");
    if (x.style.display === "none") {
        x.style.display = "block";
        y.style.display = "none";
    } else {
    	x.style.display = "none";
        y.style.display = "block";
    }
}