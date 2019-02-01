

import javax.persistence.EntityManager;
import java.util.List;

public class TestBean {

    private EntityManager em;
    private UserDAO users;

    public void setup(EntityManager em){
        this.em = em;
        this.users = new UserDAO(em);
    }

    public List<User> getUsers(){
        return users.userList();
    }
}
