package com.levelp.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class MessageDAO extends EntityDAO {

    @PersistenceContext
    private EntityManager em;

    @Autowired(required=true)
    public MessageDAO(EntityManager manager) {
        super(manager);
    }

    public Message createMessage(String text, String attechedFiles, Room room){
        Message message = new Message(text, attechedFiles);
        message.setRoom(room);
        em.persist(message);
        return message;
    }

    public Message findByRoom(String roomTitle){
        return getManager().createNamedQuery("findMessageByRoom", Message.class)
                .setParameter("roomTitle", roomTitle)
                .getSingleResult();
    }

}
