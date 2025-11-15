package com.example.sosy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "users")
public class User {

    @Column(name = "fullname", nullable = false)
    @NotBlank(message = "Full name is required")
    private String fullname;

    @Id
    @Column(name = "flat", nullable = false)
    @NotBlank(message = "Flat number is required")
    private String flat;

    @Column(name = "aadhaar", nullable = false)
    @NotBlank(message = "Aadhaar number is required")
    @Size(min = 12, max = 12, message = "Aadhaar number must be 12 digits")
    private String aadhaar;

    @Column(name = "occupation", nullable = false)
    @NotBlank(message = "Occupation is required")
    private String occupation;

    @Column(name = "members", nullable = false)
    @NotNull(message = "Number of family members is required")
    private Integer members;

    @Column(name = "family_names", nullable = false)
    @NotBlank(message = "Family names are required")
    @JsonProperty("family_names")
    private String familyNames;

    @Column(name = "phone", nullable = false)
    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 10, message = "Phone number must be 10 digits")
    private String phone;

    @Column(name = "email", nullable = false)
    @NotBlank(message = "Email address is required")
    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password is required")
    @Size(min = 4, message = "Password must be at least 4 characters long")
    private String password;

    // ✅ Committee status
    @Column(name = "is_committee", nullable = false)
    private Boolean isCommittee;

    // Getters and Setters

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getAadhaar() {
        return aadhaar;
    }

    public void setAadhaar(String aadhaar) {
        this.aadhaar = aadhaar;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Integer getMembers() {
        return members;
    }

    public void setMembers(Integer members) {
        this.members = members;
    }

    public String getFamilyNames() {
        return familyNames;
    }

    public void setFamilyNames(String familyNames) {
        this.familyNames = familyNames;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCommittee() {
        return Boolean.TRUE.equals(isCommittee);
    }

    public void setCommittee(boolean isCommittee) {
        this.isCommittee = isCommittee;
    }

    @Override
    public String toString() {
        return "User{" +
                "flat='" + flat + '\'' +
                ", fullname='" + fullname + '\'' +
                ", aadhaar='" + aadhaar + '\'' +
                ", occupation='" + occupation + '\'' +
                ", members=" + members +
                ", familyNames='" + familyNames + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isCommittee=" + isCommittee +
                '}';
    }
}