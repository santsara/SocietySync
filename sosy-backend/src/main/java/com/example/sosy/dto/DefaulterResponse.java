package com.example.sosy.dto;

import com.example.sosy.model.Defaulter;
import java.util.List;

public class DefaulterResponse {

    private String status;
    private String message;
    private boolean isCommittee;
    private List<Defaulter> defaulters;

    // ✅ Constructors

    // Basic success with message only
    public DefaulterResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    // Success with committee flag and list of defaulters
    public DefaulterResponse(String status, String message, boolean isCommittee, List<Defaulter> defaulters) {
        this.status = status;
        this.message = message;
        this.isCommittee = isCommittee;
        this.defaulters = defaulters;
    }

    // Success with committee flag and no message
    public DefaulterResponse(String status, boolean isCommittee, List<Defaulter> defaulters) {
        this.status = status;
        this.isCommittee = isCommittee;
        this.defaulters = defaulters;
    }

    // ✅ Getters
    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public boolean isCommittee() {
        return isCommittee;
    }

    public List<Defaulter> getDefaulters() {
        return defaulters;
    }

    // ✅ Setters
    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCommittee(boolean committee) {
        isCommittee = committee;
    }

    public void setDefaulters(List<Defaulter> defaulters) {
        this.defaulters = defaulters;
    }
}