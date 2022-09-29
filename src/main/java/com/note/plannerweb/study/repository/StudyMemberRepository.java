package com.note.plannerweb.study.repository;

import com.note.plannerweb.study.domain.StudyMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyMemberRepository extends JpaRepository<StudyMember,Long> {
}
