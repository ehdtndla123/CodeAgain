package com.note.plannerweb.member.controller;

import com.note.plannerweb.config.model.response.ListResult;
import com.note.plannerweb.config.model.response.SingleResult;
import com.note.plannerweb.config.model.service.ResponseService;
import com.note.plannerweb.config.security.JwtProvider;
import com.note.plannerweb.member.domain.Member;
import com.note.plannerweb.member.dto.*;
import com.note.plannerweb.member.repository.MemberRepository;
import com.note.plannerweb.member.service.MemberService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Api(tags = {"1. Member"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/api")
public class MemberController {

    private final MemberService memberService;
    private final JwtProvider jwtProvider;
    private final ResponseService responseService;

    /**
     * 회원정보 목록조회
     */

    @ApiOperation(value = "회원정보 목록조회",notes = "회원종보 목록을 조회합니다.")
    @GetMapping("/members")
    public ListResult<MemberResponseDto> getMemberList(){
        return this.responseService.getListResult(this.memberService.getMemberList());
    }


    /**
     * 회원정보조회
     */

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 AccessToken",
                    required = true, dataType = "String",paramType = "header"
            )
    })
    @ApiOperation(value = "회원정보조회",notes = "회원정보를 조회합니다.")
    @GetMapping("/members/token")
    public SingleResult<MemberResponseDto> getMember(HttpServletRequest request){
        return this.responseService.getSingleResult(this.memberService.withdraw(jwtProvider.resolveToken(request)));
    }


    /**
     * 회원탈퇴
     */
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 AccessToken",
                    required = true, dataType = "String",paramType = "header"
            )
    })
    @ApiOperation(value = "회원탈퇴",notes = "회원탈퇴를 합니다.")
    @DeleteMapping("/members/token")
    public SingleResult<MemberResponseDto> withdraw(HttpServletRequest request){
        return responseService.getSingleResult(memberService.findByToken(jwtProvider.resolveToken(request)));
    }


}
