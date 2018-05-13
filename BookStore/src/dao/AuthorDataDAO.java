package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Author;
import provider.ConnectionProvider;

public class AuthorDataDAO {
	
	public static List<Author> searchAuthor(Author author) {
		try{  
			   Connection con= ConnectionProvider.getConnection(); 
			   
			   
			   PreparedStatement ps = con.prepareStatement("Select * From Author where name like ? ");
			   ps.setString(1, "%" + author.getName() + "%"); 
			   ResultSet set = ps.executeQuery(); 
			   List<Author> pubs = new ArrayList<Author>();
			   while(set.next()) {
				   pubs.add(new Author(set));
			   }
			   return pubs;
		   } catch (SQLException ex) {
			   return null;
		   }catch(Exception e){
			   e.printStackTrace();
		   }  
		   return null;
	}
	public static Integer getAuthorId (String authorName) {
		try{  
			   Connection con= ConnectionProvider.getConnection();     
			   PreparedStatement ps=con.prepareStatement(  
			       "Select * From Author where name = ?;");     
			   ps.setString(1, authorName);
			   ResultSet set = ps.executeQuery(); 
			   if (set.next()) {
				   Author author = new Author(set);
				   return author.getAid();
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
	public static List<Author> searchFirstMatchAuthor(Author author) {
		try{  
			   Connection con= ConnectionProvider.getConnection(); 
			   PreparedStatement ps = con.prepareStatement("Select * From Author where name like ? limit 10");
			   ps.setString(1, author.getName() + "%"); 
			   ResultSet set = ps.executeQuery(); 
			   List<Author> pubs = new ArrayList<Author>();
			   while(set.next()) {
				   pubs.add(new Author(set));
			   }
			   return pubs;
		   } catch (SQLException ex) {
			   return null;
		   }catch(Exception e){
			   e.printStackTrace();
		   }  
		   return null;
	}
	public static String addAuthor(Author author) {
		try{  
			   Connection con= ConnectionProvider.getConnection();     
			   PreparedStatement ps=con.prepareStatement(  
			       "insert into Author (Name)\n" + 
			       "	values(?);");  
			   
			   ps.setString(1, author.getName());
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
