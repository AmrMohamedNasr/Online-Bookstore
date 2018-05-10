package servlet;

import java.io.IOException;
import org.apache.commons.validator.routines.EmailValidator;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import bean.User;
import dao.UserDataDAO;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
    public RegistrationServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp");
 
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        if (userName.trim().isEmpty() || password.trim().isEmpty() || firstName.trim().isEmpty() 
        		|| lastName.trim().isEmpty() || address.trim().isEmpty()) {
        	String errorMessage = "Please fill all fields.";
          	 
            request.setAttribute("errorMessage", errorMessage);
            set_parameters(request, firstName, lastName,
            		password, password2, email, address,
            		phone, userName);
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp");
 
            dispatcher.forward(request, response);
            return;
        }
        if (!password.equals(password2)) {
        	String errorMessage = "Repeated Password Doesn't match original one.";
        	 
            request.setAttribute("errorMessage", errorMessage);
            set_parameters(request, firstName, lastName,
            		password, password2, email, address,
            		phone, userName);
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp");
 
            dispatcher.forward(request, response);
            return;
        }
        if (!EmailValidator.getInstance().isValid(email)) {
        	String errorMessage = "Please enter a valid email.";
       	 
            request.setAttribute("errorMessage", errorMessage);
            set_parameters(request, firstName, lastName,
            		password, password2, email, address,
            		phone, userName);
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp");
 
            dispatcher.forward(request, response);
            return;
        }
        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.matches() || phone.length() > 15) {
        	String errorMessage = "Please enter a valid phone number(less than or equal to 15 digits).";
       	 
            request.setAttribute("errorMessage", errorMessage);
            set_parameters(request, firstName, lastName,
            		password, password2, email, address,
            		phone, userName);
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp");
 
            dispatcher.forward(request, response);
            return;
        }
        User userAccount = new User(userName, password, email, 0, false, firstName, lastName, phone, address);
        String error = UserDataDAO.addUser(userAccount);
        if (error != null) {
            String errorMessage = error;
 
            request.setAttribute("errorMessage", errorMessage);
            set_parameters(request, firstName, lastName,
            		password, password2, email, address,
            		phone, userName);
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp");
 
            dispatcher.forward(request, response);
            return;
        }
 

        response.sendRedirect(request.getContextPath() + "/login");
 
    }
    
    static void set_parameters(HttpServletRequest request, String firstName, String lastName,
    		String password, String password2, String email, String address,
    		String phone, String username) {
    	request.setAttribute("firstName", firstName);
    	request.setAttribute("lastName", lastName);
    	request.setAttribute("username", username);
    	request.setAttribute("phone", phone);
    	request.setAttribute("address", address);
    	request.setAttribute("password", password);
    	request.setAttribute("password2", password2);
    	request.setAttribute("email", email);
    }
}
