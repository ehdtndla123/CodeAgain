package com.note.plannerweb.member.signlogin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.note.plannerweb.member.domain.Member;
import com.note.plannerweb.member.dto.MemberLoginRequestDto;
import com.note.plannerweb.member.repository.MemberRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;


import java.awt.*;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SignControllerTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String name="ehdtndla123";
    private String email="ehdtndla123@naver.com";
    private String password="1234";


    @BeforeEach
    public void setUp(){
       memberRepository.save(Member.builder()
                       .name(name)
                       .password(passwordEncoder.encode(password))
                       .email(email)
                       .roles(Collections.singletonList("ROLE_USER"))
               .build());
   }

   @Test
    public void 로그인_성공() throws  Exception{
        String object =objectMapper.writeValueAsString(MemberLoginRequestDto.builder()
                .email(email)
                .password(password)
                .build());

        //given
       ResultActions actions= mockMvc.perform(post("/api/login")
               .content(object)
               .accept(MediaType.APPLICATION_JSON)
               .contentType(MediaType.APPLICATION_JSON));

       //then
//       actions.andDo(print())
//               .andExpect(status().isOk())
//               .andExpect(jsonPath("$.success").value(true))
//               .andExpect(jsonPath("$.code").value(0))
//               .andExpect(jsonPath("$.msg").exists());
   }




}
