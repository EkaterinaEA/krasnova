import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;


@Controller
@RequestMapping(path = "/add-user")
public class AddUserController {

    private final EntityManager em;
    private final UserDAO users;

    @Autowired
    public AddUserController(EntityManager em, UserDAO users) {
        this.em = em;
        this.users = users;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String addUserForm(ModelMap modelMap){
        AddUserPageBean bean = new AddUserPageBean("admin", "", "");
        modelMap.addAttribute("bean", bean);
        return "add-user";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String postAddUserForm(@RequestParam String kind,
                                  @RequestParam String login,
                                  ModelMap model){
        if (kind == null){
            return "add-user";
        }

        if (login == null || login.isEmpty()){
            AddUserPageBean bean = new AddUserPageBean(kind, login, "Login is empty");
            model.addAttribute("bean", bean);
            return "add-user";
        }
        em.getTransaction().begin();
        try {
            if (users.inviteUser(login, "password", "email") == null){
                // TODO error message
                return "add-user";
            }
            em.getTransaction().commit();
        }catch (Throwable t){
            em.getTransaction().rollback();
            throw t;
        }
        return "redirect:/";
    }
}
