package com.example.sosy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sosy.model.SolvedQuery;

public interface SolvedQueryRepository extends JpaRepository<SolvedQuery, Long> {
    List<SolvedQuery> findAllByOrderByDateSolvedDesc();

    public Object findByOriginalQueryId(Long queryId);
}
