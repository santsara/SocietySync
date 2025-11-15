package com.example.sosy.dto;

import java.time.LocalDateTime;

public class QueryResponse {

    private Long id;
    private String title;
    private String description;
    private String status;
    private String residentName;
    private String residentFlatNo;
    private LocalDateTime dateRaised;
    private LocalDateTime dateSolved;
    private String committeeAnswer;
    private String answeredBy;

    // --- Getters ---
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public String getResidentName() { return residentName; }
    public String getResidentFlatNo() { return residentFlatNo; }
    public LocalDateTime getDateRaised() { return dateRaised; }
    public LocalDateTime getDateSolved() { return dateSolved; }
    public String getCommitteeAnswer() { return committeeAnswer; }
    public String getAnsweredBy() { return answeredBy; }

    // --- Setters (Implemented) ---
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }

    public void setResidentFlatNo(String residentFlatNo) {
        this.residentFlatNo = residentFlatNo;
    }

    public void setDateSolved(LocalDateTime dateSolved) {
        this.dateSolved = dateSolved;
    }

    public void setDateRaised(LocalDateTime dateRaised) {
        this.dateRaised = dateRaised;
    }

    public void setCommitteeAnswer(String committeeAnswer) {
        this.committeeAnswer = committeeAnswer;
    }

    public void setAnsweredBy(String answeredBy) {
        this.answeredBy = answeredBy;
    }
}