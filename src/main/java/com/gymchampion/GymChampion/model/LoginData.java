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

}
