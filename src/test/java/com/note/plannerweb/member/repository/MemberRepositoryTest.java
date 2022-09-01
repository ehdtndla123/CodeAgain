//package com.note.plannerweb.member.repository;
//
//import com.note.plannerweb.member.domain.Member;
//import com.note.plannerweb.note.domain.Note;
//import com.sun.jdi.event.ExceptionEvent;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//import org.assertj.core.api.*;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//
//import static org.apache.tomcat.util.IntrospectionUtils.clear;
//import static org.assertj.core.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@SpringBootTest
//@Transactional
//class MemberRepositoryTest {
//    @Autowired
//    MemberRepository memberRepository;
//    @Autowired
//    EntityManager em;
//
//    @AfterEach
//    private void after(){
//        em.clear();
//    }
//
//    @Test
//    public void 회원저장_성공() throws Exception{
//        //given
//        Member member= Member.builder()
//                .email("ehdtndla123@naver.com")
//                .name("장동수")
//                .password("1234567890")
//                .build();
//
//        //when
//        Member saveMember=memberRepository.save(member);
//
//        //then
//        Member findMember=memberRepository.findById(saveMember.getId()).orElseThrow(()->new RuntimeException("저장된 회원이 없습니다."));
//
//        assertThat(findMember).isSameAs(saveMember);
//        assertThat(findMember).isSameAs(member);
//
//    }
//
//    @Test
//    public void 오류_회원가입시_이메일_없음() throws Exception{
//        //given
//        Member member = Member.builder()
//                .name("장동숴이")
//                .password("123456")
//                .build();
//        //when then
//        assertThrows(Exception.class,()-> memberRepository.save(member));
//    }
//
//    @Test void 이그잼플(){
//        String a="1";
//    }
//
//    @Test
//    public void 오류_회원가입시_이름이_없음() throws  Exception{
//        //given
//        Member member = Member.builder()
//                .email("ehdtndla12@naver.com")
//                .password("123456")
//                .build();
//        //when,then
//        assertThrows(Exception.class,()-> memberRepository.save(member));
//    }
//
//    @Test
//    public void 오류_회원가입시_비밀번호_없음() throws  Exception{
//        //given
//        Member member = Member.builder()
//                .email("ehdtndla12@naver.com")
//                .name("장동수")
//                .build();
//        //when,then
//        assertThrows(Exception.class,()-> memberRepository.save(member));
//    }
//
//    @Test
//    public void 오류_회원가입시_중복된_이메일_있음() throws Exception{
//        //given
//        Member member1 = Member.builder()
//                .name("장동수")
//                .email("123")
//                .password("123456")
//                .build();
//
//        Member member2 = Member.builder()
//                .name("장동수")
//                .email("123")
//                .password("12sda3456")
//                .build();
//
//        memberRepository.save(member1);
//
//        //when,then
//        assertThrows(Exception.class,()->memberRepository.save(member2));
//    }
//
//    @Test
//    public void 성공_회원수정() throws Exception {
//        //given
//        Member member1 = Member.builder()
//                .name("장동수")
//                .email("ehdtndla12@naver.com")
//                .password("123456")
//                .build();
//
//        memberRepository.save(member1);
//        clear();
//
//        String updateName = "updateName";
//
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//        //when
//        Member findMember = memberRepository.findById(member1.getId()).orElseThrow(() -> new Exception());
//        findMember.updateName(updateName);
//        em.flush();
//
//        //then
//        Member findUpdateMember = memberRepository.findById(findMember.getId()).orElseThrow(() -> new Exception());
//
//        assertThat(findUpdateMember).isSameAs(findMember);
//        assertThat(findUpdateMember.getName()).isEqualTo(updateName);
//        assertThat(findUpdateMember.getName()).isEqualTo(member1.getName());
//    }
//    @Test
//    public void 성공_회원삭제() throws Exception {
//        //given
//        Member member1 = Member.builder()
//                .name("장동수")
//                .email("ehdtndla12@naver.com")
//                .password("123456")
//                .build();
//
//        memberRepository.save(member1);
//        clear();
//
//        //when
//        memberRepository.delete(member1);
//        clear();
//
//        //then
//        assertThrows(Exception.class, () -> memberRepository.findById(member1.getId()).orElseThrow(() -> new Exception()));
//    }
//    @Test
//    public void findByEmail() throws Exception{
//        //given
//        String userEmail="ehdtndla123@naver.com";
//        Member member1 = Member.builder()
//                .name("장동수")
//                .email("ehdtndla123@naver.com")
//                .password("123456")
//                .build();
//
//        memberRepository.save(member1);
//        clear();
//
//
//        //when,then
//        assertThat(memberRepository.findByEmail(userEmail).get().getName()).isEqualTo(member1.getName());
//        assertThat(memberRepository.findByEmail(userEmail).get().getPassword()).isEqualTo(member1.getPassword());
//        assertThrows(Exception.class,()->memberRepository.findByEmail(userEmail+"123").orElseThrow(()->new Exception()));
//
//
//
//    }
//
//}