package com.example.sosy.controller;

import com.example.sosy.dto.DefaulterRequest;
import com.example.sosy.dto.DefaulterResponse;
import com.example.sosy.model.Defaulter;
import com.example.sosy.service.DefaulterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/defaulters")
public class DefaulterController {

    @Autowired
    private DefaulterService service;

    // ✅ GET: Fetch all defaulters
    @GetMapping
    public DefaulterResponse getAll(HttpSession session) {
        boolean isCommittee = Boolean.TRUE.equals(session.getAttribute("is_committee"));
        List<Defaulter> defaulters = service.getAll();
        return new DefaulterResponse("success", null, isCommittee, defaulters);
    }

    // ✅ POST: Add new defaulter
    @PostMapping
    public DefaulterResponse add(@RequestBody DefaulterRequest req, HttpSession session) {
        boolean isCommittee = Boolean.TRUE.equals(session.getAttribute("is_committee"));
        if (!isCommittee) return new DefaulterResponse("error", "Access denied");

        String flat = req.getFlatNumber() != null ? req.getFlatNumber().trim() : null;
        String name = req.getResidentName() != null ? req.getResidentName().trim() : null;
        Double amount = req.getUnpaidAmount();

        if (flat == null || name == null || amount == null)
            return new DefaulterResponse("error", "All fields required");

        if (!service.verifyUser(name, flat))
            return new DefaulterResponse("error", "Name and flat do not match");

        Defaulter d = new Defaulter(flat, name, amount);
        service.save(d);

        return new DefaulterResponse("success", "Defaulter added");
    }

    // ✅ PUT: Update existing defaulter (supports flat number change)
    @PutMapping
    public DefaulterResponse update(@RequestBody DefaulterRequest req, HttpSession session) {
        boolean isCommittee = Boolean.TRUE.equals(session.getAttribute("is_committee"));
        if (!isCommittee) return new DefaulterResponse("error", "Access denied");

        String originalFlat = req.getOriginalFlatNumber() != null ? req.getOriginalFlatNumber().trim() : null;
        String flat = req.getFlatNumber() != null ? req.getFlatNumber().trim() : null;
        String name = req.getResidentName() != null ? req.getResidentName().trim() : null;
        Double amount = req.getUnpaidAmount();

        // ⚠️ CRITICAL CHECK: Ensure your request body has all these fields
        if (originalFlat == null || flat == null || name == null || amount == null)
            return new DefaulterResponse("error", "All fields required");

        // ⚠️ CRITICAL CHECK: Ensure the NEW flat/name pair exists in the users table
        if (!service.verifyUser(name, flat))
            return new DefaulterResponse("error", "Name and flat do not match");

        Defaulter updated = new Defaulter(flat, name, amount);
        boolean success = service.updateDefaulter(originalFlat, updated);

        // ⚠️ CRITICAL CHECK: Returned if the original flat number was not found in the DB
        if (!success) return new DefaulterResponse("error", "Defaulter not found");

        return new DefaulterResponse("success", "Defaulter updated");
    }

    // ✅ DELETE: Remove defaulter by flat number (via path variable)
    @DeleteMapping("/{flatNumber}")
    public DefaulterResponse delete(@PathVariable String flatNumber, HttpSession session) {
        boolean isCommittee = Boolean.TRUE.equals(session.getAttribute("is_committee"));
        if (!isCommittee) return new DefaulterResponse("error", "Access denied");

        if (flatNumber == null || flatNumber.trim().isEmpty())
            return new DefaulterResponse("error", "Invalid flat number");

        service.delete(flatNumber.trim());
        return new DefaulterResponse("success", "Defaulter deleted");
    }
}