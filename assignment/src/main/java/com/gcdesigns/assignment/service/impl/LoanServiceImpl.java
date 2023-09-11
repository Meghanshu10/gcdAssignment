package com.gcdesigns.assignment.service.impl;

import com.gcdesigns.assignment.dto.AggregatedResult;
import com.gcdesigns.assignment.entity.Loan;
import com.gcdesigns.assignment.exception.InvalidLoanException;
import com.gcdesigns.assignment.repository.LoanRepository;
import com.gcdesigns.assignment.service.IMongoService;
import com.gcdesigns.assignment.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    private static Logger log = LoggerFactory.getLogger(LoanServiceImpl.class);

    @Autowired
    private IMongoService mongoService;

    @Autowired
    LoanRepository loanRepository;

    @Override public String validateLoan(Loan loan){
        var str = "";
        if (loan.getPaymentDate().isAfter(loan.getDueDate())){
            throw new InvalidLoanException(loan.getLoanId());
        }
        else {
            str = "Validation Successful";
            saveData(loan);
        }

        return str;

    }
    @Override public void saveData(Loan loan){
        loanRepository.save(loan);
    }

    @Override
    public List<AggregatedResult> getAggregation() {
        log.info("Invoking getAggregation... ");
        return mongoService.getAggregation();
    }
}
