package com.example.sosy.service;

import com.example.sosy.model.FundBalance;
import com.example.sosy.repository.FundBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FundBalanceService {

    @Autowired
    private FundBalanceRepository fundBalanceRepository;

    // ✅ Fetch the current fund balance
    public Optional<FundBalance> getFundBalance() {
        return fundBalanceRepository.findAll().stream().findFirst();
    }

    // ✅ Update the fund balance
    public boolean updateFundBalance(double maintenanceFund, double donationFund) {
        Optional<FundBalance> fundOpt = getFundBalance();
        if (fundOpt.isPresent()) {
            FundBalance fund = fundOpt.get();
            fund.setMaintenanceFund(maintenanceFund);
            fund.setDonationFund(donationFund);
            fundBalanceRepository.save(fund);
            return true;
        }
        return false;
    }
}