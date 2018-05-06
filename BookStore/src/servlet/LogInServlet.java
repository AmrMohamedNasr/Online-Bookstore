package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Cart;
import bean.User;
import dao.UserDataDAO;
import utils.AppUtils;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
    public LogInServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/logInView.jsp");
 
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        User userAccount = UserDataDAO.findUser(userName, password);
        if (userAccount == null) {
            String errorMessage = "Invalid userName or Password";
 
            request.setAttribute("errorMessage", errorMessage);
 
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/logInView.jsp");
 
            dispatcher.forward(request, response);
            return;
        }
 
        AppUtils.storeLoginedUser(request.getSession(), userAccount);
        AppUtils.storeCart(request.getSession(), new Cart());
        // 
        int redirectId = -1;
        try {
	    	if (request.getParameter("redirectId") != null) {
	    		redirectId = Integer.parseInt(request.getParameter("redirectId"));
	    	}
    	} catch (NumberFormatException e) {
    		
    	}
        String requestUri = AppUtils.getRedirectAfterLoginUrl(request.getSession(), redirectId);
        if (requestUri != null) {
            response.sendRedirect(requestUri);
        } else {
            // Default after successful login
            // redirect to /userInfo page
            response.sendRedirect(request.getContextPath() + "/user");
        }
 
    }
}
