package com.example.sosy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sosy.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    // Declares the Spring Data JPA query method
    List<Contact> findAllByOrderByContactGroupAscSortOrderAsc();
    
    // Ensure all other required methods are also present
}