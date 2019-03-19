package com.gymchampion.GymChampion.model;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LoginData {

    @Id
    @GeneratedValue
    private int loginId;

    @Column(unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    public LoginData() {
    }

    public LoginData(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
