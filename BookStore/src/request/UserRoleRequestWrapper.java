package request;

import java.security.Principal;
import config.SecurityConfig.ROLE;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import bean.User;

public class UserRoleRequestWrapper extends HttpServletRequestWrapper {
	private User user;
    private HttpServletRequest realRequest;
 
    public UserRoleRequestWrapper(User user, HttpServletRequest request) {
        super(request);
        this.user = user;
        this.realRequest = request;
    }
 
    @Override
    public boolean isUserInRole(String role) {
        if (user == null) {
            return this.realRequest.isUserInRole(role);
        }
        if (user.isManager()) {
        	return role.equals(ROLE.MANAGER.toString());
        } else {
        	return role.equals(ROLE.USER.toString());
        }
    }
 
    @Override
    public Principal getUserPrincipal() {
        if (this.user == null) {
            return realRequest.getUserPrincipal();
        }
 
        // Make an anonymous implementation to just return our user
        return new Principal() {
            @Override
            public String getName() {
                return user.getFirstName();
            }
        };
    }
}
