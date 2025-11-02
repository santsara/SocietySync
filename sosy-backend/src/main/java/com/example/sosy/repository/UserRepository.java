package com.example.sosy.repository;

import com.example.sosy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String>
{
    // Finds a user by their flat number (primary key).
    Optional<User> findByFlat(String flat);
    /**
     * Finds a user by their full name and flat number.
     * Used for defaulter verification.
    */
    Optional<User> findByFullnameAndFlat(String fullname, String flat);
}