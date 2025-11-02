package com.example.sosy.repository;

import com.example.sosy.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    // Custom method to fetch all contacts, ordered by groupName, then sortOrder
    List<Contact> findAllByOrderByContactGroupAscSortOrderAsc();
}