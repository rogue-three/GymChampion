package com.gymchampion.GymChampion.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "session")
public class Session {

    @Id
    @Column(name = "session_id")
    @GeneratedValue
    private int sessionId;

    @Column(name = "session_key", nullable = false)
    private String sessionKey;

    @Column(name = "login_date", nullable = false)
    private Date loginDate;

    @Column(name = "active", nullable = false)
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "user_login")
    private User user;

    public Session() {}

    public Session(String sessionKey, Date loginDate, boolean active) {
        this.sessionKey = sessionKey;
        this.loginDate = loginDate;
        this.active = active;
    }

    public int getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(int sessionId) { this.sessionId = sessionId; }

    public String getSessionKey() { return this.sessionKey; }

    public void setSessionKey(String sessionKey) { this.sessionKey = sessionKey; }

    public Date getLoginDate() {
        return this.loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User getUser() { return this.user; }

    public void setUser(User user) { this.user = user; }
}
