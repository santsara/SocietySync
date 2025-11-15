package com.example.sosy.model;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "SOLVED_QUERIES")
public class SolvedQuery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_query_id")
    private Long originalQueryId;

    @Column(name = "committee_answer", length = 2000)
    private String committeeAnswer;

    @Column(name = "answered_by")
    private String answeredBy;

    @Column(name = "date_solved")
    private LocalDateTime dateSolved;

    // ADDED: to store original query's resident info
    @Column(name = "resident_flat_no", nullable = false)
    private String residentFlatNo;

    @Column(name = "resident_name", nullable = false)
    private String residentName;

    @Column(name = "title")
    private String title;

    @Column(name = "description", length = 2000)
    private String description;

    // --- Getters and Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getOriginalQueryId() { return originalQueryId; }
    public void setOriginalQueryId(Long originalQueryId) { this.originalQueryId = originalQueryId; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCommitteeAnswer() { return committeeAnswer; }
    public void setCommitteeAnswer(String committeeAnswer) { this.committeeAnswer = committeeAnswer; }

    public String getAnsweredBy() { return answeredBy; }
    public void setAnsweredBy(String answeredBy) { this.answeredBy = answeredBy; }

    public LocalDateTime getDateSolved() { return dateSolved; }
    public void setDateSolved(LocalDateTime dateSolved) { this.dateSolved = dateSolved; }

    public String getResidentFlatNo() { return residentFlatNo; }
    public void setResidentFlatNo(String residentFlatNo) { this.residentFlatNo = residentFlatNo; }

    public String getResidentName() { return residentName; }
    public void setResidentName(String residentName) { this.residentName = residentName; }
}