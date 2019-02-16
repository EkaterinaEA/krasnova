package com.levelp.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class MessageDAO {

    @PersistenceContext
    private EntityManager em;

    private Room room;

    @Transactional
    public Message createMessage(String text, String attechedFiles, Room room){
        Message message = new Message(text, attechedFiles, room);
        return message;
    }

    public List<Message> findMessagesByRoom(String roomTitle){
        return em.createNamedQuery("findMessageByRoom", Message.class)
                .setParameter("roomTitle", room.getRoomTitle())
                .getResultList();
    }
}
