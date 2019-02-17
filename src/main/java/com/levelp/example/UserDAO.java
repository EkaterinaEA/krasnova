package com.levelp.example;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class UserDAO {

    @PersistenceContext
    private EntityManager em;

    public User inviteUser(String kind, String login){
        User user;

        switch (kind) {
            case "client":
                user = new Client(login);
                break;
            case "admin":
                user = new Admin(login);
                break;
            default:
                return null;
        }
        em.persist(user);
        return user;
    }

    public void deleteUser(long userID){
        User user = em.find(User.class, userID);
        if(user == null) throw new EntityNotFoundException("User with id " + userID + " is not found");
        em.remove(user);
    }

    public User findById(long id){
        return em.createNamedQuery("findUserById", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public User findByLogin(String login){

//        return em.createQuery("from User where login = :lll", User.class)
//                .setParameter("lll", login)
//
        return em.createNamedQuery("findUserByLogin", User.class)
                .setParameter("login", login)
                .getSingleResult();
    }

    public User findByEmail(String email){
        return em.createNamedQuery("findUserByEmail", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public List<User> listUsers() {
        return em.createQuery("from User", User.class).getResultList();
    }

    public List<User> findAll(){
        return em.createNamedQuery("findAllUsers", User.class)
                .getResultList();
    }

}
