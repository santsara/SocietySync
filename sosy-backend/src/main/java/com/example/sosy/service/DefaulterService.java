package com.example.sosy.service;

import com.example.sosy.model.Defaulter;
import com.example.sosy.repository.DefaulterRepository;
import com.example.sosy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DefaulterService {

    @Autowired
    private DefaulterRepository defaulterRepo;

    @Autowired
    private UserRepository userRepo; // Used for verifyUser

    public List<Defaulter> getAll() {
        return defaulterRepo.findAll();
    }

    public boolean verifyUser(String name, String flat) {
        // Assume userRepo.findByFullnameAndFlat exists
        return userRepo.findByFullnameAndFlat(name, flat).isPresent(); 
    }

    public void save(Defaulter d) {
        defaulterRepo.save(d);
    }

    public void delete(String flatNumber) {
        defaulterRepo.deleteById(flatNumber);
    }

    public Optional<Defaulter> findByFlat(String flatNumber) {
        return defaulterRepo.findById(flatNumber);
    }

    // 🏆 FINAL LOGIC WITH DEBUGGING
    @Transactional
    public boolean updateDefaulter(String originalFlatNumber, Defaulter updated) {
        
        System.out.println("DEBUG: Starting updateDefaulter for originalFlat: " + originalFlatNumber);
        System.out.println("DEBUG: New data - Flat: " + updated.getFlatNumber() + ", Name: " + updated.getResidentName() + ", Amount: " + updated.getUnpaidAmount());
        
        Optional<Defaulter> existingOpt = defaulterRepo.findById(originalFlatNumber);
        
        if (!existingOpt.isPresent()) {
            System.out.println("DEBUG: Defaulter not found in DB by originalFlatNumber.");
            return false;
        }
        
        String newFlatNumber = updated.getFlatNumber().trim();
        boolean flatNumberChanged = !originalFlatNumber.equals(newFlatNumber);

        if (flatNumberChanged) {
            // Case 1: Primary key changed (Delete old, save new)
            System.out.println("DEBUG: Case 1: Primary Key CHANGE detected.");
            
            defaulterRepo.deleteById(originalFlatNumber);
            defaulterRepo.save(updated); 
            
            System.out.println("DEBUG: Old record deleted. New record saved.");
            
        } else {
            // Case 2: Primary key UNCHANGED (Update managed entity)
            System.out.println("DEBUG: Case 2: Primary Key UNCHANGED. Updating managed entity.");
            Defaulter existing = existingOpt.get();
            
            // Apply updates
            existing.setResidentName(updated.getResidentName());
            existing.setUnpaidAmount(updated.getUnpaidAmount());
            
            defaulterRepo.save(existing); 
            
            System.out.println("DEBUG: Managed entity updated and saved.");
        }
        
        System.out.println("DEBUG: updateDefaulter finished. Returning true. Check console for SQL errors.");
        return true;
    }
}