package com.gymchampion.GymChampion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Gender {

    @Id
    @GeneratedValue
    @Column(name = "gender_id")
    private int genderId;

    @Column(nullable = false)
    private String sex;


    public Gender(String sex) {
        this.sex = sex;
    }

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
