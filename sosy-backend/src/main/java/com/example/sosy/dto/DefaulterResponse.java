package com.example.sosy.dto;

import java.util.List;

import com.example.sosy.model.Defaulter;

public class DefaulterResponse {
    private String status;
    private String message;
    private int is_committee; // 1 for true, 0 for false, matching PHP response
    private List<Defaulter> defaulters;

    public DefaulterResponse(String status, String message, int is_committee, List<Defaulter> defaulters2) {
        this.status = status;
        this.message = message;
        this.is_committee = is_committee;
        this.defaulters = defaulters2;
    }

    public DefaulterResponse(String status, String message) {
        this.status = status;
        this.message = message;
        this.is_committee = 0;
        this.defaulters = null;
    }

    // Getters and Setters (Required by Spring Boot for JSON serialization)

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public int getIs_committee() { return is_committee; }
    public void setIs_committee(int is_committee) { this.is_committee = is_committee; }
    public List<Defaulter> getDefaulters() { return defaulters; }
    public void setDefaulters(List<Defaulter> defaulters) { this.defaulters = defaulters; }

    // (No nested Defaulter class here — use com.example.sosy.model.Defaulter)
}