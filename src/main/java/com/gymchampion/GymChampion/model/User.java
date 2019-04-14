package com.gymchampion.GymChampion.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "gym_user")
public class User {

    @Id
    @Column(name = "user_login", length = 15, unique = true)
    private String login;

    @Column(name = "nickname", length = 30)
    private String nickname;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "weight")
    private double weight;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;

    public User() {}

    public User(String login, String nickname, Date birthDate, double weight) {
        this.login = login;
        this.nickname = nickname;
        this.birthDate = birthDate;
        this.weight = weight;
    }

    public User(String login, String nickname, double weight) {
        this.login = login;
        this.nickname = nickname;
        this.weight = weight;
    }

}



