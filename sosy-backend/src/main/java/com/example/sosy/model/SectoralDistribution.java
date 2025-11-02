package com.example.sosy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SectoralDistribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sectorName;

    private Double allocatedFunds;

    // Default Constructor (required by JPA)
    public SectoralDistribution() {
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getSectorName() {
        return sectorName;
    }

    public Double getAllocatedFunds() {
        return allocatedFunds;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public void setAllocatedFunds(Double allocatedFunds) {
        this.allocatedFunds = allocatedFunds;
    }
}