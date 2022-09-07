package com.note.plannerweb.plan.controller;

import com.note.plannerweb.config.model.response.ListResult;
import com.note.plannerweb.config.model.response.SingleResult;
import com.note.plannerweb.config.model.service.ResponseService;
import com.note.plannerweb.config.security.JwtProvider;
import com.note.plannerweb.plan.domain.Planner;
import com.note.plannerweb.plan.dto.PlanCreateRequest;
import com.note.plannerweb.plan.dto.PlannerCreateRequest;
import com.note.plannerweb.plan.dto.PlannerResponse;
import com.note.plannerweb.plan.service.PlannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = {"3. Planner"})
@RequestMapping(value = "/api/planners")
public class PlannerController {

    private final PlannerService plannerService;

    private final JwtProvider jwtProvider;

    private final ResponseService responseService;

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 AccessToken",
                    required = true, dataType = "String",paramType = "header"
            )
    })
    @ApiOperation(value = "자신의 플래너 조회",notes = "자신의 플래너 목록을 조회합니다.")
    @GetMapping
    public ListResult<PlannerResponse> getPlannerList(HttpServletRequest request){
        return responseService.getListResult(plannerService.getPlannerList(jwtProvider.resolveToken(request)));
    }
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 AccessToken",
                    required = true, dataType = "String",paramType = "header"
            )
    })
    @ApiOperation(value = "플래너 생성",notes = "플래너를 생성합니다.")
    @PostMapping
    public SingleResult<Long> createPlanner(HttpServletRequest request, @RequestBody PlannerCreateRequest plannerCreateRequest){
        return responseService.getSingleResult(plannerService.createPlanner(plannerCreateRequest,jwtProvider.resolveToken(request)));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 AccessToken",
                    required = true, dataType = "String",paramType = "header"
            )
    })
    @ApiOperation(value="플랜 생성",notes = "플랜을 생성합니다.")
    @PostMapping("/{plannerId}")
    public SingleResult<Long> createPlan(HttpServletRequest request,@PathVariable Long plannerId, @RequestBody PlanCreateRequest planCreateRequest){
        return responseService.getSingleResult(plannerService.createPlan(planCreateRequest,plannerId,jwtProvider.resolveToken(request)));
    }




}
