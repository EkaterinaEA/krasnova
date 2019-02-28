package com.levelp.example.web;

import org.springframework.security.core.userdetails.User;
import com.levelp.example.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;
import sun.plugin.liveconnect.SecurityContextHelper;

import java.util.Date;


// обработчик запросов
@Controller
public class IndexController {

    @Autowired
    private UserDAO users;

    public void setUsers(UserDAO users) {
        this.users = users;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String indexPage(ModelMap model){

        //достанем текущего user через Principal (объект User): (lesson 11, 3:28:00)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = authentication == null
                || authentication.getPrincipal() instanceof String
                ? null : (User) authentication.getPrincipal();

        String currentUserName = currentUser == null ? "" : currentUser.getUsername();

        IndexPageBean bean = new IndexPageBean(new Date(),
                users.listUsers(),
                currentUserName);

        model.addAttribute("bean", bean);

        return "index";
    }

}
