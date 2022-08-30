package com.note.plannerweb.plan.controller;

import com.note.plannerweb.plan.domain.Planner;
import com.note.plannerweb.plan.dto.PlanCreateRequest;
import com.note.plannerweb.plan.service.PlannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = {"3. Planner"})
@RequestMapping(value = "/api/Planner")
public class PlannerController {

    private final PlannerService plannerService;

    @ApiOperation(value = "모든 플래너 조회",notes = "모든 플래너 목록을 조회합니다.")
    @GetMapping
    public List<Planner> getPlannerList(){
        return this.plannerService.getPlannerList();
    }
    @ApiOperation(value = "플래너 생성",notes = "플래너를 생성합니다.")
    @PostMapping
    public Long createPlanner(){
        return this.plannerService.createPlanner();
    }

    @ApiOperation(value="플랜 생성",notes = "플랜을 생성합니다.")
    @PostMapping("/{plannerId}")
    public Long createPlan(@PathVariable Long plannerId, @RequestBody PlanCreateRequest planCreateRequest){
        Planner planner=this.plannerService.findPlannerById(plannerId);
        return this.plannerService.createPlan(planner,planCreateRequest);
    }

}
