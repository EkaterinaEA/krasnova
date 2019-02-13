import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;


@Service
@Transactional
public class RoomDAO extends EntityDAO{

    @Autowired
    public RoomDAO(EntityManager manager) {
        super(manager);
    }

    public Room createRoom(String roomTitle, int countryIndex, int regionIndex){
        Room room = new Room(roomTitle, countryIndex, regionIndex);
        getManager().getTransaction().begin();
        getManager().persist(room);
        getManager().getTransaction().commit();
        return room;
    }

    public Room enterTheRoomByName(String roomTitle){
        return getManager().createNamedQuery("enterTheRoomByName", Room.class)
                .setParameter("roomTitle", roomTitle)
                .getSingleResult();
    }
}
