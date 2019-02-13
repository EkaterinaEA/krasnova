import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;

@Controller
public class AddMessageController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private EntityManager em;

    @Autowired
    private List<PassportVerificationServise> allVerificationServices;

    @Autowired
    @Qualifier("police")
    private PassportVerificationServise policeVerificationService;

    @GetMapping(path = "/add-message")
    // @RequestMapping (method = RequestMethod.GET, path = "/add-subject")
    public String addMessageForm(@ModelAttribute(name = "subject") AddMessageFormBean form){
        return "add-message";
    }

    @PostMapping(path = "/add-message")
    public String postForm(
            @ModelAttribute(name = "message") AddMessageFormBean form,
            // позводит пробросить ошибку:
            BindingResult bindingResult
    ) {
        boolean verified = false;
        // обойдём список сервисов
        for (PassportVerificationServise servise : allVerificationServices) {
            if (servise.isValid(form.getName(), form.getPassportNumber())) {
                verified = true;
                break;
            }
        }
        if (!verified){
            bindingResult.addError(new FieldError("message", "name",
                    "Паспортные данные неверны"));
            return "add-message";
        }

        em.getTransaction().begin();
        try {
            Message created = messageDAO.createMessage(form.getText(), form.getAttachedFiles());
            created.setSubject(form.getSubject());
            em.getTransaction().commit();
        } catch (Throwable t){
            em.getTransaction().rollback();
            bindingResult.addError(new ObjectError("message", "Не удалось создать сообщение: "
            + t.getMessage() + "."));
        return "add-message";
    }
        return "redirect:/";
}
}

