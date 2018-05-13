package servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import bean.Author;
import bean.Book;
import bean.Cart;
import bean.Category;
import bean.Publisher;
import dao.AuthorDataDAO;
import dao.BookDataDAO;
import dao.CategoryDataDAO;
import dao.PublisherDataDAO;
import utils.AppUtils;

@WebServlet("/book")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
    public BookServlet() {
        super();
    }
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String isbn = request.getParameter("queries[isbn]");
    	String title = request.getParameter("queries[title]");
        String publicationDate = request.getParameter("queries[date]");
        String price1 = request.getParameter("queries[price1]");
        String price2 = request.getParameter("queries[price2]");
        String category = request.getParameter("queries[category]");
        String publisher = request.getParameter("queries[publisher]");
        String author = request.getParameter("queries[authors]");
        String pageS = request.getParameter("page");
        String perPageS = request.getParameter("perPage");
        String offsetS = request.getParameter("offset");
        String hasStock = request.getParameter("queries[hasStock]");
        boolean hasStockB = hasStock != null && hasStock.equals("yes");
        StringBuilder sort_attr = new StringBuilder();
        handle_sort_attr_parsing(request, sort_attr);
        int page = 0,perPage = 0,offset = 0;
        if (pageS != null) {
        	page = Integer.parseInt(pageS);
        }
        if (perPageS != null) {
        	perPage = Integer.parseInt(perPageS);
        }
        if (offsetS != null) {
        	offset = Integer.parseInt(offsetS);
        }
        Float pr1 = null, pr2 = null;
        JSONObject jsonResp = new JSONObject();
        JSONArray empArr = new JSONArray();
        jsonResp.put("queryRecordCount", 0);
        jsonResp.put("totalRecordCount", BookDataDAO.total_record());
        jsonResp.put("records", empArr);
    	int code = 200;
    	String message = "";
    	Long iIsbn;
    	List<List<Author>> authors = null;
    	List<Publisher> pubs = null;
    	List<Category> categories = null;
    	Date date;
    	if (price1 == null || price1.trim().isEmpty()) {
    		pr1 = null;
    	} else {
    		try {
    			pr1 = Float.parseFloat(price1);
    		} catch (NumberFormatException e) {
    			code = 400;
        		message = "Invalid Price , enter a number please.";
        		jsonResp.put("code", code);
                jsonResp.put("message", message);
                response.setContentType("application/json");
                response.getWriter().write(jsonResp.toJSONString());
                response.getWriter().close();
                return;
    		}
    	}
    	if (price2 == null || price2.trim().isEmpty()) {
    		pr2 = null;
    	} else {
    		try {
    			pr2 = Float.parseFloat(price2);
    		} catch (NumberFormatException e) {
    			code = 400;
        		message = "Invalid Price , enter a number please.";
        		jsonResp.put("code", code);
                jsonResp.put("message", message);
                response.setContentType("application/json");
                response.getWriter().write(jsonResp.toJSONString());
                response.getWriter().close();
                return;
    		}
    	}
    	if (isbn == null || isbn.trim().isEmpty()) {
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
    			iIsbn = Long.parseLong(isbn);
    		}
    	}
    	if (title == null || title.trim().isEmpty()) {
    		title = null;
    	}
    	if (publicationDate == null || publicationDate.trim().isEmpty()) {
    		date = null;
    	} else {
    		java.util.Date utilDate;
			try {
				utilDate = new SimpleDateFormat("MM/dd/yyyy").parse(publicationDate);
				date = new Date(utilDate.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
				date = null;
			}	
    	}
    	if (category == null || category.trim().isEmpty()) {
    		categories = null;
    	} else {
    		Category cat = new Category(null, category);
    		categories = CategoryDataDAO.searchCategory(cat);
    		if (categories.isEmpty()) {
    			response.setContentType("application/json");
                response.getWriter().write(jsonResp.toJSONString());
                response.getWriter().close();
                return;
    		}
    	}
    	if (publisher == null || publisher.trim().isEmpty()) {
    		pubs = null;
    	} else {
    		Publisher pub = new Publisher(null, publisher, null, null);
    		pubs = PublisherDataDAO.searchPublisher(pub);
    		if (pubs.isEmpty()) {
    			response.setContentType("application/json");
                response.getWriter().write(jsonResp.toJSONString());
                response.getWriter().close();
                return;
    		}
    	}
    	if (author == null || author.trim().isEmpty()) {
    		authors = null;
    	} else {
    		authors = new ArrayList<List<Author>> ();
    		String[] authorNames = author.split(";");
    		for (int i = 0; i < authorNames.length; i++) {
    			Author aut = new Author(null, authorNames[i].trim());
    			authors.add(AuthorDataDAO.searchAuthor(aut));
    		}
    	}
    	Book searchBook = new Book(iIsbn, title, date, null, pr1, null, null, null);
    	AtomicInteger queryCount = new AtomicInteger(0);
    	List<Book> books = BookDataDAO.selectBookQuery(searchBook, pr1, pr2, authors, categories, pubs,
    			perPage, offset, queryCount, sort_attr.toString().isEmpty()?null:sort_attr.toString(),
    					hasStockB);
    	JSONArray array = new JSONArray();
    	Cart userCart = AppUtils.getCart(request.getSession());
    	for (int i = 0;books != null && i < books.size(); i++) {
    		JSONObject obj = new JSONObject();
    		obj.put("isbn", books.get(i).getIsbn());
    		obj.put("title", books.get(i).getTitle());
    		obj.put("publicationDate", books.get(i).getPublicationDate().toString());
    		obj.put("price", books.get(i).getPrice());
    		obj.put("category", CategoryDataDAO.getCategoryName(books.get(i).getCid()));
    		obj.put("publisher", PublisherDataDAO.getPublisherName(books.get(i).getPid()));
    		obj.put("inStock", books.get(i).getCopiesNumber());
    		if (!userCart.hasBook(books.get(i).getIsbn())) {
    			obj.put("addToCart",
					"<button onclick='add_to_cart("+books.get(i).getIsbn() +")' >Add to Cart</button>");
    		} else {
    			obj.put("addToCart", 
					"<button onclick='modify_in_cart("+books.get(i).getIsbn() +")' style='background-color:#006400'>Modify Cart</button>");
    		}
    		array.add(obj);
    	}
    	code = 200;
    	message = "Success";
    	jsonResp.put("code", code);
        jsonResp.put("message", message);
        jsonResp.put("records", array);
        jsonResp.put("queryRecordCount", queryCount.get());
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
    public static void handle_sort_attr_parsing(HttpServletRequest request, StringBuilder sort_builder) {
    	handle_sort_single_attr("isbn", request.getParameter("sorts[isbn]"), sort_builder);
    	handle_sort_single_attr("title", request.getParameter("sorts[title]"), sort_builder);
    	handle_sort_single_attr("price", request.getParameter("sorts[price]"), sort_builder);
    	handle_sort_single_attr("Category.name", request.getParameter("sorts[category]"), sort_builder);
    	handle_sort_single_attr("publicationDate", request.getParameter("sorts[publicationDate]"), sort_builder);
    	handle_sort_single_attr("Publisher.name", request.getParameter("sorts[publisher]"), sort_builder);
    	handle_sort_single_attr("copiesNumber", request.getParameter("sorts[inStock]"), sort_builder);
    }
    public static void handle_sort_single_attr(String attr, String s, StringBuilder sort_builder) {
    	if (s != null) {
    		if (sort_builder.length() != 0) {
    			sort_builder.append(" , ");
    		}
    		sort_builder.append(attr);
    		sort_builder.append(" ");
    		sort_builder.append(s.equals("1") ? "ASC" : "DESC");
    	}
    }
}

