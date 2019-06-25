package com.levibrooke.petexpensestracker.Model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="expense")
public class Expense {

    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    long id;

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    Date createdAt;
    String description;
    String categoryName;

    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    Double amount;

    @ManyToOne
    public AppUser appUser;

    public Expense(){}

    public Expense(String description, Double amount, Date createdAt, String categoryName) {
        this.description = description;
        this.amount = amount;
        this.createdAt = createdAt;
        this.categoryName = categoryName;
    }

    public long getId() { return id; }

    public Date getCreatedAt() { return createdAt; }

    public String getDescription() { return description; }

    public Double getAmount() { return amount; }

    public AppUser getAppUser() { return appUser; }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public void setDescription(String description) { this.description = description; }

    public void setAmount(Double amount) { this.amount = amount; }

    public String getCategoryName() { return categoryName; }

    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

}
