package com.note.plannerweb.controller;

import com.note.plannerweb.member.domain.Member;
import com.note.plannerweb.member.repository.MemberRepository;
import com.note.plannerweb.member.service.MemberService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@WithMockUser(username = "mockUser")
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private static int id;

    @BeforeEach
    public void setUp(){
        Member saveMember=memberRepository.save(Member.builder()
                        .name("ehdtndla123")
                        .password(passwordEncoder.encode("password"))
                        .email("ehdtndla123@naver.com")
                        .roles(Collections.singletonList("ROLE_USER"))
                .build());

        id=Math.toIntExact(saveMember.getId());
    }

    @Test
    @DisplayName("")
    public void memberInfo_email() throws Exception{

        // given


        // when


        // then
        ResultActions actions= mockMvc.perform(get("/api/member"));

    }






}
