package com.note.plannerweb.member.dto;

import com.note.plannerweb.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberRequestDto {
    private String email;
    private String name;

    @Builder
    public MemberRequestDto(String email,String name){
        this.email=email;
        this.name=name;
    }

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .name(name)
                .build();

    }
}
