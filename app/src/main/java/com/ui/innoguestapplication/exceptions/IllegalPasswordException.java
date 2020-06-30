package com.ui.innoguestapplication.exceptions;

import com.ui.innoguestapplication.backend.APIRequests;

public class IllegalPasswordException extends Exception {

    public IllegalPasswordException(APIRequests.LoginState message){
        super(message.toString());
    }
    public IllegalPasswordException(){
        super("");
    }

}
