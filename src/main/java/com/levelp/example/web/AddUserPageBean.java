package com.levelp.example.web;

public class AddUserPageBean {

    private final String kind;
    private final String login;
    private final String loginError;

    public AddUserPageBean(String kind, String login, String loginError) {
        this.kind = kind;
        this.login = login;
        this.loginError = loginError;
    }

    public String getKind() {
        return kind;
    }

    public String getLogin() {
        return login;
    }

    public String getLoginError() {
        return loginError;
    }
}
