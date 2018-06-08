package Populators;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Publisher;
import dao.PublisherDataDAO;

/**
 * PublisherGenerator
 * Created on: May 06, 2018
 * Author: marc
 */
public class PublisherGenerator extends Thread {
    public void run()
    {
        try
        {
            // Displaying the thread that is running
            System.out.println ("Thread " +
                    Thread.currentThread().getId() +
                    " is running, Add Publishers");
            addPublisher(500000);

        }
        catch (Exception e)
        {
            // Throwing an exception
            e.printStackTrace();
            System.out.println ("Exception is caught");
        }
    }

    void addPublisher(int count) throws SQLException, ClassNotFoundException {
        List<Publisher> publisherList = new ArrayList<Publisher>();
        for (int i = 0; i < count; i++) {
            Publisher publisher = new Publisher(null, null, null, null);
            String name = DataGenerator.getRandomName() + " "
                    + DataGenerator.getRandomName() + " " + DataGenerator.getRandomName();
            publisher.setName(name);
            publisher.setAddress("Address " + i);
            publisher.setPhone("012" + i);
            publisherList.add(publisher);
        }

        PublisherDataDAO.addPublishers(publisherList);
    }
}
