package com.example.sosy.controller;

import com.example.sosy.dto.ContactRequest;
import com.example.sosy.model.Contact;
import com.example.sosy.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService service;

    /**
     * Retrieves all data and includes the 'isCommittee' status for frontend logic.
     */
    @GetMapping
    public Map<String, Object> getAllContacts(HttpSession session) {
        List<Contact> contacts = service.getAllContacts();
        boolean isCommittee = Boolean.TRUE.equals(session.getAttribute("is_committee"));

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", contacts);
        response.put("isCommittee", isCommittee); 
        
        return response;
    }

    /**
     * Handles creation or update of contact records.
     * Access is restricted to committee members via HttpSession check.
     */
    @PostMapping
    public Map<String, String> saveOrUpdateContact(@RequestBody ContactRequest request, HttpSession session) {
        Map<String, String> response = new HashMap<>();
        
        // 1. Authorization Check
        boolean isCommittee = Boolean.TRUE.equals(session.getAttribute("is_committee"));
        if (!isCommittee) {
            response.put("status", "error");
            response.put("message", "Access denied. Only committee members can modify contacts.");
            return response;
        }

        // 2. Validation
        if (request.getContactGroup() == null || request.getContactGroup().trim().isEmpty()) {
            response.put("status", "error");
            response.put("message", "Contact Group is required.");
            return response;
        }
        if (request.getContactRole() == null || request.getContactRole().trim().isEmpty()) {
            response.put("status", "error");
            response.put("message", "Contact Role/Detail is required.");
            return response;
        }
        if (request.getContactNumber() == null || request.getContactNumber().trim().isEmpty()) {
            response.put("status", "error");
            response.put("message", "Contact Number is required.");
            return response;
        }
        
        // 3. Delegation to Service
        String message = service.saveOrUpdateContact(request);

        // 4. Return Response
        if (message.startsWith("Contact record not found")) {
            response.put("status", "error");
        } else {
            response.put("status", "success");
        }
        response.put("message", message);
        return response;
    }
}