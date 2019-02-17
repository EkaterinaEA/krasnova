package com.levelp.example;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Service
@Transactional
public class RoomDAO {

    @PersistenceContext
    private EntityManager em;

    public Room createRoom(long roomId, String roomTitle, int countryIndex, int regionIndex){
        Room room = new Room(roomId, roomTitle, countryIndex, regionIndex);
        return room;
    }

    public Room enterTheRoom(String roomTitle){
        return em.createNamedQuery("enterTheRoom", Room.class)
                .setParameter("roomTitle", roomTitle)
                .getSingleResult();
    }

}
