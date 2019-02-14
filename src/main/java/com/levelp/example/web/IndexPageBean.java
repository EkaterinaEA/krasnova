package com.levelp.example.web;


import com.levelp.example.User;

import java.util.Date;
import java.util.List;

public class IndexPageBean {

    private final Date cureentDate;
    private final List<User> users;


    public IndexPageBean(Date cureentDate, List<User> users) {
        this.cureentDate = cureentDate;
        this.users = users;
    }

    public Date getCureentDate() {
        return cureentDate;
    }

    public List<User> getUsers() {
        return users;
    }
}
