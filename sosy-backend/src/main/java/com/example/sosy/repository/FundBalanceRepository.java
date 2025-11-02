package com.example.sosy.repository;
import com.example.sosy.model.FundBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundBalanceRepository extends JpaRepository<FundBalance, Long> {
}