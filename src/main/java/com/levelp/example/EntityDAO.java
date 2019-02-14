package com.levelp.example;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class EntityDAO {

    private EntityManager manager;

    public EntityDAO(EntityManager manager) {
        this.manager = manager;
    }

    public EntityManager getManager() {
        return manager;
    }

    public void setManager(EntityManager manager) {
        this.manager = manager;
    }
}
