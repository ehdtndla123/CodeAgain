package com.note.plannerweb.member.controller;

import com.note.plannerweb.config.security.JwtProvider;
import com.note.plannerweb.member.dto.MemberLoginResponseDto;
import com.note.plannerweb.member.dto.MemberSignupRequestDto;
import com.note.plannerweb.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = "0. SignUp_Login")
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/api")
public class SignController {

    private final MemberService memberService;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @ApiOperation(value ="로그인", notes = "이메일로 로그인을 합니다.")
    @GetMapping("/login")
    public String login(@ApiParam(value = "로그인 아이디 : 이메일",required = true) @RequestParam String email,
                        @ApiParam(value = "로그인 비밀번호",required = true) @RequestParam String password){
        MemberLoginResponseDto memberLoginResponseDto=this.memberService.login(email,password);

        return jwtProvider.createToken(String.valueOf(memberLoginResponseDto.getMemberId()),memberLoginResponseDto.getRoles());
    }

    @ApiOperation(value = "회원가입",notes = "이메일로 회원가입을 합니다.")
    @PostMapping("/signup")
    public Long signup(@ApiParam(value="회원가입 아이디: 이메일",required = true)@RequestParam String email,
                       @ApiParam(value="회원가입 아이디: 비밀번호",required = true)@RequestParam String password,
                       @ApiParam(value="회원가입 아이디: 이름",required = true)@RequestParam String name){
        MemberSignupRequestDto memberSignupRequestDto=MemberSignupRequestDto.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .build();

        return this.memberService.signup(memberSignupRequestDto);
    }
}
