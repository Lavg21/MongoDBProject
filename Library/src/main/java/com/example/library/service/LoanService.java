package com.example.library.service;

import com.example.library.domain.entity.Loan;
import com.example.library.exception.EntityNotFoundException;
import com.example.library.exception.InvalidFieldException;
import com.example.library.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    @Autowired
    private final LoanRepository loanRepository;

    public List<Loan> getAllLoans() {
        return loanRepository.getAllLoans();
    }

    public Loan getLoanById(String loanId) {
        Loan loan = loanRepository.getLoanById(loanId);
        if (loan == null) {
            throw new EntityNotFoundException("The loan was not found!");
        }
        return loan;
    }

    public String createLoan(Loan loan) {
        validateLoan(loan);

        loanRepository.createLoan(loan);
        return "SUCCESS";
    }

    public String updateLoan(String loanId, Loan updatedLoan) {
        validateLoan(updatedLoan);

        if (!loanRepository.isLoanExists(loanId)) {
            throw new EntityNotFoundException("The loan was not found!");
        }
        loanRepository.updateLoan(loanId, updatedLoan);
        return "SUCCESS";
    }

    public String deleteLoan(String loanId) {
        if (!loanRepository.isLoanExists(loanId)) {
            throw new EntityNotFoundException("The loan was not found!");
        }
        loanRepository.deleteLoan(loanId);
        return "SUCCESS";
    }

    private void validateLoan(Loan loan) {
        if (StringUtils.isEmpty(loan.getBookId()) || StringUtils.isEmpty(loan.getReaderId())
                || StringUtils.isEmpty(loan.getLoanDate()) || StringUtils.isEmpty(loan.getDueDate())
                || StringUtils.isEmpty(loan.getReturnDate())) {
            throw new InvalidFieldException("Loan name cannot be empty!");
        }
    }
}
