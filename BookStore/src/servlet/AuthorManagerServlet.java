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

import bean.Author;
import dao.AuthorDataDAO;
import utils.ParseUtils;

@WebServlet("/authormgr")
public class AuthorManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
    public AuthorManagerServlet() {
        super();
    }
    
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String aid = request.getParameter("queries[aid]");
        String name = request.getParameter("queries[name]");
        String perPageS = request.getParameter("perPage");
        String offsetS = request.getParameter("offset");
        JSONObject jsonResp = new JSONObject();
        JSONArray empArr = new JSONArray();
        jsonResp.put("queryRecordCount", 0);
        jsonResp.put("totalRecordCount", AuthorDataDAO.total_record());
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
        Integer iaid;
        iaid = ParseUtils.IntegerParse(aid, false, error);
        if (iaid != null && iaid == -1 && error.get()) {
        	code = 400;
    		message = "Invalid aid , enter a number please.";
    		ParseUtils.doJsonResponse(jsonResp, response, code, message);
            return;
        }
    	if (name == null || name.trim().isEmpty()) {
    		name = null;
    	} else {
    		name = name.trim();
    	}
    	Author searchAut = new Author(iaid, name);
    	AtomicInteger queryCount = new AtomicInteger(0);
    	List<Author> authors = AuthorDataDAO.searchAuthor(searchAut, perPage, offset,
    			sort_attr.toString().isEmpty()?null:sort_attr.toString(),
    					queryCount);
    	JSONArray array = new JSONArray();
    	for (int i = 0;authors != null && i < authors.size(); i++) {
    		JSONObject obj = new JSONObject();
    		obj.put("aid", authors.get(i).getAid());
    		obj.put("name", authors.get(i).getName());
			obj.put("editAuthor",
					"<button onclick='edit_author_info("+authors.get(i).getAid() +")' >Edit Author</button>");
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
    	String aid = request.getParameter("aid");
        String name = request.getParameter("name");
        String edit = request.getParameter("edit");
        JSONObject jsonResp = new JSONObject();
        String message = "";
        int code = 200;
        Integer iaid;
        AtomicBoolean error = new AtomicBoolean();
        iaid = ParseUtils.IntegerParse(aid, false, error);
        if (iaid != null && iaid == -1 && error.get()) {
        	code = 400;
    		message = "Invalid aid , enter a number please.";
    		ParseUtils.doJsonResponse(jsonResp, response, code, message);
            return;
        }
    	if (name == null || name.trim().isEmpty()) {
    		name = null;
    	} else {
    		name = name.trim();
    	}
        if (edit != null && edit.equals("true")) {
        	if (name == null || iaid == null) {
        		code = 400;
        		message = "Missing Info to modify author.";
        	} else {
    			Author aut = new Author(iaid, null);
    			List<Author> existTest = AuthorDataDAO.searchAuthor(aut);
    			if ( existTest == null || existTest.isEmpty()) {
    				code = 400;
            		message = "Invalid AID.";
    			} else {
    				String res = AuthorDataDAO.updateAuthor(iaid, name);
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
        		message = "Missing attribute for new author.";
        	} else {
	        	Author author = new Author(null, name);
	        	String res = AuthorDataDAO.addAuthor(author);
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
    	handle_sort_single_attr("aid", request.getParameter("sorts[aid]"), sort_builder);
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