package com.gymchampion.GymChampion.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "gym_user")
public class User {

    @Id
    @Column(length = 15)
    private String login;

    @Column(length = 30)
    private String nickname;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "weight")
    private double weight;

    @Column(name = "archivized")
    private boolean archivized;

    @ManyToOne
    private Gender gender;

    public User() {}

    public User(String login, String nickname, Date birthdayDate, double weight, boolean archivized) {
        this.login = login;
        this.nickname = nickname;
        this.birthDate = birthdayDate;
        this.weight = weight;
        this.archivized = archivized;
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

    public Date getBirthdayDate() {
        return this.birthDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthDate = birthdayDate;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isArchivized() {
        return this.archivized;
    }

    public void setArchivized(boolean archivized) {
        this.archivized = archivized;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}



