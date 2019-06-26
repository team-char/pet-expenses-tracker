package com.levibrooke.petexpensestracker.Model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="expense")
public class Expense {

    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    long id;

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    Date expenseDate;

    @Column(name = "created_at", updatable = false)
    Date createdAt;

    @Column(name = "updated_at")
    Date updatedAt;

    String description;
    String categoryName;

    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    Double amount;

    @ManyToOne
    public AppUser appUser;

    public Expense(){}

    public Expense(String description, Double amount, String expenseDate, String categoryName) {
        this.description = description;
        this.amount = amount;
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.categoryName = categoryName;

        try {
            this.expenseDate = new SimpleDateFormat("yyyy-MM-dd").parse(expenseDate);
        } catch (ParseException e) {
            System.out.println(e);
        }
    }

    public long getId() { return id; }

    public Date getCreatedAt() { return createdAt; }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getDescription() { return description; }

    public Double getAmount() { return amount; }

    public AppUser getAppUser() { return appUser; }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public void setDescription(String description) { this.description = description; }

    public void setAmount(Double amount) { this.amount = amount; }

    public String getCategoryName() { return categoryName; }

    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public void setUpdatedAt(Date updatedAt) {this.updatedAt = updatedAt;
    }

}
