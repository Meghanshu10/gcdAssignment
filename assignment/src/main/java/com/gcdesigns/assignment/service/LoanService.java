package com.gcdesigns.assignment.service;

import com.gcdesigns.assignment.dto.AggregatedResult;
import com.gcdesigns.assignment.entity.Loan;

import java.util.List;

public interface LoanService {
    public String validateLoan(Loan loan);
    public void saveData(Loan loan);

    public List<AggregatedResult> getAggregation();
}
