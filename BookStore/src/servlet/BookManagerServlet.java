package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
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
import bean.Category;
import bean.Publisher;
import dao.AuthorDataDAO;
import dao.BookDataDAO;
import dao.CategoryDataDAO;
import dao.PublisherDataDAO;
import utils.ParseUtils;

@WebServlet("/bookmgr")
public class BookManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
    public BookManagerServlet() {
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
        String lowThreshold = request.getParameter("queries[threshold1]");
        String highThreshold = request.getParameter("queries[threshold2]");
        String lowcopies = request.getParameter("queries[copies1]");
        String highcopies= request.getParameter("queries[copies2]");
        String perPageS = request.getParameter("perPage");
        String offsetS = request.getParameter("offset");
        String conauthor = request.getParameter("conauthor");
        JSONObject jsonResp = new JSONObject();
        JSONArray empArr = new JSONArray();
        jsonResp.put("queryRecordCount", 0);
        jsonResp.put("totalRecordCount", BookDataDAO.total_record());
        jsonResp.put("records", empArr);
        StringBuilder sort_attr = new StringBuilder();
        handle_sort_attr_parsing(request, sort_attr);
        int perPage = 0,offset = 0;
        int code = 200;
    	String message = "";
        AtomicBoolean error = new AtomicBoolean();
        perPage = ParseUtils.IntegerParse(perPageS, true, error);
        if (perPage == -1 && error.get()) {
        	code = 400;
    		message = "Invalid PerPage , enter a number please.";
    		ParseUtils.doJsonResponse(jsonResp, response, code, message);
            return;
        }
        offset = ParseUtils.IntegerParse(offsetS, true, error);
        if (offset == -1 && error.get()) {
        	code = 400;
    		message = "Invalid Offset , enter a number please.";
    		ParseUtils.doJsonResponse(jsonResp, response, code, message);
            return;
        }
        Integer lt, ht, lc, hc;
        lt = ParseUtils.IntegerParse(lowThreshold, false, error);
        if (lt != null && lt == -1 && error.get()) {
        	code = 400;
    		message = "Invalid Lower Limit On Threshold , enter a number please.";
    		ParseUtils.doJsonResponse(jsonResp, response, code, message);
            return;
        }
        ht = ParseUtils.IntegerParse(highThreshold, false, error);
        if (ht != null && ht == -1 && error.get()) {
        	code = 400;
    		message = "Invalid Higher Limit On Threshold , enter a number please.";
    		ParseUtils.doJsonResponse(jsonResp, response, code, message);
            return;
        }
        lc = ParseUtils.IntegerParse(lowcopies, false, error);
        if (lc != null && lc == -1 && error.get()) {
        	code = 400;
    		message = "Invalid Lower Limit On Number Of Copies , enter a number please.";
    		ParseUtils.doJsonResponse(jsonResp, response, code, message);
            return;
        }
        hc = ParseUtils.IntegerParse(highcopies, false, error);
        if (hc != null && hc == -1 && error.get()) {
        	code = 400;
    		message = "Invalid Higher Limit On Number Of Copies , enter a number please.";
    		ParseUtils.doJsonResponse(jsonResp, response, code, message);
            return;
        }
        Float pr1 = null, pr2 = null;
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
        		ParseUtils.doJsonResponse(jsonResp, response, code, message);
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
        		ParseUtils.doJsonResponse(jsonResp, response, code, message);
                return;
    		}
    	}
    	if (isbn == null || isbn.trim().isEmpty()) {
    		iIsbn = null;
    	} else {
    		if (!isInteger(isbn)) {
    			code = 400;
        		message = "Invalid ISBN , enter a number please.";
        		ParseUtils.doJsonResponse(jsonResp, response, code, message);
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
    			ParseUtils.doJsonResponse(jsonResp, response, code, message);
                return;
    		}
    	}
    	if (publisher == null || publisher.trim().isEmpty()) {
    		pubs = null;
    	} else {
    		Publisher pub = new Publisher(null, publisher, null, null);
    		pubs = PublisherDataDAO.searchPublisher(pub);
    		if (pubs.isEmpty()) {
    			ParseUtils.doJsonResponse(jsonResp, response, code, message);
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
    					lt, ht, lc, hc);
    	JSONArray array = new JSONArray();
    	for (int i = 0;books != null && i < books.size(); i++) {
    		JSONObject obj = new JSONObject();
    		obj.put("isbn", books.get(i).getIsbn());
    		obj.put("title", books.get(i).getTitle());
    		obj.put("publicationDate", books.get(i).getPublicationDate().toString());
    		obj.put("price", books.get(i).getPrice());
    		obj.put("category", CategoryDataDAO.getCategoryName(books.get(i).getCid()));
    		obj.put("publisher", PublisherDataDAO.getPublisherName(books.get(i).getPid()));
    		obj.put("numberOfCopies", books.get(i).getCopiesNumber());
    		obj.put("threshold", books.get(i).getThreshold());
			obj.put("editBook",
					"<button onclick='edit_book_info("+books.get(i).getIsbn() +")' >Edit Book</button>");
			if (conauthor  != null && conauthor.equals("true")) {
				StringBuilder authorsS = new StringBuilder();
				List<Author> bookAuthors = AuthorDataDAO.getAuthorsOfBook(books.get(i).getIsbn());
				for (int k = 0; k < bookAuthors.size(); k++) {
					if (k != 0) {
						authorsS.append(";");
					}
					authorsS.append(bookAuthors.get(k).getName());
				}
				obj.put("authors", authorsS.toString());
			}
    		array.add(obj);
    	}
    	code = 200;
    	message = "Success";
        jsonResp.put("records", array);
        jsonResp.put("queryRecordCount", queryCount.get());
        response.setStatus(200);
        ParseUtils.doJsonResponse(jsonResp, response, code, message);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String isbn = request.getParameter("isbn");
    	String oldisbn = request.getParameter("oldisbn");
    	String title = request.getParameter("title");
        String publicationDate = request.getParameter("date");
        String price = request.getParameter("price");
        String copies = request.getParameter("copies");
        String category = request.getParameter("category");
        String publisher = request.getParameter("publisher");
        String authors = request.getParameter("authors");
        String threshold = request.getParameter("threshold");
        String edit = request.getParameter("edit");
        JSONObject jsonResp = new JSONObject();
        String message = "";
        int code = 200;
        Float pr;
        if (price == null || price.trim().isEmpty()) {
    		pr = null;
    	} else {
    		try {
    			pr = Float.parseFloat(price);
    		} catch (NumberFormatException e) {
    			code = 400;
        		message = "Invalid Price , enter a number please.";
        		ParseUtils.doJsonResponse(jsonResp, response, code, message);
                return;
    		}
    	}
        Long iIsbn, ioldIsbn;
        Integer cop, thres;
        if (isbn == null || isbn.trim().isEmpty()) {
    		iIsbn = null;
    	} else {
    		if (!isInteger(isbn)) {
    			code = 400;
        		message = "Invalid new ISBN , enter a number please.";
        		ParseUtils.doJsonResponse(jsonResp, response, code, message);
                return;
    		} else {
    			iIsbn = Long.parseLong(isbn);
    		}
    	}
        if (oldisbn == null || oldisbn.trim().isEmpty()) {
    		ioldIsbn = null;
    	} else {
    		if (!isInteger(oldisbn)) {
    			code = 400;
        		message = "Invalid old ISBN , enter a number please.";
        		ParseUtils.doJsonResponse(jsonResp, response, code, message);
                return;
    		} else {
    			ioldIsbn = Long.parseLong(oldisbn);
    		}
    	}
        if (copies == null || copies.trim().isEmpty()) {
    		cop = null;
    	} else {
    		if (!isInteger(copies)) {
    			code = 400;
        		message = "Invalid copies number , enter a number please.";
        		ParseUtils.doJsonResponse(jsonResp, response, code, message);
                return;
    		} else {
    			cop = Integer.parseInt(copies);
    		}
    	}
        if (threshold == null || threshold.trim().isEmpty()) {
    		thres = null;
    	} else {
    		if (!isInteger(threshold)) {
    			code = 400;
        		message = "Invalid threshold , enter a number please.";
        		ParseUtils.doJsonResponse(jsonResp, response, code, message);
                return;
    		} else {
    			thres = Integer.parseInt(threshold);
    		}
    	}
    	if (title == null || title.trim().isEmpty()) {
    		title = null;
    	}
    	Date date;
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
    	Integer cid, pid;
    	if (category == null || category.trim().isEmpty()) {
    		cid = null;
    	} else {
    		cid = CategoryDataDAO.getCategoryId(category);
    		if (cid == null) {
	    		code = 400;
	    		message = "Invalid Category Name '"+  category + "' , enter a valid category please.";
	    		ParseUtils.doJsonResponse(jsonResp, response, code, message);
	    		return;
    		}
    	}
    	if (publisher == null || publisher.trim().isEmpty()) {
    		pid = null;
    	} else {
    		pid = PublisherDataDAO.getPublisherId(publisher);
    		if (pid == null) {
	    		code = 400;
	    		message = "Invalid Publisher Name '"+  publisher + "' , enter a valid publisher please.";
	    		ParseUtils.doJsonResponse(jsonResp, response, code, message);
	    		return;
    		}
    	}
    	List<Author> oldAuthor, newAuthor;
    	if (authors == null || authors.trim().isEmpty()) {
    		newAuthor = null;
    	} else {
    		newAuthor = new ArrayList<Author> ();
    		String[] authorNames = authors.split(";");
    		for (int i = 0; i < authorNames.length; i++) {
    			Author aut = AuthorDataDAO.getAuthorByName(authorNames[i].trim());
    			if (aut == null) {
    				code = 400;
            		message = "Invalid Author Name '"+  authorNames[i] + "' , enter a valid author please.";
            		ParseUtils.doJsonResponse(jsonResp, response, code, message);
            		return;
    			} else {
    				if (!newAuthor.contains(aut)) {
    					newAuthor.add(aut);
    				}
    			}
    		}
    	}
        if (edit != null && edit.equals("true")) {
        	if (ioldIsbn == null) {
        		code = 400;
        		message = "Missing old Isbn for the modified book.";
        	} else {
        		Book oldBook = BookDataDAO.findBook(ioldIsbn);
        		if (oldBook == null) {
        			code = 400;
        			message = "No Book Found with the old ISBN";
        		} else {
	        		oldAuthor = AuthorDataDAO.getAuthorsOfBook(oldBook.getIsbn());
        			Book newBook = new Book(iIsbn, title, date, thres, pr, cop, cid, pid);
	        		try {
						String res = BookDataDAO.updateBook(oldBook, newBook, oldAuthor, newAuthor);
						if (res == null) {
							code = 200;
							message = "Edited Successfully.";
						} else {
							code = 400;
							message = res;
						}
					} catch (SQLException e) {
						code = 400;
						message = e.getMessage();
					}
        		}
        	}
        } else {
        	if (iIsbn == null || title == null || date == null
        			|| thres == null || pr == null || cop == null || cid == null
        			|| pid == null) {
        		code = 400;
        		message = "Missing attribute for new book.";
        	} else {
	        	Book newBook = new Book(iIsbn, title, date, thres, pr, cop, cid, pid);
	        	try {
		        	String res = BookDataDAO.addBook(newBook, newAuthor);
		        	if (res == null) {
		        		code = 200;
		        		message = "Added successfully.";
		        	} else {
		        		code = 400;
		        		message = res;
		        	}
	        	} catch (Exception e) {
	        		message = e.getMessage();
	        		code = 400;
	        	}
        	}
        }
        ParseUtils.doJsonResponse(jsonResp, response, code, message);
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
    	handle_sort_single_attr("copiesNumber", request.getParameter("sorts[numberOfCopies]"), sort_builder);
    	handle_sort_single_attr("threshold", request.getParameter("sorts[threshold]"), sort_builder);
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


