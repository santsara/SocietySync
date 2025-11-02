package com.example.sosy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime; // <-- REQUIRED IMPORT

@Entity
@Table(name = "SOCIETY_QUERIES") // Matches the SQL table name
public class ResidentQuery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Query Details  
    private String title;
    private String description;

    // Resident Details
    @JsonProperty("residentName")
    private String residentName;
    @JsonProperty("residentFlatNo")
    private String residentFlatNo;

    // Status and Dates
    @JsonProperty("status")     
    private String status;
    
    // 💡 FIX 1: Change dateRaised from String to LocalDateTime
    @JsonProperty("dateRaised")
    private LocalDateTime dateRaised;    
    // 💡 FIX 2: Change dateSolved from String to LocalDateTime
    @JsonProperty("dateSolved")
    private LocalDateTime dateSolved; 

    // Resolution Details
    @JsonProperty("committeeAnswer")   
    private String committeeAnswer;
    @JsonProperty("answeredBy")
    private String answeredBy;

    // Default Constructor (Required by JPA)
    public ResidentQuery() {}

    // --- Getters and Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getResidentName() { return residentName; }
    public void setResidentName(String residentName) { this.residentName = residentName; }

    public String getResidentFlatNo() { return residentFlatNo; }
    public void setResidentFlatNo(String residentFlatNo) { this.residentFlatNo = residentFlatNo; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    // 💡 FIX 3: Update setter signature to accept LocalDateTime
    public LocalDateTime getDateRaised() { return dateRaised; }
    public void setDateRaised(LocalDateTime dateRaised) { this.dateRaised = dateRaised; }

    // 💡 FIX 4: Update setter signature to accept LocalDateTime
    public LocalDateTime getDateSolved() { return dateSolved; }
    public void setDateSolved(LocalDateTime dateSolved) { this.dateSolved = dateSolved; }

    public String getCommitteeAnswer() { return committeeAnswer; }
    public void setCommitteeAnswer(String committeeAnswer) { this.committeeAnswer = committeeAnswer; }

    public String getAnsweredBy() { return answeredBy; }
    public void setAnsweredBy(String answeredBy) { this.answeredBy = answeredBy; }

    // Optionally, add a toString() method for debugging...
}