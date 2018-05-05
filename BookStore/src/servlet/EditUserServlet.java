package servlet;

import java.io.IOException;
import org.apache.commons.validator.routines.EmailValidator;

import org.json.simple.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import bean.User;
import utils.AppUtils;
import utils.UserDataDAO;

@WebServlet("/editUser")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
    public EditUserServlet() {
        super();
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        String oldPassword = request.getParameter("oldpassword");
    	String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String userId = request.getParameter("upuserid");
        JSONObject jsonResp = new JSONObject();
    	int code = 200;
    	String message = "";
    	User loggedIn = AppUtils.getLoginedUser(request.getSession());
        if (oldPassword != null) {
        	if (password.trim().isEmpty()) {
	        	message = "Please fill all fields.";
	          	code = 400;
	        }
        	if (!password.equals(password2)) {
	        	message = "Repeated Password Doesn't match original one.";
	        	code = 400;
	        }
        	if (!UserDataDAO.md5(oldPassword).equals(loggedIn.getPassword())) {
        		message = "Old password is not entered correctly.";
        		code = 400;
        	}
        	if (code == 200) {
        		User userAccount = new User(loggedIn.getUsername(), password,
    	        		"", loggedIn.getUid(), loggedIn.isManager(),
    	        		"", "", "", "");
        		String err = UserDataDAO.updateUser(userAccount);
        		if (err == null) {
        			message = "Changed Password Successfully...";
        		} else {
        			message = err;
        			code = 400;
        		}
        	}
        } else if (userId != null) {
        	Pattern pattern = Pattern.compile("^[0-9]+$");
        	Matcher matcher = pattern.matcher(userId);
        	if (!matcher.matches()) {
	        	message = "Please enter a valid user id";
	       	 	code = 400;
	        }
        	if (code == 200) {
        		User userAccount = new User("", "",
        				"", Integer.parseInt(userId), true,
        				"", "", "", "");
        		String error = UserDataDAO.updateUser(userAccount);
		        if (error != null) {
		            message = error;
		            code = 400;
		        } else {
		        	message = "Upgraded User Successfully...";
		        }
        	}
        } else {
	        if (firstName.trim().isEmpty() 
	        		|| lastName.trim().isEmpty() || address.trim().isEmpty()) {
	        	message = "Please fill all fields.";
	          	code = 400;
	        }
	        
	        if (!EmailValidator.getInstance().isValid(email)) {
	        	message = "Please enter a valid email.";
	       	 	code = 400;
	        }
	        Pattern pattern = Pattern.compile("^[0-9]+$");
	        Matcher matcher = pattern.matcher(phone);
	        if (!matcher.matches() || phone.length() > 15) {
	        	message = "Please enter a valid phone number(less than or equal to 15 digits).";
	       	 	code = 400;
	        }
	        User userAccount = new User(loggedIn.getUsername(), "",
	        		email, loggedIn.getUid(), loggedIn.isManager(),
	        		firstName, lastName, phone, address);
	        if (code == 200) {
		        String error = UserDataDAO.updateUser(userAccount);
		        if (error != null) {
		            message = error;
		            code = 400;
		        } else {
		        	message = "Updated Profile Sucessfully...";
		        }
	        }
	        
        }
        jsonResp.put("code", code);
        jsonResp.put("message", message);
        response.setContentType("application/json");
        response.getWriter().write(jsonResp.toJSONString());
        response.getWriter().close();
    }
}
