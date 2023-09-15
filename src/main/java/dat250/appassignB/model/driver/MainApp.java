package dat250.appassignB.model.driver;

import dat250.appassignB.model.IoTdevice;
import dat250.appassignB.model.Poll;
import dat250.appassignB.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainApp {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("voting-application");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Create user
            User user = new User();
            user.setUsername("john_doe");
            user.setPassword("secret1234");

            // Create poll
            Poll poll = new Poll();
            poll.setIsPrivate(false);
            poll.setTimeLimit(30);
            poll.setUser(user);

            // Create IoTdevice
            IoTdevice iotDevice = new IoTdevice();
            iotDevice.setRedVotes(10);
            iotDevice.setGreenVotes(5);
            iotDevice.setPoll(poll);
            poll.setIotDevice(iotDevice);

            // Persist entities
            em.persist(user);
            em.persist(poll);
            em.persist(iotDevice);

            em.getTransaction().commit();

            // Fetch and display the persisted data (optional)
            User fetchedUser = em.find(User.class, user.getId());
            System.out.println("Fetched User: " + fetchedUser.getUsername());

            Poll fetchedPoll = em.find(Poll.class, poll.getId());
            System.out.println("Fetched Poll ID: " + fetchedPoll.getId());

            IoTdevice fetchedIoTdevice = em.find(IoTdevice.class, iotDevice.getId());
            System.out.println("Fetched IoTdevice ID: " + fetchedIoTdevice.getId());

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
