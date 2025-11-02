package com.example.sosy.controller;

import com.example.sosy.dto.FundBalanceResponse;
import com.example.sosy.dto.FundUpdateRequest;
import com.example.sosy.model.FundBalance;
import com.example.sosy.service.FundBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/fundflow")
public class FundBalanceController {

    @Autowired
    private FundBalanceService fundBalanceService;

    // ✅ GET: Fetch fund balance
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FundBalanceResponse getFundFlow(HttpSession session) {
        String flat = (String) session.getAttribute("flat");
        Object committeeObj = session.getAttribute("is_committee");

        boolean isCommittee = "true".equals(String.valueOf(committeeObj));

        System.out.println("Session flat: " + flat);
        System.out.println("Session is_committee: " + isCommittee);

        if (flat == null || flat.isEmpty()) {
            return new FundBalanceResponse("error", "Not logged in");
        }

        Optional<FundBalance> fundOpt = fundBalanceService.getFundBalance();
        if (fundOpt.isPresent()) {
            FundBalance fund = fundOpt.get();
            return new FundBalanceResponse("success", fund.getMaintenanceFund(), fund.getDonationFund(), isCommittee);
        } else {
            return new FundBalanceResponse("error", "Fund record not found");
        }
    }

    // ✅ POST: Update fund balance (committee only)
    @PostMapping("/update")
    public Map<String, Object> updateFund(@RequestBody FundUpdateRequest request, HttpSession session) {
        Map<String, Object> response = new HashMap<>();

        String flat = (String) session.getAttribute("flat");
        Object committeeObj = session.getAttribute("is_committee");

        boolean isCommittee = committeeObj instanceof Boolean && (Boolean) committeeObj;

        System.out.println("Update attempt by flat: " + flat + ", is_committee: " + isCommittee);

        if (flat == null || flat.isEmpty()) {
            response.put("status", "error");
            response.put("message", "Not logged in");
            return response;
        }

        if (!isCommittee) {
            response.put("status", "error");
            response.put("message", "Access denied");
            return response;
        }

        if (request.getMaintenanceFund() == null || request.getDonationFund() == null) {
            response.put("status", "error");
            response.put("message", "Missing fund values");
            return response;
        }

        boolean success = fundBalanceService.updateFundBalance(request.getMaintenanceFund(), request.getDonationFund());
        if (success) {
            response.put("status", "success");
        } else {
            response.put("status", "error");
            response.put("message", "Database update failed");
        }

        return response;
    }
}