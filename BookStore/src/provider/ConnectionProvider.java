package provider;

import java.sql.Connection;
import java.sql.DriverManager;
import provider.Provider;

public class ConnectionProvider {
	private static Connection con=null;  
	static{  
		try{  
			Class.forName(Provider.DRIVER).newInstance();  
			con = DriverManager.getConnection(Provider.CONNECTION_URL, Provider.USERNAME, Provider.PASSWORD);  
		}catch(Exception e){
			e.printStackTrace();
		}
	}  
	  
	public static Connection getCon(){
		if (con == null) {
			try{  
				Class.forName(Provider.DRIVER);  
				con = DriverManager.getConnection(Provider.CONNECTION_URL, Provider.USERNAME, Provider.PASSWORD);  
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	    return con;  
	}  
}
