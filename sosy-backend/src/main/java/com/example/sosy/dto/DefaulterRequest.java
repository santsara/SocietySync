package com.example.sosy.dto;

import com.example.sosy.model.Defaulter;
import java.util.Optional;
import com.example.sosy.service.DefaulterService;
import org.springframework.stereotype.Service;

public class DefaulterRequest {

    private Long id;
    private String residentName;
    private String flatNumber;
    private Double unpaidAmount;

    // ✅ Getters
    public Long getId() {
        return id;
    }

    public String getResidentName() {
        return residentName;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public Double getUnpaidAmount() {
        return unpaidAmount;
    }

    // ✅ Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public void setUnpaidAmount(Double unpaidAmount) {
        this.unpaidAmount = unpaidAmount;
    }

    private String originalFlatNumber;

public String getOriginalFlatNumber() {
    return originalFlatNumber;
}

public void setOriginalFlatNumber(String originalFlatNumber) {
    this.originalFlatNumber = originalFlatNumber;
}



    //Optional<Defaulter> existing = service.findByFlat(req.getOriginalFlatNumber().trim());
}
