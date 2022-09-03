package com.note.plannerweb.member.service;

import com.note.plannerweb.config.security.JwtProvider;
import com.note.plannerweb.member.domain.Member;
import com.note.plannerweb.member.dto.*;
import com.note.plannerweb.member.repository.MemberRepository;
import com.note.plannerweb.token.domain.RefreshToken;
import com.note.plannerweb.token.dto.TokenRequestDto;
import com.note.plannerweb.token.dto.tokenDto;
import com.note.plannerweb.token.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.stream.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;
    private final ModelMapper modelMapper;

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public MemberResponseDto findById(Long id){
        Member member=this.memberRepository.findById(id).orElseThrow(()->new IllegalArgumentException());
        return new MemberResponseDto(member);
    }

    @Transactional
    public List<MemberResponseDto> getMemberList(){
       return this.memberRepository.findAll().stream()
                .map(o->modelMapper.map(o,MemberResponseDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteById(Long id){
        this.memberRepository.deleteById(id);
    }

    @Transactional
    public tokenDto login(MemberLoginRequestDto memberLoginRequestDto){
//        Member member=this.memberRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
//        if(!passwordEncoder.matches(password,member.getPassword()))
//            throw new IllegalArgumentException();
//        return new MemberLoginResponseDto(member);
        // 회원 정보 존재하는지 확인
        System.out.println("This point ");
        Member member=this.memberRepository.findByEmail(memberLoginRequestDto.getEmail())
                .orElseThrow(IllegalArgumentException::new); //CEmailLoginFailedException
        System.out.println("This point ");
        //회원 패스워드 일치 여부 확인
        if(!passwordEncoder.matches(memberLoginRequestDto.getPassword(),member.getPassword()))
            throw new IllegalArgumentException(); //CEmailLoginFailedException

        //AccessToken, RefreshToken 발급
        tokenDto tokendto=jwtProvider.createTokenDto(member.getId(),member.getRoles());
        System.out.println("This point ");
        //RefreshToken 저장
        RefreshToken refreshToken= RefreshToken.builder()
                .key(member.getId())
                .token(tokendto.getRefreshToken())
                .build();
        System.out.println(refreshToken);
        System.out.println("This point end");
        this.refreshTokenRepository.save(refreshToken);
        System.out.println("createTokenDto test");
        return tokendto;
    }

    @Transactional
    public Long signup(MemberSignupRequestDto memberSignupRequestDto){
//        if(this.memberRepository.findByEmail(memberSignupRequestDto.getEmail()).orElse(null)==null){
//            return this.memberRepository.save(memberSignupRequestDto.toEntity()).getId();
//        }
//        else throw new IllegalArgumentException();
        if(this.memberRepository.findByEmail(memberSignupRequestDto.getEmail()).isPresent())
            throw new IllegalArgumentException(); // CEmailSignupFailedException()
        return this.memberRepository.save(memberSignupRequestDto.toEntity()).getId();
    }

    @Transactional
    public tokenDto reissue(TokenRequestDto tokenRequestDto){
        //만료된 refresh token 에러
        if(!jwtProvider.validateToken(tokenRequestDto.getAccessToken())){
            throw new IllegalArgumentException(); //CrefreshTokenException()
        }

        //AccessToken 에서 Membername (pk) 가져오기
        String accessToken = tokenRequestDto.getAccessToken();
        Authentication authentication= jwtProvider.getAuthentication(accessToken);

        // member pk로 멤버 검색 / repo 에 저장된 Refresh token 이 없음
        Member member=this.memberRepository.findById(Long.parseLong(authentication.getName()))
                .orElseThrow(IllegalArgumentException::new); //CUserNotFoundException
        RefreshToken refreshToken=this.refreshTokenRepository.findByKey(member.getId())
                .orElseThrow(IllegalArgumentException::new); //CUserNotFoundException

        //리프레시 토큰 불일치 에러
        if(!refreshToken.getToken().equals(tokenRequestDto.getRefreshToken()))
            throw new IllegalArgumentException(); //CRefreshTokenExceiption

        // AccessToken, RefreshToken 토큰 재발급, 리프레쉬 토큰 저장
        tokenDto newCreatedToken=this.jwtProvider.createTokenDto(member.getId(),member.getRoles());
        RefreshToken updateRefreshToken=refreshToken.updateToken(newCreatedToken.getRefreshToken());
        this.refreshTokenRepository.save(updateRefreshToken);

        return newCreatedToken;

    }
}
