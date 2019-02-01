import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = Room.FIND_ROOM_BY_ID_QUERY, query = "from Room where id = : id"),
        @NamedQuery(name = Room.FIND_ROOM_BY_TITLE_QUERY, query = "from Room where title = : id"),
        @NamedQuery(name = Room.FIND_ROOM_BY_COUNTRY_QUERY, query = "from Room where country = : id"),
        @NamedQuery(name = Room.FIND_ROOM_BY_REGION_QUERY, query = "from Room where region = : id"),
        @NamedQuery(name = Room.FIND_ROOMS_QUERY, query = "from Room"),
})

@Entity
public class Room {

    public static final String FIND_ROOM_BY_ID_QUERY = "findRoomById";
    public static final String FIND_ROOM_BY_TITLE_QUERY = "findRoomByTitle";
    public static final String FIND_ROOM_BY_COUNTRY_QUERY = "findRoomByCountry";
    public static final String FIND_ROOM_BY_REGION_QUERY = "findRoomByRegion";
    public static final String FIND_ROOMS_QUERY = "findRoom";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_id_generator")
    @SequenceGenerator(name = "room_id_generator", sequenceName = "room_id_seq")
    @Column (name = "room_id")
    private long roomId;

    @Column(name = "title", unique = true, nullable = false)
    private String roomTitle;

    @Column(name = "country", nullable = false)
    private String Country;

    @Column(name = "region", nullable = false)
    private String Region;

    @OneToMany
    private Client client;

    @OneToOne
    private Admin admin;

    @OneToMany
    private Message message;

    public Room() {}

    public Room(String roomTitle, String country, String region) {
        this.roomTitle = roomTitle;
        Country = country;
        Region = region;
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

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

}
