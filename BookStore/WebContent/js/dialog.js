function showDialog(message, defV, callback, extraV) {
	var d = $("#promptDialog");
	$("#promptDialogLabel").text(message);
	$("#promptDialogNum").val(defV);
	d.promptCallback = callback;
	d.extraPromptParam = extraV;
	d.dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            "Ok": function() {
            	$("#promptForm").off("submit");
            	callback(extraV, $("#promptDialogNum").val());
                $(this).dialog("close");
            },
            "Cancel": function() {
            	$("#promptForm").off("submit");
                $(this).dialog("close");
            }
        }
    });
	$("#promptForm").one("submit", function (e) {
		callback(extraV, $("#promptDialogNum").val());
        $("#promptDialog").dialog("close");
        return false;
	});
	d.dialog("open");
}