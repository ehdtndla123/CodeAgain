package com.note.plannerweb.study.controller;

import com.note.plannerweb.config.model.response.ListResult;
import com.note.plannerweb.config.model.response.SingleResult;
import com.note.plannerweb.config.model.service.ResponseService;
import com.note.plannerweb.config.security.JwtProvider;
import com.note.plannerweb.study.domain.StudyProblem;
import com.note.plannerweb.study.dto.*;
import com.note.plannerweb.study.service.StudyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Api(tags = {"4. Study"})
@RequestMapping(value = "/api/study", produces = "application/json; charset=UTF8")
public class StudyController {

    private final StudyService studyService;
    private final JwtProvider jwtProvider;
    private final ResponseService responseService;


    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 AccessToken",
                    required = true, dataType = "String", paramType = "header"
            )
    })
    @ApiOperation(value = "스터디 생성", notes = "스터디를 생성합니다.")
    @PostMapping
    public SingleResult<StudyResponse> createStudy(HttpServletRequest request,@RequestBody StudyCreate studyCreate) {
        return responseService.getSingleResult(studyService.createStudy(jwtProvider.resolveToken(request), studyCreate));
    }





    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 AccessToken",
                    required = true, dataType = "String", paramType = "header"
            )
    })
    @ApiOperation(value = "자신의 스터디 조회", notes = "자신의 스터디를 조회합니다.")
    @GetMapping
    public SingleResult<StudyResponse> getStudyInfo(HttpServletRequest request) {
        return responseService.getSingleResult(studyService.getStudyInfoByToken(jwtProvider.resolveToken(request)));
    }







    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 AccessToken",
                    required = true, dataType = "String", paramType = "header"
            )
    })
    @ApiOperation(value = "스터디에 플랜 추가", notes = "스터디에 플랜을 추가합니다.")
    @PostMapping(value = "/plans/{studyId}")
    public SingleResult<StudyPlanResponse> createStudyPlan(HttpServletRequest request, @PathVariable Long studyId, @RequestBody StudyPlanCreate studyPlanCreate) {
        return responseService.getSingleResult(studyService.createStudyPlan(jwtProvider.resolveToken(request), studyId, studyPlanCreate));
    }




    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 AccessToken",
                    required = true, dataType = "String", paramType = "header"
            )
    })
    @ApiOperation(value = "스터디 조회 By studyId", notes = "스터디를 조회합니다.")
    @GetMapping(value = "/{studyId}")
    public SingleResult<StudyResponse> getStudyInfoById(@PathVariable Long studyId) {
        return responseService.getSingleResult(studyService.getStudyInfoById(studyId));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 AccessToken",
                    required = true, dataType = "String", paramType = "header"
            )
    })
    @ApiOperation(value = "스터디 조회 By SNO", notes = "스터디를 조회합니다.")
    @GetMapping(value = "/sno/{studySNO}")
    public SingleResult<StudyResponse> getStudyInfoBySno(@PathVariable Long studySNO) {
        return responseService.getSingleResult(studyService.getStudyInfoBySNO(studySNO));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 AccessToken",
                    required = true, dataType = "String", paramType = "header"
            )
    })
    @ApiOperation(value = "스터디 참가 By SNO", notes = "스터디에 참가합니다.")
    @PostMapping(value = "/join/{sno}")
    public SingleResult<StudyMemberResponse> joinBySno(HttpServletRequest request,@PathVariable Long sno) {
        return responseService.getSingleResult(studyService.joinStudy(jwtProvider.resolveToken(request), sno));
    }



    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 AccessToken",
                    required = true, dataType = "String", paramType = "header"
            )
    })
    @ApiOperation(value = "해당 스터디의 멤버들 조회", notes = "해당 스터디의 멤버들을 조회합니다.")
    @GetMapping(value = "/members/{studyId}")
    public ListResult<StudyMemberResponse> getStudyMemberList(@PathVariable Long studyId){
        return responseService.getListResult(studyService.getStudyMemberList(studyId));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 AccessToken",
                    required = true, dataType = "String", paramType = "header"
            )
    })
    @ApiOperation(value = "해당 스터디의 계획들 조회", notes = "해당 스터디의 계획들을 조회합니다.")
    @GetMapping(value = "/plans/{studyId}")
    public ListResult<StudyPlanResponse> getStudyPlanList(@PathVariable Long studyId){
        return responseService.getListResult(studyService.getStudyPlanList(studyId));
    }



//    @ApiImplicitParams({
//            @ApiImplicitParam(
//                    name = "X-AUTH-TOKEN",
//                    value = "로그인 성공 후 AccessToken",
//                    required = true, dataType = "String", paramType = "header"
//            )
//    })
//    @ApiOperation(value = "스터디 삭제", notes = "스터디를 삭제합니다.")
//    @DeleteMapping(value = "/{studyId}")
//    public SingleResult<StudyResponse> deleteStudy(HttpServletRequest request, @PathVariable Long studyId) {
//        return responseService.getSingleResult(studyService.deleteStudy(jwtProvider.resolveToken(request), studyId));
//    }

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 AccessToken",
                    required = true, dataType = "String", paramType = "header"
            )
    })
    @ApiOperation(value = "스터디그룹 나가기", notes = "스터디 그룹에서 나갑니다 By token")
    @DeleteMapping(value = "/{studyId}")
    public SingleResult<StudyMemberResponse> deleteStudy(HttpServletRequest request, @PathVariable Long studyId) {
        return responseService.getSingleResult(studyService.deleteStudyMember(jwtProvider.resolveToken(request), studyId));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 AccessToken",
                    required = true, dataType = "String", paramType = "header"
            )
    })
    @ApiOperation(value = "모든 스터디 리스트 보기", notes = "모든 리스트 출력하기")
    @GetMapping(value = "/list")
    public ListResult<StudyResponse> getStudyList() {
        return responseService.getListResult(studyService.getStudyList());
    }

//    @ApiImplicitParams({
//            @ApiImplicitParam(
//                    name = "X-AUTH-TOKEN",
//                    value = "로그인 성공 후 AccessToken",
//                    required = true, dataType = "String", paramType = "header"
//            )
//    })
//    @ApiOperation(value = "스터디 플랜에 문제 추가", notes = "스터디 플랜에 문제 추가하기")
//    @PostMapping("/problems")
//    public SingleResult<StudyProblemResponse> createStudyProblem(HttpServletRequest request, @RequestBody StudyProblemCreate studyProblemCreate) {
//        return responseService.getSingleResult(studyService.createStudyProblem(jwtProvider.resolveToken(request), studyProblemCreate));
//    }

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 AccessToken",
                    required = true, dataType = "String", paramType = "header"
            )
    })
    @ApiOperation(value = "스터디 여부", notes = "스터디에 속해있는지 없는지 T / F")
    @PostMapping("/check")
    public SingleResult<Boolean> checkStudy(HttpServletRequest request) {
        return responseService.getSingleResult(studyService.checkGroup(jwtProvider.resolveToken(request)));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 AccessToken",
                    required = true, dataType = "String", paramType = "header"
            )
    })
    @ApiOperation(value = "문제 답변을 수정합니다.", notes = "문제에 대한 답변을 수정합니다.")
    @PutMapping("/problems/{studyProblemId}")
    public SingleResult<StudyProblemResponse> updateStudyProblem(HttpServletRequest request, @PathVariable Long studyProblemId, @RequestBody StudyProblemUpdate studyProblemUpdate) {
        return responseService.getSingleResult(studyService.updateStudyProblem(jwtProvider.resolveToken(request), studyProblemId, studyProblemUpdate));
    }

}
