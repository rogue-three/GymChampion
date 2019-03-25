package com.gymchampion.GymChampion.model;

import javax.persistence.*;

@Entity
@Table(name = "login_data")
public class LoginData {

    @Id
    @Column(name = "login_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loginId;

    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Column(name = "user_email", nullable = false, length = 30, unique = true)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_login")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_role")
    private Role userRole;


    public LoginData() {}

    public LoginData(String password, String email, User user, Role userRole) {
        this.password = password;
        this.email = email;
        this.user = user;
        this.userRole = userRole;
    }

    public int getLoginId() {
        return this.loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() { return this.user; }

    public void setUser(User user) { this.user = user; }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
