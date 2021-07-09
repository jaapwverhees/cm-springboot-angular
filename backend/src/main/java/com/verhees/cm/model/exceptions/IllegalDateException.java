package com.verhees.cm.model.exceptions;

public class IllegalDateException extends RuntimeException{
    public IllegalDateException(){
        super("Date is Invalid");
    }
}
