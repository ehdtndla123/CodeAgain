package com.note.plannerweb.member.controller;

import com.note.plannerweb.config.security.JwtProvider;
import com.note.plannerweb.member.dto.MemberLoginRequestDto;
import com.note.plannerweb.member.dto.MemberLoginResponseDto;
import com.note.plannerweb.member.dto.MemberSignupRequestDto;
import com.note.plannerweb.member.service.MemberService;
import com.note.plannerweb.token.dto.TokenRequestDto;
import com.note.plannerweb.token.dto.tokenDto;
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
    @PostMapping("/login")
    public tokenDto login(@ApiParam(value = "로그인 요청 DTO",required = true)
                          @RequestBody MemberLoginRequestDto memberLoginRequestDto) {
        System.out.println("This point ");
//        MemberLoginResponseDto memberLoginResponseDto=this.memberService.login(memberLoginRequestDto);
//
//        return jwtProvider.createTokenDto(String.valueOf(memberLoginResponseDto.getMemberId()),memberLoginResponseDto.getRoles());
//    }
        System.out.println("This point ");
        tokenDto tokendto=this.memberService.login(memberLoginRequestDto);
        System.out.println("login complete");
        return tokendto;
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

    @ApiOperation(value="엑세스, 리프레시 토큰 재발급"
    ,notes = "엑세스 토큰 만료시 회원 검증 후 리프레쉬 토큰을 검증해서 엑세스 토큰과 리프레시 토큰을 재발급합니다.")
    @PostMapping("/reissue")
    public tokenDto reissue(@ApiParam(value="토큰 재발급 요청 DTO",required = true)
                            @RequestBody TokenRequestDto tokenRequestDto){
        return this.memberService.reissue(tokenRequestDto);
    }
}
