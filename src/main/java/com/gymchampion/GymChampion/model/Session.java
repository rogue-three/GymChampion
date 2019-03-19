package com.gymchampion.GymChampion.model;


import javax.persistence.*;
import java.util.Date;

public class Session {

    @ManyToOne
    private String login;

    @Column(name = "session_id", nullable = false)
    private String sessionId;

    @Column(name = "login_date", nullable = false)
    private Date loginDate;

    @Column(nullable = false)
    private boolean active;

    public Session() {
    }

    public Session(String login, String sessionId, Date loginDate, boolean active) {
        this.login = login;
        this.sessionId = sessionId;
        this.loginDate = loginDate;
        this.active = active;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
