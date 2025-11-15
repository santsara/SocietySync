package com.example.sosy.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sosy.dto.AnswerRequest;
import com.example.sosy.dto.QueryResponse;
import com.example.sosy.model.RaisedQuery;
import com.example.sosy.model.SolvedQuery;
import com.example.sosy.model.User;
import com.example.sosy.repository.RaisedQueryRepository;
import com.example.sosy.repository.SolvedQueryRepository;
import com.example.sosy.repository.UserRepository;

@Service
public class QueryService {

    @Autowired
    private RaisedQueryRepository raisedQueryRepo;

    @Autowired
    public SolvedQueryRepository solvedQueryRepo; 

    @Autowired
    private UserRepository userRepo;

    // --- Core Fix Methods ---

    public Optional<SolvedQuery> findSolvedQueryByOriginalId(Long queryId) {
        Object result = solvedQueryRepo.findByOriginalQueryId(queryId);
        
        if (result instanceof Optional) {
             @SuppressWarnings("unchecked")
             Optional<SolvedQuery> solvedQueryOptional = (Optional<SolvedQuery>) result;
             return solvedQueryOptional;
        } else if (result instanceof SolvedQuery) {
            return Optional.of((SolvedQuery) result);
        }
        return Optional.empty();
    }
    
    @Transactional
    public Optional<SolvedQuery> editSolvedQuery(AnswerRequest request, String answeredByName) {
        try {
            Object repositoryResult = solvedQueryRepo.findByOriginalQueryId(request.getQueryId());
            
            Optional<SolvedQuery> solvedQueryOptional;
            if (repositoryResult instanceof Optional) {
                 @SuppressWarnings("unchecked")
                 Optional<SolvedQuery> castedResult = (Optional<SolvedQuery>) repositoryResult;
                 solvedQueryOptional = castedResult;
            } else if (repositoryResult instanceof SolvedQuery) {
                solvedQueryOptional = Optional.of((SolvedQuery) repositoryResult);
            } else {
                solvedQueryOptional = Optional.empty();
            }

            if (!solvedQueryOptional.isPresent()) {
                throw new RuntimeException("Solved query not found for ID: " + request.getQueryId());
            }

            SolvedQuery solved = solvedQueryOptional.get();
            solved.setCommitteeAnswer(request.getCommitteeAnswer());
            solved.setAnsweredBy(answeredByName);
            solved.setDateSolved(LocalDateTime.now());
            return Optional.of(solvedQueryRepo.save(solved));
            
        } catch (Exception e) {
            throw new RuntimeException("Error editing solved query: " + e.getMessage());
        }
    }

    @Transactional
    public Optional<SolvedQuery> answerQuery(AnswerRequest request, String answeredByName) {
        Optional<RaisedQuery> raisedQueryOptional = raisedQueryRepo.findById(request.getQueryId());
        if (raisedQueryOptional.isPresent()) {
            RaisedQuery original = raisedQueryOptional.get();
            
            // 1. Check if already solved
            if (original.getIsSolved() != null && original.getIsSolved()) {
                return this.editSolvedQuery(request, answeredByName); 
            }
            
            // 2. Create SolvedQuery and save
            SolvedQuery solved = new SolvedQuery();
            solved.setOriginalQueryId(original.getId());
            solved.setTitle(original.getTitle());
            solved.setDescription(original.getDescription());
            solved.setCommitteeAnswer(request.getCommitteeAnswer());
            solved.setAnsweredBy(answeredByName);
            solved.setDateSolved(LocalDateTime.now());
            solved.setResidentFlatNo(original.getResidentFlatNo());
            solved.setResidentName(original.getResidentName());
            
            try {
                // 3. Save the solved query first
                SolvedQuery savedSolved = solvedQueryRepo.save(solved);
                
                // 4. Update original RaisedQuery status
                original.setIsSolved(true);
                raisedQueryRepo.save(original);
                
                return Optional.of(savedSolved);
            } catch (Exception e) {
                // If there's an error, rollback both saves
                throw new RuntimeException("Error saving query resolution: " + e.getMessage());
            }
        }
        return Optional.empty();
    }

    // --- Utility and Mapping Methods ---

    public User findUserByFlatNo(String flatNo) {
        return userRepo.findByFlat(flatNo) 
                .orElseThrow(() -> new RuntimeException("User not found for flat: " + flatNo));
    }

    @Transactional
    public RaisedQuery createNewQuery(RaisedQuery query, User resident) {
        query.setResidentFlatNo(resident.getFlat());
        query.setResidentName(resident.getFullname());
        query.setDateRaised(LocalDateTime.now());
        return raisedQueryRepo.save(query);
    }

    public List<QueryResponse> getRaisedQueries() {
        return raisedQueryRepo.findByIsSolvedFalseOrderByDateRaisedDesc().stream()
                .map(this::mapRaisedToQueryResponse)
                .collect(Collectors.toList());
    }

    public List<QueryResponse> getSolvedQueries() {
        List<SolvedQuery> solved = solvedQueryRepo.findAllByOrderByDateSolvedDesc();
        return solved.stream()
                .map(solvedQuery -> {
                    // 💡 CRITICAL CHANGE: Always fetch the original query (which is NOT deleted)
                    Optional<RaisedQuery> originalQueryOpt = raisedQueryRepo.findById(solvedQuery.getOriginalQueryId());
                    return mapSolvedToQueryResponse(solvedQuery, originalQueryOpt.orElse(null));
                })
                .collect(Collectors.toList());
    }

    private QueryResponse mapRaisedToQueryResponse(RaisedQuery query) {
        QueryResponse dto = new QueryResponse();
        dto.setId(query.getId());
        dto.setTitle(query.getTitle());
        dto.setDescription(query.getDescription());
        dto.setStatus("Raised");
        dto.setResidentFlatNo(query.getResidentFlatNo());
        dto.setResidentName(query.getResidentName());
        dto.setDateRaised(query.getDateRaised());
        dto.setDateSolved(null);
        dto.setCommitteeAnswer(null);
        dto.setAnsweredBy(null);
        return dto;
    }

    private QueryResponse mapSolvedToQueryResponse(SolvedQuery solved, RaisedQuery original) {
        QueryResponse dto = new QueryResponse();
        dto.setId(solved.getOriginalQueryId());
        
        // 💡 CRITICAL CHANGE: REFERENCE TITLE AND DESCRIPTION FROM THE ORIGINAL QUERY
        dto.setTitle(original != null ? original.getTitle() : "[Original Query Missing]");
        dto.setDescription(original != null ? original.getDescription() : "[Original Query Missing]");
        
        dto.setStatus("Solved");
        dto.setResidentFlatNo(solved.getResidentFlatNo());
        dto.setResidentName(solved.getResidentName());
        dto.setDateRaised(original != null ? original.getDateRaised() : null);
        dto.setCommitteeAnswer(solved.getCommitteeAnswer());
        dto.setAnsweredBy(solved.getAnsweredBy());
        dto.setDateSolved(solved.getDateSolved());
        return dto;
    }
}