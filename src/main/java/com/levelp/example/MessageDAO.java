package com.levelp.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class MessageDAO extends EntityDAO {

    private final EntityManager em;

    private Room room;

    public MessageDAO(@Autowired EntityManager em) {
        super(em);
        this.em = em;
    }

    public Message createMessage(String text, String attechedFiles, Room room){
        Message message = new Message(text, attechedFiles, room);
        return message;
    }

    public List<Message> findMessagesByRoom(String roomTitle){
        return getManager().createNamedQuery("findMessageByRoom", Message.class)
                .setParameter("roomTitle", room.getRoomTitle())
                .getResultList();
    }
}
