package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Publisher;
import provider.ConnectionProvider;

public class PublisherDataDAO {
	public static String getPublisherName(int pid) {
		try{  
			   Connection con= ConnectionProvider.getCon();     
			   PreparedStatement ps=con.prepareStatement(  
			       "Select * From Publisher where pid = ?;");  
			   
			   ps.setInt(1, pid);
			   ResultSet set = ps.executeQuery(); 
			   if (set.next()) {
				   Publisher pub = new Publisher(set);
				   return pub.getName();
			   } else {
				   return null;
			   }
		   } catch (SQLException ex) {
			   return null;
		   }catch(Exception e){
			   e.printStackTrace();
		   }  
		   return null;
	}
	public static List<Publisher> searchPublisher(Publisher publisher) {
		try{  
			   Connection con= ConnectionProvider.getCon(); 
			   boolean name = publisher.getName() != null,
					  address = publisher.getAddress() != null,
					  phone = publisher.getPhone() != null;
			   StringBuilder sql = new StringBuilder("Select * From Publisher where ");
			   boolean prev_added = false;
			   if (phone) {
				   sql.append("phone like \'%");
				   sql.append(publisher.getPhone());
				   sql.append("%\'");
				   prev_added = true;
			   }
			   if (name) {
				   if (prev_added) {
					   sql.append(" and ");
				   }
				   sql.append(" name like \'%");
				   sql.append(publisher.getName());
				   sql.append("%\'");
				   prev_added = true;
			   }
			   if (address) {
				   if (prev_added) {
					   sql.append(" and ");
				   }
				   sql.append(" address like \'%");
				   sql.append(publisher.getAddress());
				   sql.append("%\'");
				   prev_added = true;
			   }
			   PreparedStatement ps=con.prepareStatement(  
			       sql.toString());  
			   
			   ResultSet set = ps.executeQuery(); 
			   List<Publisher> pubs = new ArrayList<Publisher>();
			   while(set.next()) {
				   pubs.add(new Publisher(set));
			   }
			   return pubs;
		   } catch (SQLException ex) {
			   return null;
		   }catch(Exception e){
			   e.printStackTrace();
		   }  
		   return null;
	}
	public static String addPublisher(Publisher publisher) {
		try{  
			   Connection con= ConnectionProvider.getCon();     
			   PreparedStatement ps=con.prepareStatement(  
			       "insert into Publisher (Name, Phone, Address)\n" + 
			       "	values(?, ?, ?);");  
			   
			   ps.setString(1, publisher.getName());
			   ps.setString(2, publisher.getPhone());
			   ps.setString(3, publisher.getAddress());
			   ps.executeUpdate(); 
			   return null;
		   } catch (SQLException ex) {
			   return ex.getMessage();
		   }catch(Exception e){
			   e.printStackTrace();
		   }  
		   return "Unknown Error";
	}
}
