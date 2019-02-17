package com.levelp.example.web;

import com.levelp.example.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    private final UserDAO users;

    @Autowired
    public UserController(UserDAO users) {
        this.users = users;
    }

    @GetMapping(path = "add-user")
    public String addUserForm(ModelMap modelMap){
        AddUserPageBean bean = new AddUserPageBean("admin", "", "");
        modelMap.addAttribute("bean", bean);
        return "add-user";
    }

    @RequestMapping(method = RequestMethod.POST)
    @PostMapping(path = "add-user")
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
            if (users.inviteUser("kind", "login") == null){
                // TODO error message
                return "add-user";
            }
        return "redirect:/";
    }

    @PostMapping(path = "add-user")
    public String delete(@RequestParam long userID){
        users.deleteUser(userID);
        return "redirect:/";
    }

}
