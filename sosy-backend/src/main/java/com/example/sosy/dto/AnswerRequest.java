package com.example.sosy.dto;

// This DTO maps the data sent from the resolution modal in qr_raisedQuery.html
public class AnswerRequest {
    private Long queryId;
    private String committeeAnswer;

    // Getters
    public Long getQueryId() { 
        return queryId; 
    }
    
    public String getCommitteeAnswer() { 
        return committeeAnswer; 
    }

    // Setters (CRITICAL for Spring to map the request body)
    public void setQueryId(Long queryId) { 
        this.queryId = queryId; 
    }
    
    public void setCommitteeAnswer(String committeeAnswer) { 
        this.committeeAnswer = committeeAnswer; 
    }
}