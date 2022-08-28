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
// 순수한 단위 테스트가 좋다. 단위테스트 = 순수 자바 형식으로 테스트
// 통합 테스트
@Transactional //테스트케이스에 transactional 추가할시 DB 에 트랜잭션 시작하고 테스트 완료 후에 rollback 한다. 다음 테스트에 영향 x
@SpringBootTest //스프링 컨테이너와 테스트를 함께 실행한다.
public class NoteServiceTest {

    // MemberService memberService=new memberService();
    // MemberRepository memberRepository=new MemberRepository;
    @Autowired NoteRepository noteRepository;
    @Autowired NoteService noteService;
//    @BeforeEach
//    public void beforeEach(){
//        noteRepository=new NoteRepository();
//        noteService= new NoteService(noteRepository);
//    }
//
//    @AfterEach
//    public void afterEach(){
//         repository.clearsotore();
//    }

    @Test
    @DisplayName("오답노트 생성 테스트")
    void createMember(){
        //given

        //when

        //then
    }
}
