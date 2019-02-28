package com.levelp.example;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
// @RepositoryRestResource(path = "messages",
// collectionResourceRel = "messages")
public interface MessageDAO extends JpaRepository<Message, Long> {

    //   генераия запросов по умолчанию по конвенции Spring Repository. Поиск Subject по cadNum:
    //   Message findMessageByRoom(String room);

    Page<Message> findByRoom_IdAAndLogin(
        long roomId,
        String login,
        Pageable pageable
    );

    // делаем свой метод:
    @Query ("from Message where room =: room")
    List <Message> findMessageByRoom(Room room);


   // @PersistenceContext
   // private EntityManager em;

   // private Room room;

   // @Transactional
   // public Message createMessage(String text, String attechedFiles, Room room){
   //     Message message = new Message(text, attechedFiles, room);
   //     return message;
   // }

   // public List<Message> findMessagesByRoom(String roomTitle){
   //     return em.createNamedQuery("findMessageByRoom", Message.class)
   //             .setParameter("roomTitle", room.getRoomTitle())
   //             .getResultList();
   // }
}
