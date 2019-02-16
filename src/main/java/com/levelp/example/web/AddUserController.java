package com.levelp.example.web;

import com.levelp.example.UserDAO;
import com.levelp.example.web.AddUserPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(path = "/add-user")
public class AddUserController {

    private final UserDAO users;

    @Autowired
    public AddUserController(UserDAO users) {
        this.users = users;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String addUserForm(ModelMap modelMap){
        AddUserPageBean bean = new AddUserPageBean("admin", "", "");
        modelMap.addAttribute("bean", bean);
        return "add-user";
    }

    @RequestMapping(method = RequestMethod.POST)
    @PostMapping
    @Transactional
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
            if (users.inviteUser(login, "password", "email") == null){
                // TODO error message
                return "add-user";
            }
        return "redirect:/";
    }
}
