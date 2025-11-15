package com.example.sosy.service;

import com.example.sosy.dto.NoticeRequest;
import com.example.sosy.model.Notice;
import com.example.sosy.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository repository;

    // Fetch all notices, newest first
    public List<Notice> getAllNotices() {
        return repository.findAllByOrderByIdDesc(); 
    }

    /**
     * Handles both creating a new notice (ID null) and updating an existing one (ID present).
     */
    public String saveOrUpdateNotice(NoticeRequest request) {
        
        Notice notice;

        if (request.getId() != null) {
            // UPDATE Logic
            Optional<Notice> existing = repository.findById(request.getId());

            if (existing.isPresent()) {
                notice = existing.get();
            } else {
                return "Notice record not found with ID: " + request.getId();
            }
        } else {
            // CREATE Logic
            notice = new Notice();
        }
        
        // Map DTO fields
        notice.setTitle(request.getTitle().trim());
        notice.setContent(request.getContent().trim());
        
        // Date Logic: 
        // 1. If updating, keep the existing datePosted from the request (which the frontend passes back).
        // 2. If creating (request.getId() == null), set the current date/time.
        if (request.getId() == null) {
             DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd, yyyy | HH:mm");
             notice.setDatePosted("Posted: " + dtf.format(LocalDateTime.now()));
        } else if (request.getDatePosted() != null && !request.getDatePosted().trim().isEmpty()) {
             // For updates, ensure the existing date is preserved if provided.
             notice.setDatePosted(request.getDatePosted());
        }

        repository.save(notice);
        
        return (request.getId() == null ? "New notice posted" : "Notice updated") + " successfully.";
    }
}