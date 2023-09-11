package com.gcdesigns.assignment.service.impl;

import com.gcdesigns.assignment.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SchedulerServiceImpl {

    @Autowired
    private LoanRepository loanRepository;

    @Scheduled(fixedRateString = "86400000")
    public void checkLoanStatus() {
        loanRepository.findAll().forEach(loan -> {
            if(loan.getDueDate().isBefore(LocalDate.now())){
                System.out.println("Due date for you loan id - "+loan.getLoanId()+" have passed on - "+loan.getDueDate());
            }
        });
    }
}
