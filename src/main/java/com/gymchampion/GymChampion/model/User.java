package com.gymchampion.GymChampion.model;

import javax.persistence.*;
import java.util.Date;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthdayDate) {
        this.birthDate = birthdayDate;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}



