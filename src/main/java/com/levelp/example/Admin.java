package com.levelp.example;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Ad")

public class Admin extends User {

    @Column
    private String region;

    @Column
    private String country;

    public Admin() {}

    public Admin(String login) {
        setLogin(login);
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
