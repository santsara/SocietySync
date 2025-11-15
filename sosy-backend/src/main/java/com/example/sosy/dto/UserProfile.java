package com.example.sosy.dto;

/**
 * Data Transfer Object for carrying basic user profile information 
 * to be displayed on the homepage.
 */
public class UserProfile {
    
    // Corresponds to User.fullname
    private String fullName;
    
    // Corresponds to User.flat (The primary key and identifier)
    private String flatNo; 
    
    // Corresponds to User.phone
    private String mobileNo; 

    /**
     * Constructor used by the UserService to map data from the User entity.
     */
    public UserProfile(String fullName, String flatNo, String mobileNo) {
        this.fullName = fullName;
        this.flatNo = flatNo;
        this.mobileNo = mobileNo;
    }

    // Getters for JSON serialization (REQUIRED by Spring Boot/Jackson)
    
    public String getFullName() {
        return fullName;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }
}
