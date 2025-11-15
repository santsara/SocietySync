package com.example.sosy.dto;

public class FundBalanceResponse {
    private String status;
    private Double maintenance_fund;
    private Double donation_fund;
    private Boolean is_committee;
    private String message;

    // ✅ Constructor for success response
    public FundBalanceResponse(String status, Double maintenance_fund, Double donation_fund, Boolean is_committee) {
        this.status = status;
        this.maintenance_fund = maintenance_fund;
        this.donation_fund = donation_fund;
        this.is_committee = is_committee;
    }

    // ✅ Constructor for error response
    public FundBalanceResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    // ✅ Getters
    public String getStatus() {
        return status;
    }

    public Double getMaintenance_fund() {
        return maintenance_fund;
    }

    public Double getDonation_fund() {
        return donation_fund;
    }

    public Boolean getIs_committee() {
        return is_committee;
    }

    public String getMessage() {
        return message;
    }
}