var serverUrl = "http://localhost:8080/BookStore"
$(function() {
	
    var owner = $('#owner');
    var cardNumber = $('#cardNumber');
    var cardNumberField = $('#card-number-field');
    var CVV = $("#cvv");
    var cmonth = $("#cmonth");
    var cyear = $("#cyear");
    var mastercard = $("#mastercard");
    var confirmButton = $('#confirm-purchase');
    var visa = $("#visa");
    var amex = $("#amex");

    // Use the payform library to format and validate
    // the payment fields.

    cardNumber.payform('formatCardNumber');
    CVV.payform('formatCardCVC');


    cardNumber.keyup(function() {

        amex.removeClass('transparent');
        visa.removeClass('transparent');
        mastercard.removeClass('transparent');

        if ($.payform.validateCardNumber(cardNumber.val()) == false) {
            cardNumberField.addClass('has-error');
        } else {
            cardNumberField.removeClass('has-error');
            cardNumberField.addClass('has-success');
        }

        if ($.payform.parseCardType(cardNumber.val()) == 'visa') {
            mastercard.addClass('transparent');
            amex.addClass('transparent');
        } else if ($.payform.parseCardType(cardNumber.val()) == 'amex') {
            mastercard.addClass('transparent');
            visa.addClass('transparent');
        } else if ($.payform.parseCardType(cardNumber.val()) == 'mastercard') {
            amex.addClass('transparent');
            visa.addClass('transparent');
        }
    });

    confirmButton.click(function(e) {

        e.preventDefault();

        var isCardValid = $.payform.validateCardNumber(cardNumber.val());
        var isCvvValid = $.payform.validateCardCVC(CVV.val());
        var curDate = new Date();
        if(owner.val().length < 5){
            make_notification("Wrong owner name");
        } else if (!isCardValid) {
        	make_notification("Wrong card number");
        } else if (!isCvvValid) {
        	make_notification("Wrong CVV");
        } else if (Number(curDate.getFullYear()) > Number(cyear.val()) + 2000 || (Number(curDate.getFullYear()) == Number(cyear.val()) + 2000 && Number(curDate.getMonth()) + 1 > Number(cmonth.val()))) {
        	make_notification("Wrong Date");
        } else {
        	data = {owner : owner.val(), cardNumber : cardNumber.val(), ccv : CVV.val(),
        			cmonth : cmonth.val(), cyear : cyear.val()};
        	var url = serverUrl  + "/purchase";
        	$.ajax({
	           type: "POST",
	           url: url,
	           data: data, // serializes the form's elements.
	           success: function(data)
	           {
	        	   if (data.code == 200) {
	        		   make_notification("Purchased Successfully");
	        		   update_cart_table($('#cartDiv1'), $('#cartDiv2'), $('#cartTable'));
	        		   $(location).attr('href',serverUrl + "/user");
	        	   } else {
	        		   make_notification(data.message);
	        	   }
	           },
	           error:function(result)
 		        {
 		        	   make_notification(data.message);
 		       	}
 		     });
            cardNumber.val("");
            CVV.val("");
            owner.val("");
        }
    });
});
