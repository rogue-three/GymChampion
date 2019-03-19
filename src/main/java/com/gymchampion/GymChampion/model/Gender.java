package com.gymchampion.GymChampion.model;

import javax.persistence.*;

@Entity
@Table(name="gender")
public class Gender {


    @Id
    @GeneratedValue
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
