package com.note.plannerweb.member.repository;

import com.note.plannerweb.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {
        List<Member> findByName(String name);
        List<Member> findByEmail(String email);
}
