package com.note.plannerweb.service;

import com.note.plannerweb.note.repository.NoteRepository;
import com.note.plannerweb.note.service.NoteService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@Transactional
@SpringBootTest
public class NoteServiceTest {

    // MemberService memberService=new memberService();
    // MemberRepository memberRepository=new MemberRepository;
    NoteRepository noteRepository;
    NoteService noteService;
    @BeforeEach
    public void beforeEach(){
        //noteRepository=new NoteRepository();
        //noteService= new NoteService(noteRepository);
    }

    @AfterEach
    public void afterEach(){
        // repository.clearsotore();
    }

    @Test
    @DisplayName("오답노트 생성 테스트")
    void createMember(){
        //given

        //when

        //then
    }
}
