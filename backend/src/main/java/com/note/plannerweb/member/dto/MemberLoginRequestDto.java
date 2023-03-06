package com.note.plannerweb.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@NoArgsConstructor
public class MemberLoginRequestDto {
    private String email;
    private String password;


    @Builder
    public MemberLoginRequestDto(String email,String password){
        this.email=email;
        this.password=password;
    }


}
