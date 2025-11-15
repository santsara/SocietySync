package com.example.sosy.model;

import javax.persistence.Column; // 💡 Import required for @Column
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table; // 💡 Added @Table annotation for safety

@Entity
@Table(name = "contact") // Explicitly map to the 'contact' table
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ FIX: Map camelCase field to snake_case DB column
    @Column(name = "contact_group")
    private String contactGroup; // e.g., "Committee Members", "Emergency Services"
    
    // ✅ FIX: Map camelCase field to snake_case DB column
    @Column(name = "contact_role")
    private String contactRole;  // e.g., "Secretary: Mrs. Deshmukh", "Police Assistance"
    
    // ✅ FIX: Map camelCase field to snake_case DB column
    @Column(name = "contact_number")
    private String contactNumber;
    
    // ✅ FIX: Map camelCase field to snake_case DB column
    @Column(name = "sort_order")
    private Integer sortOrder;   // Used to maintain a consistent order within a group

    // Default Constructor (required by JPA)
    public Contact() {
    }

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