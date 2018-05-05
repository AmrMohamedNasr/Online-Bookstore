package utils;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import config.SecurityConfig;
import config.SecurityConfig.ROLE;

public class SecurityUtils {
	
	 // Check whether this 'request' is required to login or not.
    public static boolean isSecurityPage(HttpServletRequest request) {
        String urlPattern = UrlPatternUtils.getUrlPattern(request);
        
        Set<ROLE> roles = SecurityConfig.getAllAppRoles();
 
        for (ROLE role : roles) {
            List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }
 
    // Check if this 'request' has a 'valid role'?
    public static boolean hasPermission(HttpServletRequest request) {
        String urlPattern = UrlPatternUtils.getUrlPattern(request);
 
        Set<ROLE> allRoles = SecurityConfig.getAllAppRoles();
 
        for (ROLE role : allRoles) {
            if (!request.isUserInRole(role.toString())) {
                continue;
            }
            List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }
}
