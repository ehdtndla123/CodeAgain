package com.note.plannerweb.except;

public class EmailLoginFailedCException extends RuntimeException{
    public EmailLoginFailedCException(){
        super();
    }

    public EmailLoginFailedCException(String msg){
        super(msg);
    }

    public EmailLoginFailedCException(String msg,Throwable cause){
        super(msg,cause);
    }
}
