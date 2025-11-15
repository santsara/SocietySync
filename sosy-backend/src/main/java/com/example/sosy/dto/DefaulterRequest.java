package com.example.sosy.dto;

public class DefaulterRequest {
    private String resident_name;
    private String flat_number; // Primary key for updates/deletes
    private Double unpaid_amount;

    // Getters and Setters
    public String getResident_name() { return resident_name; }
    public void setResident_name(String resident_name) { this.resident_name = resident_name; }
    public String getFlat_number() { return flat_number; }
    public void setFlat_number(String flat_number) { this.flat_number = flat_number; }
    public Double getUnpaid_amount() { return unpaid_amount; }
    public void setUnpaid_amount(Double unpaid_amount) { this.unpaid_amount = unpaid_amount; }
}