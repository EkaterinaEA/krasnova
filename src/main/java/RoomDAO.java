import javax.persistence.Column;
import javax.persistence.EntityManager;

public class RoomDAO extends EntityDAO{

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
