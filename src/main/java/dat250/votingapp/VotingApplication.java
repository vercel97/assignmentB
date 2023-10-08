package dat250.votingapp;

import dat250.votingapp.model.*;
import dat250.votingapp.service.JsonReader;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class VotingApplication {
    static final String PERSISTENCE_UNIT_NAME = "g4-appassignA";

    public static void main(String[] args) {

        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
             EntityManager em = factory.createEntityManager()) {

            em.getTransaction().begin();
            createTestData(em);
            em.getTransaction().commit();

            inspectTheDatabase();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private static void createTestData(EntityManager em) {

        /*// Create IoTDevice with associated IoTDisplay
        IoTDevice1 device = new IoTDevice1();
        IoTDisplay1 display = new IoTDisplay1();
        device.setDisplay(display);
        display.setDevice(device);
        device.setGreenVotes(5);
        device.setRedVotes(23);


        // Create Poll
        Poll1 poll = new Poll1();
        poll.setPairedIoT(device);
        poll.setDuration(100);
        poll.setPrivate(false);
        device.setPairedPoll(poll);


        // Create User
        AppUser1 a_user = new AppUser1();
        a_user.setUsername("username1");
        a_user.setEmail("user1@example.com");
        a_user.setPassword("password1");
        List<Poll1> userPolls = new ArrayList<Poll1>();
        userPolls.add(poll);
        a_user.setPolls(userPolls);


        // Create Voter, which is a subclass of User
        Voter1 voter = new Voter1();
        voter.setUsername("voter1");
        voter.setEmail("voter1@example.com");
        voter.setPassword("password1");
        voter.setHasVoted(false);


        // Create WebClient
        AppWebClient1 appWebClient = new AppWebClient1();
        appWebClient.setRedVotes(10);
        appWebClient.setGreenVotes(20);*/

        JsonReader jsonReader = new JsonReader();
        List<AppUser> userList = new ArrayList<>();
        List<Poll> pollList = new ArrayList<>();

        try{
            userList = jsonReader.readUserData("src/main/resources/data/userdata.json");
            pollList = jsonReader.readPollData("src/main/resources/data/polldata.json");
        } catch (IOException e){
            e.printStackTrace();
        }

        //iterate through the list to persist users
        for (AppUser aUser : userList ){
            em.persist(aUser);
        }

        //iterate through the list to persist polls
        for (Poll aPoll : pollList ){
            em.persist(aPoll);
        }




        /*em.persist(device);
        em.persist(display);
        em.persist(poll);
        em.persist(a_user);
        em.persist(voter);
        em.persist(appWebClient);*/
    }


    private static void inspectTheDatabase(){

        try {
            // Load the H2 driver class
            Class.forName("org.h2.Driver");

            // Connect to the H2 database
            Connection connection = DriverManager.getConnection("jdbc:h2:file:./DB;DB_CLOSE_DELAY=-1");

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute query to show tables
            ResultSet resultSet = statement.executeQuery("SHOW TABLES");

            // Print the names of the tables
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                System.out.println("Table: " + tableName);


                Statement contentStatement = connection.createStatement();
                ResultSet contentResultSet = contentStatement.executeQuery("SELECT * FROM " + tableName);

                ResultSetMetaData metaData = contentResultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                // Print column names
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(metaData.getColumnName(i) + "\t");
                }
                System.out.println();

                // Print rows
                while (contentResultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(contentResultSet.getString(i) + "\t");
                    }
                    System.out.println();
                }

                System.out.println("-----------------------------");
                contentResultSet.close();
                contentStatement.close();

            }

            // Close all resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}