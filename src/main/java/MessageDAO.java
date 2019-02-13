import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@Transactional
public class MessageDAO extends EntityDAO {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public MessageDAO(EntityManager manager) {
        super(manager);
    }

    @Transactional
    public Message createMessage(String text, String attechedFiles, Room room){
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

}
