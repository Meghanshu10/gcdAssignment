package com.gcdesigns.assignment.exception;

public class InvalidLoanException extends RuntimeException{

    private final String id;

    public InvalidLoanException(final String id){
        super("The payment date of Loan with Id:"+id+" is greater than the due date.");
        this.id=id;
    }

    public String getId() {
        return id;
    }
}
