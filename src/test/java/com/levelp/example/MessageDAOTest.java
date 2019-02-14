package com.levelp.example;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;

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

    @Autowired(required=true)
    private MessageDAO messages;

    @Autowired
    private RoomDAO roomDAO;

    @After
    public void stop(){
        if(em!=null){
            em.close();
        }
    }

    @Test
    public void testCreateMessage() {

        em.getTransaction().begin();

        Room room = roomDAO.createRoom("roomTitle", 1, 1);
        Message message = messages.createMessage("Hello, World!", "picture", room);

        em.getTransaction().commit();

        assertNotEquals(0L, message.getText());
        assertNotEquals(0L, message.getAttachedFiles());
        assertNotEquals(0L, message.getRoom());
        assertEquals("picture", message.getAttachedFiles());
    }

    @Test
    public void testFindByRoom() {

        em.getTransaction().begin();

        Room room = roomDAO.createRoom("roomTitle", 1, 1);
        Message message = messages.createMessage("messageText", "attechedFiles", room);
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