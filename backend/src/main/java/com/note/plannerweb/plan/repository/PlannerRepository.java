package com.note.plannerweb.plan.repository;

import com.note.plannerweb.plan.domain.Planner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlannerRepository extends JpaRepository<Planner,Long> {
}
