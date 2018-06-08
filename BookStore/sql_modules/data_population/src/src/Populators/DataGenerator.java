package Populators;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import bean.Author;
import bean.Book;
import bean.Category;
import bean.Publisher;
import bean.User;
import dao.AuthorDataDAO;
import dao.BookDataDAO;
import dao.CategoryDataDAO;
import dao.PublisherDataDAO;
import dao.UserDataDAO;

/**
 * DataGenerator
 * Created on: May 06, 2018
 * Author: marc
 */
public class DataGenerator {
    private static List<String> names;
    private static List<String> nouns;
    private static List<String> verbs;
    private static List<String> adverbs;
    private static List<String> categories = Arrays.asList("Science"
            , "Art", "Religion", "History", "Geography");
    private static List<Publisher> publishers;
    private static List<Author> authors;
    private static Random random;

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException, InterruptedException {
        DataGenerator dataGenerator = new DataGenerator();

        names = dataGenerator.getValues("names");
        nouns = dataGenerator.getValues("nouns");
        adverbs = dataGenerator.getValues("adverbs");
        verbs = dataGenerator.getValues("verbs");
        random = new Random();
        int pcount = 500000, acount = 500000;
        int bcount = 1000000;
        List<Publisher> publisherList = new ArrayList<Publisher>();
        for (int i = 0; i < categories.size(); i++) {
        	CategoryDataDAO.addCategory(new Category(null, categories.get(i)));
        }
        System.out.println("Finish Category");
        User root = new User("root", "root", "root@root.com", 0, true,
        		"firstRoot", "lastRoot", "013123552", "13 root street, java city, pc");
        UserDataDAO.addUser(root);
        System.out.println("Finish Root");
        for (int i = 0; i < pcount; i++) {
            Publisher publisher = new Publisher(null, null, null, null);
            String name = DataGenerator.getRandomName() + " "
                    + DataGenerator.getRandomName() + " " + DataGenerator.getRandomName();
            publisher.setName(name);
            publisher.setAddress("Address " + i);
            publisher.setPhone("012" + i);
            publisherList.add(publisher);
           
        }
        System.out.println("Begin Publisher");
        PublisherDataDAO.addPublishers(publisherList);
        System.out.println("Finish Publisher");
        List<Author> authorss = new ArrayList<Author>();
		for (int i = 0; i < acount; i++) {
            Author author = new Author(null, null);
            String name = DataGenerator.getRandomName() + " "
                    + DataGenerator.getRandomName() + " " + DataGenerator.getRandomName();
            author.setName(name);
            authorss.add(author);
        }
        System.out.println("Begin Author");
        AuthorDataDAO.addAuthors(authorss);
        System.out.println("Finish Author");
        authors = AuthorDataDAO.searchAuthor(new Author(null, null));
        publishers = PublisherDataDAO.searchPublisher(new Publisher(null,
        		null, null, null));
        List<Book> books = new ArrayList<Book>();
        List<List<Author>> bookAuthors = new ArrayList<List<Author>>();
        for (int i = 0; i < bcount; i++) {
            Book book = new Book(null,null,null,null,null,null,null,null);
            book.setIsbn((long)i);
            book.setTitle(DataGenerator.getRandomNoun() + " " + DataGenerator.getRandomVerb()
                    + " " + DataGenerator.getRandomAdverb());
            book.setCid(DataGenerator.getRandomCategory());
            book.setPublicationDate(new java.sql.Date(DataGenerator.getRandomYear()));
            book.setThreshold(DataGenerator.getRandomNum(100));
            book.setCopiesNumber(DataGenerator.getRandomNum(10) * 20);
            book.setPrice(DataGenerator.getRandomFloat() + DataGenerator.getRandomNum(1000));
            book.setPid(DataGenerator.getRandomPublisherId());
            int authorsCount = DataGenerator.getRandomNum(5);
            bookAuthors.add(new LinkedList<Author>());
            for (int j = 0; j < authorsCount; j++) {
                Author author = new Author(null,null);
                author.setAid(DataGenerator.getRandomAuthorId());
                bookAuthors.get(i).add(author);
            }
            books.add(book);
        }
        System.out.println("Begin Book");
        BookDataDAO.addBooks(books, bookAuthors);
        System.out.println("Finish Book");
        System.out.println("Done populating..");
    }
    static Float getRandomFloat() {
    	return random.nextFloat();
    }
    static String getRandomName() {
        int n = random.nextInt(names.size());
        return names.get(n);
    }

    static String getRandomVerb() {
        int n = random.nextInt(verbs.size());
        return verbs.get(n);
    }

    static String getRandomAdverb() {
        int n = random.nextInt(adverbs.size());
        return adverbs.get(n);
    }

    static String getRandomNoun() {
        int n = random.nextInt(nouns.size());
        return nouns.get(n);
    }

    static Integer getRandomCategory() {
        int n = random.nextInt(categories.size());
        return n;
    }

    static long getRandomYear() {
    	int year = random.nextInt(2018);
    	int month = random.nextInt(12) + 1;
    	int day = random.nextInt(27) + 1;
    	Date d;
		try {
			d = new SimpleDateFormat("yyyy-MM-dd").parse(year + "-" + month + "-" +day);
		} catch (ParseException e) {
			e.printStackTrace();
			d = new Date();
		}
        return d.getTime();
    }

    static int getRandomNum(int x) {
        return random.nextInt(x);
    }

    static Integer getRandomPublisherId() {
        return publishers.get(random.nextInt(publishers.size())).getPid();
    }

    static Integer getRandomAuthorId() {
        return authors.get(random.nextInt(authors.size())).getAid();
    }

    private List<String> getValues(String fileName) throws IOException {
        List<String> values = new ArrayList<String>();
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null)
            values.add(st);
        br.close();
        return values;
    }
}
