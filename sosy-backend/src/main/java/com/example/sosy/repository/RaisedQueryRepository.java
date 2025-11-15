package com.example.sosy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sosy.model.RaisedQuery;

public interface RaisedQueryRepository extends JpaRepository<RaisedQuery, Long> {
    List<RaisedQuery> findAllByOrderByDateRaisedDesc();

    List<RaisedQuery> findByIsSolvedFalseOrderByDateRaisedDesc();
}
