package com.example.sosy.model;

import javax.persistence.*;

@Entity
@Table(name = "festival_budget")
public class FestivalBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Fixed to camelCase, mapped to DB column
    @Column(name = "festival_name", insertable = true, updatable = true)
    private String festivalName;
    
    // Fixed to camelCase, mapped to DB column
    @Column(name = "expected_budget", insertable = true, updatable = true)
    private Double expectedBudget;
    
    // Fixed to camelCase, mapped to DB column
    @Column(name = "donations_received", insertable = true, updatable = true)
    private Double donationsReceived;
    
    // Fixed to camelCase, mapped to DB column
    @Column(name = "money_used", insertable = true, updatable = true)
    private Double moneyUsed;

    // Correctly reads the GENERATED column
    @Column(name = "remaining_funds", insertable = false, updatable = false)
    private Double remainingFunds;

    // --- Constructors (Omitted for brevity) ---
    public FestivalBudget() {}

    // --- Getters (Standard Java Naming) ---
    public Long getId() {
        return id;
    }

    public String getFestivalName() { // ✅ Fix
        return festivalName;
    }

    public Double getExpectedBudget() { // ✅ Fix
        return expectedBudget;
    }

    public Double getDonationsReceived() { // ✅ Fix
        return donationsReceived;
    }

    public Double getMoneyUsed() { // ✅ Fix
        return moneyUsed;
    }

    public Double getRemainingFunds() {
        return remainingFunds;
    }

    // --- Setters (Standard Java Naming) ---
    public void setId(Long id) {
        this.id = id;
    }

    public void setFestivalName(String festivalName) { // ✅ Fix
        this.festivalName = festivalName;
    }

    public void setExpectedBudget(Double expectedBudget) { // ✅ Fix
        this.expectedBudget = expectedBudget;
    }

    public void setDonationsReceived(Double donationsReceived) { // ✅ Fix
        this.donationsReceived = donationsReceived;
    }

    public void setMoneyUsed(Double moneyUsed) { // ✅ Fix
        this.moneyUsed = moneyUsed;
    }
}