function upgrade_user(uid) {
	var url = serverUrl + "/usermgr";
	var data ={'uid':uid};
   $.ajax({
      type: "POST",
      url: url,
      data: data, // serializes the form's elements.
      success: function(data)
      {
   	   if (data.code == 200) {
              make_notification("User Upgraded..");
              update_user_table();
   	   } else {
   		   	make_notification("Couldn't Upgrade User..");
   	   }
      },
      error:function(result)
      {
    	  make_notification("Couldn't Upgrade User..");
      }
    });
}