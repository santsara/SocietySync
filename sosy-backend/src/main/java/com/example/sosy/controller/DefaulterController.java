package com.example.sosy.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sosy.dto.DefaulterRequest;
import com.example.sosy.dto.DefaulterResponse;
import com.example.sosy.model.Defaulter;
import com.example.sosy.service.DefaulterService;

@RestController
@RequestMapping("/api/defaulters") // Matches the endpoint in the corrected JS
public class DefaulterController {

    @Autowired
    private DefaulterService defaulterService;

    // Helper to get committee status (assuming 'is_committee' is stored as a Boolean)
    private int getCommitteeStatus(HttpSession session) {
        Boolean isCommittee = (Boolean) session.getAttribute("is_committee");
        return Boolean.TRUE.equals(isCommittee) ? 1 : 0;
    }

    // GET
    @GetMapping
    public DefaulterResponse getAllDefaulters(HttpSession session) {
        int isCommittee = getCommitteeStatus(session);
        List<Defaulter> defaulters = defaulterService.getAll();
        return new DefaulterResponse("success", null, isCommittee, defaulters);
    }


    // POST - Add
    @PostMapping
    public DefaulterResponse addDefaulter(@RequestBody DefaulterRequest req, HttpSession session) {
        System.out.println("Add defaulter request received: " + req); // Debug log
        
        if (getCommitteeStatus(session) != 1) {
            return new DefaulterResponse("error", "Access denied");
        }

        // Input validation
        if (req.getResident_name() == null || req.getFlat_number() == null || req.getUnpaid_amount() == null) {
            return new DefaulterResponse("error", "All fields required");
        }

        if (req.getUnpaid_amount() <= 0) {
            return new DefaulterResponse("error", "Amount must be greater than 0");
        }

        try {
            // Create model from request
            Defaulter newDefaulter = new Defaulter();
            newDefaulter.setResident_name(req.getResident_name());
            newDefaulter.setFlat_number(req.getFlat_number());
            newDefaulter.setUnpaid_amount(req.getUnpaid_amount());

            // Print debug info
            System.out.println("Adding defaulter: " + newDefaulter.getResident_name() 
                + ", Flat: " + newDefaulter.getFlat_number() 
                + ", Amount: " + newDefaulter.getUnpaid_amount());

            // Save and return success
            Defaulter saved = defaulterService.save(newDefaulter);
            System.out.println("Successfully added defaulter with ID: " + saved.getId());
            return new DefaulterResponse("success", "Defaulter added successfully");
        } catch (IllegalArgumentException e) {
            // Handle validation errors
            System.err.println("Validation error: " + e.getMessage());
            return new DefaulterResponse("error", e.getMessage());
        } catch (Exception e) {
            // Handle other errors
            System.err.println("Error adding defaulter: " + e);
            e.printStackTrace();
            return new DefaulterResponse("error", "Database error: Please check your input and try again");
        }
    }

    // PUT - Edit
    @PutMapping
    public DefaulterResponse updateDefaulter(@RequestBody DefaulterRequest req, HttpSession session) {
        if (getCommitteeStatus(session) != 1) {
            return new DefaulterResponse("error", "Access denied");
        }

        if (req.getResident_name() == null || req.getFlat_number() == null || req.getUnpaid_amount() == null) {
            return new DefaulterResponse("error", "All fields required");
        }

        if (req.getUnpaid_amount() <= 0) {
            return new DefaulterResponse("error", "Amount must be greater than 0");
        }

        try {
            Optional<Defaulter> updatedDefaulter = defaulterService.update(req);
            return updatedDefaulter.map(d -> new DefaulterResponse("success", "Defaulter updated"))
                                .orElse(new DefaulterResponse("error", "Defaulter not found"));
        } catch (IllegalArgumentException e) {
            return new DefaulterResponse("error", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new DefaulterResponse("error", "Database error: Please check your input and try again");
        }
    }
    
    // DELETE
    // The PHP DELETE uses a JSON body, which is unconventional for DELETE,
    // so we'll adapt by using a separate endpoint path or changing the client.
    // Easiest adaptation is a dedicated path for the ID.
    @DeleteMapping("/{flatNumber}")
    public DefaulterResponse deleteDefaulter(@PathVariable String flatNumber, HttpSession session) {
        if (getCommitteeStatus(session) != 1) {
            return new DefaulterResponse("error", "Access denied");
        }

        if (flatNumber == null || flatNumber.trim().isEmpty()) {
            return new DefaulterResponse("error", "Invalid flat number");
        }

        String normalizedFlatNumber = flatNumber.trim().toUpperCase();
        if (!defaulterService.existsById(normalizedFlatNumber)) {
            return new DefaulterResponse("error", "Defaulter not found");
        }

        defaulterService.delete(normalizedFlatNumber);
        return new DefaulterResponse("success", "Defaulter deleted");
    }
}