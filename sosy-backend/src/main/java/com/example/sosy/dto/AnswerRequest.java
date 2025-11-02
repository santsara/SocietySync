package com.example.sosy.dto;

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

    // Setters
    public void setQueryId(Long queryId) { 
        this.queryId = queryId; 
    }
    
    public void setCommitteeAnswer(String committeeAnswer) { 
        this.committeeAnswer = committeeAnswer; 
    }

}