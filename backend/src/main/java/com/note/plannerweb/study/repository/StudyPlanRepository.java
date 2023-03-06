package com.note.plannerweb.study.repository;

import com.note.plannerweb.study.domain.StudyPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyPlanRepository extends JpaRepository<StudyPlan,Long> {
}
