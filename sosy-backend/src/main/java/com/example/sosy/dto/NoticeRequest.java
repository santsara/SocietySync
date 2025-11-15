package com.example.sosy.dto;

public class NoticeRequest {
    private Long id;
    private String title;
    private String content;
    private String datePosted;

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