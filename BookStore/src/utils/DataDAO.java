package utils;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.User;
import provider.ConnectionProvider;

public class DataDAO {
	 
	   // Find a User by userName and password.
	   public static User findUser(String userName, String password) {
		   try{  
			   Connection con= ConnectionProvider.getCon();     
			   PreparedStatement ps=con.prepareStatement(  
			       "select * from User where username=? and password=?");  
			   
			   ps.setString(1, userName);
			   ps.setString(2, md5(password));     
			   ResultSet rs=ps.executeQuery(); 
			   
			   if(rs.next()) {
				   User u = new User(rs);
				   return u;
			   } else {
				   return null;
			   }
			                 
		   }catch(Exception e){
			   e.printStackTrace();
		   }  
	      
	      return null;
	   }
	   
	   public static String addUser(User user) {
		   try{  
			   Connection con= ConnectionProvider.getCon();     
			   PreparedStatement ps=con.prepareStatement(  
			       "insert into User (username, password, email, firstName, lastName, phone, address, manager)\n" + 
			       "	values(?, ?, ?, ?, ?, ?,\n" + 
			       "		?, ?);");  
			   
			   ps.setString(1, user.getUsername());
			   ps.setString(2, md5(user.getPassword()));     
			   ps.setString(3, user.getEmail());
			   ps.setString(4, user.getFirstName());
			   ps.setString(5, user.getLastName());
			   ps.setString(6, user.getPhone());
			   ps.setString(7, user.getAddress());
			   ps.setBoolean(8, user.isManager());
			   ps.executeUpdate(); 
			   return null;
		   } catch (SQLException ex) {
			   return ex.getMessage();
		   }catch(Exception e){
			   e.printStackTrace();
		   }  
		   return "Unknown Error";
	   }
	   
	   public static String md5( String source ) {
		    try {
		        MessageDigest md = MessageDigest.getInstance( "MD5" );
		        byte[] bytes = md.digest( source.getBytes("UTF-8") );
		        return getString( bytes );
		    } catch( Exception e )  {
		        e.printStackTrace();
		        return null;
		    }
		}
	   
	   private static String getString( byte[] bytes ) 
	   {
	     StringBuffer sb = new StringBuffer();
	     for( int i=0; i<bytes.length; i++ )     
	     {
	        byte b = bytes[ i ];
	        String hex = Integer.toHexString((int) 0x00FF & b);
	        if (hex.length() == 1) 
	        {
	           sb.append("0");
	        }
	        sb.append( hex );
	     }
	     return sb.toString();
	   }
}
