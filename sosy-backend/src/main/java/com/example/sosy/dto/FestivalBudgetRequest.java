package com.example.sosy.dto;

public class FestivalBudgetRequest {
    private Long id;
    private String festivalName;
    private Double expectedBudget;
    private Double donationsReceived;
    private Double moneyUsed;

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
