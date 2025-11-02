package com.example.sosy.service;

import com.example.sosy.dto.AnswerRequest;
import com.example.sosy.model.ResidentQuery;
import com.example.sosy.repository.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class QueryService {

    @Autowired
    private QueryRepository repository;

    public List<ResidentQuery> getRaisedQueries() {
        return repository.findAllByStatusOrderByDateRaisedAsc("RAISED");
    }

    public List<ResidentQuery> getSolvedQueries() {
        return repository.findAllByStatusOrderByDateSolvedDesc("SOLVED"); 
    }

    /**
     * Creates a new query from a resident.
     */
    public ResidentQuery createNewQuery(ResidentQuery query) {
        query.setStatus("RAISED");
        query.setDateRaised(LocalDateTime.now());
        
        // Clear committee fields to ensure they are null/empty initially
        query.setCommitteeAnswer(null);
        query.setAnsweredBy(null);
        query.setDateSolved(null);
        return repository.save(query);
    }
    
    /**
     * Updates an existing RAISED query to SOLVED status.
     * The committee member's name is passed from the Controller.
     */
    // MODIFIED: Added String answeredByName parameter
    public Optional<ResidentQuery> answerQuery(AnswerRequest request, String answeredByName) {
        
        Optional<ResidentQuery> optionalQuery = repository.findById(request.getQueryId());

        if (optionalQuery.isPresent()) {
            ResidentQuery query = optionalQuery.get();
            
            // Check if it's already solved
            if ("SOLVED".equals(query.getStatus())) {
                 return Optional.empty(); // Indicate error/already solved
            }

            // Update fields to mark as solved
            query.setStatus("SOLVED");
            query.setCommitteeAnswer(request.getCommitteeAnswer());
            
            // MODIFIED: Use the name passed from the Controller, not the DTO
            query.setAnsweredBy(answeredByName);
            
            query.setDateSolved(LocalDateTime.now());
            
            return Optional.of(repository.save(query));
        }

        return Optional.empty();
    }
}