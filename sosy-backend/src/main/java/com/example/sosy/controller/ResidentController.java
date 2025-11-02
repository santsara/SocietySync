package com.example.sosy.controller;

import com.example.sosy.dto.ResidentRequest;
import com.example.sosy.model.Resident;
import com.example.sosy.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/residents")
public class ResidentController {

    @Autowired
    private ResidentService service;

    /**
     * Retrieves all data and includes the 'isCommittee' status for frontend logic.
     */
    @GetMapping
    public Map<String, Object> getAllResidents(HttpSession session) {
        List<Resident> residents = service.getAllResidents();
        boolean isCommittee = Boolean.TRUE.equals(session.getAttribute("is_committee"));

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", residents);
        response.put("isCommittee", isCommittee); 
        
        return response;
    }

    /**
     * Handles creation or update of resident records.
     * Access is restricted to committee members via HttpSession check.
     */
    @PostMapping
    public Map<String, String> saveOrUpdateResident(@RequestBody ResidentRequest request, HttpSession session) {
        Map<String, String> response = new HashMap<>();
        
        // 1. Authorization Check
        boolean isCommittee = Boolean.TRUE.equals(session.getAttribute("is_committee"));
        if (!isCommittee) {
            response.put("status", "error");
            response.put("message", "Access denied. Only committee members can modify residents.");
            return response;
        }

        // 2. Validation
        if (request.getProfession() == null || request.getProfession().trim().isEmpty()) {
            response.put("status", "error");
            response.put("message", "Profession is required.");
            return response;
        }
        if (request.getNameAndTitle() == null || request.getNameAndTitle().trim().isEmpty()) {
            response.put("status", "error");
            response.put("message", "Name/Title is required.");
            return response;
        }
        if (request.getContactNumber() == null || request.getContactNumber().trim().isEmpty()) {
            response.put("status", "error");
            response.put("message", "Contact Number is required.");
            return response;
        }
        
        // 3. Delegation to Service
        String message = service.saveOrUpdateResident(request);

        // 4. Return Response
        if (message.startsWith("Resident record not found")) {
            response.put("status", "error");
        } else {
            response.put("status", "success");
        }
        response.put("message", message);
        return response;
    }
}