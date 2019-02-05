import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Room.FIND_ROOM_BY_ID_QUERY, query = "from Room where roomId = : roomId"),
        @NamedQuery(name = Room.FIND_ROOM_BY_TITLE_QUERY, query = "from Room where roomTitle = : roomTitle"),
        @NamedQuery(name = Room.FIND_ROOMS_QUERY, query = "from Room"),
})

@Entity
public class Room {

    public static final String FIND_ROOM_BY_ID_QUERY = "findRoomById";
    public static final String FIND_ROOM_BY_TITLE_QUERY = "findRoomByTitle";
    public static final String FIND_ROOMS_QUERY = "findRoom";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_id_generator")
    @SequenceGenerator(name = "room_id_generator", sequenceName = "room_id_seq")
    @Column (name = "roomId")
    private long roomId;

    @Column(name = "roomTitle", unique = true, nullable = false)
    private String roomTitle;

    @Column(name = "countryIndex", nullable = false)
    private int countryIndex;

    @Column(name = "regionIndex", nullable = false)
    private int regionIndex;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Client> clients;

    @OneToOne
    private Admin admin;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Message> messageListFromRoom;

    public Room() {}

    public Room(String roomTitle, int countryIndex, int regionIndex) {
        this.roomTitle = roomTitle;
        this.countryIndex = countryIndex;
        this.regionIndex = regionIndex;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    public int getCountryIndex() {
        return countryIndex;
    }

    public void setCountryIndex(int countryIndex) {
        this.countryIndex = countryIndex;
    }

    public int getRegionIndex() {
        return regionIndex;
    }

    public void setRegionIndex(int regionIndex) {
        this.regionIndex = regionIndex;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<Message> getMessageListFromRoom() {
        return messageListFromRoom;
    }

    public void setMessageListFromRoom(List<Message> messageListFromRoom) {
        this.messageListFromRoom = messageListFromRoom;
    }
}
