package com.gcdesigns.assignment.service;

import com.gcdesigns.assignment.dto.AggregatedResult;
import com.gcdesigns.assignment.entity.Loan;
import com.gcdesigns.assignment.exception.InvalidLoanException;
import com.gcdesigns.assignment.repository.LoanRepository;
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
public class LoanServiceImplTest {

    @Mock
    LoanRepository loanRepository;

    @Mock
    IMongoService mongoService;

    @InjectMocks
    LoanServiceImpl loanService = new LoanServiceImpl();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = InvalidLoanException.class)
    public void testValidateloan(){
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
        Assertions.assertEquals(loanService.validateLoan(success_loan),str);

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
            Assertions.assertEquals(loanService.validateLoan(failure_loan),new InvalidLoanException(failure_loan.getLoanId()));

    }

    @Test
    public void testSaveData(){
        Mockito.when(loanRepository.save(Mockito.any(Loan.class))).thenReturn(
                new Loan()
        );
        Assertions.assertNotNull(loanRepository.findAll());
    }

    @Test
    public void testGetAggregation(){
        List<AggregatedResult> list = new ArrayList<AggregatedResult>();
        list.add(new AggregatedResult(
                10000,10,0.1
        ));
        Mockito.when(mongoService.getAggregation()).thenReturn(
                list
        );
        Assertions.assertEquals(loanService.getAggregation(),list);
    }
}
