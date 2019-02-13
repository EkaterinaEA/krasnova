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
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserDAOTest {

    @Autowired
    private EntityManagerFactory factory;

    @Autowired
    private EntityManager em;

    @Autowired
    private UserDAO dao;

    @Before
    public void setup() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        em = factory.createEntityManager();
        dao = new UserDAO(em);
    }

    @After
    public void stop() {
        if (em != null) {
            em.close();
        }
        if (factory != null) {
            factory.close();
        }
    }

    @Test
    public void testInviteUser() throws Exception {
        User user = dao.inviteUser("login", "password", "email");
        assertNotEquals(0L, user.getUserID());
        assertEquals("login", user.getLogin());
        assertEquals("password", user.getPassword());
        assertEquals("email", user.getEmail());
        assertNotNull(user.getMessageListFromUser());
        assertTrue(user.getMessageListFromUser().isEmpty());
    }

    @Test
    public void testDeleteUser() throws Exception {
        User user = dao.inviteUser("login", "password", "email");
        User userFound = em.find(User.class, user.getUserID());
        dao.deleteUser(userFound);
        assertNull(em.find(User.class, user.getUserID()));
    }

    @Test
    public void testFindById() throws Exception {
        User user = dao.inviteUser("login", "password", "email");
        User userFound = dao.findById(user.getUserID());
        assertEquals(user, userFound);
    }

    @Test
    public void testFindByLogin() throws Exception {
        User user = dao.inviteUser("login", "password", "email");
        User userFound = dao.findByLogin(user.getLogin());
        assertEquals(user, userFound);
    }

    @Test
    public void testFindByEmail() throws Exception {
        User user = dao.inviteUser("login", "password", "email");
        User userFound = dao.findByEmail(user.getEmail());
        assertEquals(user, userFound);
    }

    @Test
    public void testFindAll() throws Exception {
        List<User> users = dao.findAll();
        assertNotNull(users);
    }

}