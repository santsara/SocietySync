package com.example.sosy.model;

import javax.persistence.*;

@Entity
@Table(name = "fund_balance")
public class FundBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "maintenance_fund")
    private double maintenanceFund;

    @Column(name = "donation_fund")
    private double donationFund;

    // ✅ No-arg constructor (required by JPA)
    public FundBalance() {}

    // ✅ Full constructor
    public FundBalance(double maintenanceFund, double donationFund) {
        this.maintenanceFund = maintenanceFund;
        this.donationFund = donationFund;
    }

    // ✅ Getters
    public double getMaintenanceFund() {
        return maintenanceFund;
    }

    public double getDonationFund() {
        return donationFund;
    }

    // ✅ Setters
    public void setMaintenanceFund(double maintenanceFund) {
        this.maintenanceFund = maintenanceFund;
    }

    public void setDonationFund(double donationFund) {
        this.donationFund = donationFund;
    }

    // ✅ Optional: for debugging
    @Override
    public String toString() {
        return "FundBalance{" +
                "maintenanceFund=" + maintenanceFund +
                ", donationFund=" + donationFund +
                '}';
    }
}