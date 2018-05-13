package servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import bean.Book;
import bean.Cart;
import dao.BookDataDAO;
import utils.AppUtils;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
    public CartServlet() {
        super();
    }
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	JSONObject jsonResp = new JSONObject();
    	int code = 200;
    	String message = "success";
    	Cart cart = AppUtils.getCart(request.getSession());
    	cart.update_prices();
    	AppUtils.storeCart(request.getSession(), cart);
    	JSONArray array = new JSONArray();
    	for (int i = 0;cart.getItems() != null && i < cart.getItems().size(); i++) {
    		JSONObject obj = new JSONObject();
    		Book book = cart.getItems().get(i).getBook();
    		obj.put("isbn", book.getIsbn());
    		obj.put("title", book.getTitle());
    		obj.put("price", book.getPrice());
    		obj.put("copies", cart.getItems().get(i).getQuantity());
    		obj.put("inStock", book.getCopiesNumber());
    		obj.put("totalPrice", book.getPrice() * cart.getItems().get(i).getQuantity());
    		array.add(obj);
    	}
    	jsonResp.put("code", code);
        jsonResp.put("message", message);
        jsonResp.put("values", array);
        jsonResp.put("totalcartprice", cart.getTotalPrice());
        response.setStatus(200);
        response.setContentType("application/json");
        response.getWriter().write(jsonResp.toJSONString());
        response.getWriter().close();
    }
    
    @SuppressWarnings("unchecked")
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	JSONObject jsonResp = new JSONObject();
    	int code = 200;
    	String message = "success";
    	Cart cart = AppUtils.getCart(request.getSession());
    	cart.clearCart();
    	AppUtils.storeCart(request.getSession(), cart);
    	jsonResp.put("code", code);
        jsonResp.put("message", message);
        response.setStatus(200);
        response.setContentType("application/json");
        response.getWriter().write(jsonResp.toJSONString());
        response.getWriter().close();
    }
    
    @SuppressWarnings("unchecked")
	protected void doPut(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	
    	BufferedReader reader = request.getReader();
    	System.out.println(reader.readLine());
    	JSONObject jsonResp = new JSONObject();
    	int code = 200;
    	String message = "";
    	
    	jsonResp.put("code", code);
        jsonResp.put("message", message);
        response.setStatus(200);
        response.setContentType("application/json");
        response.getWriter().write(jsonResp.toJSONString());
        response.getWriter().close();
    }
    
    @SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String isbn = request.getParameter("isbn");
    	String amount = request.getParameter("amount");
    	JSONObject jsonResp = new JSONObject();
    	int code = 200;
    	String message = "";
    	if (isbn == null || !isInteger(isbn)) {
        		code = 400;
        		message = "Missing or bad ISBN in request...";
    	} else if (amount == null) {
    		Cart cart = AppUtils.getCart(request.getSession());
    		Long iIsbn = Long.parseLong(isbn);
    		if(!cart.hasBook(iIsbn)) {
    			Book b = BookDataDAO.findBook(iIsbn);
    			if (b.getCopiesNumber() < 1) {
    				code = 400;
    				message = "Not enough in stock to satisfy request...";
    			} else {
	    			cart.addToCart(b);
	    			AppUtils.storeCart(request.getSession(), cart);
	    			message = "success";
    			}
    		} else {
    			message = "success";
    		}
    	} else {
    		if (!isInteger(amount)) {
    			code = 400;
        		message = "Bad amount in request...";
    		} else {
				Cart cart = AppUtils.getCart(request.getSession());
	    		Long iIsbn = Long.parseLong(isbn);
	    		Integer iquantity = Integer.parseInt(amount);
	    		if(!cart.hasBook(iIsbn)) {
	    			Book b = BookDataDAO.findBook(iIsbn);
	    			if (b.getCopiesNumber() >= iquantity) {
		    			cart.addToCart(b);
		    			AppUtils.storeCart(request.getSession(), cart);
	    			} else {
	    				code = 400;
	    				message = "Not enough in stock to satisfy request...";
	    			}
	    		}
	    		if (code != 400 && BookDataDAO.findBook(iIsbn).getCopiesNumber() >= iquantity) {
		    		cart.changeQuantity(iIsbn, iquantity);
		    		AppUtils.storeCart(request.getSession(), cart);
		    		message = "success";
	    		} else {
	    			code = 400;
    				message = "Not enough in stock to satisfy request...";
	    		}
    		}
    	}
    	jsonResp.put("code", code);
        jsonResp.put("message", message);
        response.setStatus(200);
        response.setContentType("application/json");
        response.getWriter().write(jsonResp.toJSONString());
        response.getWriter().close();
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
