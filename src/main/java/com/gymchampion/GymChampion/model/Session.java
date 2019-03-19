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
}
