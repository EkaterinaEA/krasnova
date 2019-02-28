package com.levelp.example.web;

import com.levelp.example.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.method.P;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MessageDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private RoomDAO roomDAO;


    @Test
    public void testCreateMessage() throws Exception {

        Room room = roomDAO.createRoom(1,"roomTitle", 1, 1);
        User user = new User("user1", "password", "email");
        Message message = new Message("Hello, World!", "picture", room);

        messageDAO.save(message);

        Page<Message> foundPage = messageDAO.findByRoom_IdAAndLogin(room.getRoomId(),
                user.getLogin(), PageRequest.of(0, 10)); // нулевая стр, 10 шт

        // для исправления error заглянем, что есть в списке Subject:
        List<Message> foundMessages = foundPage.get().collect(Collectors.toList());
        System.out.println(foundMessages);  // error сохраняется => не нашёл

        // ожидаем увадеть тот Subject, который сохранили:
        // anyMatch - найти такой, который = тому Subject, который мы сохранили
        // anyMatch - true, если есть хоть 1 равный эл-т
        assertTrue(foundPage.get().anyMatch(e -> e.getMessageId() ==
                message.getMessageId()));

        // поиск через репозиторий:
        Message findBy = new Message();

        findBy.setRoom(room);

    //    messageDAO.findAll(Example.of(findBy, ExampleMatcher.matching()...)); - поиск по образцу

    //    assertNotEquals(0L, message.getText());
    //    assertNotEquals(0L, message.getAttachedFiles());
    //    assertEquals("picture", message.getAttachedFiles());
    //    assertNotNull(message.getRoom());
    }

    @Test
    public void testFindMessageByRoom(){

        Room room = roomDAO.createRoom(2,"roomTitle2", 2, 2);
        Message message = new Message("Hello, World!", "picture2", room);

        messageDAO.save(message);

        room.setMessageListFromRoom(Collections.singletonList(message));
        List<Message> found = room.getMessageListFromRoom();
        System.out.println(found);

        assertNotNull(found);
        assertNotEquals(0, found.size());
        assertSame(message, found.get(0));

    }
}