package com.note.plannerweb.study.repository;

import com.note.plannerweb.study.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study,Long> {
}
