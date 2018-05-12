package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import bean.Cart;
import bean.User;
import dao.PurchasesDAO;
import utils.AppUtils;
import utils.PaymentUtils;
import utils.PaymentUtils.BANK_RESPONSE;

@WebServlet("/purchase")
public class PurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
    public PurchaseServlet() {
        super();
    }
    @SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String card = request.getParameter("cardNumber");
    	if (card != null) {
    		card = card.replaceAll("\\s", "");
    	}
    	String cvv = request.getParameter("ccv");
    	String owner = request.getParameter("owner");
    	String cmonth = request.getParameter("cmonth");
    	String cyear = request.getParameter("cyear");
    	Cart cart = AppUtils.getCart(request.getSession());
    	User user = AppUtils.getLoginedUser(request.getSession());
    	cart.update_prices();
    	float fund = cart.getTotalPrice();
    	String message = "";
    	int code =  200;
    	JSONObject jsonResp = new JSONObject();
    	try {
    		if (isNumeric(card) && isNumeric(cvv) && isNumeric(cmonth) && isNumeric(cyear)
    				&& validExp(cyear, cmonth) && luhnCheck(card)) {
	    		BANK_RESPONSE bresp = PaymentUtils.authorize(card, cvv, fund);
	    		if (bresp == BANK_RESPONSE.APPROVED) {
	    			try {
						String s = PurchasesDAO.handleCart(cart, user.getUid());
						if (s == null) {
							PaymentUtils.capture(card, cvv, fund);
							cart.clearCart();
							AppUtils.storeCart(request.getSession(), cart);
							code = 200;
							message = "Successfull transaction";
						} else {
							code = 400;
							message = s;
						}
					} catch (SQLException e) {
						code = 400;
						message = e.getMessage();
					}
	    			
	    		} else if (bresp == BANK_RESPONSE.DECLINED) {
	    			code = 400;
					message = "Wrong Credit Card Information";
	    		} else if (bresp == BANK_RESPONSE.NOT_ENOUGH_FUNDS) {
	    			code = 400;
					message = "Not Enough Funds Found";
	    		} else {
	    			code = 400;
					message = "Unknown Response From Bank";
	    		}
    		} else {
    			code = 400;
				message = "Invalid input given in fields";
    		}
    	} catch (NumberFormatException e) {
    		e.printStackTrace();
    		code = 400;
			message = "Invalid input given in fields";
    	}
    	jsonResp.put("code", code);
        jsonResp.put("message", message);
        response.setStatus(200);
        response.setContentType("application/json");
        response.getWriter().write(jsonResp.toJSONString());
        response.getWriter().close();
    }
    public static boolean luhnCheck(String number) {
		int s1 = 0, s2 = 0;
		String reverse = new StringBuffer(number).reverse().toString();
		for (int i = 0 ;i < reverse.length();i++) {
			int digit = Character.digit(reverse.charAt(i), 10);
			if(i % 2 == 0) { s1 += digit; }
			else {
				s2 += 2 * digit;
				if (digit >= 5) { s2 -= 9; }
			}
		}
		return (s1 + s2) % 10 == 0;
	}
    public static boolean isNumeric(String str)
    {
      return str != null && str.matches("^[0-9]+$");
    }
    public static boolean validExp(String year, String month) {
    	LocalDateTime date = LocalDateTime.now();
    	int cm = date.getMonthValue();
    	int cy = date.getYear();
    	int m = Integer.parseInt(month);
    	int y = Integer.parseInt(year) + 2000;
    	return cy < y || (cy == y && cm <= m);
    }
}
