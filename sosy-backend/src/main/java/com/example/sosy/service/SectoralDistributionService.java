package com.example.sosy.service;

import com.example.sosy.dto.SectoralDistributionRequest;
import com.example.sosy.model.SectoralDistribution;
import com.example.sosy.repository.SectoralDistributionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectoralDistributionService {

    @Autowired
    private SectoralDistributionRepository repository;

    public List<SectoralDistribution> getAllSectoralDistributions() {
        return repository.findAll();
    }

    /**
     * Handles both creating a new sector (ID null) and updating an existing one (ID present).
     */
    public String saveOrUpdateSector(SectoralDistributionRequest request) {
        
        SectoralDistribution sector;

        if (request.getId() != null) {
            // UPDATE Logic: Check for existence
            Optional<SectoralDistribution> existing = repository.findById(request.getId());

            if (existing.isPresent()) {
                sector = existing.get();
            } else {
                return "Sector record not found with ID: " + request.getId();
            }
        } else {
            // CREATE Logic: New record
            sector = new SectoralDistribution();
        }
        
        // Map DTO fields
        sector.setSectorName(request.getSectorName().trim());
        sector.setAllocatedFunds(request.getAllocatedFunds());
        
        repository.save(sector);
        
        return "Sector budget saved successfully.";
    }
}