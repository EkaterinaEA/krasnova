package com.levelp.example;

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

    @Test
    public void testCreateMessage() {
        messages.getManager().getTransaction().begin();
        Room room = roomDAO.createRoom("roomTitle", 1, 1);
        Message message = messages.createMessage("Hello, World!", "picture", room);
        messages.getManager().getTransaction().commit();
    }

    @Test
    public void testFindByRoom() {
        messages.getManager().getTransaction().begin();
        Room room = roomDAO.createRoom("roomTitle", 1, 1);
        Message message = messages.createMessage("messageText", "attechedFiles", room);
        room.setMessageListFromRoom(Arrays.asList(message));
        messages.getManager().getTransaction().commit();
        messages.getManager().refresh(room);
        List<Message> found = room.getMessageListFromRoom();
        System.out.println(found);
        assertNotNull(found);
        assertNotEquals(0, found.size());
        assertSame(message, found.get(0));
    }
}