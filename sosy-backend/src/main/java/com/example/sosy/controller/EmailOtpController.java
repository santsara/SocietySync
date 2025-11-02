package com.example.sosy.controller; // Use your correct package structure

import com.example.sosy.service.EmailOtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/email")
public class EmailOtpController {

    @Autowired
    private EmailOtpService otpService;

    // Endpoint 1: Generates and sends OTP
    @PostMapping("/generate-otp")
    public ResponseEntity<String> generateOtp(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        if (email == null) {
            return new ResponseEntity<>("Email required", HttpStatus.BAD_REQUEST);
        }
        String result = otpService.generateAndSendOtp(email);
        
        if (result.startsWith("Error")) {
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Endpoint 2: Verifies the entered OTP
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String enteredOtp = payload.get("otp");
        
        if (email == null || enteredOtp == null) {
            return new ResponseEntity<>("Email and OTP required", HttpStatus.BAD_REQUEST);
        }
        
        if (otpService.validateOtp(email, enteredOtp)) {
            return new ResponseEntity<>("Verification successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid OTP or OTP expired", HttpStatus.UNAUTHORIZED);
        }
    }
}