package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Category;
import provider.ConnectionProvider;

public class CategoryDataDAO {
	public static String getCategoryName(int cid) {
		try{  
			   Connection con= ConnectionProvider.getCon();     
			   PreparedStatement ps=con.prepareStatement(  
			       "Select * From Category where cid = ?;");  
			   
			   ps.setInt(1, cid);
			   ResultSet set = ps.executeQuery(); 
			   if (set.next()) {
				   Category pub = new Category(set);
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
	public static List<Category> searchCategory(Category category) {
		try{  
			Connection con= ConnectionProvider.getCon(); 
			PreparedStatement ps=con.prepareStatement("Select * From Category where name like ?");  
			   ps.setString(1, "%" + category.getName() + "%"); 
			   
			   ResultSet set = ps.executeQuery(); 
			   List<Category> pubs = new ArrayList<Category>();
			   while(set.next()) {
				   pubs.add(new Category(set));
			   }
			   return pubs;
		   } catch (SQLException ex) {
			   return null;
		   }catch(Exception e){
			   e.printStackTrace();
		   }  
		   return null;
	}
	public static List<Category> getFirstMatchCategory(Category category) {
		try{  
			Connection con= ConnectionProvider.getCon(); 
			PreparedStatement ps=con.prepareStatement("Select * From Category where name like ? limit 10");  
			   ps.setString(1, category.getName() + "%"); 
			   
			   ResultSet set = ps.executeQuery(); 
			   List<Category> pubs = new ArrayList<Category>();
			   while(set.next()) {
				   pubs.add(new Category(set));
			   }
			   return pubs;
		   } catch (SQLException ex) {
			   return null;
		   }catch(Exception e){
			   e.printStackTrace();
		   }  
		   return null;
	}
	public static String addCategory(Category category) {
		try{  
			   Connection con= ConnectionProvider.getCon();     
			   PreparedStatement ps=con.prepareStatement(  
			       "insert into Category (Name)\n" + 
			       "	values(?);");  
			   
			   ps.setString(1, category.getName());
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
