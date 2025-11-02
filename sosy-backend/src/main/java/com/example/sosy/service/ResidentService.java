package com.example.sosy.service;

import com.example.sosy.dto.ResidentRequest;
import com.example.sosy.model.Resident;
import com.example.sosy.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResidentService {

    @Autowired
    private ResidentRepository repository;

    // Fetch all residents, sorted by profession and then sort order
    public List<Resident> getAllResidents() {
        return repository.findAllByOrderByProfessionAscSortOrderAsc(); 
    }

    /**
     * Handles both creating a new resident (ID null) and updating an existing one (ID present).
     */
    public String saveOrUpdateResident(ResidentRequest request) {
        
        Resident resident;

        if (request.getId() != null) {
            // UPDATE Logic
            Optional<Resident> existing = repository.findById(request.getId());

            if (existing.isPresent()) {
                resident = existing.get();
            } else {
                return "Resident record not found with ID: " + request.getId();
            }
        } else {
            // CREATE Logic
            resident = new Resident();
        }
        
        // Map DTO fields
        resident.setProfession(request.getProfession().trim());
        resident.setNameAndTitle(request.getNameAndTitle().trim());
        resident.setFlatNo(request.getFlatNo().trim());
        resident.setContactNumber(request.getContactNumber().trim());
        resident.setSortOrder(request.getSortOrder() != null ? request.getSortOrder() : 999); 

        repository.save(resident);
        
        return (request.getId() == null ? "New resident added" : "Resident updated") + " successfully.";
    }
}