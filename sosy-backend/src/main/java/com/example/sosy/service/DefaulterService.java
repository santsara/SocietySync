package com.example.sosy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sosy.dto.DefaulterRequest;
import com.example.sosy.model.Defaulter;
import com.example.sosy.repository.DefaulterRepository;

@Service
public class DefaulterService {

    @Autowired
    private DefaulterRepository defaulterRepo;

    @Autowired
    private UserService userService;

    public List<Defaulter> getAll() {
        return defaulterRepo.findAll();
    }

    public boolean verifyUser(String name, String flat) {
        return userService.verifyUser(name, flat);
    }

    public Defaulter save(Defaulter defaulter) {
        // Validate and clean input
        if (defaulter.getResident_name() == null || defaulter.getResident_name().trim().isEmpty()) {
            throw new IllegalArgumentException("Resident name cannot be empty");
        }
        if (defaulter.getFlat_number() == null || defaulter.getFlat_number().trim().isEmpty()) {
            throw new IllegalArgumentException("Flat number cannot be empty");
        }
        if (defaulter.getUnpaid_amount() == null || defaulter.getUnpaid_amount() < 0) {
            throw new IllegalArgumentException("Unpaid amount must be a positive number");
        }

        // Trim strings and format data
        defaulter.setResident_name(defaulter.getResident_name().trim());
        defaulter.setFlat_number(defaulter.getFlat_number().trim().toUpperCase());
        defaulter.setUnpaid_amount(Math.round(defaulter.getUnpaid_amount() * 100.0) / 100.0); // Round to 2 decimals
        
        // Check if a defaulter with this flat number already exists
        if (defaulterRepo.existsById(defaulter.getFlat_number())) {
            throw new IllegalArgumentException("A defaulter with this flat number already exists");
        }

        // Generate a new ID (since it's not auto-increment in DB)
        Long maxId = defaulterRepo.findAll().stream()
                .mapToLong(d -> d.getId() != null ? d.getId() : 0L)
                .max()
                .orElse(0L);
        defaulter.setId(maxId + 1);

        return defaulterRepo.save(defaulter);
    }

    public Optional<Defaulter> update(DefaulterRequest req) {
        if (req.getFlat_number() == null || req.getFlat_number().trim().isEmpty()) {
            return Optional.empty();
        }

        String flatNumber = req.getFlat_number().trim().toUpperCase();
        return defaulterRepo.findById(flatNumber).map(existingDefaulter -> {
            // Update fields
            existingDefaulter.setResident_name(req.getResident_name().trim());
            existingDefaulter.setUnpaid_amount(Math.round(req.getUnpaid_amount() * 100.0) / 100.0);
            // Keep the same ID
            return defaulterRepo.save(existingDefaulter);
        });
    }

    public void delete(String flatNumber) {
        defaulterRepo.deleteById(flatNumber);
    }

    public boolean existsById(String flatNumber) {
        return defaulterRepo.existsById(flatNumber);
    }
}