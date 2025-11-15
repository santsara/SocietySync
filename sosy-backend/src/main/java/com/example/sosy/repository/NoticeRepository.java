package com.example.sosy.repository;

import com.example.sosy.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    // Custom method to fetch all and order by ID descending (newest first)
    List<Notice> findAllByOrderByIdDesc();
}