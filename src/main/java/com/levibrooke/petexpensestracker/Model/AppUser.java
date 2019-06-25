package com.levibrooke.petexpensestracker.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(unique = true)
    String username;

    String password;
    String petName;
    String petType;

    @OneToMany(mappedBy="appUser")
    public List<Expense> expenses;

    public AppUser() {}

    public AppUser(String username, String password, String petName, String petType) {
        this.username = username;
        this.password = password;
        this.petName = petName;
        this.petType = petType;
    }

    // getters
    public long getId() {
        return id;
    }

    public String getUsername() { return username; }

    public String getPassword() {
        return password;
    }

    public String getPetName() {
        return petName;
    }

    public String getPetType() {
        return petType;
    }

    public List<Expense> getExpenses() { return expenses; }

    public void setExpenses(List<Expense> expenses) { this.expenses = expenses; }

    // UserDetails methods
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
