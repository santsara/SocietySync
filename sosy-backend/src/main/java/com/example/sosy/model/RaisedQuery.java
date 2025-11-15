package com.example.sosy.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RAISED_QUERIES")
public class RaisedQuery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Column(name = "resident_flat_no", nullable = false)
    private String residentFlatNo;

    @Column(name = "resident_name", nullable = false)
    private String residentName;

    @Column(name = "date_raised")
    private LocalDateTime dateRaised;

    @Column(name = "is_solved", nullable = false)
    private Boolean isSolved = false;

    // --- Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getResidentFlatNo() { return residentFlatNo; }
    public void setResidentFlatNo(String residentFlatNo) { this.residentFlatNo = residentFlatNo; }
    public String getResidentName() { return residentName; }
    public void setResidentName(String residentName) { this.residentName = residentName; }
    public LocalDateTime getDateRaised() { return dateRaised; }
    public void setDateRaised(LocalDateTime dateRaised) { this.dateRaised = dateRaised; }
    public Boolean getIsSolved() {
        return isSolved;
    }

    public void setIsSolved(Boolean isSolved) {
        this.isSolved = isSolved;
    }
}