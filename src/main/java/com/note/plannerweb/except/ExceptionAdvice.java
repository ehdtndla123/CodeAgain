package com.note.plannerweb.except;

import com.note.plannerweb.config.model.response.CommonResult;
import com.note.plannerweb.config.model.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult defaultException(HttpServletRequest request,Exception e){
        return this.responseService.getFailResult();
    }

    // 유저를 찾지 못했을  때 발생시키는 예외
    @ExceptionHandler(MemberNotFoundCException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected  CommonResult memberNotFoundException(HttpServletRequest request,MemberNotFoundCException e){
        return this.responseService.getFailResult();
    }

    
}
