package com.example.library.controller;

import com.example.library.domain.entity.Loan;
import com.example.library.exception.EntityNotFoundException;
import com.example.library.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @GetMapping("/loans")
    public ResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> loans = loanService.getAllLoans();
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @GetMapping("/loans/{loanId}")
    public ResponseEntity<Object> getLoanById(@PathVariable String loanId) {
        try {
            Loan loan = loanService.getLoanById(loanId);
            return new ResponseEntity<>(loan, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(createErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(createErrorResponse("There was an error when retrieving the loan!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/loans")
    public ResponseEntity<Object> createLoan(@RequestBody Loan loan) {
        try {
            String result = loanService.createLoan(loan);
            if ("SUCCESS".equals(result)) {
                return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(createErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/loans/{loanId}")
    public ResponseEntity<Object> updateLoan(@RequestBody Loan loan, @PathVariable String loanId) {
        try {
            String result = loanService.updateLoan(loanId, loan);
            if ("SUCCESS".equals(result)) {
                return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(createErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/loans/{loanId}")
    public ResponseEntity<Object> deleteLoan(@PathVariable String loanId) {
        try {
            String result = loanService.deleteLoan(loanId);
            if ("SUCCESS".equals(result)) {
                return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(createErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    private Map<String, Object> createErrorResponse(String errorMessage) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("Error message", errorMessage);
        return errorResponse;
    }
}
