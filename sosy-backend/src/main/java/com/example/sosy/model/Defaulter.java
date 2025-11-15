package com.example.sosy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "defaulters")
public class Defaulter {

    @Column(name = "resident_name", nullable = false, length = 100)
    private String resident_name;

    @Id // flat_number is the primary key in the database
    @Column(name = "flat_number", nullable = false, length = 20)
    private String flat_number;

    @Column(name = "unpaid_amount", columnDefinition = "decimal(10,2)")
    private Double unpaid_amount;

    @Column(name = "id", nullable = false)
    private Long id;

    // Constructors
    public Defaulter() {
        // Initialize with default values to prevent null fields
        this.resident_name = "";
        this.flat_number = "";
        this.unpaid_amount = 0.0;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getResident_name() { return resident_name; }
    public void setResident_name(String resident_name) { this.resident_name = resident_name; }
    public String getFlat_number() { return flat_number; }
    public void setFlat_number(String flat_number) { this.flat_number = flat_number; }
    public Double getUnpaid_amount() { return unpaid_amount; }
    public void setUnpaid_amount(Double unpaid_amount) { this.unpaid_amount = unpaid_amount; }
}