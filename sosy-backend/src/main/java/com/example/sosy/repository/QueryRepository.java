package com.example.sosy.repository;

import com.example.sosy.model.ResidentQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QueryRepository extends JpaRepository<ResidentQuery, Long> {
    
    // For Raised Queries
    List<ResidentQuery> findAllByStatusOrderByDateRaisedAsc(String status);
    
    // For Solved Queries (Newest first)
    List<ResidentQuery> findAllByStatusOrderByDateSolvedDesc(String status);
}