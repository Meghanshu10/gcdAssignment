package com.gcdesigns.assignment.exception;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InvalidLoanExceptionTest {

    @InjectMocks
    InvalidLoanException invalidLoanException;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetId(){
        String id = null;
        Assertions.assertEquals(invalidLoanException.getId(),id);
    }
}
