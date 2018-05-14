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

import bean.Publisher;
import dao.PublisherDataDAO;
import utils.ParseUtils;

@WebServlet("/publishermgr")
public class PublisherManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
    public PublisherManagerServlet() {
        super();
    }
    
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String pid = request.getParameter("queries[pid]");
        String name = request.getParameter("queries[name]");
        String address = request.getParameter("queries[address]");
        String phone = request.getParameter("queries[phone]");
        String perPageS = request.getParameter("perPage");
        String offsetS = request.getParameter("offset");
        JSONObject jsonResp = new JSONObject();
        JSONArray empArr = new JSONArray();
        jsonResp.put("queryRecordCount", 0);
        jsonResp.put("totalRecordCount",PublisherDataDAO.total_record());
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
        Integer ipid;
        ipid = ParseUtils.IntegerParse(pid, false, error);
        if (ipid != null && ipid == -1 && error.get()) {
        	code = 400;
    		message = "Invalid pid , enter a number please.";
    		ParseUtils.doJsonResponse(jsonResp, response, code, message);
            return;
        }
    	if (name == null || name.trim().isEmpty()) {
    		name = null;
    	} else {
    		name = name.trim();
    	}
    	if (address == null || address.trim().isEmpty()) {
    		address = null;
    	} else {
    		address = address.trim();
    	}
    	if (phone == null || phone.trim().isEmpty()) {
    		phone = null;
    	} else {
    		phone = phone.trim();
    	}
    	Publisher searchPub = new Publisher(ipid, name, address, phone);
    	AtomicInteger queryCount = new AtomicInteger(0);
    	List<Publisher> publishers = PublisherDataDAO.searchPublisher(searchPub, perPage, offset,
    			sort_attr.toString().isEmpty()?null:sort_attr.toString(),
    					queryCount);
    	JSONArray array = new JSONArray();
    	for (int i = 0;publishers != null && i < publishers.size(); i++) {
    		JSONObject obj = new JSONObject();
    		obj.put("pid", publishers.get(i).getPid());
    		obj.put("name", publishers.get(i).getName());
    		obj.put("address", publishers.get(i).getAddress());
    		obj.put("phone", publishers.get(i).getPhone());
			obj.put("editPublisher",
					"<button onclick='edit_publisher_info("+publishers.get(i).getPid() +")' >Edit Publisher</button>");
    		array.add(obj);
    	}
    	code = 200;
    	message = "Success";
        jsonResp.put("records", array);
        jsonResp.put("queryRecordCount", queryCount.get());
        response.setStatus(200);
        ParseUtils.doJsonResponse(jsonResp, response, code, message);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String pid = request.getParameter("pid");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String edit = request.getParameter("edit");
        JSONObject jsonResp = new JSONObject();
        String message = "";
        int code = 200;
        Integer ipid;
        AtomicBoolean error = new AtomicBoolean();
        ipid = ParseUtils.IntegerParse(pid, false, error);
        if (ipid != null && ipid == -1 && error.get()) {
        	code = 400;
    		message = "Invalid pid , enter a number please.";
    		ParseUtils.doJsonResponse(jsonResp, response, code, message);
            return;
        }
    	if (name == null || name.trim().isEmpty()) {
    		name = null;
    	} else {
    		name = name.trim();
    	}
    	if (address == null || address.trim().isEmpty()) {
    		address = null;
    	} else {
    		address = address.trim();
    	}
    	if (phone == null || phone.trim().isEmpty()) {
    		phone = null;
    	} else {
    		phone = phone.trim();
    		if (phone.length() > 15 || !isInteger(phone)) {
    			code = 400;
        		message = "Invalid phone number , enter a valid phone number please.";
        		ParseUtils.doJsonResponse(jsonResp, response, code, message);
                return;
    		}
    	}
        if (edit != null && edit.equals("true")) {
        	if (ipid == null) {
        		code = 400;
        		message = "Missing Info to modify publisher.";
        	} else {
    			Publisher aut = new Publisher(ipid, null, null, null);
    			List<Publisher> testingEx = PublisherDataDAO.searchPublisher(aut);
    			if (testingEx == null || testingEx.isEmpty()) {
    				code = 400;
            		message = "Invalid PID.";
    			} else {
    				Publisher pub = new Publisher(null, name, address, phone);
    				String res = PublisherDataDAO.updatePublisher(ipid, pub);
    				if (res == null) {
    					code = 200;
    					message = "Edited Successfully";
    				} else {
    					code = 400;
    					message = res;
    				}
        		}
        	}
        } else {
        	if (name == null || phone == null || address == null) {
        		code = 400;
        		message = "Missing attribute for new author.";
        	} else {
	        	Publisher publisher = new Publisher(null, name, address, phone);
	        	String res = PublisherDataDAO.addPublisher(publisher);
	        	if (res == null) {
	        		code = 200;
	        		message = "Added Successfully";
	        	} else {
	        		code = 400;
	        		message= res;
	        	}
        	}
        }
        ParseUtils.doJsonResponse(jsonResp, response, code, message);
    }
    
    public static boolean isInteger(String s) {
        return isInteger(s,10);
    }

    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }
    public static void handle_sort_attr_parsing(HttpServletRequest request, StringBuilder sort_builder) {
    	handle_sort_single_attr("pid", request.getParameter("sorts[pid]"), sort_builder);
    	handle_sort_single_attr("name", request.getParameter("sorts[name]"), sort_builder);
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