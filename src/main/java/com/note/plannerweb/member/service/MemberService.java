package com.note.plannerweb.member.service;

import com.note.plannerweb.member.domain.Member;
import com.note.plannerweb.member.dto.MemberLoginResponseDto;
import com.note.plannerweb.member.dto.MemberRequestDto;
import com.note.plannerweb.member.dto.MemberResponseDto;
import com.note.plannerweb.member.dto.MemberSignupRequestDto;
import com.note.plannerweb.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.el.stream.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

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

    @Transactional(readOnly=true)
    public MemberLoginResponseDto login(String email, String password){
        Member member=this.memberRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
        if(!passwordEncoder.matches(password,member.getPassword()))
            throw new IllegalArgumentException();
        return new MemberLoginResponseDto(member);
    }

    @Transactional
    public Long signup(MemberSignupRequestDto memberSignupRequestDto){
        if(this.memberRepository.findByEmail(memberSignupRequestDto.getEmail()).orElse(null)==null){
            return this.memberRepository.save(memberSignupRequestDto.toEntity()).getId();
        }
        else throw new IllegalArgumentException();
    }
}
