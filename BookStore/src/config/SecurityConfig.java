package config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;



public class SecurityConfig {

 
	public enum ROLE {USER, MANAGER;
		
		@Override
		public String toString() {
			switch(this) {
		      case USER: return "user";
		      case MANAGER: return "manager";
		      default: throw new IllegalArgumentException();
			}
		}
	};
    private static final Map<ROLE, List<String>> mapConfig = new HashMap<ROLE, List<String>>();
 
    static {
        init();
    }
 
    private static void init() {
 
        // Configure For "EMPLOYEE" Role.
        List<String> urlPatterns1 = new ArrayList<String>();
 
        urlPatterns1.add("/user");
        urlPatterns1.add("/book");
        urlPatterns1.add("/cart");
        urlPatterns1.add("/editUser");
        urlPatterns1.add("/purchase");
        mapConfig.put(ROLE.USER, urlPatterns1);
 
        // Configure For "MANAGER" Role.
        List<String> urlPatterns2 = new ArrayList<String>();
        urlPatterns2.add("/manager");
        urlPatterns2.add("/user");
        urlPatterns2.add("/book");
        urlPatterns2.add("/cart");
        urlPatterns2.add("/editUser");
        urlPatterns2.add("/purchase");
        urlPatterns2.add("/bookmgr");
        /*
        urlPatterns2.add("/authormgr");
        urlPatterns2.add("/categorymgr");
        urlPatterns2.add("/ordermgr");
        urlPatterns2.add("/usermgr");
        urlPatterns2.add("/reportmgr");
        */
        mapConfig.put(ROLE.MANAGER, urlPatterns2);
    }
 
    public static Set<ROLE> getAllAppRoles() {
        return mapConfig.keySet();
    }
 
    public static List<String> getUrlPatternsForRole(ROLE role) {
        return mapConfig.get(role);
    }
}
