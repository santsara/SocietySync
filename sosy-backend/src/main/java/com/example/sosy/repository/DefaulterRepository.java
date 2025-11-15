package com.example.sosy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sosy.model.Defaulter;

@Repository
public interface DefaulterRepository extends JpaRepository<Defaulter, String> {
}