package com.note.plannerweb.member.controller;

import com.note.plannerweb.member.domain.Member;
import com.note.plannerweb.member.dto.*;
import com.note.plannerweb.member.repository.MemberRepository;
import com.note.plannerweb.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Api(tags = {"1. Member"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/api")
public class MemberController {

    private final MemberService memberService;

//    /**
//     * 회원정보수정
//     */
//    @ApiOperation(value = "회원 정보 수정",notes = "회원정보를 수정합니다.")
//    @PutMapping("/member")
//    @ResponseStatus(HttpStatus.OK)
//    public void updateBasicInfo(@Valid @RequestBody MemberUpdateDto memberUpdateDto) throws Exception {
//        memberService.update(memberUpdateDto);
//    }
//
//    /**
//     * 비밀번호 수정
//     */
//    @ApiOperation(value = "비밀번호 수정",notes = "비밀번호를 수정합니다.")
//    @PutMapping("/member/password")
//    @ResponseStatus(HttpStatus.OK)
//    public void updatePassword(@Valid @RequestBody UpdatePasswordDto updatePasswordDto) throws Exception {
//        memberService.updatePassword(updatePasswordDto.checkPassword(),updatePasswordDto.toBePassword());
//    }
//
//
//    /**
//     * 회원탈퇴
//     */
//    @ApiOperation(value = "회원탈퇴",notes = "회원탈퇴를 합니다.")
//    @DeleteMapping("/member")
//    @ResponseStatus(HttpStatus.OK)
//    public void withdraw(@RequestBody MemberWithdrawDto memberWithdrawDto) throws Exception {
//        memberService.withdraw(memberWithdrawDto.checkPassword());
//    }
//
//
    /**
     * 회원정보조회
     */
    @ApiOperation(value = "회원정보조회",notes = "회원정보를 조회합니다.")
    @GetMapping("/members/{id}")
    public MemberResponseDto getMember(@Valid @PathVariable("id") Long id){
        return this.memberService.findById(id);
    }

    @ApiOperation(value = "회원정보 목록조회",notes = "회원종보 목록을 조회합니다.")
    @GetMapping("/members")
    public List<MemberResponseDto> getMemberList(){
        return this.memberService.getMemberList();
    }

    @ApiOperation(value="회원 삭제",notes = "회원을 삭제합니다.")
    @DeleteMapping("/members/{id}")
    public MemberResponseDto deleteMember(@PathVariable Long id){
        MemberResponseDto responseDto=this.memberService.findById(id);
        this.memberService.deleteById(id);
        return responseDto;
    }

//    /**
//     * 내정보조회
//     */
//    @ApiOperation(value = "내정보 조회",notes = "내정보를 조회 합니다.")
//    @GetMapping("/member")
//    public ResponseEntity getMyInfo(HttpServletResponse response) throws Exception {
//
//        MemberInfoDto info = memberService.getMyInfo();
//        return new ResponseEntity(info, HttpStatus.OK);
//    }
}
