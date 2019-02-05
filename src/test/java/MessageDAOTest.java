import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.assertEquals;

public class MessageDAOTest {


    private EntityManagerFactory factory;
    private EntityManager em;
    private UserDAO daoUsers;
    private MessageDAO daoMessage;

    @Before
    public void setup(){
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        em = factory.createEntityManager();
        daoUsers = new UserDAO(em);
        daoMessage = new MessageDAO(em);
    }

    @After
    public void stop(){
        if(em != null){
            em.close();
        }
        if (factory != null){
            factory.close();
        }
    }

    @Test
    public void testSendMessage() throws Exception{
        em.getTransaction().begin();
        Message message = daoMessage.sendMessage("Hello, World!", "picture");
        em.getTransaction().commit();
    }

    @Test
    public void findByRoom() throws Exception {
        Message message = daoMessage.findByRoom("roomTitle");
        Message messageFound = daoMessage.findByRoom(message.getRoom().getRoomTitle());
        assertEquals(message, messageFound);
    }

    @Test
    public void findAll() throws Exception {
    }
}