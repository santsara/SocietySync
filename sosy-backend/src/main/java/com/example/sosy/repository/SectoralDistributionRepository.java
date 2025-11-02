package com.example.sosy.repository;

import com.example.sosy.model.SectoralDistribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectoralDistributionRepository extends JpaRepository<SectoralDistribution, Long> {
    // Standard CRUD operations are inherited
}