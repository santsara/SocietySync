package com.example.sosy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sosy.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Finds a user by their flat number (primary key).
     * @param flat the flat number
     * @return an Optional containing the User if found, or empty if not
     */
    Optional<User> findByFlat(String flat);

    /**
     * Finds a user by their full name and flat number.
     * Used for defaulter verification.
     * @param fullname the resident's full name
     * @param flat the flat number
     * @return an Optional containing the User if matched, or empty if not
     */
    Optional<User> findByFullnameAndFlat(String fullname, String flat);

    // Future queries can go here, e.g.:
    // List<User> findByIsCommitteeTrue();
    
}