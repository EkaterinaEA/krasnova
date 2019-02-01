import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyApplicationListener implements ServletContextListener {

    private EntityManager em;
    private EntityManagerFactory factory;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        em = factory.createEntityManager();
        event.getServletContext().setAttribute("em", em);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        if (em != null){
            em.close();
        }
        if (factory != null){
            factory.close();
        }
    }

    private void unitUsers(){
        em.getTransaction().begin();
        try {
            new UserDAO(em).inviteUser("login", "passord", "email");
            em.getTransaction().rollback();
        } catch (Exception e){
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

}
