package com.ui.innoguestapplication.exceptions;

public class IllegalPasswordException extends Exception {

    public IllegalPasswordException(String message){
        super(message);
    }
    public IllegalPasswordException(){
        super("");
    }

}
