package com.note.plannerweb.plan.service;

import com.note.plannerweb.plan.domain.Plan;
import com.note.plannerweb.plan.domain.Planner;
import com.note.plannerweb.plan.dto.PlanCreateRequest;
import com.note.plannerweb.plan.dto.PlannerResponse;
import com.note.plannerweb.plan.repository.PlanRepository;
import com.note.plannerweb.plan.repository.PlannerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlannerService {

    private final PlannerRepository plannerRepository;
    private final PlanRepository planRepository;
    private final ModelMapper modelMapper;

    public Long createPlan(Long id,PlanCreateRequest planCreateRequest){
        Planner planner=this.plannerRepository.findById(id).orElseThrow(()->new IllegalArgumentException("플랜 추가 실패 :해당 플래너가 없습니다." +id)
                );
        Plan plan=Plan.builder()
                .planner(planner)
                .category(planCreateRequest.getCategory())
                .completion(planCreateRequest.getCompletion())
                .content(planCreateRequest.getContent())
                .build();
        return this.planRepository.save(plan).getId();
    }

    public Long createPlanner(){
        Planner planner=Planner.builder()
                .plans(new ArrayList<>())
                .build();
        return this.plannerRepository.save(planner).getId();
    }

    public List<PlannerResponse> getPlannerList(){
        return this.plannerRepository.findAll().stream()
                .map(o->this.modelMapper.map(o, PlannerResponse.class))
                .collect(Collectors.toList());
    }

    public Planner findPlannerById(Long id){
        return this.plannerRepository.findById(id).get();
    }

}
