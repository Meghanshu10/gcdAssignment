package com.gcdesigns.assignment.controller;

import com.gcdesigns.assignment.entity.Loan;
import com.gcdesigns.assignment.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

    @Autowired
    LoanService loanService;

    @PostMapping("/addLoan")
    public ResponseEntity<String> addLoan(@RequestBody Loan loan){
        String output = "";
        output = loanService.validateLoan(loan);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }


    @GetMapping("/get-aggregation")
    public ResponseEntity<Object> getAggregationData(){
        return new ResponseEntity<>(loanService.getAggregation(), HttpStatus.OK);
    }
}
