package com.levelp.example.web;

import com.levelp.example.*;
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

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestWebConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MessageDAOTest {

    @Autowired
    private EntityManagerFactory factory;

    @Autowired
    private EntityManager em;

    @Autowired
    private MessageDAO dao;

    @Autowired
    private RoomDAO roomDAO;


    @Before
    public void setup() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        em = factory.createEntityManager();
        dao = new MessageDAO();
    }

    @After
    public void stop(){
        if(em!=null){
            em.close();
        }
        if (factory != null) {
            factory.close();
        }
    }

    @Test
    public void testCreateMessage() throws Exception {

        Room room = roomDAO.createRoom(1,"roomTitle", 1, 1);
        Message message = dao.createMessage("Hello, World!", "picture", room);

        assertNotEquals(0L, message.getText());
        assertNotEquals(0L, message.getAttachedFiles());
        assertEquals("picture", message.getAttachedFiles());
        assertNotNull(message.getRoom());
    }

}