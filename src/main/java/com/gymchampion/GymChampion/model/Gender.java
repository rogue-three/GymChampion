package com.gymchampion.GymChampion.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="gender")
public class Gender {


    @Id
    @Column(name = "gender_id")
    @GeneratedValue
    private int genderId;

    @Column(nullable = false, length = 15)
    private String sex;

    @OneToMany(mappedBy = "gender")
    private List<User> user = new ArrayList<>();

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
