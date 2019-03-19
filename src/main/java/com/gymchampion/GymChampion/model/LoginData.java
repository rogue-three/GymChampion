package com.gymchampion.GymChampion.model;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.*;

@Entity
@Table(name = "login_data")
public class LoginData {

    @Id
    @GeneratedValue
    private int loginId;

    @OneToOne(targetEntity = User.class)
    private User login;

    @Column(nullable = false)
    private String password;

    public LoginData() {
    }

    public LoginData(String User, String password) {
        this.login = login;
        this.password = password;
    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public User getLogin() {
        return login;
    }

    public void setLogin(User login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
