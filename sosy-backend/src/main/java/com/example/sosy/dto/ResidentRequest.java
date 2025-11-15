package com.example.sosy.dto;

public class ResidentRequest {
    private Long id;
    private String profession;
    private String nameAndTitle;
    private String flatNo;
    private String contactNumber;
    private Integer sortOrder;

    // Getters
    public Long getId() { return id; }
    public String getProfession() { return profession; }
    public String getNameAndTitle() { return nameAndTitle; }
    public String getFlatNo() { return flatNo; }
    public String getContactNumber() { return contactNumber; }
    public Integer getSortOrder() { return sortOrder; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setProfession(String profession) { this.profession = profession; }
    public void setNameAndTitle(String nameAndTitle) { this.nameAndTitle = nameAndTitle; }
    public void setFlatNo(String flatNo) { this.flatNo = flatNo; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
}
