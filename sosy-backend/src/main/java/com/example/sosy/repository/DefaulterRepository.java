package com.example.sosy.repository;

import com.example.sosy.model.Defaulter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// ✅ Primary key type is String
public interface DefaulterRepository extends JpaRepository<Defaulter, String> {
    // No custom methods needed for update/delete logic with the flatNumber PK
}