package com.note.plannerweb.member.repository;

import com.note.plannerweb.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
