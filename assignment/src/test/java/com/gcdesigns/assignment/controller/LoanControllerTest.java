package com.gcdesigns.assignment.controller;

import com.gcdesigns.assignment.dto.AggregatedResult;
import com.gcdesigns.assignment.entity.Loan;
import com.gcdesigns.assignment.exception.InvalidLoanException;
import com.gcdesigns.assignment.repository.LoanRepository;
import com.gcdesigns.assignment.service.LoanService;
import com.gcdesigns.assignment.service.impl.LoanServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class LoanControllerTest {
    @Mock
    LoanService mockLoanService = new LoanServiceImpl();

    @Mock
    LoanRepository loanRepository;

    @InjectMocks
    LoanController loanController = new LoanController();


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddLoan(){
        String str = "Validation Successful";
        Loan success_loan = new Loan();
        success_loan.setLoanId("L1");
        success_loan.setCustomerId("C1");
        success_loan.setLenderId("LEN1");
        success_loan.setAmount(10000);
        success_loan.setRemainingAmount(10000);
        success_loan.setPaymentDate(LocalDate.parse("2023-06-05"));
        success_loan.setInterestPerDay(1);
        success_loan.setDueDate(LocalDate.parse("2023-07-05"));
        success_loan.setPenaltyPerDay(0.01);

        Mockito.when(mockLoanService.validateLoan(success_loan)).thenReturn(
                str
        );
        Assertions.assertEquals(loanController.addLoan(success_loan).getBody(),str);
    }

    @Test(expected = InvalidLoanException.class)
    public void testAddLoanException(){
        Loan failure_loan = new Loan();
        failure_loan.setLoanId("L1");
        failure_loan.setCustomerId("C1");
        failure_loan.setLenderId("LEN1");
        failure_loan.setAmount(10000);
        failure_loan.setRemainingAmount(10000);
        failure_loan.setPaymentDate(LocalDate.parse("2023-08-05"));
        failure_loan.setInterestPerDay(1);
        failure_loan.setDueDate(LocalDate.parse("2023-07-05"));
        failure_loan.setPenaltyPerDay(0.01);

        Mockito.when(mockLoanService.validateLoan(failure_loan)).thenThrow(
                new InvalidLoanException(failure_loan.getLoanId())
                );
        Assertions.assertEquals(loanController.addLoan(failure_loan),new InvalidLoanException(failure_loan.getLoanId()));

    }

    @Test
    public void testGetAggregatedData(){
        List<AggregatedResult> list = new ArrayList<AggregatedResult>();
        list.add(new AggregatedResult(
                10000,10,0.1
        ));
        Mockito.when(mockLoanService.getAggregation()).thenReturn(
          list
        );
        Assertions.assertEquals(loanController.getAggregationData().getBody(),list);
    }
}
