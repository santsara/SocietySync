package com.example.sosy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    
    // @Lob is good practice for storing long text content like a notice body.
    @Lob 
    private String content;

    private String datePosted; // For display on the notice card.

    // Default Constructor (required by JPA)
    public Notice() {
    }

    // Getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getDatePosted() { return datePosted; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
    public void setDatePosted(String datePosted) { this.datePosted = datePosted; }
}