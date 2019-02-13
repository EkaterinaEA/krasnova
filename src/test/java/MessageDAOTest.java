import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MessageDAOTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private RoomDAO roomDAO;

    @Test
    public void testSendMessage() {
        em.getTransaction().begin();
        Message message = messageDAO.createMessage("Hello, World!", "picture");
        em.getTransaction().commit();
    }

    @Test
    public void findByRoom() {
        Message message = messageDAO.findByRoom("roomTitle");
        Message messageFound = messageDAO.findByRoom(message.getRoom().getRoomTitle());
        assertEquals(message, messageFound);
    }

}