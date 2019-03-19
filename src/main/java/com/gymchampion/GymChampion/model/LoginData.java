package com.gymchampion.GymChampion.model;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.*;

@Entity
@Table(name = "login_data")
public class LoginData {

    @Id
    @Column(name = "login_id")
    @GeneratedValue
    private int loginId;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne
    private User user;

    public LoginData() {}

    public LoginData(int loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    public int getLoginId() {
        return this.loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() { return this.user; }

    public void setUser(User user) { this.user = user; }
}
