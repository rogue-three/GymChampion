package com.gymchampion.GymChampion.model;

import javax.persistence.*;

@Entity
@Table(name="gender")
public class Gender {

    @Id
    @Column(name = "gender_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genderId;

    @Column(name="sex", nullable = false, length = 15)
    private String sex;

    public Gender() {}

    public Gender(String sex) {
        this.sex = sex;
    }

    public int getGenderId() {
        return this.genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
