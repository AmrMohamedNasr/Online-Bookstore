package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import bean.Author;
import bean.Book;
import bean.Category;
import bean.Publisher;
import dao.AuthorDataDAO;
import dao.BookDataDAO;
import dao.CategoryDataDAO;
import dao.PublisherDataDAO;

@WebServlet("/auto")
public class AutoCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
    public AutoCompleteServlet() {
        super();
    }
    
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String term = request.getParameter("term");
    	String type = request.getParameter("type");
    	JSONArray arr = new JSONArray();
    	if (type.equals("category")) {
    		List<Category> cats = CategoryDataDAO.getFirstMatchCategory(new Category(null, term));
	    	for (int i = 0; i < cats.size(); i++) {
	    		arr.add(cats.get(i).getName());
	    	}
    	} else if (type.equals("title")) {
    		List<Book> cats = BookDataDAO.searchFirstMatchBook(term);
    		for (int i = 0; i < cats.size(); i++) {
	    		arr.add(cats.get(i).getTitle());
	    	}
    	} else if (type.equals("publisher")) {
    		List<Publisher> cats = PublisherDataDAO.searchFirstMatchPublisher(new Publisher(null, term, null, null));
	    	for (int i = 0; i < cats.size(); i++) {
	    		arr.add(cats.get(i).getName());
	    	}
    	} else if (type.equals("author")) {
    		List<Author> cats = AuthorDataDAO.searchFirstMatchAuthor(new Author(null, term));
	    	for (int i = 0; i < cats.size(); i++) {
	    		arr.add(cats.get(i).getName());
	    	}
    	}
    	response.setStatus(200);
        response.setContentType("application/json");
        response.getWriter().write(arr.toJSONString());
        response.getWriter().close();
    }
}
