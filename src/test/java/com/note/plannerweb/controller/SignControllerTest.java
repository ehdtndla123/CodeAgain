package com.note.plannerweb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.note.plannerweb.member.domain.Member;
import com.note.plannerweb.member.dto.MemberLoginRequestDto;
import com.note.plannerweb.member.dto.MemberSignupRequestDto;
import com.note.plannerweb.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//assert
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class SignControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @BeforeEach
    public void setUp(){
        this.memberRepository.save(Member.builder()
                        .name("ehdtndla123")
                        .email("ehdtndla123@naver.com")
                        .password(passwordEncoder.encode("123456"))
                        .roles(Collections.singletonList("ROLE_USER"))
                        .build());
        System.out.println("--------------------------------");
    }

    @Test
    @DisplayName("로그인 성공")
    void login_success() throws Exception{
        String object=objectMapper.writeValueAsString(MemberLoginRequestDto.builder()
                .email("ehdtndla123@naver.com")
                .password("123456")
                .build());
        // given

        ResultActions actions=mockMvc.perform(post("/api/login")
                .content(object) //응답 본문 내용 검증
                .accept(MediaType.APPLICATION_JSON) //
                .contentType(MediaType.APPLICATION_JSON));

        // when


        // then
        actions.andDo(print())
                .andExpect(status().isOk());
              //  .andExpect(jsonPath("$.success").value(true));
//                .andExpect(jsonPath("$.code").value(0))
//                .andExpect(jsonPath("$.msg").exists());

    }

    @Test
    @DisplayName("회원가입 성공")
    public void sign_success() throws Exception{

        // given
        String object=objectMapper.writeValueAsString(MemberSignupRequestDto.builder()
                .name("ehdtndla123123")
                .email("ehdtndla123@naver.com123")
                .password("123")
                .build());
        ResultActions actions=mockMvc.perform(post("/api/signup")
                .content(object)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        // when

        // then
        actions.andDo(print())
                .andExpect(status().isOk());
             //   .andExpect((result -> assertTrue(result.getResolvedException().getClass().isAssignableFrom(IllegalArgumentException.class))));

    }


    @Test
    @DisplayName("회원가입 실패")
    public void sign_fail() throws Exception{

        // given
        String object=objectMapper.writeValueAsString(MemberSignupRequestDto.builder()
                .name("ehdtndla123123")
                .email("ehdtndla123@naver.com")
                .password("123")
                .build());
        ResultActions actions=mockMvc.perform(post("/api/signup")
                .content(object)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        // when

        // then
        actions.andDo(print())
                .andExpect(status().isOk());
        //   .andExpect((result -> assertTrue(result.getResolvedException().getClass().isAssignableFrom(IllegalArgumentException.class))));

    }

    @Test
    @DisplayName("로그인 실패")
    void login_fail() throws Exception{
        String object=objectMapper.writeValueAsString(MemberLoginRequestDto.builder()
                .email("ehdtndla123@naver.com")
                .password("123456789")
                .build());
        // given

        ResultActions actions=mockMvc.perform(post("/api/login")
                .content(object) //응답 본문 내용 검증
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        // when


        // then
        actions.andDo(print())
                .andExpect(status().isOk());

    }

}
