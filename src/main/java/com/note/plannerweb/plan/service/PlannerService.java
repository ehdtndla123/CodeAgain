package com.note.plannerweb.plan.service;

import com.note.plannerweb.plan.repository.PlannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlannerService {

    private final PlannerRepository plannerRepository;


}
