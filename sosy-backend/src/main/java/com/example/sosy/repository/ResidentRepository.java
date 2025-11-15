package com.example.sosy.repository;

import com.example.sosy.model.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, Long> {
    // Custom method to fetch all contacts, ordered by profession, then flatNo
    List<Resident> findAllByOrderByProfessionAscSortOrderAsc();
}