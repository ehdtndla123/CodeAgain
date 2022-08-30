package com.note.plannerweb.plan.service;

import com.note.plannerweb.plan.domain.Plan;
import com.note.plannerweb.plan.domain.Planner;
import com.note.plannerweb.plan.dto.PlanCreateRequest;
import com.note.plannerweb.plan.repository.PlanRepository;
import com.note.plannerweb.plan.repository.PlannerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlannerService {

    private final PlannerRepository plannerRepository;
    private final PlanRepository planRepository;
    private final ModelMapper modelMapper;

    public Long createPlan(Planner planner,PlanCreateRequest planCreateRequest){
        Plan plan=Plan.builder()
                .planner(planner)
                .category(planCreateRequest.getCategory())
                .completion(planCreateRequest.getCompletion())
                .content(planCreateRequest.getContent())
                .build();
        return this.planRepository.save(plan).getId();
    }

    public Long createPlanner(){
        return this.plannerRepository.save(new Planner(new ArrayList<>())).getId();
    }

    public List<Planner> getPlannerList(){
        return this.plannerRepository.findAll();
    }

    public Planner findPlannerById(Long id){
        return this.plannerRepository.findById(id).get();
    }

}
