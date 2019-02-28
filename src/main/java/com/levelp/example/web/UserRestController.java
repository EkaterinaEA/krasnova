package com.levelp.example.web;

import com.levelp.example.User;
import com.levelp.example.UserDAO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.NoResultException;

// @ResponceBody - возвращаемый объект и есть тело ответа
@RestController  // = @ResponceBody + @Controller
public class UserRestController {

    private final UserDAO userDAO;

    public UserRestController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // отображение пользователя:
    @GetMapping("/api/users")
    public User userByLogin(@RequestParam String login){
        return userDAO.findByLogin(login);
    }

    @DeleteMapping
    public boolean deleteUser(@RequestParam String login){
        try {
            User user = userDAO.findByLogin(login);
            userDAO.deleteUser(user.getUserID());
            return true;
        } catch (NoResultException notFound){
            return false;
        }
    }

}
