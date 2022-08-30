package com.note.plannerweb.member.controller;

import com.note.plannerweb.member.domain.Member;
import com.note.plannerweb.member.repository.MemberRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"1. Member"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/api")
public class MemberController {

    private  final MemberRepository memberRepository;

    @ApiOperation(value = "모든 회원 조회", notes = "모든 회원 목록을 조회합니다.")
    @GetMapping("/members")
    public List<Member> findAllMember(){
        return memberRepository.findAll();
    }

    @ApiOperation(value = "회원 등록",notes = "회원을 등록합니다.")
    @PostMapping("/member")
    public Member save(@ApiParam(value = "회원 이메일",required = true)@RequestParam String email,
                       @ApiParam(value = "회원 이름",required = true)@RequestParam String name){
        Member member = Member.builder()
                .email(email)
                .name(name)
                .build();

        return memberRepository.save(member);
    }

    @ApiOperation(value ="회원 검색 (이름)",notes = "이름으로 회원을 검색합니다.")
    @GetMapping("/findMemberByName/{name}")
    public List<Member> findMemberByName(@ApiParam(value = "회원 이름", required = true)@PathVariable String name){
        return memberRepository.findByName(name);
    }

    @ApiOperation(value= "회원 검색 (이메일)",notes = "이메일로 회원을 검색합니다.")
    @GetMapping("/findMemberByEmail/{email}")
    public List<Member> findMemberbyEmail(@ApiParam(value = "회원 이메일",required = true)@PathVariable String email){
        return memberRepository.findByEmail(email);
    }

}
