package com.note.plannerweb.plan.repository;

import com.note.plannerweb.plan.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan,Long> {
}
