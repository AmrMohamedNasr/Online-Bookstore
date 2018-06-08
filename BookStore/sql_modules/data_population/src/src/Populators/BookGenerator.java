package Populators;


import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import bean.Author;
import bean.Book;
import dao.BookDataDAO;
import provider.ConnectionProvider;

/**
 * BookGenerator
 * Created on: May 06, 2018
 * Author: marc
 */
public class BookGenerator extends Thread {
    public void run()
    {
        try
        {
            // Displaying the thread that is running
            System.out.println ("Thread " +
                    Thread.currentThread().getId() +
                    " is running, Add Books.");
            addBooks(1000000);
        }
        catch (Exception e)
        {
            // Throwing an exception
            e.printStackTrace();
            System.out.println ("Exception is caught");
        }
    }

    public void addBooks(int count) throws SQLException, ClassNotFoundException {
       Connection con = ConnectionProvider.getConnection();
        con.setAutoCommit(false);
        for (long i = 700000; i < count + 700000; i++) {
            Book book = new Book(null,null,null,null,null,null,null,null);
            book.setIsbn(i);
            book.setTitle(DataGenerator.getRandomNoun() + " " + DataGenerator.getRandomVerb()
                    + " " + DataGenerator.getRandomAdverb());
            book.setCid(DataGenerator.getRandomCategory());
            book.setPublicationDate(new Date(DataGenerator.getRandomYear()));
            book.setThreshold(DataGenerator.getRandomNum(100));
            book.setCopiesNumber(DataGenerator.getRandomNum(10) * 20);
            book.setPrice(DataGenerator.getRandomFloat() + DataGenerator.getRandomNum(1000));
            book.setPid(DataGenerator.getRandomPublisherId());
            int authorsCount = DataGenerator.getRandomNum(5);
            List<Author> authors = new LinkedList<Author>();
            for (int j = 0; j < authorsCount; j++) {
                Author author = new Author(null,null);
                author.setAid(DataGenerator.getRandomAuthorId());
                authors.add(author);
            }
            BookDataDAO.addBook(book, authors);
        }
        con.commit();
        con.setAutoCommit(true);
    }
}
