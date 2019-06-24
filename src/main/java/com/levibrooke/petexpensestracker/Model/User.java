package com.levibrooke.petexpensestracker.Model;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(unique = true)
    String username;

    String password;
    String petName;
    String petType;

    public User() {}

    public User(String username, String password, String petName, String petType) {
        this.username = username;
        this.password = password;
        this.petName = petName;
        this.petType = petType;
    }

    // getters
    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPetName() {
        return petName;
    }

    public String getPetType() {
        return petType;
    }
}
