package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import request.UserRoleRequestWrapper;
import utils.AppUtils;
import utils.SecurityUtils;

@WebFilter("/*")
public class SecurityFilter implements Filter {
	  public SecurityFilter() {
	  }
	 
	    @Override
	    public void destroy() {
	    }
	 
	    @Override
	    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
	            throws IOException, ServletException {
	        HttpServletRequest request = (HttpServletRequest) req;
	        HttpServletResponse response = (HttpServletResponse) resp;
	 
	        String servletPath = request.getServletPath();
	        // User information stored in the Session.
	        // (After successful login).
	        User loginedUser = AppUtils.getLoginedUser(request.getSession());
	        HttpServletRequest wrapRequest = request;
	        if ((servletPath.equals("/login") || servletPath.equals("/register"))&& loginedUser == null) {
	            chain.doFilter(request, response);
	            return;
	        } else if (servletPath.equals("/login") || servletPath.equals("/register")) {
	        	wrapRequest = new UserRoleRequestWrapper(loginedUser, request);
	        	response.sendRedirect(wrapRequest.getContextPath() + "/user");
                return;
	        }
	        
	 
	        if (loginedUser != null) {
	            // Wrap old request by a new Request with userName and Roles information.
	            wrapRequest = new UserRoleRequestWrapper(loginedUser, request);
	        }
	 
	        // Pages must be signed in.
	        if (SecurityUtils.isSecurityPage(request)) {
	 
	            // If the user is not logged in,
	        	// Redirect to the login page.
	            if (loginedUser == null) {
	 
	                String requestUri = request.getRequestURI();
	 
	                // Store the current page to redirect to after successful login.
	                int redirectId = AppUtils.storeRedirectAfterLoginUrl(request.getSession(), requestUri);
	 
	                response.sendRedirect(wrapRequest.getContextPath() + "/login?redirectId=" + redirectId);
	                return;
	            }
	 
	            // Check if the user has a valid role?
	            boolean hasPermission = SecurityUtils.hasPermission(wrapRequest);
	            if (!hasPermission) {
	 
	                RequestDispatcher dispatcher //
	                = request.getServletContext().getRequestDispatcher("/WEB-INF/views/accessDenied.jsp");
	 
	                dispatcher.forward(request, response);
	                return;
	            }
	        }
	        chain.doFilter(wrapRequest, response);
	    }
	 
	    @Override
	    public void init(FilterConfig fConfig) throws ServletException {
	 
	    }
}
