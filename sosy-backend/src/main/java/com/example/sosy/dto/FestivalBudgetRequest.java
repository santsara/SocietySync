package com.example.sosy.dto;

// Import required for JSON property mapping
import com.fasterxml.jackson.annotation.JsonProperty;

public class FestivalBudgetRequest {
    private Long id;
    // Explicitly define JSON property name to match frontend payload
    @JsonProperty("festivalName") 
    private String festivalName; 
    
    @JsonProperty("expectedBudget") 
    private Double expectedBudget;
    
    @JsonProperty("donationsReceived") 
    private Double donationsReceived;
    
    @JsonProperty("moneyUsed") 
    private Double moneyUsed;

    // ✅ Getters (Standardized to getFieldName)
    public Long getId() {
        return id;
    }

    public String getFestivalName() { // ✅ Fix: Must be getFestivalName()
        return festivalName;
    }

    public Double getExpectedBudget() { // ✅ Fix: Must be getExpectedBudget()
        return expectedBudget;
    }

    public Double getDonationsReceived() { // ✅ Fix: Must be getDonationsReceived()
        return donationsReceived;
    }

    public Double getMoneyUsed() { // ✅ Fix: Must be getMoneyUsed()
        return moneyUsed;
    }

    // ✅ Setters (Standardized to setFieldName)
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