package com.example.sosy.controller;

import java.util.HashMap;
import java.util.List;
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

import com.example.sosy.dto.AnswerRequest;
import com.example.sosy.dto.QueryResponse;
import com.example.sosy.model.RaisedQuery;
import com.example.sosy.model.SolvedQuery;
import com.example.sosy.model.User;
import com.example.sosy.service.QueryService;

@RestController
@RequestMapping("/api/quickresolve")
public class QueryController {

    @Autowired
    private QueryService service;

    @GetMapping("/session")
    public ResponseEntity<Map<String, Object>> getSessionData(HttpSession session) {
        Map<String, Object> sessionData = new HashMap<>();
        sessionData.put("isCommittee", session.getAttribute("is_committee"));
        sessionData.put("fullName", session.getAttribute("fullName"));
        sessionData.put("flat", session.getAttribute("flat"));
        return ResponseEntity.ok(sessionData);
    }
    
    @GetMapping("/raised")
    public ResponseEntity<List<QueryResponse>> getRaisedQueries() {
        List<QueryResponse> queries = service.getRaisedQueries();
        return ResponseEntity.ok(queries);
    }
    
    @GetMapping("/solved")
    public ResponseEntity<List<QueryResponse>> getSolvedQueries() {
        List<QueryResponse> queries = service.getSolvedQueries();
        return ResponseEntity.ok(queries);
    }

    @PostMapping("/write")
    public ResponseEntity<Map<String, String>> writeQuery(@RequestBody RaisedQuery query, HttpSession session) {
        Map<String, String> response = new HashMap<>();
        
        if (query.getTitle() == null || query.getTitle().trim().isEmpty() ||
            query.getDescription() == null || query.getDescription().trim().isEmpty()) {
            response.put("status", "error");
            response.put("message", "Title and description are required for the query.");
            return ResponseEntity.badRequest().body(response);
        }

        String residentFlatNo = Optional.ofNullable(session.getAttribute("flat"))
                                             .map(Object::toString)
                                             .orElse(null);

        if (residentFlatNo == null || residentFlatNo.trim().isEmpty()) {
            response.put("status", "error");
            response.put("message", "User session invalid or flat number missing. Please log in again.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        
        try {
            User loggedInUser = service.findUserByFlatNo(residentFlatNo);
            service.createNewQuery(query, loggedInUser);
            
            response.put("status", "success");
            response.put("message", "Your query has been successfully posted to Raised Queries.");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("status", "error");
            response.put("message", "Failed to submit query: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to submit query due to a server error.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * Endpoint for committee members to answer/edit a query resolution.
     */
    @PostMapping("/answer")
    public ResponseEntity<Map<String, String>> answerQuery(@RequestBody AnswerRequest request, HttpSession session) {
        Map<String, String> response = new HashMap<>();
        
        // Access control: Committee member check
        boolean isCommittee = Boolean.TRUE.equals(session.getAttribute("is_committee"));
        if (!isCommittee) {
            response.put("status", "error");
            response.put("message", "Access denied. Only committee members can answer queries.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        // Validation
        if (request.getQueryId() == null || request.getCommitteeAnswer() == null || request.getCommitteeAnswer().trim().isEmpty()) {
            response.put("status", "error");
            response.put("message", "Query ID and resolution answer are required.");
            return ResponseEntity.badRequest().body(response);
        }
        
        // 🛑 CRITICAL FIX: Explicitly cast and handle nulls for session attributes 🛑
        String fullName = (String) session.getAttribute("fullName");
        String flat = (String) session.getAttribute("flat");
        
        // Default to empty string if null, simplifying the check logic below
        fullName = (fullName == null) ? "" : fullName;
        flat = (flat == null) ? "" : flat;
        
        // Debugging log (optional, but good for verification)
        System.out.println("--- RESOLUTION ATTEMPT ---");
        System.out.println("Retrieved fullName: [" + fullName + "]");
        System.out.println("Retrieved flat: [" + flat + "]");
        System.out.println("--------------------------");
        
        String answeredByName;
        if (!fullName.isEmpty() && !flat.isEmpty()) {
             // Example: "Anjali Deshmukh (B308)"
            answeredByName = fullName + " (" + flat + ")";
        } else {
            // Fallback only if both are missing (which should not happen after login fix)
            answeredByName = "Committee Admin";
        }
        // -------------------------------------------------------------------------


        try {
            // Find solved query by original ID
            Optional<SolvedQuery> solvedQuery = service.findSolvedQueryByOriginalId(request.getQueryId());
            boolean isAlreadySolved = solvedQuery.isPresent();
            
            Optional<?> result;
            String successMessage;

            if (isAlreadySolved) {
                // If found in solved table, EDIT/UPDATE the solution
                result = service.editSolvedQuery(request, answeredByName);
                successMessage = "Query solution has been successfully updated.";
            } else {
                // If not found in solved table, SOLVE/MOVE from raised to solved
                result = service.answerQuery(request, answeredByName);
                successMessage = "Query has been successfully answered and marked as resolved.";
            }

            if (result.isPresent()) {
                response.put("status", "success");
                response.put("message", successMessage);
                return ResponseEntity.ok(response);
            } else {
                response.put("status", "error");
                response.put("message", "Failed to find or update the query. Check if ID is correct.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            System.err.println("CRITICAL SERVER ERROR processing answer for ID " + request.getQueryId() + ": " + e.getMessage());
            e.printStackTrace(); 
            
            response.put("status", "error");
            response.put("message", "Server error while processing answer. Check server console for full error trace."); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}