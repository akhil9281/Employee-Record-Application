package com.akhil.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long ID;
    @NotNull
    private String name;
    @Column(nullable = false)
    private int income;
    @Column(nullable = false)
    private int savings;
    @Email(message = "Not a valid email")
    private String email;

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User(String name, int income, int savings, String email) {
        this.name = name;
        this.savings = savings;
        this.income = income;
        this.email = email;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getSavings() {
        return savings;
    }

    public void setSavings(int savings) {
        this.savings = savings;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
