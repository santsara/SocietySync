package com.example.sosy.controller;

import com.example.sosy.dto.NoticeRequest;
import com.example.sosy.model.Notice;
import com.example.sosy.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notices")
public class NoticeController {

    @Autowired
    private NoticeService service;

    /**
     * Retrieves all data and includes the 'isCommittee' status for frontend logic.
     * Non-committee members have read-only access.
     */
    @GetMapping
    public Map<String, Object> getAllNotices(HttpSession session) {
        List<Notice> notices = service.getAllNotices();
        boolean isCommittee = Boolean.TRUE.equals(session.getAttribute("is_committee"));

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", notices);
        response.put("isCommittee", isCommittee); // Committee status included
        
        return response;
    }

    /**
     * Handles creation or update of notice records.
     * Access is restricted to committee members via HttpSession check.
     */
    @PostMapping
    public Map<String, String> saveOrUpdateNotice(@RequestBody NoticeRequest request, HttpSession session) {
        Map<String, String> response = new HashMap<>();
        
        // 1. Authorization Check
        boolean isCommittee = Boolean.TRUE.equals(session.getAttribute("is_committee"));
        if (!isCommittee) {
            response.put("status", "error");
            response.put("message", "Access denied. Only committee members can post notices.");
            return response;
        }

        // 2. Validation
        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            response.put("status", "error");
            response.put("message", "Notice title is required.");
            return response;
        }
        if (request.getContent() == null || request.getContent().trim().isEmpty()) {
            response.put("status", "error");
            response.put("message", "Notice content is required.");
            return response;
        }
        
        // 3. Delegation to Service
        String message = service.saveOrUpdateNotice(request);

        // 4. Return Response
        if (message.startsWith("Notice record not found")) {
            response.put("status", "error");
        } else {
            response.put("status", "success");
        }
        response.put("message", message);
        return response;
    }
}