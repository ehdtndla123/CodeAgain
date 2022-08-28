package com.note.plannerweb.plan.service;

import com.note.plannerweb.plan.repository.PlannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlannerRepository plannerRepository;


}
