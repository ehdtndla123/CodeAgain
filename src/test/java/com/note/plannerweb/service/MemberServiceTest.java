package com.note.plannerweb.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@Transactional
@SpringBootTest
class MemberServiceTest {
    @AfterEach
    public void afterEach(){
        // repository.clearsotore();
    }
    @Test
    @DisplayName("회원 생성 테스트")
    void createMember(){
        //given

        //when

        //then
    }
}
