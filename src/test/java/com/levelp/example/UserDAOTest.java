package com.levelp.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserDAOTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserDAO dao;

    @Test
    public void testInviteUser() throws Exception {
        User user = dao.inviteUser( "kind", "login");
        assertNotNull(user);
        assertNotEquals(0L, user.getUserID());
        assertEquals("login", user.getLogin());
    }

    @Test
    public void testDeleteUser() throws Exception {
        User user = dao.inviteUser("kind", "login");
        User userFound = em.find(User.class, user.getUserID());
        dao.deleteUser(userFound.getUserID());
        assertNull(em.find(User.class, user.getUserID()));
    }

    @Test
    public void testFindById() throws Exception {
        User user = dao.inviteUser("kind", "login");
        User userFound = dao.findById(user.getUserID());
        assertEquals(user, userFound);
    }

    @Test
    public void testFindByLogin() throws Exception {
        User user = dao.inviteUser("kind", "login");
        User userFound = dao.findByLogin(user.getLogin());
        assertEquals(user, userFound);
    }

    @Test
    public void testFindAll() throws Exception {
        List<User> users = dao.findAll();
        assertNotNull(users);
    }

}