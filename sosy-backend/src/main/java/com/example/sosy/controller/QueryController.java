package com.example.sosy.controller;

import com.example.sosy.dto.AnswerRequest;
import com.example.sosy.model.ResidentQuery;
import com.example.sosy.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/quickresolve")
public class QueryController {

    @Autowired
    private QueryService service;

    /**
     * Endpoint to fetch all RAISED queries.
     */
    @GetMapping("/raised")
    public List<ResidentQuery> getRaisedQueries() {
        return service.getRaisedQueries();
    }
    
    /**
     * Endpoint to fetch all SOLVED queries.
     */
    @GetMapping("/solved")
    public List<ResidentQuery> getSolvedQueries() {
        return service.getSolvedQueries();
    }

    /**
     * Endpoint for residents to submit a new query (Write Query).
     */
    @PostMapping("/write")
    public ResponseEntity<Map<String, String>> writeQuery(@RequestBody ResidentQuery query, HttpSession session) {
        Map<String, String> response = new HashMap<>();

        // Basic validation
        if (query.getTitle() == null || query.getTitle().trim().isEmpty() || query.getDescription() == null || query.getDescription().trim().isEmpty()) {
            response.put("status", "error");
            response.put("message", "Title and description are required.");
            return ResponseEntity.badRequest().body(response);
        }
        
        // **IMPORTANT:** In a real app, you would retrieve and set these from the user's session:
        // query.setResidentName(session.getAttribute("userName").toString());
        // query.setResidentFlatNo(session.getAttribute("userFlatNo").toString());
        
        service.createNewQuery(query);
        response.put("status", "success");
        response.put("message", "Your query has been successfully posted to Raised Queries.");
        return ResponseEntity.ok(response);
    }
    
    /**
     * Endpoint for committee members to answer a query.
     */
    @PostMapping("/answer")
    public ResponseEntity<Map<String, String>> answerQuery(@RequestBody AnswerRequest request, HttpSession session) {
        Map<String, String> response = new HashMap<>();
        
        // 1. Authorization Check (Assuming "is_committee" is set upon login)
        // **NOTE:** Using Boolean.TRUE.equals() is best practice for checking session attributes.
        boolean isCommittee = Boolean.TRUE.equals(session.getAttribute("is_committee"));
        if (!isCommittee) {
            response.put("status", "error");
            response.put("message", "Access denied. Only committee members can answer queries.");
            return ResponseEntity.status(403).body(response);
        }

        // 2. Validation
        if (request.getQueryId() == null || request.getCommitteeAnswer() == null || request.getCommitteeAnswer().trim().isEmpty()) {
            response.put("status", "error");
            response.put("message", "Query ID and answer are required.");
            return ResponseEntity.badRequest().body(response);
        }
        
        // 3. Set the committee member's name from the session/context
        String answeredByName;
        
        // **PRODUCTION-READY LOGIC** (Requires login/session setup)
        // answeredByName = session.getAttribute("committeeUserName").toString(); 

        // **TEMPORARY LOGIC for Testing** (Uses the name from your committee image)
        Object committeeUserObj = session.getAttribute("committeeUserName");
        if (committeeUserObj != null) {
            answeredByName = committeeUserObj.toString();
        } else {
            // Using a default test name if session data is not available
            answeredByName = "Committee Admin (Test User)"; 
        }

        // 4. Delegation to Service (Passing the AnswerRequest and the name)
        // MODIFIED: Passing answeredByName as the second parameter
        Optional<ResidentQuery> updatedQuery = service.answerQuery(request, answeredByName);

        if (updatedQuery.isPresent()) {
            response.put("status", "success");
            response.put("message", "Query has been successfully answered and moved to Solved Queries.");
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "Failed to find or update the query (already solved or ID invalid).");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
