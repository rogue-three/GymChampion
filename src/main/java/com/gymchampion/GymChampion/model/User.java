package com.gymchampion.GymChampion.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "gym_user")
public class User {

    @Id
    @Column(name = "user_login", length = 15, unique = true)
    private String login;

    @Column(name = "nickname", length = 30)
    private String nickname;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "weight")
    private double weight;

    @Column(name = "archivized")
    private boolean archivized;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;

    @OneToMany(mappedBy = "user")
    private List<Session> sessions;

    @ManyToMany
    @JoinColumn(name = "training_id")
    private List<Training> trainings = new ArrayList<>();

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

    public List<Session> getSessions() { return this.sessions; }

    public void setSessions(List<Session> sessions) { this.sessions = sessions; }

    public List<Training> getTrainings() { return this.trainings; }

    public void setTrainings(List<Training> trainings) { this.trainings = trainings; }
}



