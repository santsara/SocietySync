package com.example.sosy.dto;

public class ContactRequest {
    private Long id;
    private String contactGroup;
    private String contactRole;
    private String contactNumber;
    private Integer sortOrder;

    // Getters
    public Long getId() { return id; }
    public String getContactGroup() { return contactGroup; }
    public String getContactRole() { return contactRole; }
    public String getContactNumber() { return contactNumber; }
    public Integer getSortOrder() { return sortOrder; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setContactGroup(String contactGroup) { this.contactGroup = contactGroup; }
    public void setContactRole(String contactRole) { this.contactRole = contactRole; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
}