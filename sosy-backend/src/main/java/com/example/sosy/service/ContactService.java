package com.example.sosy.service;

import com.example.sosy.dto.ContactRequest;
import com.example.sosy.model.Contact;
import com.example.sosy.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    // Fetch all contacts, sorted by group and then order
    public List<Contact> getAllContacts() {
        return repository.findAllByOrderByContactGroupAscSortOrderAsc(); 
    }

    /**
     * Handles both creating a new contact (ID null) and updating an existing one (ID present).
     */
    public String saveOrUpdateContact(ContactRequest request) {
        
        Contact contact;

        if (request.getId() != null) {
            // UPDATE Logic
            Optional<Contact> existing = repository.findById(request.getId());

            if (existing.isPresent()) {
                contact = existing.get();
            } else {
                return "Contact record not found with ID: " + request.getId();
            }
        } else {
            // CREATE Logic
            contact = new Contact();
        }
        
        // Map DTO fields
        contact.setContactGroup(request.getContactGroup().trim());
        contact.setContactRole(request.getContactRole().trim());
        contact.setContactNumber(request.getContactNumber().trim());
        contact.setSortOrder(request.getSortOrder() != null ? request.getSortOrder() : 999); // Use a default high sort order if none is provided

        repository.save(contact);
        
        return (request.getId() == null ? "New contact added" : "Contact updated") + " successfully.";
    }
}