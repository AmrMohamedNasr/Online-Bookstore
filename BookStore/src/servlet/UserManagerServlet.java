package servlet;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import bean.User;
import dao.UserDataDAO;
import utils.ParseUtils;

@WebServlet("/usermgr")
public class UserManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
    public UserManagerServlet() {
        super();
    }
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String username = string_parse(request.getParameter("queries[username]"));
        String email = string_parse(request.getParameter("queries[email]"));
        String firstName = string_parse(request.getParameter("queries[firstName]"));
        String lastName = string_parse(request.getParameter("queries[lastName]"));
        String phone = string_parse(request.getParameter("queries[phone]"));
        String address = string_parse(request.getParameter("queries[address]"));
        String usertypes = string_parse(request.getParameter("queries[usertype]"));
        String perPageS = request.getParameter("perPage");
        String offsetS = request.getParameter("offset");
        JSONObject jsonResp = new JSONObject();
        JSONArray empArr = new JSONArray();
        jsonResp.put("queryRecordCount", 0);
        jsonResp.put("totalRecordCount", UserDataDAO.total_record());
        jsonResp.put("records", empArr);
        StringBuilder sort_attr = new StringBuilder();
        handle_sort_attr_parsing(request, sort_attr);
        int perPage = 0,offset = 0;
        int code = 200;
    	String message = "";
        AtomicBoolean error = new AtomicBoolean();
        perPage = ParseUtils.IntegerParse(perPageS, true, error);
        if (perPage == -1 && error.get()) {
        	code = 400;
    		message = "Invalid PerPage , enter a number please.";
    		ParseUtils.doJsonResponse(jsonResp, response, code, message);
            return;
        }
        offset = ParseUtils.IntegerParse(offsetS, true, error);
        if (offset == -1 && error.get()) {
        	code = 400;
    		message = "Invalid Offset , enter a number please.";
    		ParseUtils.doJsonResponse(jsonResp, response, code, message);
            return;
        }
        boolean include_manager = false;
        boolean is_manager = false;
        if (usertypes != null && !usertypes.equals("All")) {
        	include_manager = true;
        	if (usertypes.equals("Managers")) {
        		is_manager = true;
        	} else if (usertypes.equals("Only Users")) {
        		is_manager = false;
        	} else {
        		code = 400;
        		message = "Invalid User Type , enter a valid user type please.";
        		ParseUtils.doJsonResponse(jsonResp, response, code, message);
                return;
        	}
        }
        User user = new User(username,null, email, 0, is_manager, firstName, lastName, phone, address);
        AtomicInteger queryCount = new AtomicInteger();
        List<User> users = UserDataDAO.searchUser(user, include_manager, perPage, offset,
        		sort_attr.toString().isEmpty()?null:sort_attr.toString(), queryCount);
        JSONArray array = new JSONArray();
    	for (int i = 0;users != null && i < users.size(); i++) {
    		JSONObject obj = new JSONObject();
    		obj.put("username", users.get(i).getUsername());
    		obj.put("firstName", users.get(i).getFirstName());
    		obj.put("lastName", users.get(i).getLastName());
    		obj.put("email", users.get(i).getEmail());
    		obj.put("address", users.get(i).getAddress());
    		obj.put("phone", users.get(i).getPhone());
    		String disabled = "";
    		if (users.get(i).isManager()) {
    			disabled = "disabled";
    		}
			obj.put("upgradeToManager",
					"<button onclick='upgrade_user("+users.get(i).getUid() +")' "+
			disabled +" >Promote User</button>");
    		array.add(obj);
    	}
    	code = 200;
    	message = "Success";
        jsonResp.put("records", array);
        jsonResp.put("queryRecordCount", queryCount.get());
        response.setStatus(200);
        ParseUtils.doJsonResponse(jsonResp, response, code, message);
    }
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String uids = request.getParameter("uid");
    	Integer uid;
    	AtomicBoolean error = new AtomicBoolean();
		JSONObject jsonResp = new JSONObject();
		String message = "";
		int code = 200;
        uid = ParseUtils.IntegerParse(uids, false, error);
        if (uid != null && uid == -1 && error.get()) {
        	code = 400;
    		message = "Invalid aid , enter a number please.";
    		ParseUtils.doJsonResponse(jsonResp, response, code, message);
            return;
        }
    	User user = new User(null, null, null, uid, true, null, null, null, null);
        List<User> testex = UserDataDAO.searchUser(user, false, 0, 0, null, null);
        if (testex == null || testex.isEmpty()) {
        	code = 400;
    		message= "Couldn't Find Specified User";
        } else {
        	String res = UserDataDAO.updateUser(user);
        	if (res == null) {
        		code = 200;
        		message = "Promoted Successfully";
        	} else {
        		code = 400;
        		message= res;
        	}
        }
        ParseUtils.doJsonResponse(jsonResp, response, code, message);
    }
    public static String string_parse(String x) {
    	return x != null && !x.trim().isEmpty() ? x.trim():null;
    }
    public static void handle_sort_attr_parsing(HttpServletRequest request, StringBuilder sort_builder) {
    	handle_sort_single_attr("username", request.getParameter("sorts[username]"), sort_builder);
    	handle_sort_single_attr("firstName", request.getParameter("sorts[firstName]"), sort_builder);
    	handle_sort_single_attr("lastName", request.getParameter("sorts[lastName]"), sort_builder);
    	handle_sort_single_attr("email", request.getParameter("sorts[email]"), sort_builder);
    	handle_sort_single_attr("phone", request.getParameter("sorts[phone]"), sort_builder);
    	handle_sort_single_attr("address", request.getParameter("sorts[address]"), sort_builder);
    }
    public static void handle_sort_single_attr(String attr, String s, StringBuilder sort_builder) {
    	if (s != null) {
    		if (sort_builder.length() != 0) {
    			sort_builder.append(" , ");
    		}
    		sort_builder.append(attr);
    		sort_builder.append(" ");
    		sort_builder.append(s.equals("1") ? "ASC" : "DESC");
    	}
    }
}
