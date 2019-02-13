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

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;


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
        Room room = roomDAO.createRoom("roomTitle", 1, 1);
        Message message = messageDAO.createMessage("Hello, World!", "picture", room);
        em.getTransaction().commit();
    }

    @Test
    public void testFindByRoom() {
        em.getTransaction().begin();
        Room room = roomDAO.createRoom("roomTitle", 1, 1);
        Message message = messageDAO.createMessage("messageText", "attechedFiles", room);
        room.setMessageListFromRoom(Arrays.asList(message));
        em.getTransaction().commit();
        em.refresh(room);
        List<Message> found = room.getMessageListFromRoom();
        System.out.println(found);
        assertNotNull(found);
        assertNotEquals(0, found.size());
        assertSame(message, found.get(0));
    }
}