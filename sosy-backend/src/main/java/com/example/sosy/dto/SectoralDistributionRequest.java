package com.example.sosy.dto;

public class SectoralDistributionRequest {
    private Long id;
    private String sectorName;
    private Double allocatedFunds;

    // Getters
    public Long getId() { return id; }
    public String getSectorName() { return sectorName; }
    public Double getAllocatedFunds() { return allocatedFunds; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setSectorName(String sectorName) { this.sectorName = sectorName; }
    public void setAllocatedFunds(Double allocatedFunds) { this.allocatedFunds = allocatedFunds; }
}