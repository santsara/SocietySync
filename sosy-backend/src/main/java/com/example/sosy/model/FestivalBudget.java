package com.example.sosy.model;

import javax.persistence.*;

@Entity
@Table(name = "festival_budget")
public class FestivalBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String festivalName;
    private Double expectedBudget;
    private Double donationsReceived;
    private Double moneyUsed;

    @Column(name = "remaining_funds", insertable = false, updatable = false)
    private Double remainingFunds;

    // ✅ Getters
    public Long getId() {
        return id;
    }

    public String getFestivalName() {
        return festivalName;
    }

    public Double getExpectedBudget() {
        return expectedBudget;
    }

    public Double getDonationsReceived() {
        return donationsReceived;
    }

    public Double getMoneyUsed() {
        return moneyUsed;
    }

    public Double getRemainingFunds() {
        return remainingFunds;
    }

    // ✅ Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setFestivalName(String festivalName) {
        this.festivalName = festivalName;
    }

    public void setExpectedBudget(Double expectedBudget) {
        this.expectedBudget = expectedBudget;
    }

    public void setDonationsReceived(Double donationsReceived) {
        this.donationsReceived = donationsReceived;
    }

    public void setMoneyUsed(Double moneyUsed) {
        this.moneyUsed = moneyUsed;
    }

}