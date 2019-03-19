package com.gymchampion.GymChampion.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "session")
public class Session {


    @Id
    @GeneratedValue
    @Column(name = "session_id")
    private int sessionId;

    @ManyToOne
    private User login;

    @Column(name = "session_key", nullable = false)
    private String sessionKey;

    @Column(name = "login_date", nullable = false)
    private Date loginDate;

    @Column(nullable = false)
    private boolean active;

    public Session() {
    }

    public Session(User login, String sessionId, Date loginDate, boolean active) {
        this.login = login;
        this.sessionKey = sessionId;
        this.loginDate = loginDate;
        this.active = active;
    }

    public User getLogin() {
        return login;
    }

    public void setLogin(User login) {
        this.login = login;
    }

    public String getSessionId() {
        return sessionKey;
    }

    public void setSessionId(String sessionId) {
        this.sessionKey = sessionId;
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
