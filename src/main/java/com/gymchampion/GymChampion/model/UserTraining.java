package com.gymchampion.GymChampion.model;

import javax.persistence.*;

@Entity
public class UserTraining {

    @Id
    @GeneratedValue
    private int id;



    @ManyToOne(targetEntity = User.class)
    private User userLogin;


    @ManyToOne(targetEntity = Training.class)
    private Training trainingId;


    public UserTraining() {
    }

    public UserTraining(User userLogin, Training trainingId) {
        this.userLogin = userLogin;
        this.trainingId = trainingId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(User userLogin) {
        this.userLogin = userLogin;
    }

    public Training getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Training trainingId) {
        this.trainingId = trainingId;
    }
}

