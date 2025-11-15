package com.example.sosy.service;

import com.example.sosy.dto.FestivalBudgetRequest;
import com.example.sosy.dto.FestivalBudgetResponse;
import com.example.sosy.model.FestivalBudget;
import com.example.sosy.repository.FestivalBudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FestivalBudgetService {

    @Autowired
    private FestivalBudgetRepository repository;

    public List<FestivalBudget> getAllFestivals() {
        return repository.findAll();
    }

    public Optional<FestivalBudget> findById(Long id) {
        return repository.findById(id);
    }
    
    /**
     * Handles both creating a new festival (ID null) and updating an existing one (ID present).
     * This method contains the crucial logic to prevent adding a new row on update.
     * @param request The DTO containing the festival ID and updated details.
     * @return FestivalBudgetResponse indicating success or failure.
     */
    public FestivalBudgetResponse saveOrUpdateFestival(FestivalBudgetRequest request) {
        
        FestivalBudget fb;

        if (request.getId() != null) {
            // ⭐ ATTEMPTING TO UPDATE: Must retrieve the existing record
            Optional<FestivalBudget> existing = repository.findById(request.getId());

            if (existing.isPresent()) {
                // UPDATE: Load the existing entity to preserve the ID
                fb = existing.get();
            } else {
                // ID was provided but not found, treat as an error
                return new FestivalBudgetResponse("error", "Cannot update: Festival record not found with ID: " + request.getId());
            }
        } else {
            // INSERT: ID is null, create a new entity
            fb = new FestivalBudget();
        }
        
        // ✅ CRITICAL FIX: Use the corrected, standard camelCase getters from the DTO
        fb.setFestivalName(request.getFestivalName().trim()); 
        fb.setExpectedBudget(request.getExpectedBudget()); 
        fb.setDonationsReceived(request.getDonationsReceived()); 
        fb.setMoneyUsed(request.getMoneyUsed()); 
        
        // Save/Update the entity
        repository.save(fb);

        // Return success response with the updated list of festivals
        List<FestivalBudget> updatedList = repository.findAll();
        // Use a placeholder for isCommittee since we don't need it in the service logic
        return new FestivalBudgetResponse("success", Boolean.FALSE, updatedList); 
    }
}