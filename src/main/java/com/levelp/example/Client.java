package com.levelp.example;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Cl")

public class Client extends User {

    public Client() {
    }

    public Client(String login) {
        setLogin(login);
    }
}
