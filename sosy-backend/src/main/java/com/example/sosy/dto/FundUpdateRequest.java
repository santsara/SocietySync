package com.example.sosy.dto;
public class FundUpdateRequest {
    private Double maintenanceFund;
    private Double donationFund;

    // Getters and setters
    public Double getMaintenanceFund() {
        return maintenanceFund;
    }

    public void setMaintenanceFund(Double maintenanceFund) {
        this.maintenanceFund = maintenanceFund;
    }

    public Double getDonationFund() {
        return donationFund;
    }

    public void setDonationFund(Double donationFund) {
        this.donationFund = donationFund;
    }
}
