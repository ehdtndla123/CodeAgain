package com.note.plannerweb.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class MemberUpdatePasswordDto {
    private String password;

    @Builder
    public MemberUpdatePasswordDto(String password) {
        this.password=password;
    }
}
