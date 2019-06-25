package com.levibrooke.petexpensestracker.Model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="expense")
public class Expense {

    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    long id;

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    Date createdAt;
    String name;
    String description;
    Long categoryId;

    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    Double amount;

    @ManyToOne
    public AppUser appUser;

//    @ManyToOne
//    public Long category;

    public Expense(){}

    public Expense(String name, String description, Double amount, Date createdAt, Long categoryId) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.createdAt = createdAt;
        this.categoryId = categoryId;
    }

    public long getId() { return id; }

    public Date getCreatedAt() { return createdAt; }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public Double getAmount() { return amount; }

    public AppUser getAppUser() { return appUser; }

//    public Long getCategory() { return category; }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public void setName(String name) { this.name = name; }

    public void setDescription(String description) { this.description = description; }

    public void setAmount(Double amount) { this.amount = amount; }

    public Long getCategoryId() { return categoryId; }

    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

//    public void setCategory(Long category) { this.category = category; }

//    public void setAppUser(AppUser appUser) { this.appUser = appUser; }
}
