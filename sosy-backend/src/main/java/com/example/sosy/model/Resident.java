package com.example.sosy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String profession;  // e.g., "Doctors", "Lawyers"
    private String nameAndTitle; // e.g., "Dr. Meera Joshi"
    private String flatNo;       // e.g., "A-102"
    private String contactNumber;
    private Integer sortOrder;  // For manual ordering within a profession

    // Default Constructor (required by JPA)
    public Resident() {
    }

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