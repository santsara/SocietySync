package com.example.sosy.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SOCIETY_QUERIES")
public class ResidentQuery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- Relationship to User (Foreign Key) ---
    // CRITICAL FIX: Changed to FetchType.EAGER to prevent LazyInitializationException
    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "flat", referencedColumnName = "flat", nullable = false)
    private User resident; 
    
    // --- Query Details ---
    
    @Column(name = "title", nullable = false) 
    private String title;
    
    @Column(name = "description", nullable = false, columnDefinition = "text")
    private String description;

    @Column(name = "status", nullable = false) 
    private String status;
    
    @Column(name = "dateRaised") 
    private LocalDateTime dateRaised;
    
    @Column(name = "dateSolved") 
    private LocalDateTime dateSolved; 

    // --- Resolution Details ---
    @Column(name = "committeeAnswer", columnDefinition = "text")
    private String committeeAnswer;
    
    @Column(name = "answeredBy")
    private String answeredBy;

    // Default Constructor
    public ResidentQuery() {}

    // --- Getters and Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getResident() { return resident; }
    public void setResident(User resident) { this.resident = resident; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getDateRaised() { return dateRaised; }
    public void setDateRaised(LocalDateTime dateRaised) { this.dateRaised = dateRaised; }

    public LocalDateTime getDateSolved() { return dateSolved; }
    public void setDateSolved(LocalDateTime dateSolved) { this.dateSolved = dateSolved; }

    public String getCommitteeAnswer() { return committeeAnswer; }
    public void setCommitteeAnswer(String committeeAnswer) { this.committeeAnswer = committeeAnswer; }

    public String getAnsweredBy() { return answeredBy; }
    public void setAnsweredBy(String answeredBy) { this.answeredBy = answeredBy; }
}