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
			   Connection con= ConnectionProvider.getCon(); 
			   StringBuilder sql = new StringBuilder("Select * From Author where ");
			   sql.append("name like \'%");
			   sql.append(author.getName());
			   sql.append("%\'");
			   PreparedStatement ps=con.prepareStatement(  
			       sql.toString());  
			   
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
			   Connection con= ConnectionProvider.getCon();     
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
