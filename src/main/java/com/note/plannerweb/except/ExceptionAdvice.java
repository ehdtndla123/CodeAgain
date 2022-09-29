package com.note.plannerweb.except;

import com.note.plannerweb.config.model.response.CommonResult;
import com.note.plannerweb.config.model.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
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
    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
        return this.responseService.getFailResult("Default Exception");
    }

    // 유저를 찾지 못했을  때 발생시키는 예외
    @ExceptionHandler(MemberNotFoundCException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult memberNotFoundException(HttpServletRequest request, MemberNotFoundCException e) {
        return this.responseService.getFailResult("이 유저는 존재하지 않습니다.");
    }

    @ExceptionHandler(EmailLoginFailedCException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult emailLoginFailedException(HttpServletRequest request, EmailLoginFailedCException e) {
        return this.responseService.getFailResult("가입하지 않은 아이디거나, 잘못된 비밀번호 입니다.");
    }
//
//    @ExceptionHandler(Exception.class)
//    public String custom(){
//        return "Security Exception";
//    }

    @ExceptionHandler(CAuthenticationEntryPointException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult authenticationEntryPointException(HttpServletRequest request, CAuthenticationEntryPointException e) {
        return this.responseService.getFailResult("해당 리소스에 접근하기 권한이 없습니다.");
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult accessDeniedException(HttpServletRequest request, AccessDeniedException e) {
        return this.responseService.getFailResult("Permission not accessible to this resource");
    }

    @ExceptionHandler(NoteNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult noteNotFoundException(HttpServletRequest request, NoteNotFoundException e) {
        return responseService.getFailResult("해당 노트가 존재하지 않습니다.");
    }

    @ExceptionHandler(PlannerNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult plannerNotFoundException(HttpServletRequest request, PlannerNotFoundException e) {
        return responseService.getFailResult("해당 플래너가 존재하지 않습니다.");
    }

    @ExceptionHandler(PlanNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult planNotFoundException(HttpServletRequest request, PlanNotFoundException e) {
        return responseService.getFailResult("해당 플랜이 존재하지 않습니다.");
    }

    @ExceptionHandler(SignUpFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult signUpFailedException(HttpServletRequest request, SignUpFailedException e) {
        return responseService.getFailResult("이미 존재하는 이메일입니다.");
    }

    @ExceptionHandler(StudyNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult studyNotFoundException(HttpServletRequest request, StudyNotFoundException e) {
        return responseService.getFailResult("해당 스터디가 존재하지 않습니다");
    }
}
