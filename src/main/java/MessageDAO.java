import javax.persistence.EntityManager;
import java.util.List;

public class MessageDAO extends EntityDAO {

    public MessageDAO(EntityManager manager) {
        super(manager);
    }

    public Message sendMessage(String text, String attechedFiles){
        Message message = new Message(text, attechedFiles);
        getManager().getTransaction().begin();
        getManager().persist(message);
        getManager().getTransaction().commit();
        return message;
    }

    public Message findByRoom(String roomTitle){
        return getManager().createNamedQuery("findMessageByRoom", Message.class)
                .setParameter("roomTitle", roomTitle)
                .getSingleResult();
    }

    public List<Message> findAll(){
        return getManager().createNamedQuery("findAllMessages", Message.class)
                .getResultList();
    }
}
