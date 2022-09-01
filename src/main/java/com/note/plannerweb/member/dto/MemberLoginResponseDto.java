package com.note.plannerweb.member.dto;

import com.note.plannerweb.member.domain.Member;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class MemberLoginResponseDto {
    private final Long memberId;
    private final List<String> roles;

    public MemberLoginResponseDto(Member member){
        this.memberId=member.getId();
        this.roles=member.getRoles();
    }
}
