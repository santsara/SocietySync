package com.example.sosy.controller;

import com.example.sosy.dto.FestivalBudgetRequest;
import com.example.sosy.dto.FestivalBudgetResponse;
import com.example.sosy.model.FestivalBudget;
import com.example.sosy.service.FestivalBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/festival")
public class FestivalBudgetController {

    @Autowired
    private FestivalBudgetService service;

    @GetMapping
    public FestivalBudgetResponse getAllFestivals(HttpSession session) {
        boolean isCommittee = Boolean.TRUE.equals(session.getAttribute("is_committee"));
        List<FestivalBudget> festivals = service.getAllFestivals();
        return new FestivalBudgetResponse("success", isCommittee, festivals);
    }

    // This method now calls the service method which correctly handles INSERT vs. UPDATE
    @PostMapping
    public FestivalBudgetResponse saveOrUpdateFestival(@RequestBody FestivalBudgetRequest request, HttpSession session) {
        boolean isCommittee = Boolean.TRUE.equals(session.getAttribute("is_committee"));

        if (!isCommittee) {
            return new FestivalBudgetResponse("error", "Access denied. Only committee members can modify data.");
        }

        if (request.getFestivalName() == null || request.getFestivalName().trim().isEmpty()) {
            return new FestivalBudgetResponse("error", "Festival name is required.");
        }

        // ⭐ Delegate the logic to the service layer
        FestivalBudgetResponse response = service.saveOrUpdateFestival(request);

        // If the service returned an error (e.g., ID not found), return it
        if ("error".equals(response.getStatus())) {
             return response;
        }

        // Return the final success response (you may adjust the message if needed)
        return new FestivalBudgetResponse("success", "Festival budget saved successfully");
    }
}