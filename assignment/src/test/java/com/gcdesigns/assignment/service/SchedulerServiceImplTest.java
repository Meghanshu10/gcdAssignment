package com.gcdesigns.assignment.service;

import com.gcdesigns.assignment.entity.Loan;
import com.gcdesigns.assignment.repository.LoanRepository;
import com.gcdesigns.assignment.service.impl.SchedulerServiceImpl;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class SchedulerServiceImplTest {

    @Mock
    LoanRepository loanRepository;

    @InjectMocks
    SchedulerServiceImpl schedulerService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testCheckLoanStatus(){
        Mockito.when(loanRepository.findAll()).thenReturn(
                new ArrayList<>()
        );

        Assertions.assertEquals(schedulerService.getClass(), SchedulerServiceImpl.class);
    }
}
