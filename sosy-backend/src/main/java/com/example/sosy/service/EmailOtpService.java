package com.example.sosy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailOtpService {

    // Spring's built-in mail sender
    @Autowired
    private JavaMailSender mailSender; 

    // Temporary storage for OTPs: Email -> OTP string
    private Map<String, String> otpCache = new HashMap<>(); 

    // 1. GENERATE AND SEND OTP
    public String generateAndSendOtp(String recipientEmail) {
        // Generate a 6-digit OTP
        String otp = String.format("%06d", new Random().nextInt(999999));
        
        // 1. Store the OTP in the cache
        otpCache.put(recipientEmail, otp);
        
        // 2. Prepare the email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("your.society.email@gmail.com"); // Must match spring.mail.username
        message.setTo(recipientEmail);
        message.setSubject("SocietySync Verification Code");
        message.setText("Your One-Time Password (OTP) for SocietySync is: " + otp + 
                        "\n\nThis code is valid for 5 minutes. Do not share it.");

        // 3. Send the email
        try {
            mailSender.send(message);
            return "OTP sent to " + recipientEmail;
        } catch (Exception e) {
            System.err.println("Error sending email OTP: " + e.getMessage());
            // Remove from cache if sending fails, to prevent stale codes
            otpCache.remove(recipientEmail); 
            return "Error: Could not send email.";
        }
    }

    // 2. VALIDATE OTP
    public boolean validateOtp(String recipientEmail, String enteredOtp) {
        String storedOtp = otpCache.get(recipientEmail);
        
        if (storedOtp != null && storedOtp.equals(enteredOtp)) {
            // OTP is correct - remove from cache after verification
            otpCache.remove(recipientEmail); 
            return true;
        }
        return false;
    }
}