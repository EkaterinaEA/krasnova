import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * Created by Екатерина on 28.01.2019.
 */
public class HistoryMessage {

    @OneToMany(fetch = FetchType.LAZY)
    private List<Message> messages;

    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public List<Message> getMessagesList() {
        return messages;
    }

    public void setMessagesList(List<Message> messagesList) {
        this.messages = messagesList;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
