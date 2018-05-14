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

import bean.Category;
import dao.CategoryDataDAO;
import utils.ParseUtils;

@WebServlet("/categorymgr")
public class CategoryManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
    public CategoryManagerServlet() {
        super();
    }
    
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String cid = request.getParameter("queries[cid]");
        String name = request.getParameter("queries[name]");
        String perPageS = request.getParameter("perPage");
        String offsetS = request.getParameter("offset");
        JSONObject jsonResp = new JSONObject();
        JSONArray empArr = new JSONArray();
        jsonResp.put("queryRecordCount", 0);
        jsonResp.put("totalRecordCount", CategoryDataDAO.total_record());
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
        Integer icid;
        icid = ParseUtils.IntegerParse(cid, false, error);
        if (icid != null && icid == -1 && error.get()) {
        	code = 400;
    		message = "Invalid cid , enter a number please.";
    		ParseUtils.doJsonResponse(jsonResp, response, code, message);
            return;
        }
    	if (name == null || name.trim().isEmpty()) {
    		name = null;
    	} else {
    		name = name.trim();
    	}
    	Category searchCat = new Category(icid, name);
    	AtomicInteger queryCount = new AtomicInteger(0);
    	List<Category> authors = CategoryDataDAO.searchCategory(searchCat, perPage, offset,
    			sort_attr.toString().isEmpty()?null:sort_attr.toString(),
    					queryCount);
    	JSONArray array = new JSONArray();
    	for (int i = 0;authors != null && i < authors.size(); i++) {
    		JSONObject obj = new JSONObject();
    		obj.put("cid", authors.get(i).getCid());
    		obj.put("name", authors.get(i).getName());
			obj.put("editCategory",
					"<button onclick='edit_category_info("+authors.get(i).getCid() +")' >Edit Category</button>");
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
    	String cid = request.getParameter("cid");
        String name = request.getParameter("name");
        String edit = request.getParameter("edit");
        JSONObject jsonResp = new JSONObject();
        String message = "";
        int code = 200;
        Integer icid;
        AtomicBoolean error = new AtomicBoolean();
        icid = ParseUtils.IntegerParse(cid, false, error);
        if (icid != null && icid == -1 && error.get()) {
        	code = 400;
    		message = "Invalid cid , enter a number please.";
    		ParseUtils.doJsonResponse(jsonResp, response, code, message);
            return;
        }
    	if (name == null || name.trim().isEmpty()) {
    		name = null;
    	} else {
    		name = name.trim();
    	}
        if (edit != null && edit.equals("true")) {
        	if (name == null || icid == null) {
        		code = 400;
        		message = "Missing Info to modify category.";
        	} else {
    			Category aut = new Category(icid, null);
    			List<Category> existTest = CategoryDataDAO.searchCategory(aut);
    			if ( existTest == null || existTest.isEmpty()) {
    				code = 400;
            		message = "Invalid CID.";
    			} else {
    				String res = CategoryDataDAO.updateCategory(icid, name);
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
        	if (name == null) {
        		code = 400;
        		message = "Missing attribute for new category.";
        	} else {
	        	Category cat = new Category(null, name);
	        	String res = CategoryDataDAO.addCategory(cat);
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
    
    public static void handle_sort_attr_parsing(HttpServletRequest request, StringBuilder sort_builder) {
    	handle_sort_single_attr("cid", request.getParameter("sorts[cid]"), sort_builder);
    	handle_sort_single_attr("name", request.getParameter("sorts[name]"), sort_builder);
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