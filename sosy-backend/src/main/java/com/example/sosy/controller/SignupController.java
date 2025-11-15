package com.example.sosy.controller;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sosy.dto.UserProfile;
import com.example.sosy.model.LoginRequest;
import com.example.sosy.model.User;
import com.example.sosy.service.UserService;

@RestController
@RequestMapping("/api/signup")
public class SignupController {

    @Autowired
    private UserService userService;

    // ✅ Registration endpoint
    @PostMapping
    public ResponseEntity<String> signUp(@RequestBody User user) {
        try {
            if (userService.findOptionalByFlat(user.getFlat()).isPresent()) {
                return new ResponseEntity<>("User already exists with this flat number.", HttpStatus.CONFLICT);
            }

            System.out.println("Signup request received: " + user);
            userService.saveUser(user);
            return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error registering user: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // ✅ Login endpoint
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request, HttpSession session) {
        System.out.println("Login attempt for flat: " + request.getFlat());

        Optional<User> optionalUser = userService.findOptionalByFlat(request.getFlat());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "status", "error",
                "message", "Flat number not found"
            ));
        }

        User user = optionalUser.get();

        if (!request.getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                "status", "error",
                "message", "Invalid password"
            ));
        }

        // ✅ Committee validation: enforce committee ID entry for committee members
        boolean sessionIsCommittee = false;

        if (user.isCommittee()) {
            // If the user is a committee member in DB, they MUST provide the committeeId (their flat number)
            if (request.getCommitteeId() == null || request.getCommitteeId().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "status", "error",
                    "message", "Committee ID is required for committee members. Please enter your Committee ID (your flat number)."
                ));
            }

            if (!request.getCommitteeId().equals(user.getFlat())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "status", "error",
                    "message", "Invalid Committee ID"
                ));
            }

            // Validated committee ID matches user's flat -> grant committee session rights
            sessionIsCommittee = true;
        } else {
            // If a non-committee user attempts to pass a committeeId, reject to avoid privilege escalation
            if (request.getCommitteeId() != null && !request.getCommitteeId().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of(
                    "status", "error",
                    "message", "You are not a committee member"
                ));
            }
        }

        // ✅ Store session attributes (only set is_committee true when validated)
        session.setAttribute("flat", user.getFlat());
        session.setAttribute("is_committee", sessionIsCommittee);
        // ➡️ CRITICAL: store fullName in session for downstream use
        session.setAttribute("fullName", user.getFullname());

        System.out.println("Session set: flat=" + user.getFlat() + ", fullName=" + user.getFullname() + ", is_committee=" + sessionIsCommittee);

        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "Login successful",
            "is_committee", sessionIsCommittee
        ));
    }

    // ⭐ NEW ENDPOINT: GET user profile details for homepage display
    /**
     * API Endpoint: GET /api/signup/details
     * Fetches the profile data for the currently logged-in user using the session.
     */
    @GetMapping("/details")
    public ResponseEntity<UserProfile> getLoggedInUserProfile(HttpSession session) {
        
        // 1. Get the Flat Number from the session attribute set during login.
        String flatNo = (String) session.getAttribute("flat"); 

        if (flatNo == null) {
            // User is not logged in or session expired
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); 
        }

        try {
            // 2. Use the UserService to fetch and map the data to the DTO.
            UserProfile profile = userService.getUserProfile(flatNo);
            
            // 3. Return the DTO as a JSON response.
            return ResponseEntity.ok(profile);
            
        } catch (RuntimeException e) {
            // Handle case where user is not found in the database
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}