package com.example.sosy.dto;

import java.util.List;
import com.example.sosy.model.FestivalBudget;

public class FestivalBudgetResponse {
    private String status;
    private String message;
    private Boolean isCommittee;
    private List<FestivalBudget> festivals;

    // ✅ Constructor for error response
    public FestivalBudgetResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    // ✅ Constructor for success response
    public FestivalBudgetResponse(String status, Boolean isCommittee, List<FestivalBudget> festivals) {
        this.status = status;
        this.isCommittee = isCommittee;
        this.festivals = festivals;
    }

    // ✅ Getters for JSON serialization
    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getIsCommittee() {
        return isCommittee;
    }

    public List<FestivalBudget> getFestivals() {
        return festivals;
    }
}