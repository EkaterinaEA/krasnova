

import javax.persistence.EntityManager;
import java.util.List;

public class UserDAO extends EntityDAO {

    private EntityManager em;

    public User inviteUser(String login, String password, String email){
        User user = new User(login, password, email);
        getManager().getTransaction().begin();
        getManager().persist(user);
        getManager().getTransaction().commit();
        return user;
    }

    public void deleteUser(User user){
        getManager().getTransaction().begin();
        getManager().remove(user);
        getManager().getTransaction().commit();
    }

    public User findById(long id){
        return getManager().createNamedQuery("findUserById", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public User findByLogin(String login){
        return getManager().createNamedQuery("findUserByLogin", User.class)
                .setParameter("login", login)
                .getSingleResult();
    }

    public User findByEmail(String email){
        return getManager().createNamedQuery("findUserByEmail", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public List<User> findAll(){
        return getManager().createNamedQuery("findAllUsers", User.class)
                .getResultList();
    }

    public UserDAO(EntityManager manager) {
        super(manager);
    }

    public List<User> userList(){
        return em.createQuery("from User", User.class).getResultList();
    }

}
