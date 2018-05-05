package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Author;
import bean.Book;
import bean.Category;
import bean.Publisher;
import provider.ConnectionProvider;

public class BookDataDAO {
	
	static final String[] attributes = {"ISBN", "Title", "PublicationDate", "Threshold", "Price", "CopiesNumber",
			"cid", "pid"};
	
	public static List<Book> selectBookQuery(Book book, List<List<Author>> authors, List<Category> cids, List<Publisher> pids) {
		try {
			Connection connect = ConnectionProvider.getCon();
			boolean sauthor = authors != null && !authors.isEmpty();
			boolean scategory = cids != null && !cids.isEmpty();
			boolean spublisher = pids != null && !pids.isEmpty();
			StringBuilder sb2 = new StringBuilder("SELECT Distinct ISBN, Title,PublicationDate,Threshold,"
					+ "Price,CopiesNumber,CID,PID From ");
			if (sauthor) {
				sb2.append("(Book natural join AuthoredBy natural join Author) ");
			} else {
				sb2.append(" Book ");
			}
			StringBuilder sb = new StringBuilder();
			sb.append(" where ");
			boolean prev_cond = false;
			if (book.getIsbn() != null) {
				sb.append("ISBN = ");
				sb.append((int)book.getIsbn());
				prev_cond = true;
			}
			if (book.getTitle() != null) {
				if (prev_cond) {
					sb.append(" and ");
				}
				sb.append("title like \'%");
				sb.append(book.getTitle());
				sb.append("%\'");
				prev_cond = true;
			}
			if (book.getPublicationDate() != null) {
				if (prev_cond) {
					sb.append(" and ");
				}
				sb.append("PublicationDate = \'");
				sb.append(book.getPublicationDate().toString());
				sb.append("\'");
				prev_cond = true;
			}
			if (book.getThreshold() != null) {
				if (prev_cond) {
					sb.append(" and ");
				}
				sb.append("threshold = ");
				sb.append((int)book.getThreshold());
				prev_cond = true;
			}
			if (book.getPrice() != null) {
				if (prev_cond) {
					sb.append(" and ");
				}
				sb.append("price = ");
				sb.append((int)book.getPrice());
				prev_cond = true;
			}
			if (book.getCopiesNumber() != null) {
				if (prev_cond) {
					sb.append(" and ");
				}
				sb.append("CopiesNumber = ");
				sb.append((int)book.getCopiesNumber());
				prev_cond = true;
			}
			if (scategory) {
				if (prev_cond) {
					sb.append(" and ");
				}
				sb.append("(");
				for (int i = 0; i < cids.size(); i++) {
					if (i != 0) {
						sb.append(" or ");
					}
					sb.append("cid = ");
					sb.append((int)cids.get(i).getCid());
				}
				sb.append(")");
				prev_cond = true;
			}
			if (spublisher) {
				if (prev_cond) {
					sb.append(" and ");
				}
				sb.append("(");
				for (int i = 0; i < pids.size(); i++) {
					if (i != 0) {
						sb.append(" or ");
					}
					sb.append("pid = ");
					sb.append((int)pids.get(i).getPid());
				}
				sb.append(")");
				prev_cond = true;
			}
			if (sauthor) {
				if (prev_cond) {
					sb.append(" and ");
				}
				sb.append(" isbn in ");
				sb.append("(");
				sb.append("Select Distinct ISBN From (");
				boolean prev_in_cond = false;
				for (int j = 0; j < authors.size(); j++) {
					if (!authors.get(j).isEmpty()) {
						if (prev_in_cond) {
							sb.append(" natural join ");
						}
						sb.append("( select Distinct ISBN from (AuthoredBy natural join Author) where ");
						for (int i = 0; i < authors.get(j).size(); i++) {
							if (i != 0) {
								sb.append(" or ");
							}
							sb.append("name like \'%");
							sb.append(authors.get(j).get(i).getName());
							sb.append("%\'");
						}
						sb.append(") as a");
						sb.append(j);
						prev_in_cond = true;
					}
				}
				sb.append(")");
				sb.append(")");
				prev_cond = true;
			}
			if (!sb.toString().equals(" where ")) {
				sb2.append(sb);
			}
			ResultSet set = connect.createStatement().executeQuery(sb2.toString());
			List<Book> books = new ArrayList<Book> ();
			while (set.next()) {
				books.add(new Book(set));
			}
			return books;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
		
	public static String updateBook (Book oldBook, Book book, List<Author> oldAuthors, List<Author> newAuthors) {
		try {	
			Connection connect = ConnectionProvider.getCon();
			StringBuilder sb = new StringBuilder("UPDATE Book SET ");
			boolean prev_cond = false;
			if (book.getIsbn() != null) {
				sb.append("ISBN = ");
				sb.append((int)book.getIsbn());
				prev_cond = true;
			}
			if (book.getTitle() != null) {
				if (prev_cond) {
					sb.append(" , ");
				}
				sb.append("title = \'");
				sb.append(book.getTitle());
				sb.append("\'");
				prev_cond = true;
			}
			if (book.getPublicationDate() != null) {
				if (prev_cond) {
					sb.append(" , ");
				}
				sb.append("PublicationDate = \'");
				sb.append(book.getPublicationDate().toString());
				sb.append("\'");
				prev_cond = true;
			}
			if (book.getThreshold() != null) {
				if (prev_cond) {
					sb.append(" , ");
				}
				sb.append("threshold = ");
				sb.append((int)book.getThreshold());
				prev_cond = true;
			}
			if (book.getPrice() != null) {
				if (prev_cond) {
					sb.append(" , ");
				}
				sb.append("price = ");
				sb.append((int)book.getPrice());
				prev_cond = true;
			}
			if (book.getCopiesNumber() != null) {
				if (prev_cond) {
					sb.append(" , ");
				}
				sb.append("CopiesNumber = ");
				sb.append((int)book.getCopiesNumber());
				prev_cond = true;
			}
			if (book.getCid() != null) {
				if (prev_cond) {
					sb.append(" , ");
				}
				sb.append("Cid = ");
				sb.append((int)book.getCid());
				prev_cond = true;
			}
			if (book.getPid() != null) {
				if (prev_cond) {
					sb.append(" , ");
				}
				sb.append("pid = ");
				sb.append((int)book.getPid());
				prev_cond = true;
			}
			sb.append("WHERE ISBN = ");
			sb.append((int)oldBook.getIsbn());
			connect.createStatement().executeUpdate(sb.toString());
			return null;
		} catch (SQLException ex) {
			   return ex.getMessage();
	   }catch(Exception e){
		   e.printStackTrace();
	   }  
	   return "Unknown Error";
	}

	public static String addBook(Book book) {
		try{  
			   Connection con= ConnectionProvider.getCon();     
			   PreparedStatement ps=con.prepareStatement(  
			       "insert into User (ISBN, Title, PublicationDate, Threshold, Price, CopiesNumber, Cid, Pid)\n" + 
			       "	values(?, ?, ?, ?, ?, ?,\n" + 
			       "		?, ?);");  
			   
			   ps.setInt(1, book.getIsbn());
			   ps.setString(2, book.getTitle());
			   ps.setDate(3, book.getPublicationDate());
			   ps.setInt(4, book.getThreshold());
			   ps.setInt(5, book.getPrice());
			   ps.setInt(6, book.getCopiesNumber());
			   ps.setInt(7, book.getCid());
			   ps.setInt(8, book.getPid());
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
