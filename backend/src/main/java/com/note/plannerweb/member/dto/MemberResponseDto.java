package com.note.plannerweb.member.dto;

import com.note.plannerweb.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class MemberResponseDto {
    private Long memberId;
    private String email;
    private String name;

    public MemberResponseDto(Member member){
        this.memberId=member.getId();
        this.email=member.getEmail();
        this.name=member.getName();
    }
}
