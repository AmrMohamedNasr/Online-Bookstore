package servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import bean.Author;
import bean.Book;
import bean.Category;
import bean.Publisher;
import dao.AuthorDataDAO;
import dao.BookDataDAO;
import dao.CategoryDataDAO;
import dao.PublisherDataDAO;

@WebServlet("/book")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
    public BookServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String isbn = request.getParameter("isbn");
    	String title = request.getParameter("title");
        String publicationDate = request.getParameter("date");
        String price = request.getParameter("price");
        String category = request.getParameter("category");
        String publisher = request.getParameter("publisher");
        String author = request.getParameter("authors");
        JSONObject jsonResp = new JSONObject();
    	int code = 200;
    	String message = "";
    	Integer iIsbn, iPrice;
    	List<List<Author>> authors = null;
    	List<Publisher> pubs = null;
    	List<Category> categories = null;
    	Date date;
    	if (isbn.trim().isEmpty()) {
    		iIsbn = null;
    	} else {
    		if (!isInteger(isbn)) {
    			code = 400;
        		message = "Invalid ISBN , enter a number please.";
        		jsonResp.put("code", code);
                jsonResp.put("message", message);
                response.setContentType("application/json");
                response.getWriter().write(jsonResp.toJSONString());
                response.getWriter().close();
                return;
    		} else {
    			iIsbn = Integer.parseInt(isbn);
    		}
    	}
    	if (title.trim().isEmpty()) {
    		title = null;
    	}
    	if (publicationDate.trim().isEmpty()) {
    		date = null;
    	} else {
    		java.util.Date utilDate;
			try {
				utilDate = new SimpleDateFormat("MM/dd/yyyy").parse(publicationDate);
				date = new Date(utilDate.getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				date = null;
			}	
    	}
    	if (price.trim().isEmpty()) {
    		iPrice = null;
    	} else {
    		if (!isInteger(price)) {
    			code = 400;
        		message = "Invalid Price , enter a number please.";
        		jsonResp.put("code", code);
                jsonResp.put("message", message);
                response.setContentType("application/json");
                response.getWriter().write(jsonResp.toJSONString());
                response.getWriter().close();
                return;
    		} else {
    			iPrice = Integer.parseInt(price);
    		}
    	}
    	if (category.trim().isEmpty()) {
    		categories = null;
    	} else {
    		Category cat = new Category(null, category);
    		categories = CategoryDataDAO.searchCategory(cat);
    	}
    	if (publisher.trim().isEmpty()) {
    		pubs = null;
    	} else {
    		Publisher pub = new Publisher(null, publisher, null, null);
    		pubs = PublisherDataDAO.searchPublisher(pub);
    	}
    	if (author.trim().isEmpty()) {
    		authors = null;
    	} else {
    		authors = new ArrayList<List<Author>> ();
    		String[] authorNames = author.split("\\r?\\n");
    		for (int i = 0; i < authorNames.length; i++) {
    			Author aut = new Author(null, authorNames[i]);
    			authors.add(AuthorDataDAO.searchAuthor(aut));
    		}
    	}
    	Book searchBook = new Book(iIsbn, title, date, null, iPrice, null, null, null);
    	List<Book> books = BookDataDAO.selectBookQuery(searchBook, authors, categories, pubs);
    	JSONArray array = new JSONArray();
    	for (int i = 0;books != null && i < books.size(); i++) {
    		JSONObject obj = new JSONObject();
    		obj.put("isbn", books.get(i).getIsbn());
    		obj.put("title", books.get(i).getTitle());
    		obj.put("publicationDate", books.get(i).getPublicationDate().toString());
    		obj.put("price", books.get(i).getPrice());
    		obj.put("category", CategoryDataDAO.getCategoryName(books.get(i).getCid()));
    		obj.put("publisher", PublisherDataDAO.getPublisherName(books.get(i).getPid()));
    		array.add(obj);
    	}
    	code = 200;
    	message = "Success";
    	
    	jsonResp.put("code", code);
        jsonResp.put("message", message);
        jsonResp.put("values", array);
        response.setStatus(200);
        response.setContentType("application/json");
        response.getWriter().write(jsonResp.toJSONString());
        response.getWriter().close();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	/*
        String oldPassword = request.getParameter("oldpassword");
    	String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String userId = request.getParameter("upuserid");
        JSONObject jsonResp = new JSONObject();
    	int code = 200;
    	String message = "";
    	User loggedIn = AppUtils.getLoginedUser(request.getSession());
        if (oldPassword != null) {
        	if (password.trim().isEmpty()) {
	        	message = "Please fill all fields.";
	          	code = 400;
	        }
        	if (!password.equals(password2)) {
	        	message = "Repeated Password Doesn't match original one.";
	        	code = 400;
	        }
        	if (!UserDataDAO.md5(oldPassword).equals(loggedIn.getPassword())) {
        		message = "Old password is not entered correctly.";
        		code = 400;
        	}
        	if (code == 200) {
        		User userAccount = new User(loggedIn.getUsername(), password,
    	        		"", loggedIn.getUid(), loggedIn.isManager(),
    	        		"", "", "", "");
        		String err = UserDataDAO.updateUser(userAccount);
        		if (err == null) {
        			message = "Changed Password Successfully...";
        		} else {
        			message = err;
        			code = 400;
        		}
        	}
        } else if (userId != null) {
        	Pattern pattern = Pattern.compile("^[0-9]+$");
        	Matcher matcher = pattern.matcher(userId);
        	if (!matcher.matches()) {
	        	message = "Please enter a valid user id";
	       	 	code = 400;
	        }
        	if (code == 200) {
        		User userAccount = new User("", "",
        				"", Integer.parseInt(userId), true,
        				"", "", "", "");
        		String error = UserDataDAO.updateUser(userAccount);
		        if (error != null) {
		            message = error;
		            code = 400;
		        } else {
		        	message = "Upgraded User Successfully...";
		        }
        	}
        } else {
	        if (firstName.trim().isEmpty() 
	        		|| lastName.trim().isEmpty() || address.trim().isEmpty()) {
	        	message = "Please fill all fields.";
	          	code = 400;
	        }
	        
	        if (!EmailValidator.getInstance().isValid(email)) {
	        	message = "Please enter a valid email.";
	       	 	code = 400;
	        }
	        Pattern pattern = Pattern.compile("^[0-9]+$");
	        Matcher matcher = pattern.matcher(phone);
	        if (!matcher.matches() || phone.length() > 15) {
	        	message = "Please enter a valid phone number(less than or equal to 15 digits).";
	       	 	code = 400;
	        }
	        User userAccount = new User(loggedIn.getUsername(), "",
	        		email, loggedIn.getUid(), loggedIn.isManager(),
	        		firstName, lastName, phone, address);
	        if (code == 200) {
		        String error = UserDataDAO.updateUser(userAccount);
		        if (error != null) {
		            message = error;
		            code = 400;
		        } else {
		        	message = "Updated Profile Sucessfully...";
		        }
	        }
	        
        }
        jsonResp.put("code", code);
        jsonResp.put("message", message);
        response.setContentType("application/json");
        response.getWriter().write(jsonResp.toJSONString());
        response.getWriter().close();
        */
    }
    
    public static boolean isInteger(String s) {
        return isInteger(s,10);
    }

    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }
}

