package com.gymchampion.GymChampion.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "gym_user")
public class User {

    @Id
    private String login;

    @Column(length = 30)
    private String nickname;

    @Column(name = "birthday_date")
    private Date birthdayDate;

    @ManyToOne//(targetEntity = Gender.class)
    private Gender gender;

    private double weight;

    private boolean archivized;

    public User() {
    }

    public User(String login, String nickname, Date birthdayDate, Gender gender, double weight, boolean archivized) {
        this.login = login;
        this.nickname = nickname;
        this.birthdayDate = birthdayDate;
        this.gender = gender;
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
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isArchivized() {
        return archivized;
    }

    public void setArchivized(boolean archivized) {
        this.archivized = archivized;
    }
}



