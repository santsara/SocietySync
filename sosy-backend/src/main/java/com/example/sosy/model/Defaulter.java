package com.example.sosy.model;

import javax.persistence.*;

@Entity
@Table(name = "defaulters")
public class Defaulter {

    // ✅ flatNumber is the Primary Key
    @Id
    @Column(name = "flat_number")
    private String flatNumber;

    @Column(name = "resident_name", nullable = false)
    private String residentName;

    @Column(name = "unpaid_amount", nullable = false)
    private Double unpaidAmount;

    // ✅ No-arg constructor (required by JPA)
    public Defaulter() {
    }

    // ✅ All-arg constructor
    public Defaulter(String flatNumber, String residentName, Double unpaidAmount) {
        this.flatNumber = flatNumber;
        this.residentName = residentName;
        this.unpaidAmount = unpaidAmount;
    }

    // ✅ Getters
    public String getFlatNumber() {
        return flatNumber;
    }

    public String getResidentName() {
        return residentName;
    }

    public Double getUnpaidAmount() {
        return unpaidAmount;
    }

    // ✅ Setters
    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }

    public void setUnpaidAmount(Double unpaidAmount) {
        this.unpaidAmount = unpaidAmount;
    }
}