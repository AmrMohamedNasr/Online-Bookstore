package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import bean.Order;
import dao.OrderDataDAO;
import utils.ParseUtils;

@WebServlet("/ordermgr")
public class OrderManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
    public OrderManagerServlet() {
        super();
    }
    
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String isbn = request.getParameter("queries[isbn]");
        String lqs = request.getParameter("queries[lq]");
        String hqs = request.getParameter("queries[hq]");
        String lds = request.getParameter("queries[ldate]");
        String hds = request.getParameter("queries[hdate]");
        String perPageS = request.getParameter("perPage");
        String offsetS = request.getParameter("offset");
        JSONObject jsonResp = new JSONObject();
        JSONArray empArr = new JSONArray();
        jsonResp.put("queryRecordCount", 0);
        jsonResp.put("totalRecordCount", OrderDataDAO.total_record());
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
        Long iIsbn;
        if (isbn == null || isbn.trim().isEmpty()) {
    		iIsbn = null;
    	} else {
    		if (!isInteger(isbn)) {
    			code = 400;
        		message = "Invalid ISBN , enter a number please.";
        		ParseUtils.doJsonResponse(jsonResp, response, code, message);
                return;
    		} else {
    			iIsbn = Long.parseLong(isbn);
    		}
    	}
        Integer lq, hq;
        lq = ParseUtils.IntegerParse(lqs, false, error);
        if (lq != null && lq == -1 && error.get()) {
        	code = 400;
    		message = "Invalid Minimum Quantity , enter a number please.";
    		ParseUtils.doJsonResponse(jsonResp, response, code, message);
            return;
        }
        hq = ParseUtils.IntegerParse(hqs, false, error);
        if (hq != null && hq == -1 && error.get()) {
        	code = 400;
    		message = "Invalid Maximum Quantity , enter a number please.";
    		ParseUtils.doJsonResponse(jsonResp, response, code, message);
            return;
        }
        Timestamp ts1, ts2;
        if (lds == null || lds.trim().isEmpty()) {
    		ts1 = null;
    	} else {
    		java.util.Date utilDate;
			try {
				utilDate = new SimpleDateFormat("MM/dd/yyyy").parse(lds);
				ts1 = new Timestamp(utilDate.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
				ts1 = null;
			}	
    	}
        if (hds == null || hds.trim().isEmpty()) {
    		ts2 = null;
    	} else {
    		java.util.Date utilDate;
			try {
				utilDate = new SimpleDateFormat("MM/dd/yyyy").parse(hds);
				ts2 = new Timestamp(utilDate.getTime() + 86399999L);
			} catch (ParseException e) {
				e.printStackTrace();
				ts2 = null;
			}	
    	}
        AtomicInteger queryCount = new AtomicInteger(0);
        List<Order> orders = OrderDataDAO.searchBookOrder(iIsbn, lq, hq, ts1, ts2, perPage,
        		offset, sort_attr.toString().isEmpty()?null:sort_attr.toString(), queryCount);
        JSONArray array = new JSONArray();
    	for (int i = 0;orders != null && i < orders.size(); i++) {
    		JSONObject obj = new JSONObject();
    		obj.put("isbn", orders.get(i).getIsbn());
    		obj.put("quantity", orders.get(i).getQuantity());
    		String timeStampS = "\"" + orders.get(i).getTimeRequested() + "\"";
    		obj.put("timeRequested", timeStampS);
			obj.put("confirmOrder",
					"<button onclick='confirm_order("+orders.get(i).getIsbn() +","+
							timeStampS+")' >Confirm Order</button>");
    		array.add(obj);
    	}
    	code = 200;
    	message = "Success";
        jsonResp.put("records", array);
        jsonResp.put("queryRecordCount", queryCount.get());
        response.setStatus(200);
        ParseUtils.doJsonResponse(jsonResp, response, code, message);
    }
    
    @SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String isbn = request.getParameter("isbn");
    	String timestamp = request.getParameter("timestamp");
    	String quantitys = request.getParameter("quantity");
    	String confirm = request.getParameter("confirm");
    	JSONObject jsonResp = new JSONObject();
        String message = "";
        int code = 200;
        Long iIsbn;
        AtomicBoolean error = new AtomicBoolean();
        if (isbn == null || isbn.trim().isEmpty()) {
    		iIsbn = null;
    	} else {
    		if (!isInteger(isbn)) {
    			code = 400;
        		message = "Invalid ISBN , enter a number please.";
        		ParseUtils.doJsonResponse(jsonResp, response, code, message);
                return;
    		} else {
    			iIsbn = Long.parseLong(isbn);
    		}
    	}
        Integer quantity;
        quantity = ParseUtils.IntegerParse(quantitys, false, error);
        if (quantity != null && quantity == -1 && error.get()) {
        	code = 400;
    		message = "Invalid Quantity , enter a number please.";
    		ParseUtils.doJsonResponse(jsonResp, response, code, message);
            return;
        }
        Timestamp ts;
        if (timestamp == null || timestamp.trim().isEmpty()) {
    		ts = null;
    	} else {
    		java.util.Date utilDate;
			try {
				utilDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(timestamp);
				ts = new Timestamp(utilDate.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
				ts = null;
			}	
    	}
        if (confirm != null && confirm.equals("true")) {
        	if (iIsbn == null || ts == null) {
        		code = 400;
        		message = "Invalid Attributes for confirming order.";
        	} else {
        		String res = OrderDataDAO.deleteBookOrder(iIsbn, ts);
        		if (res == null) {
        			code = 200;
            		message = "Confirmed order..";
        		} else {
        			code = 400;
            		message = res;
        		}
        	}
        } else {
        	if (iIsbn == null || quantity == null) {
        		code = 400;
        		message = "Invalid Attributes for adding order.";
        	} else {
        		Order order = new Order(iIsbn, null, quantity);
        		String res = OrderDataDAO.AddOrder(order);
        		if (res == null) {
        			code = 200;
            		message = "Added Successfully..";
        		} else {
        			code = 400;
            		message = res;
        		}
        	}
        }
        ParseUtils.doJsonResponse(jsonResp, response, code, message);
    }
    public static void handle_sort_attr_parsing(HttpServletRequest request, StringBuilder sort_builder) {
    	handle_sort_single_attr("isbn", request.getParameter("sorts[isbn]"), sort_builder);
    	handle_sort_single_attr("timeRequested", request.getParameter("sorts[timeRequested]"), sort_builder);
    	handle_sort_single_attr("quantity", request.getParameter("sorts[quantity]"), sort_builder);
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
}
