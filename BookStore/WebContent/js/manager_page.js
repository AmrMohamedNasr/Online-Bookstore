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
	$('#author-table').dynatable(
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
		    queries: $("#iauthorid,#iauthorname") ,
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
		    ajaxUrl: serverUrl + "/authormgr",
		    ajaxCache:true,
		    ajaxOnLoad: false,
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
	$('#publisher-table').dynatable(
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
			    queries: $("#ipublisherid,#ipublishername,#ipublisheraddress,#ipublisherphone") ,
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
			    ajaxUrl: serverUrl + "/publishermgr",
			    ajaxCache:true,
			    ajaxOnLoad: false,
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
	$('#category-table').dynatable(
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
			    queries: $("#icategoryid,#icategoryname") ,
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
			    ajaxUrl: serverUrl + "/categorymgr",
			    ajaxCache:true,
			    ajaxOnLoad: false,
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
	$('#order-table').dynatable(
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
			    queries: $("#iorderisbn,#iorderlq,#iorderhq,#iorderldate,#iorderhdate") ,
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
			    ajaxUrl: serverUrl + "/ordermgr",
			    ajaxCache:true,
			    ajaxOnLoad: false,
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
	$('#user-table').dynatable(
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
			    queries: $("#iuserusername,#iuserfirstname,#iuserlastname,#iuseremail,#iuserphone,#iuseraddress") ,
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
			    ajaxUrl: serverUrl + "/usermgr",
			    ajaxCache:true,
			    ajaxOnLoad: false,
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
	$('.autosauthor').each(function (e) {
			$(this).autocomplete({
				source:function(request, response) {
				$.ajax({
	                url: "auto",
	                type: "GET",
	                data: {
	                    term: request.term,
	                    type: "author"
	                },
	                dataType: "json",
	                success: function(data) {
	                    response(data);
	                }
	            });
			}
			})
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
	$('#authorAddForm').submit(function(e) {
		add_author();
	 	e.preventDefault()
	});
	$('#authorEditForm').submit(function(e) {
		edit_author();
	 	e.preventDefault()
	});
	$('#publisherAddForm').submit(function(e) {
		add_publisher();
	 	e.preventDefault()
	});
	$('#publisherEditForm').submit(function(e) {
		edit_publisher();
	 	e.preventDefault()
	});
	$('#categoryAddForm').submit(function(e) {
		add_category();
	 	e.preventDefault()
	});
	$('#categoryEditForm').submit(function(e) {
		edit_category();
	 	e.preventDefault()
	});
	$('#orderAddForm').submit(function(e) {
		add_order();
	 	e.preventDefault()
	});
});
function update_book_table() {
	$('#books-table').data('dynatable').process();
}
function update_author_table() {
	$('#author-table').data('dynatable').process();
}
function update_category_table() {
	$('#category-table').data('dynatable').process();
}
function update_publisher_table() {
	$('#publisher-table').data('dynatable').process();
}
function update_order_table() {
	$('#order-table').data('dynatable').process();
}
function update_user_table() {
	$('#user-table').data('dynatable').process();
}