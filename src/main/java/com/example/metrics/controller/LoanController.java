package com.example.metrics.controller;

import com.example.metrics.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/loans")
public class LoanController {
    // In-memory store for demo
    private final Map<String, Loan> loanStore = new HashMap<>();

    @GetMapping
    public ResponseEntity<LoanListResponse> getLoans(
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int limit) {
        // Filter and paginate (demo logic)
        List<Loan> loans = new ArrayList<>(loanStore.values());
        if (status != null) {
            loans.removeIf(l -> !status.equals(l.getStatus()));
        }
        int totalCount = loans.size();
        int fromIndex = Math.max(0, (page - 1) * limit);
        int toIndex = Math.min(fromIndex + limit, totalCount);
        List<Loan> paged = loans.subList(fromIndex, toIndex);
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("currentPage", page);
        metadata.put("totalPages", (int) Math.ceil((double) totalCount / limit));
        metadata.put("totalCount", totalCount);
        LoanListResponse response = new LoanListResponse();
        response.setData(paged);
        response.setMetadata(metadata);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<LoanResponse> createLoan(@RequestBody LoanApplicationRequest req) {
        Loan loan = new Loan();
        loan.setId(UUID.randomUUID().toString());
        loan.setClientId(req.getClientId());
        loan.setAmount(req.getAmount());
        loan.setCurrency(req.getCurrency());
        loan.setTermInMonths(req.getTermInMonths());
        loan.setInterestRate(req.getInterestRate());
        loan.setStatus("pending");
        loanStore.put(loan.getId(), loan);
        LoanResponse response = new LoanResponse();
        response.setData(loan);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<?> getLoanById(@PathVariable String loanId) {
        Loan loan = loanStore.get(loanId);
        if (loan == null) {
            ErrorResponse error = new ErrorResponse();
            error.setCode("not_found");
            error.setMessage("Loan with ID '" + loanId + "' not found.");
            return ResponseEntity.status(404).body(error);
        }
        LoanResponse response = new LoanResponse();
        response.setData(loan);
        return ResponseEntity.ok(response);
    }
}
