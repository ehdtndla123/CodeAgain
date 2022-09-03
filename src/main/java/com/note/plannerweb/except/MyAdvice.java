package com.note.plannerweb.except;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class MyAdvice {

    @ExceptionHandler(Exception.class)
    public String custom(){
        return "hello Excption";
    }

    @ExceptionHandler(CAuthenticationEntryPointException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected Exception authenticationEntryPointException(HttpServletRequest request,CAuthenticationEntryPointException e){
        return new IllegalArgumentException();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected Exception accessDeniedException(HttpServletRequest request,AccessDeniedException e){
        return e;
    }

}
