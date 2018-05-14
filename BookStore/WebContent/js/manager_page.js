$(function() {
	var serverUrl = "http://localhost:8080/BookStore";
	$("#managerNav").attr('class','active');
	/** Date Pickers **/
	$(".mydatepickers").each(function (e) {
		$(this).datepicker();
	});
	/** Tabs Initialization **/
	$("#bookTabs").tabs();
	$("#authorTabs").tabs();
	$("#categoryTabs").tabs();
	$("#publisherTabs").tabs();
	$("#orderTabs").tabs();
	/** Tables Initialization **/
	$('#books-table').dynatable(
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
		    queries: $("#iisbn,#ititle,#ipubdate,#iprice1,#iprice2,#icategory,#ipublisher,#iauthor,\
		    		#ithreshold1,#ithreshold2,#icopies1,#icopies2") ,
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
		    ajaxUrl: serverUrl + "/bookmgr",
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
	
	/** Autocomplete Inialization **/
	$('.autocategory').each(function(e) {
		$(this).autocomplete({
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
	});
	$('.autopublisher').each(function (e) {
		$(this).autocomplete({
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
	});
	$('.autotitle').each(function (e) {
		$(this).autocomplete({
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
	});
	$('.autoauthor').each(function (e) {
		$(this).autocomplete({
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
				var tok = $(this).val();
				tok = tok.substring(0, tok.lastIndexOf(";") + 1);
				var s = "";
	            if (typeof data == "undefined") {
	              tok = $(this).val();
	            }else {
	              tok += data.item.value;
	            }
	            $(this).val(tok);
			}
		});
	});
	/** Form actions **/
	$('#bookAddForm').submit(function(e) {
		add_book();
	 	e.preventDefault()
	});
	$('#bookEditForm').submit(function(e) {
		edit_book();
	 	e.preventDefault()
	});
});