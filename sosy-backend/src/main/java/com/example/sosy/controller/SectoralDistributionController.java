package com.example.sosy.controller;

import com.example.sosy.dto.SectoralDistributionRequest;
import com.example.sosy.model.SectoralDistribution;
import com.example.sosy.service.SectoralDistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sectoral-distribution")
public class SectoralDistributionController {

    @Autowired
    private SectoralDistributionService service;

    /**
     * Retrieves all data and includes the 'isCommittee' status for frontend logic.
     */
    @GetMapping
    public Map<String, Object> getAllSectoralDistributions(HttpSession session) {
        List<SectoralDistribution> distributions = service.getAllSectoralDistributions();
        boolean isCommittee = Boolean.TRUE.equals(session.getAttribute("is_committee")); // Read committee status

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", distributions);
        response.put("isCommittee", isCommittee); // ⭐ Committee status included
        
        return response;
    }

    /**
     * Handles creation or update of sectoral distribution records.
     * Access is restricted to committee members.
     */
    @PostMapping
    public Map<String, String> saveOrUpdateSector(@RequestBody SectoralDistributionRequest request, HttpSession session) {
        Map<String, String> response = new HashMap<>();
        
        // 1. Authorization Check (Ensures read-only for non-committee members)
        boolean isCommittee = Boolean.TRUE.equals(session.getAttribute("is_committee"));
        if (!isCommittee) {
            response.put("status", "error");
            response.put("message", "Access denied. Only committee members can modify data.");
            return response;
        }

        // 2. Validation
        if (request.getSectorName() == null || request.getSectorName().trim().isEmpty()) {
            response.put("status", "error");
            response.put("message", "Sector name is required.");
            return response;
        }
        
        // 3. Delegation to Service
        String message = service.saveOrUpdateSector(request);

        // 4. Return Response
        if (message.startsWith("Sector record not found")) {
            response.put("status", "error");
        } else {
            response.put("status", "success");
        }
        response.put("message", message);
        return response;
    }
}