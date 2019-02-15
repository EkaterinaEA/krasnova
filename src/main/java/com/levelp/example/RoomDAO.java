package com.levelp.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;


@Service
public class RoomDAO extends EntityDAO {

    @Autowired
    public RoomDAO(EntityManager manager) {
        super(manager);
    }

    public Room createRoom(long roomId, String roomTitle, int countryIndex, int regionIndex){
        Room room = new Room(roomId, roomTitle, countryIndex, regionIndex);
        return room;
    }

    public Room enterTheRoom(String roomTitle){
        return getManager().createNamedQuery("enterTheRoom", Room.class)
                .setParameter("roomTitle", roomTitle)
                .getSingleResult();
    }

}
