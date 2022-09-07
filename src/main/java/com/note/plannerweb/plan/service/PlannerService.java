package com.note.plannerweb.plan.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.note.plannerweb.config.security.JwtProvider;
import com.note.plannerweb.except.CAuthenticationEntryPointException;
import com.note.plannerweb.except.MemberNotFoundCException;
import com.note.plannerweb.except.PlannerNotFoundException;
import com.note.plannerweb.member.domain.Member;
import com.note.plannerweb.member.repository.MemberRepository;
import com.note.plannerweb.plan.domain.Plan;
import com.note.plannerweb.plan.domain.Planner;
import com.note.plannerweb.plan.dto.PlanCreateRequest;
import com.note.plannerweb.plan.dto.PlannerCreateRequest;
import com.note.plannerweb.plan.dto.PlannerResponse;
import com.note.plannerweb.plan.repository.PlanRepository;
import com.note.plannerweb.plan.repository.PlannerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlannerService {

    private final PlannerRepository plannerRepository;
    private final PlanRepository planRepository;
    private final ModelMapper modelMapper;

    private final JwtProvider jwtProvider;

    private final MemberRepository memberRepository;

    private final ObjectMapper objectMapper;


    public List<PlannerResponse> getPlannerList(String token){
        return getMemberByToken(token).getPlans().stream()
                .map(o->modelMapper.map(o,PlannerResponse.class))
                .collect(Collectors.toList());
    }

    public Long createPlanner(PlannerCreateRequest plannerCreateRequest,String token){
        Member member=getMemberByToken(token);
        System.out.println("?????");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<Plan> planList=plannerCreateRequest.getPlans().stream()
                .map(o->o.toEntity())
                .collect(Collectors.toList());

        planRepository.saveAll(planList);


        Planner planner=Planner.builder()
                .plans(planList)
                .targetDate(plannerCreateRequest.getTargetDate())
                .member(member)
                .build();

        for(Plan p : planList){
            p.setPlanner(planner);
        }


        member.getPlans().add(planner);

        plannerRepository.save(planner);


        if(!jwtProvider.validateToken(token)){
            throw new CAuthenticationEntryPointException();
        }
        return planner.getId();
    }


    public Long createPlan(PlanCreateRequest planCreateRequest,Long plannerId,String token){
        if(!jwtProvider.validateToken(token))
            throw new CAuthenticationEntryPointException();

        Planner planner=plannerRepository.findById(plannerId).orElseThrow(PlannerNotFoundException::new);



        Plan plan=Plan.builder()
                .planner(planner)
                .category(planCreateRequest.getCategory())
                .completion(planCreateRequest.getCompletion())
                .content(planCreateRequest.getContent())
                .build();

        planner.getPlans().add(plan);

        return this.planRepository.save(plan).getId();
    }




    public Planner findPlannerById(Long id){
        return this.plannerRepository.findById(id).get();
    }


    public Member getMemberByToken(String token){
        String userPk = this.jwtProvider.getUserPk(token);
        Long userLongPk=Long.parseLong(userPk);
        return this.memberRepository.findById(userLongPk).orElseThrow(MemberNotFoundCException::new);
    }
}
